package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void save() {
        Resume[] resumes = new Resume[3];

        resumes[0] = storage.get(UUID_1);
        resumes[1] = storage.get(UUID_2);
        resumes[2] = storage.get(UUID_3);

        Assert.assertArrayEquals(resumes, storage.getAll());
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = StorageException.class)
    public void storageOverFlow() {
        storage.clear();
        final int storage_limit = AbstractArrayStorage.STORAGE_LIMIT;
        try {
            for (int i = 0; i < storage_limit; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("not overflow yet");
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
        try {
            storage.get(UUID_4);
        } catch (ExistStorageException exist) {
            Assert.fail("Resume exist");
        }
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void update() {
        storage.clear();
        storage.save(RESUME_1);
        storage.update(RESUME_1);

        Resume resume1 = storage.get(UUID_1);

        Assert.assertSame(RESUME_1, resume1);
    }

    @Test
    public void get() {
        storage.get(UUID_1);
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[3];
        resumes[0] = RESUME_1;
        resumes[1] = RESUME_2;
        resumes[2] = RESUME_3;

        Assert.assertArrayEquals(resumes, storage.getAll());
        Assert.assertEquals(3, storage.size());
    }
}