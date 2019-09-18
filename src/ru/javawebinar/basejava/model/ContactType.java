package ru.javawebinar.basejava.model;

public enum ContactType {
    TELEPHONE("Телефон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    PROFILE_LINKED_IN("Profile LinkedIn"),
    PROFILE_GITHUB("Profile GitHub"),
    PROFILE_STACKOVERFLOW("Profile GitHub"),
    HOMEPAGE("Homepage");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
