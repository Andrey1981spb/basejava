package ru.javawebinar.basejava.model;

public class AchievementQualifications extends ResumeSections {

    public void setAchievementList(SectionType sectionType, String achievement) {
        typeStringMap.merge(sectionType, achievement, (oldVal, newVal) -> oldVal + newVal);
    }

    public void setQualificationsList(SectionType sectionType, String qualifications) {
        typeStringMap.merge(sectionType, qualifications, (oldVal, newVal) -> oldVal + newVal);
    }
}
