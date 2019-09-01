package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;
    private SQLHelper sqlHelper = new SQLHelper();

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.doQuery(connectionFactory, "DELETE FROM resume", (SQLHelper.SQLProcessor) statement -> {
            statement.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return (Resume) sqlHelper.doQuery(connectionFactory, "SELECT * FROM resume r WHERE r.uuid =?", (SQLHelper.SQLProcessor) statement -> {
            statement.setString(1, uuid);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(rs.getString("uuid").trim(), rs.getString("full_name"));
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.doQuery(connectionFactory, "UPDATE resume SET full_name=? WHERE uuid=?", (SQLHelper.SQLProcessor) statement -> {
            statement.setString(1, resume.getFullName());
            statement.setString(2, resume.getUuid());
            if (statement.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.doQuery(connectionFactory,
                "INSERT INTO resume (uuid, full_name) VALUES (?,?)", (SQLHelper.SQLProcessor) statement -> {
                    statement.setString(1, resume.getUuid());
                    statement.setString(2, resume.getFullName());
                    statement.execute();
                    return null;
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.doQuery(connectionFactory, "DELETE FROM resume WHERE uuid =?", statement -> {
            statement.setString(1, uuid);
            if (statement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return (List<Resume>) sqlHelper.doQuery(connectionFactory,
                "SELECT * FROM resume", (SQLHelper.SQLProcessor) statement -> {
                    ResultSet rs = statement.executeQuery();
                    List<Resume> list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
                    }
                    return list;
                });
    }

    @Override
    public int size() {
        return (int) sqlHelper.doQuery(connectionFactory, "SELECT count(*) FROM resume", (SQLHelper.SQLProcessor) statement -> {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return null;
        });
    }


}
