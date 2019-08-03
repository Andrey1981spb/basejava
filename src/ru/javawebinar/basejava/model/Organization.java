package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Organization extends AbstractSection {

    private Link homePage;
    private Position position;
    private List<Position> positionList = new ArrayList<>();

    public Organization(String name, String url, String title, LocalDate dateOfEntry, LocalDate dateOfExit, String description) {
        this.homePage = new Link(name, url);
        this.position = new Position(title, dateOfEntry, dateOfExit, description);
        this.positionList.add(position);
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
        result = 31 * result + position.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", position=" + position +
                ", positionList=" + positionList +
                '}';
    }
}