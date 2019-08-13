package ru.javawebinar.basejava.storage.serializeUtil;

import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public interface Serializer {

    ObjectOutput outSerialize(Resume resume, OutputStream os) throws IOException;

    Resume inSerialize(InputStream in) throws IOException;

}
