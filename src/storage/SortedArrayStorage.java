package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doArraySave(Resume resume, Object searchKey) {
        int positionInvert = ~(Integer) searchKey;
        System.arraycopy(storage, positionInvert, storage, positionInvert + 1, size - positionInvert);
        storage[positionInvert] = resume;
    }

    @Override
    protected void doArrayDelete(Object searchKey) {
        System.arraycopy(storage, (Integer) searchKey + 1, storage, (Integer) searchKey,
                size - (Integer) searchKey - 1);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}
