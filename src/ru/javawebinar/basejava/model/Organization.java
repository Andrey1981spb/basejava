package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType ( XmlAccessType.FIELD )
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;
    private List<Position> positionList = new ArrayList<>();

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link homePage, List<Position> positionList) {
        this.homePage = homePage;
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

    @XmlAccessorType ( XmlAccessType.FIELD )
    public static class Position {

        String title;
        @XmlJavaTypeAdapter ( LocalDateAdapter.class )
        LocalDate dateOfEntry;
        @XmlJavaTypeAdapter ( LocalDateAdapter.class )
        LocalDate dateOfExit;
        String description;

        public Position(String title, LocalDate dateOfEntry, LocalDate dateOfExit, String description) {
            Objects.requireNonNull(dateOfEntry, "dateOfEntry must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.title = title;
            this.dateOfEntry = dateOfEntry;
            this.dateOfExit = dateOfExit;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position that = (Position) o;
            return title.equals(title) &&
                    dateOfEntry.equals(dateOfEntry) &&
                    dateOfExit != null ? dateOfExit.equals(dateOfExit) : dateOfExit == null &&
                    description != null ? description.equals(description) : description == null;
        }

        public int hashCode() {
            int result = 0;
            result = 31 * result + dateOfEntry.hashCode();
            result = 31 * result + (dateOfExit != null ? dateOfExit.hashCode() : 0);
            result = 31 * result + title.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return
                    "  dateOfEntry=" + dateOfEntry +
                            ", dateOfExit=" + dateOfExit +
                            ", title='" + title + '\'' +
                            ", description='" + description + '\'';
        }
    }

}