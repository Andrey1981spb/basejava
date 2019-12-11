package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static ru.javawebinar.basejava.util.DateUtil.NOW;

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

        RESUME.addSection(SectionType.OBJECTIVE, new SimpleTextSection("Objective1"));
        RESUME.addSection(SectionType.PERSONAL, new SimpleTextSection("Personal data"));
        RESUME.addSection(SectionType.ACHIEVEMENT, new MarkedListSection("Achievement11", "Achievement12"));
        RESUME.addSection(SectionType.QUALIFICATIONS, new MarkedListSection("Java", "SQL"));
        RESUME.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position("Position2",2013, Month.OCTOBER,2019, Month.OCTOBER,
                                        "Автор проета"),
                                new Organization.Position("Position1",2011, Month.OCTOBER,2013, Month.OCTOBER,
                                        "Разработчик")
                        )));

        RESUME.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", "http://Organization2.ru",
                                new Organization.Position("aspirant",1996, Month.OCTOBER,2000, Month.OCTOBER,
                                        "aspirant"),
                                new Organization.Position("student",1993, Month.OCTOBER,1996, Month.NOVEMBER,
                                        "student"))
                ));

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
