package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException, ClassNotFoundException;

    private Path doPath(Path path) {
        return Paths.get(directory.toString() + '/' + path.toString());
    }

    @Override
    protected Path getSearchKey(String uuid) {
        Path path = new File(uuid).toPath();
        System.out.println(path);
        return path;
    }

    @Override
    protected boolean isValid(Path path) {
        return path.toFile().exists();
    }

    @Override
    protected void doSave(Resume resume, Path path) throws StorageException, IOException {
        Path pathFull = doPath(path);
        try {
            if (!Files.exists(pathFull)){
            Files.createFile(pathFull);}
        } catch (IOException e) {
            throw new StorageException("Couldn't create File ", pathFull.getFileName().toString(), e);
        }
        doUpdate(resume, pathFull);
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream((path).toFile())));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        Path pathFull = doPath(path);
        pathFull.toFile().delete();
    }

    @Override
    protected Resume doGet(Path path) throws IOException, ClassNotFoundException {
        Path pathFull = doPath(path);
        try {
            return doRead(new BufferedInputStream(new FileInputStream(pathFull.toFile())));
        } catch (IOException e) {
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
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return resumeList;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(path -> AbstractPathStorage.this.doDelete(path));
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
