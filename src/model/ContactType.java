package model;

public enum ContactType {
    TELEPHONE ("Телефон"),
    SKYPE("Skype"),
    MAIL("mail"),
    PROFILE_LINKED_IN("Profile LinkedIn"),
    PROFILE_GITHUB("Profile GitHub"),
    PROFILE_STACKOVERFLOW ("Profile GitHub"),
    HOMEPAGE("Homepage");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
