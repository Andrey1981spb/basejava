package model;

import java.util.ArrayList;
import java.util.List;

public class ExpEduAchievQualifications {

    public List<String> experienceList = new ArrayList<>();
    public List<String> educationList = new ArrayList<>();
    public List<String> achievementList = new ArrayList<>();
    public List<String> qualificationsList = new ArrayList<>();

    public List<String> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(String newline) {
        educationList.add(newline);
    }

    public List<String> getEducationList() {
        return educationList;
    }

    public void setEducationList(String newline) {
        educationList.add(newline);
    }

    public List<String> getAchievementList() {
        return achievementList;
    }

    public void setAchievementList(String newline) {
        achievementList.add(newline);
    }

    public List<String> getQualificationsList() {
        return qualificationsList;
    }

    public void setQualificationsList(String newline) {
        qualificationsList.add(newline);
    }

}
