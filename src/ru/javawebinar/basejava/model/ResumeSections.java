package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ResumeSections {
    private final List<String> outputList = new ArrayList<>();
    private final Map<LocalDate, String> outputMap = new HashMap<>();

    public String getPersonal() {
        return null;
    }

    public String getObjective() {
        return null;
    }

    public List<String> getAchievementList() {
        return outputList;
    }

    public List<String> getQualificationsList() {
        return outputList;
    }

    public Map<LocalDate, String> getExperienceMap() {
        return outputMap;
    }

    public Map<LocalDate, String> getEducationMap() {
        return outputMap;
    }
}