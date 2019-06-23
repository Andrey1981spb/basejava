package storage;

import model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume resume, int index) {
        storage[size] = resume;
        size++;
    }

    @Override
    protected void doDelete(int index) {
        storage[index] = storage[size - 1];
    }
}