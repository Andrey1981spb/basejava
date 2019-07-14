package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    private static final Comparator ARRAY_RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume a, Resume b) {
            if (a.getFullName().equals(b.getFullName())) {
                return a.getUuid().compareTo(b.getUuid());
            }
            return a.getFullName().compareTo(b.getFullName());
        }
    };

    public int size() {
        return size;
    }

    @Override
    public void doSave(Resume resume, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            doArraySave(resume, (Integer) searchKey);
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

    public List<Resume> getAllSorted() {
        Resume[] resumes = Arrays.copyOfRange(storage, 0, size);
        List<Resume> resumeList = Arrays.asList(resumes);
        resumeList.sort(ARRAY_RESUME_COMPARATOR);
        return resumeList;
    }

    protected boolean isValid(Object searchKey) {
        return (Integer) searchKey >= 0;

    }

    protected abstract void doArraySave(Resume resume, int searchKey);

    protected abstract void doArrayDelete(int searchKey);
}