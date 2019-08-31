package ru.javawebinar.basejava.storage;

public class SQLStorageTest extends AbstractStorageTest {

    public SQLStorageTest() {
        super(new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "andrej", "postgres"));
    }

}

