package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WorkStudyAtMAP extends ResumeSections {

    private final Map<LocalDate, String> workStudyMAP = new HashMap<>();

    @Override
    public Map<LocalDate, String> getWorkStudyMAP() {
        return workStudyMAP;
    }

    public void setWorkStudyMAP(LocalDate localDate, String workStudy) {
        workStudyMAP.put(localDate, workStudy);
    }

}