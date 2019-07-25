package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> resumeFullNameMap = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Resume searchKey) {
        resumeFullNameMap.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(Resume searchKey) {
        return searchKey;
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
    protected boolean isValid(Resume searchKey) {
        return resumeFullNameMap.containsValue(searchKey);
    }

    @Override
    protected void doDelete(Resume searchKey) {
        resumeFullNameMap.remove((searchKey).getUuid());
    }

    @Override
    protected void doUpdate(Resume resume, Resume searchKey) {
        resumeFullNameMap.put(resume.getUuid(), resume);
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(resumeFullNameMap.values());
    }

}

