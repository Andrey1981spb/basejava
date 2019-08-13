package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class ResumeTestData {
  private static final Resume RESUME = new Resume();

    public static void main(String[] args) {
        getRESUME("uuid", "Grigory Kislin");
        checkResume();
    }

    public static Resume getRESUME(String uuid, String fullName) {

        RESUME.setUuid(uuid);
        RESUME.setFullName(fullName);

        Map<ContactType, String> contactInfoMap = RESUME.getContactInfoMap();

        contactInfoMap.put(ContactType.TELEPHONE, "+7(921) 855-0482");
        contactInfoMap.put(ContactType.SKYPE, "grigory.kislin");
        contactInfoMap.put(ContactType.MAIL, "gkislin@yandex.ru");
        contactInfoMap.put(ContactType.PROFILE_LINKED_IN, "profile LinkedIn");
        contactInfoMap.put(ContactType.PROFILE_GITHUB, "https://github.com/gkislin");
        contactInfoMap.put(ContactType.PROFILE_STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        contactInfoMap.put(ContactType.HOMEPAGE, "http://gkislin.ru/");


        RESUME.setResumeSections(SectionType.PERSONAL, new SimpleTextSection("Аналитический склад ума, " +
                "сильная логика, креативность, инициативность.Пурист кода и архитектуры."));
        RESUME.setResumeSections(SectionType.OBJECTIVE, new SimpleTextSection("Ведущий стажировок и " +
                "корпоративного обучения по Java Web и Enterprise технологиям.\n"));

        RESUME.setResumeSections(SectionType.ACHIEVEMENT,
                new MarkedListSection("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                        "\"Java Enterprise\", Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                        "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n"));
        RESUME.setResumeSections(SectionType.ACHIEVEMENT,
                new MarkedListSection("Реализация двухфакторной аутентификации для онлайн платформы " +
                        "управления проектами Wrike. Интеграция с Twilio, " +
                        "DuoSecurity, Google Authenticator, Jira, Zendesk."));

        RESUME.setResumeSections(SectionType.QUALIFICATIONS,
                new MarkedListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"));
        RESUME.setResumeSections(SectionType.QUALIFICATIONS,
                new MarkedListSection("Version control: Subversion, Git, Mercury, ClearCase, Perforce"));


        RESUME.setResumeSections(SectionType.EXPERIENCE, fillExperience());

        RESUME.setResumeSections(SectionType.EDUCATION, fillEducation());

        return RESUME;

    }

    private static OrganizationSection fillExperience() {
        List<Organization> organizations = new ArrayList<>();

        Position position1 = new Position("Java Online Projects",
                LocalDate.of(2013, Month.OCTOBER, 30), null, "Автор проекта.\n" +
                "10/2013 - Сейчас\tСоздание, организация и проведение Java онлайн проектов и стажировок.");

        Organization organization1 = new Organization("Java Online Projects",
                "http://javaops.ru/", new ArrayList<Position>() {{
            add(position1);
        }});

        Position position2 = new Position("Java Online Projects",
                LocalDate.of(2013, Month.OCTOBER, 30), null, "Автор проекта.\n" +
                "10/2013 - Сейчас\tСоздание, организация и проведение Java онлайн проектов и стажировок.");

        Organization organization2 = new Organization("Java Online Projects",
                "http://javaops.ru/", new ArrayList<Position>() {{
            add(position2);
        }});

        organizations.add(organization1);
        organizations.add(organization2);

        return new OrganizationSection(organizations);

    }


    private static OrganizationSection fillEducation() {
        List<Organization> organizations = new ArrayList<>();

        Position position1 = new Position("Аспирантура (программист С, С++)",
                LocalDate.of(1993, Month.SEPTEMBER, 30),
                LocalDate.of(1996, Month.JULY, 30),
                null);

        Position position2 = new Position("Инженер (программист Fortran, C)",
                LocalDate.of(1987, Month.SEPTEMBER, 30),
                LocalDate.of(1993, Month.JULY, 30),
                null);

        Organization organization1 = new Organization("Санкт-Петербургский национальный исследовательский " +
                "университет информационных технологий, механики и оптики", "http://www.ifmo.ru/ru/", new ArrayList<Position>() {{
            add(position1);
            add(position2);
        }});

        organizations.add(organization1);

        return new OrganizationSection(organizations);
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
