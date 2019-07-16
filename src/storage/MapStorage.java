package storage;

import model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> resumeMap = new HashMap<>();

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

    @Override
    protected String getSearchKey(Object uuid) {
        return (String) uuid;
    }

    @Override
    protected boolean isValid(Object searchKey) {
        return resumeMap.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumeMap.put(searchKey.toString(), resume);
    }

    @Override
    protected List<Resume> getList (){
        return new ArrayList<>(resumeMap.values());
    }

}

