package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class Achievment extends ResumeListSections {

    private List<String> achievementList = new ArrayList<>();

    @Override
    public List<String> getData() {
        return achievementList;
    }

    public void setAchievement(String achievement) {
        achievementList.add(achievement);
    }

}
