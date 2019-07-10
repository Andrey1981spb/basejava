package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    @Override
    public void doSave(Resume resume, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            doArraySave(resume, searchKey);
            size++;
        }
    }

    public void doDelete(Object searchKey) {
        doArrayDelete((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    public void doUpdate(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    public Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected boolean isValid(Object searchKey) {
        if ((Integer) searchKey < 0) {
            return false;
        }
        return true;
    }

    protected abstract void doArraySave(Resume resume, Object searchKey);

    protected abstract void doArrayDelete(Object searchKey);
}