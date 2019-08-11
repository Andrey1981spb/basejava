package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serealizeUtil.Serializer;
import ru.javawebinar.basejava.storage.serealizeUtil.StreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private Serializer serializer = new StreamSerializer();

    protected PathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isValid(Path path) {
        return path.toFile().exists();
    }

    @Override
    protected void doSave(Resume resume, Path path) throws StorageException {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create File ", path.getFileName().toString(), e);
        }
        doUpdate(resume, path);
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            serializer.outSerialize(resume, new BufferedOutputStream(new FileOutputStream((path).toFile())));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File delete error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) throws IOException {
        try {
            return serializer.inSerialize(new BufferedInputStream(new FileInputStream(String.valueOf(path))));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Resume> getList() throws IOException {
        List<Resume> resumeList = new ArrayList<>();
        Files.list(directory).forEach(path -> {
            try {
                resumeList.add(doGet(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return resumeList;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        long size;
        try {
            size = Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Path size error", null);
        }
        return (int) size;
    }
}
