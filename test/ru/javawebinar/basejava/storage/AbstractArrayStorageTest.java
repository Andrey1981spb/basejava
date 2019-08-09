package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test ( expected = StorageException.class )
    public void storageOverFlow() throws IOException {
        String fullName = "name";
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume(fullName));
            }
        } catch (StorageException e) {
            Assert.fail("not overflow yet");
        }
        storage.save(new Resume(fullName));
    }

}
