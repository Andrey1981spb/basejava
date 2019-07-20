package model;

import java.util.Objects;
import java.util.UUID;

public class Resume {

    private String uuid;
    private String fullName;
    private PersonalObjective personalAndObjective;
    private ExpEduAchievQualifications expEduAchievQualifications;
    private ContactInfo contactInfo;

    public Resume() {
        fullName = "Grigory Kislin";
        contactInfo = new ContactInfo();
        personalAndObjective = new PersonalObjective();
        expEduAchievQualifications = new ExpEduAchievQualifications();
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public PersonalObjective getPersonalAndObjective() {
        return personalAndObjective;
    }

    public ExpEduAchievQualifications getExpEduAchievQualifications() {
        return expEduAchievQualifications;
    }

    public void setPersonalAndObjective(String personal, String objective) {
        personalAndObjective.setPersonal(personal);
        personalAndObjective.setObjective(objective);
    }

    public void setExperience(String newlineExperience) {
        expEduAchievQualifications.setExperienceList(newlineExperience);
    }

    public void setEducation(String newlineEducation) {
        expEduAchievQualifications.setEducationList(newlineEducation);
    }

    public void setAchievement(String newlineAchievement) {
        expEduAchievQualifications.setAchievementList(newlineAchievement);
    }

    public void setQualifications(String newlineQualifications) {
        expEduAchievQualifications.setQualificationsList(newlineQualifications);
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
