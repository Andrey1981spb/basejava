package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Position implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private LocalDate dateOfEntry;
    private LocalDate dateOfExit;
    private String description;

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
        return title.equals(that.title) &&
                dateOfEntry.equals(that.dateOfEntry) &&
                dateOfExit != null ? dateOfExit.equals(that.dateOfExit) : that.dateOfExit == null &&
                description != null ? description.equals(that.description) : that.description == null;
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
