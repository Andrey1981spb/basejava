package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    @Override
    public void doSave(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            saveToArray(resume, (Integer) index);
            size++;
        }
    }

    public void doDelete(Object index) {
        deleteFromArray((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    public void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected boolean isValid(Object index) {
        return (Integer) index >= 0;

    }

    protected abstract void saveToArray(Resume resume, int index);

    protected abstract void deleteFromArray(int index);

    @Override
    protected List<Resume> getList() {
        Resume[] resumes = Arrays.copyOfRange(storage, 0, size);
        return Arrays.asList(resumes);
    }
}