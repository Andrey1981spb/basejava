import java.util.Arrays;

public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int i = getIndex(resume.getUuid());
        if (i != -1) {
            System.out.println("Resume is exist");
        } else if (size == storage.length) {
            System.out.println("storage overflow");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i != -1) {
            return storage[i];
        } else {
            System.out.println("Resume does not exist");
            return null;
        }
    }

    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i != -1) {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume does not exist");
        }
    }

    public void update(Resume resume) {
        int i = getIndex(resume.getUuid());
        if (i != -1) {
            storage[i] = resume;
        } else {
            System.out.println("Resume does not exist");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
