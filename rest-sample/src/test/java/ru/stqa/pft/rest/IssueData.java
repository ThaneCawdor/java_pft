package ru.stqa.pft.rest;

import java.util.Objects;

public class IssueData {
    private int id;
    private String subject;
    private String description;

    public int getId() {
        return id;
    }

    public IssueData withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public IssueData withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IssueData withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueData issueData = (IssueData) o;
        return id == issueData.id && Objects.equals(subject, issueData.subject) && Objects.equals(description, issueData.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, description);
    }
}
