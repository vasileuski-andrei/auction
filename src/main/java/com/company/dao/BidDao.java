package com.company.dao;

import com.company.entity.BidEntity;
import com.company.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BidDao implements Dao<Integer, BidEntity> {

    private static final BidDao INSTANCE = new BidDao();

    private static final String PLACE_BET_SQL = """
            INSERT INTO bid (lot_name, lot_id, user_name, user_bid, date)
            VALUES (?, ?, ?, ?, ?);
            """;

    private static final String GET_BET_BY_ID_SQL = """
            SELECT id, lot_name, lot_id, user_name, user_bid, date
            FROM bid
            WHERE lot_id = ?
            """;

    @Override
    public List<BidEntity> findAll() {
        return null;
    }

    @SneakyThrows
    @Override
    public List<BidEntity> findById(Integer id) {

        List<BidEntity> bet = new ArrayList<>();

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
    public void update(BidEntity entity) {

    }

    @Override
    public BidEntity save(BidEntity bidEntity) throws SQLException {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(PLACE_BET_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setObject(1, bidEntity.getLotName());
            preparedStatement.setObject(2, bidEntity.getLotId());
            preparedStatement.setObject(3, bidEntity.getUserName());
            preparedStatement.setObject(4, bidEntity.getUserBid());
            preparedStatement.setObject(5, LocalDateTime.now());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            bidEntity.setId(generatedKeys.getObject("id", Integer.class));

        }

        return bidEntity;
    }

    public static BidDao getInstance () {
        return INSTANCE;
    }

    @SneakyThrows
    private BidEntity buildEntity (ResultSet resultSet) {
        return BidEntity.builder()
                .id(resultSet.getObject("id", Integer.class))
                .lotName(resultSet.getObject("lot_name", String.class))
                .lotId(resultSet.getObject("lot_id", Integer.class))
                .userName(resultSet.getObject("user_name", String.class))
                .userBid(resultSet.getObject("user_bid", Integer.class))
                .dateTime(resultSet.getObject("date", LocalDateTime.class))
                .build();
    }
}
