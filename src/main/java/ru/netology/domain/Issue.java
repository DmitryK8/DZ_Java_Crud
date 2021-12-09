package ru.netology.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Issue {
    private int id;
    private String name;
    private boolean close;
    private String author;
    private Set label;
    private Set successor;
    private Set projects;
    private Set tags;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set getLabel() {
        return label;
    }

    public void setLabel(Set label) {
        this.label = label;
    }

    public Set getSuccessor() {
        return successor;
    }

    public void setSuccessor(Set successor) {
        this.successor = successor;
    }

    public Set getProjects() {
        return projects;
    }

    public void setProjects(Set projects) {
        this.projects = projects;
    }

    public Set getTags() {
        return tags;
    }

    public void setTags(Set tags) {
        this.tags = tags;
    }
}





