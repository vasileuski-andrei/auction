package com.company.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    List<T> findAll();

    List<T> findById(K id);

    void delete(K id) throws SQLException;

    void update(T entity);

    void save(T entity) throws SQLException;
}
