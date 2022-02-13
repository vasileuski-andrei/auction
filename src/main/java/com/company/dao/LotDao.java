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
import java.util.OptionalInt;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class LotDao implements Dao<Integer, LotEntity> {

    private static final LotDao INSTANCE = new LotDao();

    private static final String GET_ALL_LOT_SQL = """       
            SELECT *
            FROM lot l
            JOIN status st ON l.status_id = st.id
            """;

//    private static final String FIND_ID = """
//        SELECT id
//        FROM ?
//        WHERE ?
//        """;

    private static final String FIND_ID = """
        SELECT id
        FROM status
        WHERE lot_status = ?
        """;

    private static final String ADD_NEW_LOT = """
        INSERT INTO lot (lot_name, owner, status_id, start_price)
        VALUES (?, ?, ?, ?)
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
        System.out.println();
        return lots;
    }

    @Override
    public Optional<LotEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(LotEntity entity) {

    }

    @SneakyThrows
    @Override
    public void save(LotEntity lotEntity) {

        try (var connection = ConnectionManager.getConnection();
            var preparedStatement = connection.prepareStatement(ADD_NEW_LOT)) {
            preparedStatement.setObject(1, lotEntity.getLotName());
            preparedStatement.setObject(2, lotEntity.getOwner());
            preparedStatement.setObject(3, findIdByLotStatusName(String.valueOf(lotEntity.getLotStatus())).get());
            preparedStatement.setObject(4, lotEntity.getStartPrice());

            preparedStatement.executeUpdate();
        }

    }


    private Optional<Integer> findIdByLotStatusName(String findId) throws SQLException {
        Integer id = null;

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_ID)) {

            preparedStatement.setObject(1, findId);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getObject("id", Integer.class);
            }

        }

        return Optional.ofNullable(id);

    }


    @SneakyThrows
    private LotEntity buildEntity (ResultSet resultSet) {

        return LotEntity.builder()
                .id(resultSet.getObject("id", Integer.class))
                .lotName(resultSet.getObject("lot_name", String.class))
                .owner(resultSet.getObject("owner", String.class))
                .lotStatus(LotStatus.valueOf(resultSet.getObject("lot_status", String.class)))
                .startPrice(resultSet.getObject("start_price", Integer.class))
                .build();
    }

    public static LotDao getInstance () {
        return INSTANCE;
    }


}
