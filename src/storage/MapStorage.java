package storage;

import model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> resumeMap = new HashMap<>();

    private final Comparator MAP_RESUME_COMPARATOR = new Comparator<Resume>() {
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
        return resumeMap.get(searchKey);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public int size() {
        return resumeMap.size();
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumeList = new ArrayList<>(resumeMap.values());
        resumeList.sort(MAP_RESUME_COMPARATOR);
        return resumeList;
    }

    protected String getSearchKey(Object uuid) {
        return (String) uuid;
    }

    protected boolean isValid(Object searchKey) {
        return resumeMap.containsKey(searchKey);

    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeMap.remove(searchKey.toString());
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumeMap.put(searchKey.toString(), resume);
    }

}

