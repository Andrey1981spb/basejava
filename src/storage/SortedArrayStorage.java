package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void doArraySave(Resume resume, int index) {
        int positionInvert = ~index;
        System.arraycopy(storage, positionInvert, storage, positionInvert + 1, size - positionInvert);
        storage[positionInvert] = resume;
    }

    @Override
    protected void doArrayDelete(int index, String uuid) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

}
