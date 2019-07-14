package storage;

import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

   protected Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
        this.storage=storage;
    }

    @Test(expected = StorageException.class)
    public void storageOverFlow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("not overflow yet");
        }
        storage.save(new Resume());
    }

}
