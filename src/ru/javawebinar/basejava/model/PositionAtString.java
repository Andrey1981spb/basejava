package ru.javawebinar.basejava.model;

import java.util.Objects;

public class PositionAtString extends ResumeSections {

    private String position;

    public PositionAtString(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionAtString that = (PositionAtString) o;
        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "PositionAtString{" +
                "position='" + position + '\'' +
                '}';
    }

    @Override
    public String getPosition() {
        return position;
    }

}