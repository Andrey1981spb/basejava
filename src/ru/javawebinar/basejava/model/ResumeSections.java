package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ResumeSections {
    private final List<String> performanseList = new ArrayList<>();
    private final Map<LocalDate, String> workStudyMAP = new HashMap<>();

    public String getPosition() {
        return null;
    }

    public List<String> getPerformanseList() {
        return performanseList;
    }

    public Map<LocalDate, String> getWorkStudyMAP() {
        return workStudyMAP;
    }
}