package hust.hedspi.project.group6.history.model;

import java.util.List;

public class Dynasty extends Description {
    private String time;
    private List<DynastyChild> dynasty;
    private static int currentId = 0;

    public Dynasty() {
    }

    public Dynasty(String name, String description, String time) {
        this.name = name;
        this.description = description;
        this.time = time;
        currentId++;
        this.id = currentId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DynastyChild> getDynasty() {
        return dynasty;
    }

    @Override
    public String toString() {
        return "Dynasty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", time='" + time + '\'' +
                ", dynasty=" + dynasty +
                '}';
    }
}
