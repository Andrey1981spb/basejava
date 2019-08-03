package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.*;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final Map<File, Resume> resumeFileMap = new HashMap<>();
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(resumeFileMap.values());
    }

    protected File getSearchKey(Object uuid) {
        return new File(directory, (String) uuid);
    }

    @Override
    protected boolean isValid(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }

    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        return resumeFileMap.get(file);
    }

    @Override
    public void clear() {
        resumeFileMap.clear();
    }

    @Override
    public int size() {
        return resumeFileMap.size();
    }
}
