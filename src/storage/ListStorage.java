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

    @Override
    protected Integer getSearchKey(Object uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isValid(Object searchKey) {
        return searchKey != null;
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

    @Override
    protected List<Resume> getList() {
        return resumeList;
    }

}

