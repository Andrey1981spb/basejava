package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    public Resume doGet(int index) {
        return resumeList.get(index);
    }

    @Override
    public void clear() {
        resumeList.removeAll(resumeList);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    public Resume[] getAll() {
        Resume[] resumes = resumeList.toArray(new Resume[size()]);
        return resumes;
    }

    protected int getIndex(String uuid) {
        for (Resume resume : resumeList) {
            if (uuid.equals(resume.getUuid())) {
                return resumeList.indexOf(resume);
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume resume, int index) {
        resumeList.add(resume);
    }

    @Override
    protected void doDelete(int index) {
        resumeList.remove(index);
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        resumeList.set(index, resume);
    }

}

