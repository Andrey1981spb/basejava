package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.storage.serializeUtil.DataStreamSerializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@XmlRootElement
@XmlAccessorType ( XmlAccessType.FIELD )
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;

    private String uuid;
    private String fullName;

    private final Map<SectionType, AbstractSection> resumeSections = new EnumMap<>(SectionType.class);
    private final Map<ContactType, String> contactInfoMap = new EnumMap<>(ContactType.class);

    public Map<SectionType, AbstractSection> getResumeSections() {
        return resumeSections;
    }

    public Map<ContactType, String> getContactInfoMap() {
        return contactInfoMap;
    }

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getContact(ContactType type) {
        return contactInfoMap.get(type);
    }

    public AbstractSection getSection(SectionType type) {
        return resumeSections.get(type);
    }

    public void addSection(SectionType sectionType, AbstractSection resumeSection) {
        resumeSections.put(sectionType, resumeSection);
    }

    public void addContact(ContactType type, String value) {
        contactInfoMap.put(type, value);
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
        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName) &&
                resumeSections.equals(resume.resumeSections) &&
                contactInfoMap.equals(resume.contactInfoMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, resumeSections, contactInfoMap);
    }

    @Override
    public int compareTo(Resume resume) {
        int cmp = fullName.compareTo(resume.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(resume.uuid);
    }

}
