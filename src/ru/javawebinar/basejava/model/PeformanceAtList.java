package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PeformanceAtList extends ResumeSections {

    private final List<String> performanseList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeformanceAtList that = (PeformanceAtList) o;
        return performanseList.equals(that.performanseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(performanseList);
    }

    @Override
    public List<String> getPerformanceList() {
        return performanseList;
    }

    @Override
    public String toString() {
        return "PerformanseAtList{" +
                "performanseList=" + performanseList +
                '}';
    }
}
