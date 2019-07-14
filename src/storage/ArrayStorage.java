package storage;

import model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doArrayDelete(int searchKey) {
        storage[searchKey] = storage[size - 1];
    }

    @Override
    protected void doArraySave(Resume resume, int searchKey) {
        storage[size] = resume;
    }

    @Override
    protected Integer getSearchKey(Object uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}