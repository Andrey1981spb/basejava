package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private static final Comparator RESUME_COMPARATOR = (Comparator<Resume>) (a, b) -> {
        if (a.getFullName().equals(b.getFullName())) {
            return a.getUuid().compareTo(b.getUuid());
        }
        return a.getFullName().compareTo(b.getFullName());
    };

    public void save(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (isValid(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, searchKey);
        }
    }

    public void delete(String uuid) {
        Object searchKey = resumeIsNotExist(uuid);
        doDelete(searchKey);
    }

    public void update(Resume resume) {
        Object searchKey = resumeIsNotExist(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = resumeIsNotExist(uuid);
        return doGet(searchKey);
    }

    private Object resumeIsNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumeList = getList();
        resumeList.sort(RESUME_COMPARATOR);
        return resumeList;
    }

    protected abstract List<Resume> getList();

    protected abstract Object getSearchKey(Object uuid);

    protected abstract boolean isValid(Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

}
