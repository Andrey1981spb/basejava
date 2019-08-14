package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializeUtil.ObjectStreamSerializer;

public class ObjectStreamFileStorageTest extends AbstractStorageTest{

    public ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
