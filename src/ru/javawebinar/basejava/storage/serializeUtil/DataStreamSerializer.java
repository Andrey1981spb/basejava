package ru.javawebinar.basejava.storage.serializeUtil;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DataStreamSerializer implements Serializer {

    @Override
    public void outSerialize(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContactInfoMap();
            dos.writeInt(contacts.size());
            for (Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            dos.writeUTF(SectionType.OBJECTIVE.toString());
            dos.writeUTF(resume.getSection(SectionType.OBJECTIVE).toString());

            dos.writeUTF(SectionType.PERSONAL.toString());
            dos.writeUTF(resume.getSection(SectionType.PERSONAL).toString());

            dos.writeUTF(SectionType.ACHIEVEMENT.toString());
            List<String> achievementList = new MarkedListSection().getPerformanceList();
            for (String achievement : achievementList) {
                dos.writeUTF(achievement);
            }

            dos.writeUTF(SectionType.QUALIFICATIONS.toString());
            List<String> qualificationsList = new MarkedListSection().getPerformanceList();
            for (String qualification : qualificationsList) {
                dos.writeUTF(qualification);
            }

            dos.writeUTF(SectionType.EXPERIENCE.toString());
            List<Organization> organizationsExperience = new OrganizationSection().getWorkStudyStringDates();
            for (Organization organization : organizationsExperience) {
                dos.writeUTF(organization.getHomePage().toString());
                dos.writeUTF(organization.getPositionList().toString());
            }

            dos.writeUTF(SectionType.EDUCATION.toString());
            List<Organization> organizationsEducation = new OrganizationSection().getWorkStudyStringDates();
            for (Organization organization : organizationsEducation) {
                dos.writeUTF(organization.getHomePage().toString());
                dos.writeUTF(organization.getPositionList().toString());
            }

        }
    }

    @Override
    public Resume inSerialize(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            resume = readToSimpleTextSection(resume, dis);

            resume = readToSimpleTextSection(resume, dis);

            resume = readToMarkedListSection(resume, dis);

            resume = readToMarkedListSection(resume, dis);

            resume = readToOrganizationSection(resume, dis);

            resume = readToOrganizationSection(resume, dis);

            return resume;
        }
    }

    private Resume readToSimpleTextSection(Resume resume, DataInputStream dis) {
        try {
            resume.addSection(SectionType.valueOf(dis.readUTF()), new SimpleTextSection(dis.readUTF()));
        } catch (IOException e) {
            throw new StorageException("Could not read SimpleTextSection", e);
        }
        return resume;
    }

    private Resume readToMarkedListSection(Resume resume, DataInputStream dis) {
        try {
            MarkedListSection markedListSection = new MarkedListSection();
            List<String> performanceList = markedListSection.getPerformanceList();
            for (String achievement : performanceList) {
                achievement = dis.readUTF();
                performanceList.add(achievement);
            }
            resume.addSection(SectionType.valueOf(dis.readUTF()), markedListSection);
        } catch (IOException e) {
            throw new StorageException("Could not read MarkedListSection", e);
        }

        return resume;
    }

    private Resume readToOrganizationSection(Resume resume, DataInputStream dis) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try {
            OrganizationSection organizationSection = new OrganizationSection();
            List<Organization> organizations = organizationSection.getWorkStudyStringDates();
            for (Organization organization : organizations) {
                organization.getHomePage().setName(dis.readUTF());
                organization.getHomePage().setUrl(dis.readUTF());
                for (Organization.Position position : organization.getPositionList()) {
                    position.setTitle(dis.readUTF());
                    position.setDateOfEntry(LocalDate.parse(dis.readUTF(), formatter));
                    position.setDateOfExit(LocalDate.parse(dis.readUTF(), formatter));
                    position.setDescription(dis.readUTF());
                }
            }
        } catch (IOException e) {
            throw new StorageException("Could not read OrganizationSection", e);
        }

        return resume;
    }

}
