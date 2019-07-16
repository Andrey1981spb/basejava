package storage;

import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test ( expected = StorageException.class )
    public void storageOverFlow() {
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
