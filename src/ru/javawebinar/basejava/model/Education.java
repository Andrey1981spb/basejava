package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class Education extends ResumeListSections {

    private final List<String> educationList = new ArrayList<>();

    @Override
    public List<String> getData() {
        return educationList;
    }

    void setEducation(String education) {
        educationList.add(education);
    }
}