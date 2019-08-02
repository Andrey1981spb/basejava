package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class ResumeTestData {
    private static final Resume resume = new Resume("Grigory Kislin");

    public static void main(String[] args) {
        fillResume();
        checkResume();
    }

    private static void fillResume() {

        Map<ContactType, String> contactInfoMap = resume.getContactInfoMap();

        contactInfoMap.put(ContactType.TELEPHONE, "+7(921) 855-0482");
        contactInfoMap.put(ContactType.SKYPE, "grigory.kislin");
        contactInfoMap.put(ContactType.MAIL, "gkislin@yandex.ru");
        contactInfoMap.put(ContactType.PROFILE_LINKED_IN, "profile LinkedIn");
        contactInfoMap.put(ContactType.PROFILE_GITHUB, "https://github.com/gkislin");
        contactInfoMap.put(ContactType.PROFILE_STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        contactInfoMap.put(ContactType.HOMEPAGE, "http://gkislin.ru/");


        resume.setResumeSections(SectionType.PERSONAL, new SimpleTextSection("Аналитический склад ума, " +
                "сильная логика, креативность, инициативность.Пурист кода и архитектуры."));
        resume.setResumeSections(SectionType.OBJECTIVE, new SimpleTextSection("Ведущий стажировок и " +
                "корпоративного обучения по Java Web и Enterprise технологиям.\n"));

        resume.setResumeSections(SectionType.ACHIEVEMENT, new MarkedListSection("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n"));
        resume.setResumeSections(SectionType.EXPERIENCE, new MarkedListSection("Реализация двухфакторной аутентификации для онлайн платформы " +
                "управления проектами Wrike. Интеграция с Twilio, " +
                "DuoSecurity, Google Authenticator, Jira, Zendesk."));


        resume.setResumeSections(SectionType.EXPERIENCE, new OrganizationSection(new Organization("\"Java Online Projects",
                LocalDate.of(2013, Month.OCTOBER, 30), null, "Автор проекта.\n" +
                "10/2013 - Сейчас\tСоздание, организация и проведение Java онлайн проектов и стажировок.")));


        resume.setResumeSections(SectionType.EXPERIENCE, new OrganizationSection(new Organization("Wrike",
                LocalDate.of(2014, Month.OCTOBER, 30),
                LocalDate.of(2016, Month.JANUARY, 30), "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, " +
                "OAuth2, JWT SSO.")));

    }

    private static void checkResume() {
        System.out.println(resume.getFullName());

        for (Map.Entry<ContactType, String> entry : resume.getContactInfoMap().entrySet()) {
            System.out.println(entry.getValue());
        }

        for (Map.Entry<SectionType, AbstractSection> entry : resume.getResumeSections().entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}
