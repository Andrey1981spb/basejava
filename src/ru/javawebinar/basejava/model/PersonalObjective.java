package ru.javawebinar.basejava.model;

public class PersonalObjective extends ResumeSections {

    public void setPersonal(SectionType sectionType, String personal) {
        typeStringMap.put(sectionType, personal);
    }

    public void setObjective(SectionType sectionType, String objective) {
        typeStringMap.put(sectionType, objective);
    }

}