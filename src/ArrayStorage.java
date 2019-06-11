
public class ArrayStorage {
    Resume[] storage = new Resume[5];
    int startPointForSave = 0;
    int startPointForDelete = 0;


    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        Resume[] resume_not_empty = this.getAll();
        for (int i = startPointForSave; i < resume_not_empty.length + 1; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume[] resume_not_empty = this.getAll();
        for (int i = 0; i < resume_not_empty.length + 1; i++) {
            if (storage[i] != null) {
                if (storage[i].uuid == uuid) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        Resume[] resume_not_empty = this.getAll();
        for (int i = startPointForDelete; i < resume_not_empty.length + 1; i++) {
            if (storage[i] != null) {
                if ((storage[i].uuid == uuid)) {
                    storage[i] = null;
                    startPointForSave = 0;
                    break;
                }
            }
        }
    }


    Resume[] getAll() {
        int initial_lenght = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                initial_lenght++;
            }
        }

        Resume[] resume_storage = new Resume[initial_lenght];
        int uniqe_imndex = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                resume_storage[uniqe_imndex] = storage[i];
                uniqe_imndex++;
            }
        }
        return resume_storage;
    }


    int size() {
        int resume_count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                resume_count++;
            }
        }
        return resume_count;
    }
}
