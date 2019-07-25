package ru.javawebinar.basejava.model;

public class Objective extends ResumeStringSections {

    private String objective;

    @Override
    public String getData() {
        return objective;
    }

    void setObjective(String input) {
        objective = input;
    }

}