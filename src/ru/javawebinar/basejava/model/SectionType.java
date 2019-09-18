package ru.javawebinar.basejava.model;

public enum SectionType {
    ACHIEVEMENT("Достижения"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    QUALIFICATIONS("Квалификация"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    EXPERIENCE("Опыт работы"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    EDUCATION("Образование"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    PERSONAL("Личные качества"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    OBJECTIVE("Позиция"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };

    private final String title;

    SectionType(String title) {
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


