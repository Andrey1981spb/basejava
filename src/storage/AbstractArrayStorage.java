package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            extendStorage(resume);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index <0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            decreaseStorage(uuid);
            storage[size - 1] = null;
            size--;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index <0) {
            System.out.println("Resume " + resume.getUuid() + " not exist");
        } else {
            int position = getIndex(resume.getUuid());
            storage[position] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index <0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
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

    protected abstract void extendStorage(Resume resume);

    protected abstract void decreaseStorage(String uuid);
}