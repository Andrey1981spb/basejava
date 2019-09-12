package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
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
                        "        ON r.uuid = c.resume_uuid  " +
                        " JOIN section s " +
                        "        ON r.uuid = s.section_uuid  " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        executeContact(resume, rs);
                        executeSection(resume, rs);
                    } while (rs.next());
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
                    deleteContact(conn, resume);
                    addContact(conn, resume);
                    deleteSection(conn, resume);
                    addSection(conn, resume);
                    return null;
                }
        );
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, resume.getUuid().trim());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    addContact(conn, resume);
                    addSection(conn, resume);
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
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumeMap = new LinkedHashMap<>();
            try (PreparedStatement ps1 = conn.prepareStatement("   " +
                    " SELECT * FROM resume " +
                    "   ORDER BY full_name, uuid")) {
                ResultSet rsResume = ps1.executeQuery();
                while (rsResume.next()) {
                    String uuid = rsResume.getString("uuid");
                    resumeMap.put(uuid, new Resume(uuid, rsResume.getString("full_name")));
                }
            }

            try (PreparedStatement ps2 = conn.prepareStatement("   " +
                    " SELECT * FROM contact " +
                    "   ORDER BY resume_uuid")) {
                ResultSet rsContact = ps2.executeQuery();

                while (rsContact.next()) {
                    String uuid = rsContact.getString("resume_uuid");
                    Resume resume = resumeMap.get(uuid);
                    executeContact(resume, rsContact);
                }

            }

            try (PreparedStatement ps3 = conn.prepareStatement("   " +
                    " SELECT * FROM section " +
                    "   ORDER BY section_uuid")) {
                ResultSet rsSection = ps3.executeQuery();

                while (rsSection.next()) {
                    String uuid = rsSection.getString("section_uuid");
                    Resume resume = resumeMap.get(uuid);
                    executeSection(resume, rsSection);
                }

            }
            return new ArrayList<>(resumeMap.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.doQuery("SELECT count(*) FROM resume r" +
                        "  LEFT JOIN contact c " +
                        "    ON r.uuid = c.resume_uuid " +
                        " LEFT JOIN section s " +
                        "    ON r.uuid = s.section_uuid  ",
                statement -> {
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                    return 0;
                });
    }

    private void executeContact(Resume resume, ResultSet rs) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            resume.addContact(type, value);
        }
    }

    private void executeSection(Resume resume, ResultSet rs) throws SQLException {
        String value = rs.getString("section_value");
        if (value != null) {
            SectionType type = SectionType.valueOf(rs.getString("section_type"));
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    resume.addSection(type, new SimpleTextSection("section_value"));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    resume.addSection(type, new MarkedListSection("section_value"));
            }
        }
    }

    private void addContact(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContactInfoMap().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addSection(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (section_uuid, section_type, section_value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : resume.getResumeSections().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                switch (e.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        ps.setString(3, ((SimpleTextSection) e.getValue()).getPosition());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = ((MarkedListSection) e.getValue()).getPerformanceList();
                        for (String value : list) {
                            ps.setString(3, value);
                        }
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContact(Connection conn, Resume resume) {
        sqlHelper.doQuery("DELETE  FROM contact WHERE resume_uuid=?", ps -> {
            ps.setString(1, resume.getUuid());
            ps.execute();
            return null;
        });
    }

    private void deleteSection(Connection conn, Resume resume) {
        sqlHelper.doQuery("DELETE  FROM section WHERE section_uuid=?", ps -> {
            ps.setString(1, resume.getUuid());
            ps.execute();
            return null;
        });
    }

}
