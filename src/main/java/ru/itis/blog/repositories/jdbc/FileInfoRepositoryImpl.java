package ru.itis.blog.repositories.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.blog.models.FileInfo;


import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class FileInfoRepositoryImpl implements FileInfoRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_STORAGEFILENAME =
            "select * from file_info where storage_file_name = ? ;";

    //language=SQL
    private static final String SQL_SELECT_BY_ID =
            "select * from file_info where id= ? ;";

    //language=SQL
    private static final String SQL_SELECT_BY_USER_ID =
            "select * from file_info where user_id= ? ;";


    //language=SQL
    private static final String SQL_INSERT =
            "insert into file_info(original_file_name, storage_file_name, type, url, user_id) values (?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    public FileInfoRepositoryImpl(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    private RowMapper<FileInfo> fileInfoRowMapper = (row, i) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .originalFileName(row.getString("original_file_name"))
                    .size(row.getLong("size"))
                    .storageFileName(row.getString("storage_file_name"))
                    .type(row.getString("type"))
                    .url(row.getString("url"))
                    .userId(row.getLong("user_id"))
                    .build();

    @Override
    public Optional<FileInfo> findOneByStorageFileName(String storageFileName) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_STORAGEFILENAME, new Object[]{storageFileName}, fileInfoRowMapper);
            return Optional.of(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<FileInfo> findOneByUserId(Long userId) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_USER_ID, new Object[]{userId}, fileInfoRowMapper);
            return Optional.of(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(FileInfo fileInfo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                    ps.setString(1, fileInfo.getOriginalFileName());
                    ps.setString(2, fileInfo.getStorageFileName());
                    ps.setString(3, fileInfo.getType());
                    ps.setString(4, fileInfo.getUrl());
                    ps.setLong(5, fileInfo.getUserId());
                    return ps;
                }, keyHolder);

        fileInfo.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void update(FileInfo model) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<FileInfo> find(Long id) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, fileInfoRowMapper);
            return Optional.of(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<FileInfo> findAll() {
        return null;
    }
}
