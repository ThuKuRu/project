package hust.hedspi.project.group6.history.model;

public class Festival extends Description {
    private String organizeTime;
    private String location;
    private static int currentId = 0;

    public Festival() {
        currentId++;
        this.id = currentId;
    }

    public Festival(String name, String description, String organizeTime, String location) {
        super(name, description);
        this.organizeTime = organizeTime;
        this.location = location;
        ++currentId;
        this.id = currentId;
    }

    public int getId() {
        return this.id;
    }

    public String getOrganizeTime() {
        return organizeTime;
    }

    public void setOrganizeTime(String organizeTime) {
        this.organizeTime = organizeTime;
    }

    public static int getNumberOfFestival() {
        return currentId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Festival{" +
                "id=" + this.id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", organizeTime='" + organizeTime + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
