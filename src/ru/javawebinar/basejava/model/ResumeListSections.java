package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public abstract class ResumeListSections {

    private final List<String> stringList = new ArrayList<>();

    public List<String> getData() {
        return stringList;
    }

}