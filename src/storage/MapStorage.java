package storage;

import model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private static final Map<String, Resume> resumeMap = new HashMap<>();

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

    public Resume[] getAll() {
        int count = 0;
        Resume[] resumes = new Resume[size()];
        for (Map.Entry<String, Resume> resumeEntry : resumeMap.entrySet()) {
            resumes[count] = resumeEntry.getValue();
            count++;
        }
        return resumes;
    }

    protected Object getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> resumeEntry : resumeMap.entrySet()) {
            if (resumeEntry.getKey() == uuid) {
                String key = resumeEntry.getKey();
                //   return Integer.valueOf(key);
                return key;
            }
        }
        return null;
    }

    protected boolean isValid(Object searchKey) {
        if (resumeMap.containsKey(searchKey)) {
            return true;
        }
        return false;
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

