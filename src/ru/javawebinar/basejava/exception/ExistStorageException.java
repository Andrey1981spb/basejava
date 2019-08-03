package ru.javawebinar.basejava.exception;

import java.io.IOException;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }
}
