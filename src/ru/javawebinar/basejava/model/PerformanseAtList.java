package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class PerformanseAtList extends ResumeSections {

    private final List<String> performanseList = new ArrayList<>();
    private final List<String> qualificationsList = new ArrayList<>();

    @Override
    public List<String> getPerformanseList() {
        return performanseList;
    }

    public void setPerformanseList(String performanse) {
        performanseList.add(performanse);
    }

}
