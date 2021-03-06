package com.company.dao;

import com.company.entity.LotEntity;
import com.company.entity.LotStatus;
import com.company.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class LotDao implements Dao<Integer, LotEntity> {

    private static final LotDao INSTANCE = new LotDao();

    private static final String GET_ALL_LOT_SQL = """
        SELECT l.id, l.lot_name, l.owner, l.start_bid, l.status_id, st.lot_status
        FROM lot l
        JOIN status st ON l.status_id = st.id
        """;

    private static final String FIND_LOT_STATUS_ID_BY_STATUS_NAME_SQL = """
        SELECT id
        FROM status
        WHERE lot_status = ?
        """;

    private static final String GET_LAST_BID_BY_ID_SQL = """
        SELECT MAX(user_bid) as last_bid
        FROM bid
        WHERE lot_id = ?
        """;

    private static final String GET_USER_WHO_MADE_LAST_BID_SQL = """
        SELECT user_name as username
        FROM bid
        WHERE lot_id = ?
        ORDER BY user_bid DESC
        LIMIT 1
            """;

    private static final String ADD_NEW_LOT_SQL = """
        INSERT INTO lot (lot_name, owner, status_id, start_bid)
        VALUES (?, ?, ?, ?)
        """;

    private static final String UPDATE_LOT_BY_ID_SQL = """
        UPDATE lot
        SET status_id = ?, lot_buyer = ?
        WHERE id = ?
        """;

    @SneakyThrows
    @Override
    public List<LotEntity> findAll() {
        List<LotEntity> lots = new ArrayList<>();

        try (var connection = ConnectionManager.getConnection();
            var preparedStatement= connection.prepareStatement(GET_ALL_LOT_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            LotEntity lotEntity = null;

            while (resultSet.next()) {
                lots.add(buildEntity(resultSet));
            }
        }
        return lots;
    }

    @Override
    public List<LotEntity> findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @SneakyThrows
    @Override
    public void update(LotEntity entity) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(UPDATE_LOT_BY_ID_SQL)) {
            preparedStatement.setObject(1, findIdByLotStatusName(String.valueOf(entity.getLotStatus())).get());
            preparedStatement.setObject(2, entity.getLotBuyer());
            preparedStatement.setObject(3, entity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public LotEntity save(LotEntity lotEntity) {
        try (var connection = ConnectionManager.getConnection();
            var preparedStatement = connection.prepareStatement(ADD_NEW_LOT_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, lotEntity.getLotName());
            preparedStatement.setObject(2, lotEntity.getOwner());
            preparedStatement.setObject(3, findIdByLotStatusName(String.valueOf(lotEntity.getLotStatus())).get());
            preparedStatement.setObject(4, lotEntity.getStartBid());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            lotEntity.setId(generatedKeys.getObject("id", Integer.class));
        }

        return lotEntity;
    }


    private Optional<Integer> findIdByLotStatusName(String statusName) throws SQLException {
        Integer id = null;

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_LOT_STATUS_ID_BY_STATUS_NAME_SQL)) {
            preparedStatement.setObject(1, statusName);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getObject("id", Integer.class);
            }
        }

        return Optional.ofNullable(id);
    }

    private Integer getLastBid(Integer lotId) {
        Integer id = null;

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(GET_LAST_BID_BY_ID_SQL)) {
            preparedStatement.setObject(1, lotId);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getObject("last_bid", Integer.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    private String getUserNameWhoMadeLastBid(Integer lotId) {
        String username = null;

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(GET_USER_WHO_MADE_LAST_BID_SQL)) {
            preparedStatement.setObject(1, lotId);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                username = resultSet.getObject("username", String.class);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username;
    }

    @SneakyThrows
    private LotEntity buildEntity (ResultSet resultSet) {
        Integer lotId = resultSet.getObject("id", Integer.class);
        return LotEntity.builder()
                .id(resultSet.getObject("id", Integer.class))
                .lotName(resultSet.getObject("lot_name", String.class))
                .owner(resultSet.getObject("owner", String.class))
                .lotStatus(LotStatus.valueOf(resultSet.getObject("lot_status", String.class)))
                .startBid(resultSet.getObject("start_bid", Integer.class))
                .lastBid(getLastBid(lotId))
                .userWhoMadeLastBid(getUserNameWhoMadeLastBid(lotId))
                .build();
    }

    public static LotDao getInstance () {
        return INSTANCE;
    }

}
