package com.company.dao;

import com.company.entity.BetEntity;
import com.company.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BetDao implements Dao<Integer, BetEntity> {

    private static final BetDao INSTANCE = new BetDao();

    private static final String PLACE_BET_SQL = """
            INSERT INTO bet (lot_name, lot_id, user_name, user_bet)
            VALUES (?, ?, ?, ?);
            """;

    private static final String GET_BET_BY_ID_SQL = """
            SELECT id, lot_name, lot_id, user_name, user_bet
            FROM bet
            WHERE lot_id = ?
            """;

    @Override
    public List<BetEntity> findAll() {
        return null;
    }

    @SneakyThrows
    @Override
    public List<BetEntity> findById(Integer id) {

        List<BetEntity> bet = new ArrayList<>();

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(GET_BET_BY_ID_SQL)) {

            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bet.add(buildEntity(resultSet));
            }

        }

        return bet;

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public void update(BetEntity entity) {

    }

    @Override
    public BetEntity save(BetEntity betEntity) throws SQLException {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(PLACE_BET_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setObject(1, betEntity.getLotName());
            preparedStatement.setObject(2, betEntity.getLotId());
            preparedStatement.setObject(3, betEntity.getUserName());
            preparedStatement.setObject(4, betEntity.getUserBet());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            betEntity.setId(generatedKeys.getObject("id", Integer.class));

        }

        return betEntity;
    }

    public static BetDao getInstance () {
        return INSTANCE;
    }

    @SneakyThrows
    private BetEntity buildEntity (ResultSet resultSet) {
        return BetEntity.builder()
                .id(resultSet.getObject("id", Integer.class))
                .lotName(resultSet.getObject("lot_name", String.class))
                .lotId(resultSet.getObject("lot_id", Integer.class))
                .userName(resultSet.getObject("user_name", String.class))
                .userBet(resultSet.getObject("user_bet", Integer.class))
                .build();
    }
}
