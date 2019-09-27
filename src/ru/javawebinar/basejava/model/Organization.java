package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

@XmlAccessorType ( XmlAccessType.FIELD )
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Organization EMPTY = new Organization("", "", Position.POSITION);

    private Link homePage;
    private List<Position> positionList = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link homePage, List<Position> positionList) {
        this.homePage = homePage;
        this.positionList = positionList;
    }

    public Link getHomePage() {
        return homePage;
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
    public static class Position implements Serializable {

        public static final Position POSITION = new Position();
        String title;
        @XmlJavaTypeAdapter ( LocalDateAdapter.class )
        LocalDate dateOfEntry;
        @XmlJavaTypeAdapter ( LocalDateAdapter.class )
        LocalDate dateOfExit;
        String description;

        public Position() {
        }

        public Position(int startYear, Month startMonth, String title, String description) {
            this(title, of(startYear, startMonth), NOW,  description);
        }

        public Position(String title, int startYear, Month startMonth, int endYear, Month endMonth, String description) {
            this(title, of(startYear, startMonth), of(endYear, endMonth),  description);
        }

        public Position(String title, LocalDate dateOfEntry, LocalDate dateOfExit, String description) {
            Objects.requireNonNull(dateOfEntry, "dateOfEntry must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.title = title;
            this.dateOfEntry = dateOfEntry;
            this.dateOfExit = dateOfExit;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public LocalDate getDateOfEntry() {
            return dateOfEntry;
        }

        public void setDateOfEntry(LocalDate dateOfEntry) {
            this.dateOfEntry = dateOfEntry;
        }

        public LocalDate getDateOfExit() {
            return dateOfExit;
        }

        public void setDateOfExit(LocalDate dateOfExit) {
            this.dateOfExit = dateOfExit;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(dateOfExit, position.dateOfExit) &&
                    Objects.equals(dateOfEntry, position.dateOfEntry) &&
                    Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
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