package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class WorkStudyStringDate {

    private String titleURL;
    private LocalDate dateOfEntry;
    private LocalDate dateOfExist;
    private String description;

    public WorkStudyStringDate(String titleURL, LocalDate dateOfEntry, LocalDate dateOfExist, String description) {
        this.titleURL = titleURL;
        this.dateOfEntry = dateOfEntry;
        this.dateOfExist = dateOfExist;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkStudyStringDate that = (WorkStudyStringDate) o;
        return titleURL.equals(that.titleURL) &&
                dateOfEntry.equals(that.dateOfEntry) &&
                dateOfExist.equals(that.dateOfExist) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleURL, dateOfEntry, dateOfExist, description);
    }

    @Override
    public String toString() {
        return "WorkStudyAtMAP{" +
                "title='" + titleURL + '\'' +
                ", dateOfEntry=" + dateOfEntry +
                ", dateOfExist=" + dateOfExist +
                ", description='" + description + '\'' +
                '}';
    }
}