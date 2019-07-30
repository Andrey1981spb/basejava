package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class PerformanseAtList extends ResumeSections {

    private final List<String> achievementList = new ArrayList<>();
    private final List<String> qualificationsList = new ArrayList<>();

    @Override
    public List<String> getAchievementList() {
        return achievementList;
    }

    public void setAchievementList(String achievement) {
        achievementList.add(achievement);
    }

    @Override
    public List<String> getQualificationsList() {
        return qualificationsList;
    }

    public void setQualificationsList(String qualifications) {
        qualificationsList.add(qualifications);
    }
}
