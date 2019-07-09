package storage;

import model.Resume;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private static final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    public Resume doGet(int index) {
        for (Map.Entry<String, Resume> resumeEntry : resumeMap.entrySet()) {
            if (resumeEntry.getValue().hashCode() == index) {
                return resumeMap.get(resumeEntry.getKey());
            }
        }
        return null;
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

    protected int getIndex(String uuid) {
        for (Map.Entry<String, Resume> resumeEntry : resumeMap.entrySet()) {
            if (resumeEntry.getKey() == uuid) {
                return resumeEntry.getValue().hashCode();
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume resume, int index) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(int index) {
        Iterator<Map.Entry<String, Resume>> resumeIterator = resumeMap.entrySet().iterator();
        while (resumeIterator.hasNext()) {
            Map.Entry<String, Resume> resumeEntry = resumeIterator.next();
            if (resumeEntry.getValue().hashCode() == index) {
                resumeIterator.remove();
            }
        }
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        resumeMap.put(resume.getUuid(), resume);
    }

}

