package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, index);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(index, uuid);
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            doUpdate(resume);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(index, uuid);
    }

    public abstract Resume[] getAll();

    public abstract void clear();

    protected abstract int getIndex(String uuid);

    protected abstract void doSave(Resume resume, int index);

    protected abstract void doDelete(int index, String uuid);

    protected abstract void doUpdate(Resume resume);

    protected abstract Resume doGet(int index, String uuid);

}
