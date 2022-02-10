package com.company.dao;

import com.company.entity.UserEntity;
import com.company.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, UserEntity> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL = """
            INSERT INTO users (name, birthday, email, password)
            VALUES (?, ?, ?, ?);
            """;

    private static final String GET_USER_BY_EMAIL = """
            SELECT id, name, birthday, email, password
            FROM users
            WHERE email = ? AND password = ?
            """;

    private UserDao() {
    }

    @SneakyThrows
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            var resultSet = preparedStatement.executeQuery();
            UserEntity userEntity = null;

            if (resultSet.next()) {
                userEntity = buildEntity(resultSet);
            }


            return Optional.ofNullable(userEntity);
        }

    }

    @Override
    @SneakyThrows
    public UserEntity save(UserEntity userEntity) {

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setObject(1, userEntity.getName());
            preparedStatement.setObject(2, userEntity.getBirthday());
            preparedStatement.setObject(3, userEntity.getEmail());
            preparedStatement.setObject(4, userEntity.getPassword());

            preparedStatement.executeUpdate();


            return null;
        }

    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(UserEntity entity) {

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private UserEntity buildEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .build();
    }
}
