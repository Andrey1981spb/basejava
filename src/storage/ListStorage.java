package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> resumeList = new ArrayList<>();

    private static final Comparator LIST_RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume a, Resume b) {
            if (a.getFullName().equals(b.getFullName())) {
                return a.getUuid().compareTo(b.getUuid());
            }
            return a.getFullName().compareTo(b.getFullName());
        }
    };

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

    public List<Resume> getAllSorted() {
        resumeList.sort(LIST_RESUME_COMPARATOR);
        return resumeList;
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

}

