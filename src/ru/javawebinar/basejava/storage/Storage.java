package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.util.List;

public interface Storage {

    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid) throws IOException;

    void delete(String uuid);

    int size();

    List<Resume> getAllSorted() throws IOException;
}

