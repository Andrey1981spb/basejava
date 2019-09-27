package ru.javawebinar.basejava.model;

import java.util.Objects;

public class SimpleTextSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    public static final SimpleTextSection EMPTY = new SimpleTextSection("");

    private String position;

    public SimpleTextSection() {
    }

    public SimpleTextSection(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleTextSection that = (SimpleTextSection) o;
        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return position;
    }

}