package hust.hedspi.project.group6.history.model;

public class DynastyChild extends Description {
    private String time;
    private static int currentId = 0;

    public DynastyChild() {
        currentId++;
        this.id = currentId;
    }

    public DynastyChild(String name, String description, String time) {
        super(name, description);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "\n{id: " + this.id + ", name: " + this.name + ", time: " + time + ", description:" + this.description + "}\n";
    }
}
