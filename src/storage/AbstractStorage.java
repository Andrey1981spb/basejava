package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.time.LocalTime;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    public LocalTime localTime;

    public void save(Resume resume) {
        resume.setLocalTime(localTime.now());
        Object searchKey = getSearchKey(resume.getUuid());
        if (isValid(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, searchKey);
        }
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(searchKey);
        }
    }

    public void update(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (!isValid(searchKey)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            doUpdate(resume, searchKey);
        }
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(searchKey);
    }

    public abstract List<Resume> getAllSorted();

    public abstract void clear();

    protected abstract Object getSearchKey(Object uuid);

    protected abstract boolean isValid(Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

}
