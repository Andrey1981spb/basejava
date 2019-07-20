package model;

public class ContactInfo {

    public String telephone = "+7(921) 855-0482";
    public String skype = "grigory.kislin";
    public String mail = "gkislin@yandex.ru";
    public String profileLinkedIn = "profile LinkedIn";
    public String profileGitHub = "https://github.com/gkislin";
    public String profileStackoverflow = "https://stackoverflow.com/users/548473/gkislin";
    public String homepage = "http://gkislin.ru/";

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
