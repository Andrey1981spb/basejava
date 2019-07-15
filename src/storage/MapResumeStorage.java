package storage;

import model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private final Map<String, Resume> resumeFullNameMap = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        resumeFullNameMap.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    public void clear() {
        resumeFullNameMap.clear();
    }

    @Override
    public int size() {
        return resumeFullNameMap.size();
    }

    @Override
    protected Resume getSearchKey(Object uuid) {
        return resumeFullNameMap.get(uuid);
    }

    @Override
    protected boolean isValid(Object searchKey) {
        return resumeFullNameMap.containsValue(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeFullNameMap.remove(((Resume)searchKey).getUuid());
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumeFullNameMap.put(resume.getUuid(), resume);
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(resumeFullNameMap.values());
    }

}

