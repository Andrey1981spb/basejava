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
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest() {
        storage = new ArrayStorage();
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void save() {
        Resume[] resumes = new Resume[3];

        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);

        Assert.assertArrayEquals(resumes, storage.getAll());
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = StorageException.class)
    public void storageOverFlow() {
        try {
            for (int i = 0; i < 9997; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("not overflow yet");
        }

        storage.save(new Resume(UUID_4));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1));
        storage.update(new Resume(UUID_2));
        storage.update(new Resume(UUID_3));

        Resume[] resumes = new Resume[3];
        resumes[0] = new Resume(UUID_1);
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);
        resumes[2] = new Resume(UUID_3);

        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void get() throws NoSuchFieldException {
        storage.get(UUID_1);

        Resume[] resumes = new Resume[1];
        resumes[0] = new Resume(UUID_1);

        Assert.assertEquals(resumes[0], new Resume(UUID_1));
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
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);

        Assert.assertArrayEquals(resumes, storage.getAll());
    }
}