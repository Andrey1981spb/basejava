package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.*;

public class Resume implements Comparable<Resume>, Serializable {

    private String uuid;
    private String fullName;
    private final Map<SectionType, AbstractSection> resumeSections = new EnumMap<>(SectionType.class);
    private final Map<ContactType, String> contactInfoMap = new EnumMap<>(ContactType.class);

    public Resume() {
    }

    public Map<SectionType, AbstractSection> getResumeSections() {
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

    public void setResumeSections(SectionType sectionType, AbstractSection resumeSection) {
        resumeSections.put(sectionType, resumeSection);
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
        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public int compareTo(Resume resume) {
        int cmp = fullName.compareTo(resume.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(resume.uuid);
    }

}
