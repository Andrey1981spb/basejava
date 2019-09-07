package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.util.Config;

public class SQLStorageTest extends AbstractStorageTest {

    public SQLStorageTest() {
        super(Config.get().getStorage());
    }

}

