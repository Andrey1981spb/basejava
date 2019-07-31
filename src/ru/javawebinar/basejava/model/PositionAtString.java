package ru.javawebinar.basejava.model;

public class PositionAtString extends ResumeSections {

    private String position;

    @Override
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}