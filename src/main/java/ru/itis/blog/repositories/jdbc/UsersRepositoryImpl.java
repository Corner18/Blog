package ru.itis.blog.repositories.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.blog.models.Role;
import ru.itis.blog.models.State;
import ru.itis.blog.models.User;


import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_CONFIRMCODE =
            "select * from itis_user where confirm_code = ? ;";

    //language=SQL
    private static final String SQL_SELECT_BY_ID =
            "select * from itis_user where id= ? ;";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL =
            "select * from itis_user where email = ? ;";

    //language=SQL
    private static final String SQL_INSERT =
            "insert into itis_user(email, name, password, state, confirm_code, role, created_at, avatar, phone_number) values (?,?,?,?,?,?,?,?,?)";

    //language=SQL
    private static final String SQL_UPDATE =
            "update itis_user SET email = ?, name = ?, password = ?, state = ?, confirm_code = ?, " +
                    "role = ?, created_at = ?, avatar = ?, phone_number = ? where id = ?";

    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    private RowMapper<User> userRowMapper = (row, i) ->

            User.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .email(row.getString("email"))
                    .password(row.getString("password"))
                    .state(State.valueOf(row.getString("state")))
                    .confirmCode(row.getString("confirm_code"))
                    .role(Role.valueOf(row.getString("role")))
                    .avatar(row.getString("avatar"))
                    .createdAt(row.getTimestamp("created_at"))
                    .phoneNumber(row.getString("phone_number"))
                    .build();


    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByConfirmCode(String token) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_CONFIRMCODE, new Object[]{token}, userRowMapper);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                    ps.setString(1, user.getEmail());
                    ps.setString(2, user.getName());
                    ps.setString(3, user.getPassword());
                    ps.setString(4, user.getState().toString());
                    ps.setString(5, user.getConfirmCode());
                    ps.setString(6, user.getRole().toString());
                    ps.setTimestamp(7, user.getCreatedAt());
                    ps.setString(8, user.getAvatar());
                    ps.setString(9,user.getPhoneNumber());
                    return ps;
                }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(SQL_UPDATE,
                user.getEmail(),
                user.getName(),
                user.getPassword(),
                user.getState().toString(),
                user.getConfirmCode(),
                user.getRole().toString(),
                user.getCreatedAt(),
                user.getAvatar(),
                user.getPhoneNumber(),
                user.getId());

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<User> find(Long id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, userRowMapper);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
