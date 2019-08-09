package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    public Resume doGet(Integer searchKey) {
        return resumeList.get(searchKey);
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
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isValid(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        resumeList.add(resume);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        int t = searchKey;
        resumeList.remove(t);
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        resumeList.set(searchKey, resume);
    }

    @Override
    protected List<Resume> getList() {
        return resumeList;
    }

}
