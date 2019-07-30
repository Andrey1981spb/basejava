package ru.javawebinar.basejava.model;

public enum SectionType {
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование"),
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция");

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}


