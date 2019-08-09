package ru.javawebinar.basejava.storage.serealizeUtil;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class StreamSerializer implements Serializer {

    @Override
    public ObjectOutput outSerialize(Resume resume, OutputStream os) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(resume);
        return oos;
    }

    @Override
    public Resume inSerialize(InputStream in) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
