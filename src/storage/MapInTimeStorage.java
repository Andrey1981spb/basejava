package storage;

import model.Resume;

import java.time.LocalTime;
import java.util.*;

public class MapInTimeStorage extends AbstractStorage {

    private final Map<LocalTime, Resume> resumeMapInTime = new HashMap<>();

    private final Comparator MAP_RESUME_COMPARATOR = (Comparator<Resume>) (a, b) -> a.getUuid().compareTo(b.getUuid());

    @Override
    protected void doSave(Resume resume, Object searchKey) {

        resumeMapInTime.put(resume.getLocalTime(), resume);
    }

    @Override
    public Resume doGet(Object searchKey) {
        return resumeMapInTime.get(searchKey);
    }

    @Override
    public void clear() {
        resumeMapInTime.clear();
    }

    @Override
    public int size() {
        return resumeMapInTime.size();
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumeList = new ArrayList<>(resumeMapInTime.values());
        resumeList.sort(MAP_RESUME_COMPARATOR);
        return resumeList;
    }

    protected Object getSearchKey(Object uuid) {
        if (resumeMapInTime.get(uuid)==null){
            return uuid;
        }
        return resumeMapInTime.get(uuid).getLocalTime();
    }

    protected boolean isValid(Object searchKey) {
        return resumeMapInTime.containsKey(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeMapInTime.remove(searchKey.toString());
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumeMapInTime.put((LocalTime) searchKey, resume);
    }

}

