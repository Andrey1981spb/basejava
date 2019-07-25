package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class Experience extends ResumeListSections {

    private final List<String> experienceList = new ArrayList<>();

    @Override
    public List<String> getData() {
        return experienceList;
    }

    void setExperience(String experience) {
        experienceList.add(experience);
    }

}