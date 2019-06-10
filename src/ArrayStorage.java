
public class ArrayStorage {
    Resume[] storage = new Resume[5];
    int f = 0;
    int c = 0;

    void clear() {
        int j;
        for (j = 0; j < storage.length; j++) {
            storage[j] = null;
        }
    }

    void save(Resume r) {
            for (int i = f; i < storage.length; i++) {
                if (storage[i]==null) {
                    storage[i] = r;
                    break;
            }
        }
    }

    Resume get(String uuid) {
        int j;
        for (j = 0; j < storage.length; j++) {
            if (storage[j] != null) {
                if (storage[j].uuid == uuid) {
                    return storage[j];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        int m;
        for (m = c; m < storage.length; m++) {
            if (storage[m] != null) {
                if ((storage[m].uuid == uuid)) {
                    storage[m] = null;
                    f=0;
                    break;
                }
            }
        }
    }

    Resume[] getAll() {
        return storage;
    }

    int size() {
        return storage.length;
    }
}
