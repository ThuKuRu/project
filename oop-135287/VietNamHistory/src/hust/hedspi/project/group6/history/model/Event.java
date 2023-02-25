package hust.hedspi.project.group6.history.model;

import java.util.List;

public class Event extends Description {
    private String organizeTime;
    private String quanTa;
    private String quanDich;
    private String ketQua;
    private List<Figure> relatedFigure;
    private static int currentId = 0;

    public Event(String name, String description, String organizeTime, List<Figure> relatedFigure) {
        super(name, description);
        this.id = ++currentId;
        this.organizeTime = organizeTime;
        this.relatedFigure = relatedFigure;
    }

    public Event() {
        this.id = ++currentId;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public String getOrganizeTime() {
        return organizeTime;
    }

    public String getQuanTa() {
        return quanTa;
    }

    public String getQuanDich() {
        return quanDich;
    }

    public String getKetQua() {
        return ketQua;
    }

    public List<Figure> getRelatedFigure() {
        return relatedFigure;
    }

    public String toString() {
        return "==========================" + "\n"
                + "ID: " + this.getId() + "\n"
                + this.getOrganizeTime() + "\n"
                + this.getName() + "\n"
                + "Nhân vật chính liên quan: "
                + this.relatedFigure + "\n";
    }
}