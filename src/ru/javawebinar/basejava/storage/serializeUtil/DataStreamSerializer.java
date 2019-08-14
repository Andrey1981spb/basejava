package ru.javawebinar.basejava.storage.serializeUtil;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

public class DataStreamSerializer implements Serializer {

    @Override
    public void outSerialize(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContactInfoMap();
            dos.writeInt(contacts.size());
            for (Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = r.getResumeSections();
            for (Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                int fieldsCount = getFields(entry).length;
                dos.writeInt(fieldsCount);
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF((Arrays.toString(getFields(entry))));
            }
        }
    }

    @Override
    public Resume inSerialize(InputStream is) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int length = dis.readInt();
            toAddSections(length, dis, new SimpleTextSection(dis.readUTF()));

            length = dis.readInt();
            toAddSections(length, dis, new MarkedListSection(dis.readUTF()));

            length = dis.readInt();
            toAddSections(length, dis, new OrganizationSection(
                    new Organization(dis.readUTF(), dis.readUTF(),
                            new Organization.Position(dis.readUTF(), LocalDate.parse(dis.readUTF(), formatter),
                                    LocalDate.parse(dis.readUTF(), formatter), dis.readUTF())
                    )));

            return resume;
        }
    }

    private Field[] getFields(Entry entry) {
        return entry.getValue().getClass().getDeclaredFields();
    }

    private void toAddSections(int length, DataInputStream dis, AbstractSection abstractSection) {
        Arrays.stream(new Resume[length]).forEach(resume1 -> {
            try {
                resume1.addSections(SectionType.valueOf(dis.readUTF()), abstractSection);
            } catch (IOException e) {
                throw new StorageException("Could not read for sections", e);
            }
        });
    }

}
