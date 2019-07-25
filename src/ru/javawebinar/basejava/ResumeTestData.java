package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    private static List<Resume> resumeList = new ArrayList<>();
    private static Resume resume = new Resume();
    private static List<ResumeListSections> resumeListSections = new ArrayList<>();
    private static List<ResumeStringSections> resumeStringSections = new ArrayList<>();

    public static void main(String[] args) {
        fillResume();
        checkResume();
    }

    private static void fillResume() {

        resumeList.add(resume);

        resume.setContactInfo("+7(921) 855-0482", "grigory.kislin", "gkislin@yandex.ru",
                "profile LinkedIn", "https://github.com/gkislin",
                "https://stackoverflow.com/users/548473/gkislin", "http://gkislin.ru/"
        );

        resume.setResumeSections(SectionType.PERSONAL,"Аналитический склад ума, сильная логика, креативность, инициативность.Пурист кода и архитектуры.");

        Personal personal = resume.getPersonal();

        resumeStringSections.add(personal);

        resume.setResumeSections(SectionType.OBJECTIVE, "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям.\n");

        Objective objective = resume.getObjective();

        resumeStringSections.add(objective);

        resume.setResumeSections(SectionType.ACHIEVEMENT, "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        resume.setResumeSections(SectionType.ACHIEVEMENT, "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, " +
                "DuoSecurity, Google Authenticator, Jira, Zendesk.");
        resume.setResumeSections(SectionType.ACHIEVEMENT, "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO " +
                "аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        resume.setResumeSections(SectionType.ACHIEVEMENT, "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), " +
                "Commet, HTML5, Highstock для алгоритмического трейдинга.");
        resume.setResumeSections(SectionType.ACHIEVEMENT, "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, " +
                "JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        resume.setResumeSections(SectionType.ACHIEVEMENT, "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, " +
                "Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");

        Achievment achievment = resume.getAchievment();

        resumeListSections.add(achievment);

        resume.setResumeSections(SectionType.QUALIFICATIONS, "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "XML / XSD / XSLT, SQL, C / C++, Unix shell scripts");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "Java Frameworks:Java 8 (Time API, Streams),Guava, Java, Executor, MyBatis, Spring(MVC, Security, Data, Clouds, Boot)," +
                " JPA(Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT / GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium(htmlelements).");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "Python:Django.");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "JavaScript:jQuery, ExtJS, Bootstrap.js, underscore.js");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "Технологии: Servlet, JSP / JSTL, JAX - WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, " +
                "JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "Инструменты:Maven + plugin development, Gradle, настройка Ngnix,");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "администрирование Hudson / Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        resume.setResumeSections(SectionType.QUALIFICATIONS, "Родной русский, английский upper intermediate\n");

        Qualifications qualifications = resume.getQualifications();

        resumeListSections.add(qualifications);

        resume.setResumeSections(SectionType.EXPERIENCE, "Java Online Projects\n" +
                "10/2013 - Сейчас\tАвтор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        resume.setResumeSections(SectionType.EXPERIENCE, "Wrike\n" +
                "10/2014 - 01/2016\tСтарший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        resume.setResumeSections(SectionType.EXPERIENCE, "RIT Center\n" +
                "04/2012 - 10/2014\tJava архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), " +
                "сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, " +
                "Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        resume.setResumeSections(SectionType.EXPERIENCE, "Luxoft (Deutsche Bank)\n" +
                "12/2010 - 04/2012\tВедущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной " +
                "части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. " +
                "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        resume.setResumeSections(SectionType.EXPERIENCE, "Yota\n" +
                "06/2008 - 12/2010\tВедущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        resume.setResumeSections(SectionType.EXPERIENCE, "Enkata\n" +
                "03/2007 - 06/2008\tРазработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        resume.setResumeSections(SectionType.EXPERIENCE, "Siemens AG\n" +
                "01/2005 - 02/2007\tРазработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        resume.setResumeSections(SectionType.EXPERIENCE, "Alcatel\n" +
                "09/1997 - 01/2005\tИнженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).\n");

        Experience experience = resume.getExperience();

        resumeListSections.add(experience);

        resume.setResumeSections(SectionType.EDUCATION, "Coursera\n" +
                "03/2013 - 05/2013\t\"Functional Programming Principles in Scala\" by Martin Odersky");
        resume.setResumeSections(SectionType.EDUCATION, "Luxoft\n" +
                "03/2011 - 04/2011\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        resume.setResumeSections(SectionType.EDUCATION, "Siemens AG\n" +
                "01/2005 - 04/2005\t3 месяца обучения мобильным IN сетям (Берлин)");
        resume.setResumeSections(SectionType.EDUCATION, "Alcatel\n" +
                "09/1997 - 03/1998\t6 месяцев обучения цифровым телефонным сетям (Москва)");
        resume.setResumeSections(SectionType.EDUCATION, "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики\n" +
                "09/1993 - 07/1996\tАспирантура (программист С, С++)\n" +
                "09/1987 - 07/1993\tИнженер (программист Fortran, C)");
        resume.setResumeSections(SectionType.EDUCATION, "Заочная физико-техническая школа при МФТИ\n" +
                "09/1984 - 06/1987\tЗакончил с отличием");

        Education education = resume.getEducation();

        resumeListSections.add(education);
    }

    private static void checkResume() {
        System.out.println(resume.getFullName());

        for (Resume resume : resumeList) {
            System.out.println(resume.getFullName());

            for (ContactType contactType : ContactType.values()) {
                String contact = getContact(resume, contactType);
                System.out.println(contact);
            }

            for (ResumeStringSections resumeSection : resumeStringSections) {
                String sectionData = resumeSection.getData();
                System.out.println(sectionData);
            }

            for (ResumeListSections resumeSection : resumeListSections) {
                List<String> sectionData = resumeSection.getData();
                for (String section : sectionData) {
                    System.out.println(section);
                }
            }

        }
    }

    private static String getContact(Resume resume, ContactType contactType) {
        switch (contactType) {
            case TELEPHONE:
                return resume.getContactInfo().getTelephone();

            case SKYPE:
                return resume.getContactInfo().getSkype();

            case MAIL:
                return resume.getContactInfo().getMail();

            case PROFILE_LINKED_IN:
                return resume.getContactInfo().getProfileLinkedIn();

            case PROFILE_GITHUB:
                return resume.getContactInfo().getProfileGitHub();

            case PROFILE_STACKOVERFLOW:
                return resume.getContactInfo().getProfileStackoverflow();

            case HOMEPAGE:
                return resume.getContactInfo().getHomepage();
        }
        return null;
    }

}
