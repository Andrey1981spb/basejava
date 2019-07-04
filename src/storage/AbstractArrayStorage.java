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
    public void doSave(Resume resume, int index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            doArraySave(resume, index);
            size++;
        }
    }

    public void doDelete(int index) {
        doArrayDelete(index);
        storage[size - 1] = null;
        size--;
    }

    public void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    public Resume doGet(int index) {
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void doArraySave(Resume resume, int index);

    protected abstract void doArrayDelete(int index);
}