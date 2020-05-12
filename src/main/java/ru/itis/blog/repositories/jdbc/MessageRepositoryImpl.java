package ru.itis.blog.repositories.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.blog.models.Message;


import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;


@Component
public class MessageRepositoryImpl implements MessageRepository {

    private JdbcTemplate jdbcTemplate;

    public MessageRepositoryImpl(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    private RowMapper<Message> messageRowMapper = (row, i) ->

            Message.builder()
                    .id(row.getLong("id"))
                    .receiver_email(row.getString("receiver_email"))
                    .sender_email(row.getString("sender_email"))
                    .text(row.getString("text"))
                    .page_id(row.getString("page_id"))
                    .build();

    //language=SQL
    private static final String SQL_SELECT_BY_RECEIVER =
            "select * from simple_message where receiver_email = ? ;";

    //language=SQL
    private static final String SQL_SELECT_BY_SENDER =
            "select * from simple_message where sender_email = ? ;";

    //language=SQL
    private static final String SQL_SELECT_BY_SENDER_AND_RECEIVER =
            "select * from simple_message where sender_email = ? and receiver_email = ? ;";

    //language=SQL
    private static final String SQL_INSERT =
            "insert into simple_message(receiver_email, sender_email, text, page_id) values (?,?,?,?)";

    //language=SQL
    private static final String SQL_UPDATE =
            "update simple_message SET receiver_email = ?, sender_email = ?, text = ?, page_id = ? where id = ? ";

    //language=SQL
    private static final String SQL_SELECT_BY_ID =
            "select * from simple_message where id= ? ;";

    //language=SQL
    private static final String SQL_DISTINCT =
            "select distinct on (page_id) * from simple_message ";


    @Override
    public List<Message> getAllByReceiver_Email(String email) {
        return jdbcTemplate.query(SQL_SELECT_BY_RECEIVER, new Object[]{email}, messageRowMapper);
    }

    @Override
    public List<Message> getAllBySender_Email(String email) {
        return jdbcTemplate.query(SQL_SELECT_BY_SENDER, new Object[]{email}, messageRowMapper);
    }

    @Override
    public List<Message> getAllBySender_EmailAndReceiver_Email(String senderEmail, String receiverEmail) {
        return jdbcTemplate.query(SQL_SELECT_BY_SENDER_AND_RECEIVER, new Object[]{senderEmail, receiverEmail}, messageRowMapper);
    }

    @Override
    public void save(Message message) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                    ps.setString(1, message.getReceiver_email());
                    ps.setString(2, message.getSender_email());
                    ps.setString(3, message.getText());
                    ps.setString(4, message.getPage_id());
                    return ps;
                }, keyHolder);

        message.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void update(Message message) {
        jdbcTemplate.update(SQL_UPDATE,
                message.getReceiver_email(),
                message.getSender_email(),
                message.getText(),
                message.getPage_id());
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<Message> find(Long id) {
        try {
            Message message = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, messageRowMapper);
            return Optional.of(message);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public List<Message> distinct() {
        return jdbcTemplate.query(SQL_DISTINCT, messageRowMapper);
    }
}
