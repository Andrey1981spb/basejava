package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MarkedListSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    public static final MarkedListSection EMPTY = new MarkedListSection("");

    private List<String> performanseList;

    public MarkedListSection() {
    }

    public MarkedListSection(String... items) {
        this(Arrays.asList(items));
    }

    public MarkedListSection(List<String> performanseList) {
        Objects.requireNonNull(performanseList, "items must not be null");
        this.performanseList = performanseList;
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
        return String.valueOf(performanseList);
    }
}
