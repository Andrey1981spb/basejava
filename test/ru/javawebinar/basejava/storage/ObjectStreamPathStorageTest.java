package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.storage.serializeUtil.ObjectStreamSerializer;

public class ObjectStreamPathStorageTest extends AbstractStorageTest{

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }

}
