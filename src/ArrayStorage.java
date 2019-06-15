import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[5];
    int size;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == r.uuid) {
                System.out.println("Resume is exist");
                return;
            }
        }
        storage[size] = r;
        size++;

        String overFlow = (storage.length == size) ? "storage overflow" : storage.length - size + " cells are available";
        System.out.println(overFlow);
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
            checkElement(i,null);
        }
        return null;
    }

    void checkElement (int element, Resume r) {
        if (storage[element]!=r){
            if (element==size-1){
                System.out.println("Resume does not exist");
            }
        }

    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            }
            checkElement(i,null);
        }
    }

    void update (Resume r) {
        for (int i = 0; i < size; i++){
            if (storage[i].uuid==r.uuid){
                storage[i] = r;
                break;
            }
            checkElement(i, r);
        }
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage,size);
    }

    int size() {
        return size;
    }
}
