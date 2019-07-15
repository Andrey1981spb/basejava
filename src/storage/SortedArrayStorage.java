package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public String fullName;

    @Override
    protected void saveToArray(Resume resume, int index) {
        int positionInvert = ~index;
        System.arraycopy(storage, positionInvert, storage, positionInvert + 1, size - positionInvert);
        storage[positionInvert] = resume;
    }

    @Override
    protected void deleteFromArray(int index) {
        System.arraycopy(storage, index + 1, storage, index,
                size - index - 1);
    }

    @Override
    protected Integer getSearchKey(Object uuid) {
        Resume searchKey = new Resume((String) uuid, fullName);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}
