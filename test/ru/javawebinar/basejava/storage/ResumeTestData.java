package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class ResumeTestData {
    private static Resume RESUME;

    public static void main(String[] args) {
        getResume("uuid", "Grigory Kislin");
        checkResume();
    }

    public static Resume getResume(String uuid, String fullName) {

        RESUME = new Resume(uuid, fullName);

        RESUME.addContact(ContactType.MAIL, "mail1@ya.ru");
        RESUME.addContact(ContactType.TELEPHONE, "11111");
        RESUME.addContact(ContactType.SKYPE, "skype2");
        RESUME.addContact(ContactType.TELEPHONE, "22222");
        RESUME.addSection(SectionType.OBJECTIVE, new SimpleTextSection("Objective1"));
        RESUME.addSection(SectionType.PERSONAL, new SimpleTextSection("Personal data"));
        RESUME.addSection(SectionType.ACHIEVEMENT, new MarkedListSection("Achivment11", "Achivment12"));
        RESUME.addSection(SectionType.QUALIFICATIONS, new MarkedListSection("Java", "SQL"));
        RESUME.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position("Position2",
                                        LocalDate.of(2013, Month.OCTOBER, 30), null, "Автор проета"),
                                new Organization.Position("Position1",
                                        LocalDate.of(2011, Month.OCTOBER, 30),
                                        LocalDate.of(2013, Month.OCTOBER, 30), "Автор проета")
                        )));

        RESUME.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position("aspirant", LocalDate.of(1996, Month.OCTOBER, 30),
                                        LocalDate.of(2000, Month.OCTOBER, 30),
                                        null),
                                new Organization.Position("student", LocalDate.of(1996, Month.OCTOBER, 30),
                                        LocalDate.of(2000, Month.OCTOBER, 30),
                                        null)),
                        new Organization("Organization12", "http://Organization12.ru")));
        RESUME.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position("Position", LocalDate.of(2008, Month.OCTOBER, 30),
                                        LocalDate.of(2011, Month.OCTOBER, 30),
                                        "content1"))));

        return RESUME;
    }

    private static void checkResume() {
        System.out.println(RESUME.getFullName());

        for (Map.Entry<ContactType, String> entry : RESUME.getContactInfoMap().entrySet()) {
            System.out.println(entry.getValue());
        }

        for (Map.Entry<SectionType, AbstractSection> entry : RESUME.getResumeSections().entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}
