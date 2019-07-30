package ru.javawebinar.basejava.model;

import java.util.*;

public class Resume {

    private String uuid;
    private String fullName;
    private final Map<SectionType, ResumeSections> resumeSections = new HashMap<>();
    private final Map<ContactType, String> contactInfoMap = new HashMap<>();

    public Resume() {
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<SectionType, ResumeSections> getResumeSections() {
        return resumeSections;
    }

    public Map<ContactType, String> getContactInfoMap() {
        return contactInfoMap;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

}
