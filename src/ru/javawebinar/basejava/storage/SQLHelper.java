package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLHelper {

    public <T> T doQuery(ConnectionFactory connectionFactory, String query, SQLProcessor<T> processor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return processor.process(ps);
        } catch (SQLException e) {
            throw new ExistStorageException(null);
        }
    }

    public interface SQLProcessor<T> {
        T process(PreparedStatement statement) throws SQLException;
    }

}
