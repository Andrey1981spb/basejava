package ru.javawebinar.basejava.model;

import java.util.*;

public class Resume {

    private String uuid;
    private String fullName;
    private final List<ResumeSections> resumeSections = new ArrayList<>();
    private final Map<ContactType, String> contactInfoMap = new HashMap<>();
    private PersonalObjective personalObjective;
    private AchievementQualifications achievmentQualifications;
    private ExperienceEducation experienceEducation;

    public Resume() {
        fullName = "Grigory Kislin";
        personalObjective = new PersonalObjective();
        achievmentQualifications = new AchievementQualifications();
        experienceEducation = new ExperienceEducation();
    }

    public List<ResumeSections> getResumeSections() {
        return resumeSections;
    }

    public Map<ContactType, String> getContactInfoMap() {
        return contactInfoMap;
    }

    public PersonalObjective getPersonalObjective() {
        return personalObjective;
    }

    public AchievementQualifications getAchievmentQualifications() {
        return achievmentQualifications;
    }

    public ExperienceEducation getExperienceEducation() {
        return experienceEducation;
    }

    public void setResumeSections(SectionType sectionType, String inputData) {
        if (sectionType == SectionType.PERSONAL) {
            personalObjective.setPersonal(sectionType, inputData);
        } else if (sectionType == SectionType.OBJECTIVE) {
            personalObjective.setObjective(sectionType, inputData);
        } else if (sectionType == SectionType.ACHIEVEMENT) {
            achievmentQualifications.setAchievementList(sectionType, inputData);
        } else if (sectionType == SectionType.QUALIFICATIONS) {
            achievmentQualifications.setQualificationsList(sectionType, inputData);
        } else if (sectionType == SectionType.EXPERIENCE) {
            experienceEducation.setExperienceList(sectionType, inputData);
        } else if (sectionType == SectionType.EDUCATION) {
            experienceEducation.setEducationList(sectionType, inputData);
        }
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

}
