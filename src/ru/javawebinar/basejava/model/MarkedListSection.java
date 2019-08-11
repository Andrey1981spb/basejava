package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MarkedListSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private final List<String> performanseList = new ArrayList<>();

    public MarkedListSection(String data) {
        performanseList.add(data);
    }

    public List<String> getPerformanceList() {
        return performanseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkedListSection that = (MarkedListSection) o;
        return performanseList.equals(that.performanseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(performanseList);
    }

    @Override
    public String toString() {
        return  String.valueOf(performanseList);
    }
}
