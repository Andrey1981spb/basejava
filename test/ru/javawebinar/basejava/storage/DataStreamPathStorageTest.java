package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.storage.serializeUtil.DataStreamSerializer;

public class DataStreamPathStorageTest extends AbstractStorageTest{

    public DataStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }

}
