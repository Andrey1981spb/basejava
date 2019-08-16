package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {
    //   protected static final File STORAGE_DIR = new File("../basejava/storage");
    protected static final File STORAGE_DIR = new File("/Users/andrej/basejava/storage");

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = ResumeTestData.getResume(UUID_1, "fullName1");
        RESUME_2 = ResumeTestData.getResume(UUID_2, "fullName2");
        RESUME_3 = ResumeTestData.getResume(UUID_3, "fullName3");
        RESUME_4 = ResumeTestData.getResume(UUID_4, "fullName4");
    }

    protected AbstractStorageTest(Storage storage) {
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
        assertEquals(3, storage.size());
    }

    @Test ( expected = ExistStorageException.class )
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEquals(RESUME_4, storage.get(RESUME_4.getUuid()));
        assertEquals(4, storage.size());
    }

    @Test ( expected = NotExistStorageException.class )
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test ( expected = NotExistStorageException.class )
    public void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test ( expected = NotExistStorageException.class )
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1, "fullName1");
        storage.update(resume);
        assertTrue(resume.equals(storage.get(UUID_1)));
        Resume resume2 = ResumeTestData.getResume(UUID_1, "fullName1");
        storage.update(resume2);
        assertTrue(resume2.equals(storage.get(UUID_1)));
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test ( expected = NotExistStorageException.class )
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(RESUME_1);
        resumeList.add(RESUME_2);
        resumeList.add(RESUME_3);

        Assert.assertEquals(resumeList, storage.getAllSorted());
        Assert.assertEquals(3, storage.getAllSorted().size());
    }
}