package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {

    private final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    public Resume doGet(String searchKey) {
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
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isValid(String searchKey) {
        return resumeMap.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume resume, String searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(String searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, String searchKey) {
        resumeMap.put(searchKey, resume);
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(resumeMap.values());
    }

}
