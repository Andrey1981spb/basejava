package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

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
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "Grigory Kislin");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

}
