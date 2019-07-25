package ru.javawebinar.basejava.model;

public class ContactInfo {

    private String telephone = "+7(921) 855-0482";
    private String skype = "grigory.kislin";
    private String mail = "gkislin@yandex.ru";
    private String profileLinkedIn = "profile LinkedIn";
    private String profileGitHub = "https://github.com/gkislin";
    private String profileStackoverflow = "https://stackoverflow.com/users/548473/gkislin";
    private String homepage = "http://gkislin.ru/";

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getProfileLinkedIn() {
        return profileLinkedIn;
    }

    public void setProfileLinkedIn(String profileLinkedIn) {
        this.profileLinkedIn = profileLinkedIn;
    }

    public String getProfileGitHub() {
        return profileGitHub;
    }

    public void setProfileGitHub(String profileGitHub) {
        this.profileGitHub = profileGitHub;
    }

    public String getProfileStackoverflow() {
        return profileStackoverflow;
    }

    public void setProfileStackoverflow(String profileStackoverflow) {
        this.profileStackoverflow = profileStackoverflow;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
