package ru.javawebinar.basejava.model;

public class PositionAtString extends ResumeSections {

    private String personal;
    private String objective;

    @Override
    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    @Override
    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }
}