package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serealizeUtil.StreamSerializer;

public class ObjectStreamStorageTest extends AbstractStorageTest{

    public ObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new StreamSerializer()));
    }
}
