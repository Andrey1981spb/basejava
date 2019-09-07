package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SQLHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    public final SQLHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SQLHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.doQuery("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.doQuery("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    resume = executeContact(resume, rs);
                    return resume;
                });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=? WHERE uuid=?")) {
                        ps.setString(1, resume.getFullName());
                        ps.setString(2, resume.getUuid());
                        if (ps.executeUpdate() == 0) {
                            throw new NotExistStorageException(resume.getUuid());
                        }
                        ps.execute();
                    }
                    sqlHelper.doQuery("DELETE FROM contact WHERE resume_uuid=?",
                            ps -> {
                                ps.setString(1, resume.getUuid());
                                ps.execute();
                                return null;
                            });

                    try (PreparedStatement ps = conn.prepareStatement("UPDATE contact SET type=?,value=? WHERE resume_uuid=?")) {
                        for (Map.Entry<ContactType, String> e : resume.getContactInfoMap().entrySet()) {
                            ps.setString(1, e.getKey().name());
                            ps.setString(2, e.getValue());
                            ps.setString(3, resume.getUuid());
                            ps.addBatch();
                        }
                        ps.executeBatch();
                    }
                    return null;
                }
        );
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                        for (Map.Entry<ContactType, String> e : resume.getContactInfoMap().entrySet()) {
                            ps.setString(1, resume.getUuid());
                            ps.setString(2, e.getKey().name());
                            ps.setString(3, e.getValue());
                            ps.addBatch();
                        }
                        ps.executeBatch();
                    }
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.doQuery("DELETE FROM resume WHERE uuid =?", statement -> {
            statement.setString(1, uuid);
            if (statement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.doQuery("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "ORDER BY full_name, uuid",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    Map<String, Resume> resumeMap = new HashMap<>();
                    while (rs.next()) {
                        String uuid = rs.getString("uuid");
                        Resume resume = resumeMap.get(rs.getString(uuid));
                        if (resume == null) {
                            resume = new Resume(uuid, rs.getString("full_name"));
                            resumeMap.put(uuid, resume);
                        }
                        executeContact(resume, rs);
                    }
                    return new ArrayList<>(resumeMap.values());
                });
    }

    @Override
    public int size() {
        return sqlHelper.doQuery("SELECT count(*) FROM resume r" +
                        "  LEFT JOIN contact c " +
                        "    ON r.uuid = c.resume_uuid ",
                statement -> {
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                    return 0;
                });
    }

    private Resume executeContact(Resume resume, ResultSet rs) throws SQLException {
        do {
            String value = rs.getString("value");
            ContactType type = ContactType.valueOf(rs.getString("type"));
            resume.addContact(type, value);
        } while (rs.next());
        return resume;
    }

}
