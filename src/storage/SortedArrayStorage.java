package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    private String fullName;

    @Override
    protected void doArraySave(Resume resume, int searchKey) {
        int positionInvert = ~searchKey;
        System.arraycopy(storage, positionInvert, storage, positionInvert + 1, size - positionInvert);
        storage[positionInvert] = resume;
    }

    @Override
    protected void doArrayDelete(int searchKey) {
        System.arraycopy(storage, searchKey + 1, storage, searchKey,
                size - searchKey - 1);
    }

    @Override
    protected Integer getSearchKey(Object uuid) {
        Resume searchKey = new Resume((String) uuid, fullName);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}
