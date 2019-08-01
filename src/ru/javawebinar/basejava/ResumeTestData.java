package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class ResumeTestData {
    private static final Resume resume = new Resume();

    private static final Map<SectionType, ResumeSections> resumeSections = resume.getResumeSections();
/*
    public static void main(String[] args) {
        fillResume();
        checkResume();
    }

    private static void fillResume() {

        resume.setFullName("Grigory Kislin");

        Map<ContactType, String> contactInfoMap = resume.getContactInfoMap();

        contactInfoMap.put(ContactType.TELEPHONE, "+7(921) 855-0482");
        contactInfoMap.put(ContactType.SKYPE, "grigory.kislin");
        contactInfoMap.put(ContactType.MAIL, "gkislin@yandex.ru");
        contactInfoMap.put(ContactType.PROFILE_LINKED_IN, "profile LinkedIn");
        contactInfoMap.put(ContactType.PROFILE_GITHUB, "https://github.com/gkislin");
        contactInfoMap.put(ContactType.PROFILE_STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        contactInfoMap.put(ContactType.HOMEPAGE, "http://gkislin.ru/");


        personal.setPosition("Аналитический склад ума, сильная логика, креативность, инициативность.Пурист кода и архитектуры.");

        objective.setPosition("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям.\n");

        resumeSections.put(SectionType.PERSONAL, personal);
        resumeSections.put(SectionType.EDUCATION, objective);


        achievement.setPerformanseList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n");
        achievement.setPerformanseList("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, " +
                "DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.setPerformanseList("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO " +
                "аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievement.setPerformanseList("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), " +
                "Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievement.setPerformanseList("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, " +
                "JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievement.setPerformanseList("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, " +
                "Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");


        qualifications.setPerformanseList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.setPerformanseList("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.setPerformanseList("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.setPerformanseList("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualifications.setPerformanseList("XML / XSD / XSLT, SQL, C / C++, Unix shell scripts");
        qualifications.setPerformanseList("Java Frameworks:Java 8 (Time API, Streams),Guava, Java, Executor, MyBatis, Spring(MVC, Security, Data, Clouds, Boot)," +
                " JPA(Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT / GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium(htmlelements).");
        qualifications.setPerformanseList("Python:Django.");
        qualifications.setPerformanseList("JavaScript:jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.setPerformanseList("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.setPerformanseList("Технологии: Servlet, JSP / JSTL, JAX - WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, " +
                "JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.setPerformanseList("Инструменты:Maven + plugin development, Gradle, настройка Ngnix,");
        qualifications.setPerformanseList("администрирование Hudson / Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualifications.setPerformanseList("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualifications.setPerformanseList("Родной русский, английский upper intermediate\n");


        resumeSections.put(SectionType.ACHIEVEMENT, achievement);
        resumeSections.put(SectionType.QUALIFICATIONS, qualifications);


        experience.setWorkStudyMAP(LocalDate.of(2013, Month.OCTOBER, 30), "Java Online Projects, Автор проекта.\n" +
                "10/2013 - Сейчас\tСоздание, организация и проведение Java онлайн проектов и стажировок.");
        experience.setWorkStudyMAP(LocalDate.of(2016, Month.JANUARY, 30), "Wrike\n" +
                "10/2014 - 01/2016\tСтарший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        experience.setWorkStudyMAP(LocalDate.of(2014, Month.MARCH, 30), "RIT Center\n" +
                "04/2012 - 10/2014\tJava архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), " +
                "сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, " +
                "Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        experience.setWorkStudyMAP(LocalDate.of(2012, Month.APRIL, 30), "Luxoft (Deutsche Bank)\n" +
                "12/2010 - 04/2012\tВедущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной " +
                "части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. " +
                "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        experience.setWorkStudyMAP(LocalDate.of(2010, Month.DECEMBER, 30), "Yota\n" +
                "06/2008 - 12/2010\tВедущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        experience.setWorkStudyMAP(LocalDate.of(2008, Month.JUNE, 30), "Enkata\n" +
                "03/2007 - 06/2008\tРазработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        experience.setWorkStudyMAP(LocalDate.of(2007, Month.FEBRUARY, 28), "Siemens AG\n" +
                "01/2005 - 02/2007\tРазработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        experience.setWorkStudyMAP(LocalDate.of(2005, Month.JANUARY, 30), "Alcatel\n" +
                "09/1997 - 01/2005\tИнженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).\n");


        education.setWorkStudyMAP(LocalDate.of(2013, Month.MAY, 30), "Coursera\n" +
                "03/2013 - 05/2013\t\"Functional Programming Principles in Scala\" by Martin Odersky");
        education.setWorkStudyMAP(LocalDate.of(2011, Month.APRIL, 30), "Luxoft\n" +
                "03/2011 - 04/2011\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        education.setWorkStudyMAP(LocalDate.of(2005, Month.APRIL, 30), "Siemens AG\n" +
                "01/2005 - 04/2005\t3 месяца обучения мобильным IN сетям (Берлин)");
        education.setWorkStudyMAP(LocalDate.of(1998, Month.MARCH, 30), "Alcatel\n" +
                "09/1997 - 03/1998\t6 месяцев обучения цифровым телефонным сетям (Москва)");
        education.setWorkStudyMAP(LocalDate.of(1996, Month.JULY, 30), "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики\n" +
                "09/1993 - 07/1996\tАспирантура (программист С, С++)\n");
        education.setWorkStudyMAP(LocalDate.of(1993, Month.JULY, 30),
                "09/1987 - 07/1993\tИнженер (программист Fortran, C)");
        education.setWorkStudyMAP(LocalDate.of(1987, Month.JUNE, 30), "Заочная физико-техническая школа при МФТИ\n" +
                "09/1984 - 06/1987\tЗакончил с отличием");

        resumeSections.put(SectionType.EXPERIENCE, experience);
        resumeSections.put(SectionType.EDUCATION, education);
    }

    private static void checkResume() {
        System.out.println(resume.getFullName());

        for (Map.Entry<ContactType, String> entry : resume.getContactInfoMap().entrySet()) {
            System.out.println(entry.getValue());
        }

        for (Map.Entry<SectionType, ResumeSections> entry : resumeSections.entrySet()) {
            List<String> sections = getDataFromSection(entry.getKey(), entry.getValue());
            assert sections != null;
            for (String section : sections) {
                System.out.println(section);
            }

        }

    }

    private static List<String> getDataFromSection(SectionType sectionBlock, ResumeSections resumeSections) {

        List<String> outputList = new ArrayList<>();

        if ((sectionBlock==SectionType.PERSONAL)||(sectionBlock==SectionType.OBJECTIVE)){
            outputList.add(resumeSections.getPosition());
            return outputList;
        } else if ((sectionBlock==SectionType.ACHIEVEMENT)||(sectionBlock==SectionType.QUALIFICATIONS)){
            outputList = resumeSections.getPerformanseList();
            return outputList;
        } else if ((sectionBlock==SectionType.EXPERIENCE)||(sectionBlock==SectionType.EDUCATION)){
            outputList = new ArrayList<>(resumeSections.getWorkStudyMAP().values());
            return outputList;
        }

        return null;
    }
*/
}
