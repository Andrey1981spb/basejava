package storage;

import model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteFromArray(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        super.doUpdate(resume, index);
    }

    @Override
    protected void saveToArray(Resume resume, int index) {
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