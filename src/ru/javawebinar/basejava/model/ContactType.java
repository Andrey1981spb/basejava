package ru.javawebinar.basejava.model;

public enum ContactType {
    TELEPHONE("Телефон"),
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("skype:" + value, value);
        }
    },
    MAIL("Почта") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("mailto:" + value, value);
        }
    },

    PROFILE_LINKED_IN("Profile LinkedIn"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    PROFILE_GITHUB("Profile GitHub"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    PROFILE_STACKOVERFLOW("Profile GitHub"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    HOMEPAGE("Homepage"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, title);
    }

    public String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }

}
