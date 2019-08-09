package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serealizeUtil.ChoiseSerializer;
import ru.javawebinar.basejava.storage.serealizeUtil.Serializer;
import ru.javawebinar.basejava.storage.serealizeUtil.StreamSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    ChoiseSerializer choiseSerializer = new ChoiseSerializer();
    Serializer serializer = new StreamSerializer();

    protected ObjectStreamPathStorage(String directory) {
        super(directory);
    }
    @Override
    protected void doWrite(Resume resume, OutputStream os) throws IOException {
        choiseSerializer.setSerializer(serializer);
        serializer.outSerialize(resume, os);
    }

    @Override
    protected Resume doRead(InputStream in) throws IOException, ClassNotFoundException {
        choiseSerializer.setSerializer(serializer);
        return serializer.inSerialize(in);
    }
}
