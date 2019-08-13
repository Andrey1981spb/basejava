package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serealizeUtil.Serializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private Serializer serializer;

    protected PathStorage(String dir, Serializer serializer) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.serializer = serializer;
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
            throw new StorageException("Path write error ", resume.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File delete error ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializer.inSerialize(new BufferedInputStream(new FileInputStream(String.valueOf(path))));
        } catch (IOException e) {
            throw new StorageException("Error get path ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> getList() {
        return getDirectory().map(path -> doGet(path)).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getDirectory().forEach(this::doDelete);
    }

    @Override
    public int size() {
        long size;
        size = getDirectory().count();
        return (int) size;
    }

    private Stream<Path> getDirectory() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Get directory error", null);
        }
    }
}
