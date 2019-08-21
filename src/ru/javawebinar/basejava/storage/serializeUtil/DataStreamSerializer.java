package ru.javawebinar.basejava.storage.serializeUtil;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

            Map<SectionType, AbstractSection> sections = resume.getResumeSections();
            dos.writeInt(sections.size());
            for (Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(sectionType.name());

                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((SimpleTextSection) section).getPosition());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> performanceList = ((MarkedListSection) section).getPerformanceList();
                        dos.writeInt(performanceList.size());
                        for (String performance : performanceList) {
                            dos.writeUTF(performance);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = ((OrganizationSection) section).getWorkStudyStringDates();
                        dos.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            List<Organization.Position> positionList = organization.getPositionList();
                            dos.writeInt(positionList.size());
                            for (Organization.Position position : positionList) {
                                dos.writeUTF(position.getTitle());
                                doWriteLocalDate(dos, position.getDateOfEntry());
                                doWriteLocalDate(dos, position.getDateOfExit());
                                dos.writeUTF(position.getDescription());
                            }
                        }
                        break;
                }
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
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, doReadSections(dis, sectionType));
            }
            return resume;
        }
    }

    private AbstractSection doReadSections(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new SimpleTextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                int size = dis.readInt();
                List<String> stringList = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    stringList.add(dis.readUTF());
                }
                return new MarkedListSection(stringList);
            case EXPERIENCE:
            case EDUCATION:
                size = dis.readInt();
                List<Organization> organizations = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    organizations.add(
                            new Organization(new Link(dis.readUTF(), dis.readUTF()),
                                    readPosition(dis)
                            )
                    );
                    return new OrganizationSection(organizations);
                }
        }
        return null;
    }

    private List<Organization.Position> readPosition(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Organization.Position> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new Organization.Position(dis.readUTF(),
                    doReadLocalDate(dis), doReadLocalDate(dis),
                    dis.readUTF()
            ));
        }
        return list;
    }

    private void doWriteLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
        dos.writeInt(localDate.getDayOfWeek().getValue());
    }

    private LocalDate doReadLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }

}