package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    public Resume doGet(Object searchKey) {
        return resumeList.get((Integer) searchKey);
    }

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    public Resume[] getAll() {
        return resumeList.toArray(new Resume[size()]);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume[] resumes = resumeList.toArray(new Resume[size()]);
        for (int i = 0; i < resumes.length; i++) {
            if (uuid.equals(resumes[i].getUuid())) {
                return Integer.valueOf(i);
            }
        }
        return Integer.valueOf(-1);
    }

    @Override
    protected boolean isValid(Object searchKey) {
        if ((Integer) searchKey < 0) {
            return false;
        }
        return true;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        resumeList.add(resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        int t = (Integer) searchKey;
        resumeList.remove(t);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumeList.set((Integer) searchKey, resume);
    }

}

