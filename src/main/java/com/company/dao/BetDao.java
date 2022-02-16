package com.company.dao;

import com.company.entity.BetEntity;
import com.company.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BetDao implements Dao<Integer, BetEntity> {

    private static final BetDao INSTANCE = new BetDao();

    private static final String PLACE_BET_SQL = """
            INSERT INTO bet (lot_name, lot_id, user_name, user_bet)
            VALUES (?, ?, ?, ?);
            """;

    private static final String GET_BET_BY_ID_SQL = """
            SELECT *
            FROM bet
            WHERE lot_id = ?
            """;

    @Override
    public List<BetEntity> findAll() {
        return null;
    }

    @SneakyThrows
    @Override
    public Optional<BetEntity> findById(Integer id) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(PLACE_BET_SQL)) {

            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            BetEntity betEntity = null;

            while (resultSet.next()) {
                betEntity = buildEntity(resultSet);

            }

            return Optional.ofNullable(betEntity);
        }

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public void update(BetEntity entity) {

    }

    @Override
    public void save(BetEntity entity) throws SQLException {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(PLACE_BET_SQL)) {

            preparedStatement.setObject(1, entity.getLotName());
            preparedStatement.setObject(2, entity.getLotId());
            preparedStatement.setObject(3, entity.getUserName());
            preparedStatement.setObject(4, entity.getUserBet());

            preparedStatement.executeUpdate();

        }
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
