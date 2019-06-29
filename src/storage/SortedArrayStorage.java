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
    protected void doSave(Resume resume, int index) {
        int positionInvert = ~index;
        int j = -positionInvert - 1;
        System.arraycopy(storage, positionInvert, storage, positionInvert + 1, size - j);
        storage[positionInvert] = resume;
    }

    @Override
    protected void doDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}
