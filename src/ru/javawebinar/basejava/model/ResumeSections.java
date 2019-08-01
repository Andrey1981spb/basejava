package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ResumeSections {
    private final List<String> performanseList = new ArrayList<>();
    private final List<WorkStudyStringDate> workStudyStringDates = new ArrayList<>();

    public String getPosition() {
        return null;
    }

    public List<String> getPerformanceList() {
        return performanseList;
    }

    public List<WorkStudyStringDate> getWorkStudyStringDates() {
        return workStudyStringDates;
    }


}