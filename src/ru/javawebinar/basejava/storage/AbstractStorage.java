package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void save(Resume resume) throws IOException {
        LOG.info("Save " + resume);
        SK searchKey = getSearchKey(resume.getUuid());
        if (isValid(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, searchKey);
        }
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getSearchKeyIfNotExist(uuid);
        doDelete(searchKey);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getSearchKeyIfNotExist(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public Resume get(String uuid) throws IOException, ClassNotFoundException {
        LOG.info("Get " + uuid);
        SK searchKey = getSearchKeyIfNotExist(uuid);
        return doGet(searchKey);
    }

    private SK getSearchKeyIfNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isValid(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() throws IOException, ClassNotFoundException {
        List<Resume> resumeList = getList();
        resumeList.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return resumeList;
    }

    protected abstract List<Resume> getList() throws IOException, ClassNotFoundException;

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isValid(SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey) throws StorageException, IOException;

    protected abstract void doDelete(SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract Resume doGet(SK searchKey) throws IOException, ClassNotFoundException;

}
