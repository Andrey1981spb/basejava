public class ArrayStorage {
    Resume[] storage = new Resume[5];
    int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
            size = 0;
        }
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                if (storage[i].uuid == uuid) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i <= size; i++) {
            if ((storage[i].uuid == uuid)) {
                storage[i] = storage[size-1];
                storage[size-1] = null;
                size--;
                break;
            }
        }
    }

    Resume[] getAll() {
        Resume[] resumeStorage = new Resume[size];
        for (int i = 0; i < size; i++) {
                resumeStorage[i] = storage[i];
        }
        return resumeStorage;
    }

    int size() {
        return size;
    }
}
