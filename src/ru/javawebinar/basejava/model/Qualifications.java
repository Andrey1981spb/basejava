package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class Qualifications extends ResumeListSections {

    private final List<String> qualificationsList = new ArrayList<>();

    @Override
    public List<String> getData() {
        return qualificationsList;
    }

    public void setQualifications(String qualifications) {
        qualificationsList.add(qualifications);
    }
}
