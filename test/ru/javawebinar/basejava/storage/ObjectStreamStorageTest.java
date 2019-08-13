package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializeUtil.StreamSerializer;

public class ObjectStreamStorageTest extends AbstractStorageTest{

    public ObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new StreamSerializer()));
    }
}
