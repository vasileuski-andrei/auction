package com.company.dao;

import com.company.entity.BetEntity;
import com.company.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BetDao implements Dao<Integer, BetEntity> {

    private static final BetDao INSTANCE = new BetDao();

    private static final String ADD_BET_SQL = """
            INSERT INTO bet (lot_name, lot_id, user_name, user_bet)
            VALUES (?, ?, ?, ?);
            """;

    @Override
    public List<BetEntity> findAll() {
        return null;
    }

    @Override
    public Optional<BetEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public void update(BetEntity entity) {

    }

    @Override
    public BetEntity save(BetEntity entity) throws SQLException {

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(ADD_BET_SQL)) {

            preparedStatement.setObject(1, entity.getLotName());
            preparedStatement.setObject(2, entity.getLodId());
            preparedStatement.setObject(3, entity.getUserName());
            preparedStatement.setObject(4, entity.getUserBet());

            preparedStatement.executeUpdate();

        }

        return null;
    }

    public static BetDao getInstance () {
        return INSTANCE;
    }
}
