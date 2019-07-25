package ru.javawebinar.basejava.model;

public class Personal extends ResumeStringSections {

    private String personal;

    @Override
    public String getData() {
        return personal;
    }

    void setPersonal(String input) {
        personal = input;
    }

}