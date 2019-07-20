import model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    static List<Resume> resumeList = new ArrayList<>();
    static Resume resume = new Resume();

    public static void main(String[] args) {
        fillResume();
        checkResume();
    }

    static void fillResume() {
        resumeList.add(resume);

        resume.setContactInfo("+7(921) 855-0482", "grigory.kislin", "gkislin@yandex.ru",
                "profile LinkedIn", "https://github.com/gkislin",
                "https://stackoverflow.com/users/548473/gkislin", "http://gkislin.ru/"
        );

        resume.setPersonalAndObjective("Аналитический склад ума, сильная логика, креативность, инициативность.Пурист кода и архитектуры.",
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям.\n");

        resume.setAchievement("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        resume.setAchievement("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, " +
                "DuoSecurity, Google Authenticator, Jira, Zendesk.");
        resume.setAchievement("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO " +
                "аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        resume.setAchievement("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), " +
                "Commet, HTML5, Highstock для алгоритмического трейдинга.");
        resume.setAchievement("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, " +
                "JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        resume.setAchievement("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, " +
                "Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");


        resume.setQualifications("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        resume.setQualifications("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        resume.setQualifications("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        resume.setQualifications("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        resume.setQualifications("XML / XSD / XSLT, SQL, C / C++, Unix shell scripts");
        resume.setQualifications("Java Frameworks:Java 8 (Time API, Streams),Guava, Java, Executor, MyBatis, Spring(MVC, Security, Data, Clouds, Boot)," +
                " JPA(Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT / GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium(htmlelements).");
        resume.setQualifications("Python:Django.");
        resume.setQualifications("JavaScript:jQuery, ExtJS, Bootstrap.js, underscore.js");
        resume.setQualifications("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        resume.setQualifications("Технологии: Servlet, JSP / JSTL, JAX - WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, " +
                "JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        resume.setQualifications("Инструменты:Maven + plugin development, Gradle, настройка Ngnix,");
        resume.setQualifications("администрирование Hudson / Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        resume.setQualifications("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        resume.setQualifications("Родной русский, английский upper intermediate\n");

        resume.setExperience("Java Online Projects\n" +
                "10/2013 - Сейчас\tАвтор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        resume.setExperience("Wrike\n" +
                "10/2014 - 01/2016\tСтарший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        resume.setExperience("RIT Center\n" +
                "04/2012 - 10/2014\tJava архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), " +
                "сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, " +
                "Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        resume.setExperience("Luxoft (Deutsche Bank)\n" +
                "12/2010 - 04/2012\tВедущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной " +
                "части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. " +
                "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        resume.setExperience("Yota\n" +
                "06/2008 - 12/2010\tВедущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        resume.setExperience("Enkata\n" +
                "03/2007 - 06/2008\tРазработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        resume.setExperience("Siemens AG\n" +
                "01/2005 - 02/2007\tРазработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        resume.setExperience("Alcatel\n" +
                "09/1997 - 01/2005\tИнженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).\n");

        resume.setEducation("Coursera\n" +
                "03/2013 - 05/2013\t\"Functional Programming Principles in Scala\" by Martin Odersky");
        resume.setEducation("Luxoft\n" +
                "03/2011 - 04/2011\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        resume.setEducation("Siemens AG\n" +
                "01/2005 - 04/2005\t3 месяца обучения мобильным IN сетям (Берлин)");
        resume.setEducation("Alcatel\n" +
                "09/1997 - 03/1998\t6 месяцев обучения цифровым телефонным сетям (Москва)");
        resume.setEducation("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики\n" +
                "09/1993 - 07/1996\tАспирантура (программист С, С++)\n" +
                "09/1987 - 07/1993\tИнженер (программист Fortran, C)");
        resume.setEducation("Заочная физико-техническая школа при МФТИ\n" +
                "09/1984 - 06/1987\tЗакончил с отличием");
    }

    static void checkResume() {
        System.out.println(resume.getFullName());

        for (Resume resume : resumeList) {
            System.out.println(resume.getFullName());

            for (ContactType contactType : ContactType.values()) {
                String contact = getContact(resume, contactType);
                System.out.println(contact);
            }
            for (SectionPOType sectionType : SectionPOType.values()) {
                String sectionString = getSectionString(resume, sectionType);
                System.out.println(sectionString);
            }

            for (SectionAQEEType sectionType : SectionAQEEType.values()) {
                List<String> sectionList = getSectionList(resume, sectionType);
                for (String section : sectionList) {
                    System.out.println(section);
                }
            }

        }
    }

    static String getContact(Resume resume, ContactType contactType) {
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

    static String getSectionString(Resume resume, SectionPOType sectionType) {
        switch (sectionType) {
            case PERSONAL:
                return resume.getPersonalAndObjective().getPersonal();

            case OBJECTIVE:
                return resume.getPersonalAndObjective().getObjective();
        }
        return null;
    }

    static List<String> getSectionList(Resume resume, SectionAQEEType sectionType) {
        List<String> list;
        switch (sectionType) {
            case ACHIEVEMENT:
                list = resume.getExpEduAchievQualifications().getAchievementList();
                break;
            case QUALIFICATIONS:
                list = resume.getExpEduAchievQualifications().getQualificationsList();
                break;

            case EXPERIENCE:
                list = resume.getExpEduAchievQualifications().getExperienceList();
                break;

            case EDUCATION:
                list = resume.getExpEduAchievQualifications().getEducationList();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sectionType);
        }
        return list;
    }

}
