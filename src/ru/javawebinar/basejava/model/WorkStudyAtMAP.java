package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WorkStudyAtMAP extends ResumeSections {

    private final Map<LocalDate, String> experienceMap = new HashMap<>();
    private final Map<LocalDate, String> educationMap = new HashMap<>();

    @Override
    public Map<LocalDate, String> getExperienceMap() {
        return experienceMap;
    }

    @Override
    public Map<LocalDate, String> getEducationMap() {
        return educationMap;
    }

    public void setExperienceMap(LocalDate localDate, String experience) {
        experienceMap.put(localDate, experience);
    }

    public void setEducationMap(LocalDate localDate, String education) {
        educationMap.put(localDate, education);
    }
}