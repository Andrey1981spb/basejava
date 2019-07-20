package model;

public enum SectionPOType {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция");


    private String title;

    SectionPOType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}


