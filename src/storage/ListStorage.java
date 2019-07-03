package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListStorage extends AbstractStorage {

    List<Resume> resumeList = new CopyOnWriteArrayList<>();

    @Override
    public Resume doGet(int index, String uuid) {
        for (Resume resume : resumeList) {
            if (uuid.equals(resume.getUuid())) {
                return resume;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        Iterator<Resume> resumeIterator = resumeList.iterator();
        while (resumeIterator.hasNext()) {
            Resume resume = resumeIterator.next();
            resumeList.remove(resume);
        }
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    public Resume[] getAll() {
        List<Resume> resumeList2 = new ArrayList<>();
        for (Resume resume : resumeList) {
            resumeList2.add(resume);
        }
        Resume[] resumes = resumeList2.toArray(new Resume[size()]);
        return resumes;
    }

    protected int getIndex(String uuid) {
        for (Resume resume : resumeList) {
            if (uuid.equals(resume.getUuid())) {
                return +1;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume resume, int index) {
        resumeList.add(resume);
    }

    @Override
    protected void doDelete(int index, String uuid) {
        Iterator<Resume> resumeIterator = resumeList.iterator();
        while (resumeIterator.hasNext()) {
            Resume resume = resumeIterator.next();
            if (uuid.equals(resume.getUuid())) {
                resumeList.remove(resume);
            }
        }
    }

    @Override
    protected void doUpdate(Resume resume) {
        for (Resume resume1 : resumeList) {
            if (resume1.equals(resume)) {
                resumeList.set(resumeList.indexOf(resume1), resume);
            }
        }
    }

}

