
public class ArrayStorage {
    Resume[] storage = new Resume[5];
    int size = 0;

    void clear() {
        size = this.size();
        for (int i = 0; i < size+1; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        size = this.size();
        for (int i = 0; i < size+1; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        size = this.size();
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
        size = this.size();
        for (int i = 0; i <= size + 1; i++) {
            if (storage[i] != null) {
                if ((storage[i].uuid == uuid)) {
                    storage[i] = storage[size];
                    storage[size] = null;
                    break;
                }
            }
        }
    }

    Resume[] getAll() {
        size = this.size();
        Resume[] resumeStorage = new Resume[size];
        int uniqeIndex = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                resumeStorage[uniqeIndex] = storage[i];
                uniqeIndex++;
            }
        }
        return resumeStorage;
    }

    int size() {
        size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                size++;
            }
        }
        return size;
    }
}
