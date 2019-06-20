package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            Resume searchKey = new Resume();
            searchKey.setUuid(uuid);
            int position = Arrays.binarySearch(storage, 0, size, searchKey);
            storage[position] = null;
            System.arraycopy(storage, position + 1, storage, position, size - position + 1);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume " + resume.getUuid() + " not exist");
        } else {
            Resume searchKey = new Resume();
            searchKey.getUuid();
            int position = Arrays.binarySearch(storage, 0, size, searchKey);
            storage[position] = resume;
        }
    }


    @Override
    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            Resume searchKey = new Resume();
            searchKey.getUuid();
            int position = Arrays.binarySearch(storage, 0, size, searchKey);
            storage[position] = resume;
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
