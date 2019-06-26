import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import storage.ArrayStorage;
import storage.Storage;

public class AbstractArrayStorageTest {
    private Storage storage = new ArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < 9998; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("not overflow yet");
        }
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void size() {
        Assert.assertEquals(9999, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void save() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void storageOverFlow() {
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid4");
    }

    @Test(expected = NotExistStorageException.class)
    public void update() {
        storage.update(new Resume("uuid4"));
    }

    @Test(expected = NotExistStorageException.class)
    public void get() {
        storage.get("uuid4");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertNotNull(storage);
    }

    @Test
    public void getAll() {
        Assert.assertNotNull(storage);
    }
}