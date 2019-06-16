import java.util.Arrays;

public class ArrayStorage {
    private final Resume[] storage = new Resume[3];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void save(Resume resume) {
        int i = compareValue(resume);
        if (i == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Resume is exist");
        }
        if (size == storage.length) {
            System.out.println("storage overflow");
        }
    }

    public Resume get(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        int i = compareValue(resume);
        if (i != -1) {
            return storage[i];
        } else {
            System.out.println("Resume does not exist");
        }
        return null;
    }

    public void delete(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        int i = compareValue(resume);
        if (i != -1) {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume does not exist");
        }
    }

    public void update(Resume resume) {
        int i = compareValue(resume);
        if (i != -1) {
            storage[i] = resume;
        } else {
            System.out.println("Resume does not exist");
        }
    }

    public int compareValue(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(resume)) {
                return i;
            }
        }
        return -1;
    }


    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
