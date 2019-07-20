package model;

public enum SectionAQEEType {
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    SectionAQEEType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}


