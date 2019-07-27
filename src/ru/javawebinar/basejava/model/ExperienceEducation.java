package ru.javawebinar.basejava.model;

public class ExperienceEducation extends ResumeSections {

    public void setExperienceList(SectionType sectionType, String experience) {
        typeStringMap.merge(sectionType, experience, (oldVal, newVal) -> oldVal + newVal);
    }

    public void setEducationList(SectionType sectionType, String qualifications) {
        typeStringMap.merge(sectionType, qualifications, (oldVal, newVal) -> oldVal + newVal);
    }
}