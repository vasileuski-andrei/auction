package com.company.dao;

import com.company.entity.UserEntity;
import com.company.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.*;
import static lombok.AccessLevel.*;


@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Integer, UserEntity> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_USER_SQL = """
            INSERT INTO users (name, birth_date, email, password, role_id)
            VALUES (?, ?, ?, ?, ?);
            """;

    private static final String GET_USER_BY_EMAIL_SQL = """
            SELECT id, name, birth_date, email, password
            FROM users
            WHERE email = ? AND password = ?
            """;

    private static final String DELETE_USER_SQL = """
            DELETE FROM users
            WHERE id = ?
            """;

    private static final String GET_PASS_SQL = """
            SELECT password
            FROM users
            WHERE id = ?
            """;

    private static final String FIND_ROLE_ID_BY_ROLE_NAME_SQL = """
        SELECT id
        FROM role
        WHERE role = ?
        """;

    @SneakyThrows
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_SQL)) {

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
    public UserEntity save(UserEntity userEntity) throws SQLException {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_USER_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, userEntity.getName());
            preparedStatement.setObject(2, userEntity.getBirthDate());
            preparedStatement.setObject(3, userEntity.getEmail());
            preparedStatement.setObject(4, userEntity.getPassword());
            preparedStatement.setObject(5, findIdByRole(String.valueOf(userEntity.getRole())).get());
            System.out.println();
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            userEntity.setId(generatedKeys.getObject("id", Integer.class));

        }

        return userEntity;
    }

    private Optional<Integer> findIdByRole(String role) throws SQLException {
        Integer id = null;

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_ROLE_ID_BY_ROLE_NAME_SQL)) {

            preparedStatement.setObject(1, role);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getObject("id", Integer.class);
                System.out.println();
            }
            System.out.println();
        }

        return Optional.ofNullable(id);

    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public List<UserEntity> findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {

            preparedStatement.setObject(1, id);
            var i = preparedStatement.executeUpdate();
        }

    }

    @Override
    public void update(UserEntity entity) {

    }

    @SneakyThrows
    public Optional<String> getPasswordById(Integer id) {
        String password = null;

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(GET_PASS_SQL)) {

            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                password = resultSet.getObject("password", String.class);
            }

        }

        return Optional.ofNullable(password);
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private UserEntity buildEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthDate(resultSet.getObject("birth_date", Date.class).toLocalDate())
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .build();
    }
}
