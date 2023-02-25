package hust.hedspi.project.group6.history.model;

public class HistoricalSite extends Description {
    private String place;
    private static int currentId = 0;

    public HistoricalSite() {
        currentId++;
        this.id = currentId;
    }

    public HistoricalSite(String name, String description, String place) {
        super(name, description);
        this.place = place;
        currentId++;
        this.id = currentId;
    }

    public String getPlace() {
        return place;
    }

}
