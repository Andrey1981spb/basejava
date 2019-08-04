package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class Organization extends AbstractSection {

    private Link homePage;
    private List<Position> positionList = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String url, List<Position> positionList) {
        this.homePage = new Link(name, url);
        this.positionList = positionList;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) &&
                positionList.equals(that.positionList);
    }

    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + positionList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", positionList=" + positionList +
                '}';
    }

}