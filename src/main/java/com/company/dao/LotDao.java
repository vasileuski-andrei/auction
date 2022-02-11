package com.company.dao;

import com.company.entity.LotEntity;
import com.company.entity.LotStatus;
import com.company.entity.UserEntity;
import com.company.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class LotDao implements Dao<Integer, LotEntity> {

    private static final LotDao INSTANCE = new LotDao();

//    private static final String GET_ALL_LOTS_SQL = """
//            SELECT id, name, owner_id
//            FROM lot
//            """;

    private static final String GET_ALL_LOTS_SQL = """
            SELECT *
            FROM lot l 
            JOIN status st ON l.status_id = st.id
            JOIN users u ON l.owner_id = u.id 
            """;

    @SneakyThrows
    @Override
    public List<LotEntity> findAll() {

        List<LotEntity> lots = new ArrayList<>();

        try (var connection = ConnectionManager.getConnection();
            var preparedStatement= connection.prepareStatement(GET_ALL_LOTS_SQL)) {


            var resultSet = preparedStatement.executeQuery();
            LotEntity lotEntity = null;

            while (resultSet.next()) {
                lots.add(buildEntity(resultSet));
            }

        }

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

    @Override
    public LotEntity save(LotEntity entity) {
        return null;
    }

    @SneakyThrows
    private LotEntity buildEntity(ResultSet resultSet) {

        return LotEntity.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .owner(resultSet.getObject("email", String.class))
                .lotStatus(LotStatus.valueOf(resultSet.getObject("lot_status", String.class)))
                .build();
    }

    public static LotDao getInstance() {
        return INSTANCE;
    }


}
