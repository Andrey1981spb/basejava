package ru.javawebinar.basejava.model;

import java.util.Objects;
import java.util.UUID;

public class Resume {

    private String uuid;
    private String fullName;
    private ContactInfo contactInfo;
    private Personal personal;
    private Objective objective;
    private Achievment achievment;
    private Education education;
    private Experience experience;
    private Qualifications qualifications;

    public Resume() {
        fullName = "Grigory Kislin";
        contactInfo = new ContactInfo();
        personal = new Personal();
        objective = new Objective();
        achievment = new Achievment();
        education = new Education();
        experience = new Experience();
        qualifications = new Qualifications();
    }

    public void setResumeSections(SectionType sectionType, String inputData) {
        if (sectionType == SectionType.PERSONAL) {
            personal.setPersonal(inputData);
        } else if (sectionType == SectionType.OBJECTIVE) {
            objective.setObjective(inputData);
        } else if (sectionType == SectionType.ACHIEVEMENT) {
            achievment.setAchievement(inputData);
        } else if (sectionType == SectionType.QUALIFICATIONS) {
            qualifications.setQualifications(inputData);
        } else if (sectionType == SectionType.EXPERIENCE) {
            experience.setExperience(inputData);
        } else if (sectionType == SectionType.EDUCATION) {
            education.setEducation(inputData);
        }
    }

    public Personal getPersonal() {
        return personal;
    }

    public Objective getObjective() {
        return objective;
    }

    public Achievment getAchievment() {
        return achievment;
    }

    public Education getEducation() {
        return education;
    }

    public Experience getExperience() {
        return experience;
    }

    public Qualifications getQualifications() {
        return qualifications;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void setContactInfo(String telephone, String skype, String mail, String profileLinkedIn,
                               String profileGitHub, String profileStackoverflow, String homepage) {
        contactInfo.setTelephone(telephone);
        contactInfo.setSkype(skype);
        contactInfo.setMail(mail);
        contactInfo.setProfileLinkedIn(profileLinkedIn);
        contactInfo.setProfileGitHub(profileGitHub);
        contactInfo.setProfileStackoverflow(profileStackoverflow);
        contactInfo.setHomepage(homepage);
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
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
