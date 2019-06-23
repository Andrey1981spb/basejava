package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void extendStorage(Resume resume) {
        int position = getIndex(resume.getUuid());
        int positionInvert = ~ position;
        size++;
        System.arraycopy(storage, positionInvert, storage, positionInvert + 1, size - positionInvert);
        storage[positionInvert] = resume;
    }

    @Override
    protected void decreaseStorage(String uuid) {
        int position = getIndex(uuid);
        System.arraycopy(storage, position + 1, storage, position, size - position + 1);
    }
}
