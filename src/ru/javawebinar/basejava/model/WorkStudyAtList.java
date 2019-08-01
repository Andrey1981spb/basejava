package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkStudyAtList extends ResumeSections {

    private List<WorkStudyStringDate> workStudyStringDates = new ArrayList<>();

    public WorkStudyAtList(List<WorkStudyStringDate> workStudyStringDates) {
        this.workStudyStringDates = workStudyStringDates;
    }

    @Override
    public List<WorkStudyStringDate> getWorkStudyStringDates() {
        return workStudyStringDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkStudyAtList that = (WorkStudyAtList) o;
        return workStudyStringDates.equals(that.workStudyStringDates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workStudyStringDates);
    }

    @Override
    public String toString() {
        return "WorkStudyAtList{" +
                "workStudyStringDates=" + workStudyStringDates +
                '}';
    }
}
