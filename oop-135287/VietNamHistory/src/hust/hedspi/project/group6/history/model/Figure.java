package hust.hedspi.project.group6.history.model;

import java.util.ArrayList;

public class Figure extends Description {
    private String dynasty;
    private String year;
    private String mienHieu;
    private String thuyHieu;
    private String nienHieu;
    private String tenHuy;
    private String theThu;
    private ArrayList<String> event = new ArrayList<String>();
    private static int currentId = 0;

    public Figure() {
        currentId++;
        this.id = currentId;
    }

    public Figure(
            String name,
            String description,
            String year,
            String dynasty,
            String mienHieu,
            String thuyHieu,
            String nienHieu,
            String tenHuy,
            String theThu
    ) {
        super(name, description);
        this.year = year;
        this.dynasty = dynasty;
        this.mienHieu = mienHieu;
        this.nienHieu = nienHieu;
        this.tenHuy = tenHuy;
        this.thuyHieu = thuyHieu;
        this.theThu = theThu;
        currentId++;
        this.id = currentId;
    }

    public String getDynasty() {
        return dynasty;
    }

    public ArrayList<String> getEvent() {
        return event;
    }

    public String getYear() {
        return year;
    }

    public String getMienHieu() {
        return mienHieu;
    }

    public String getThuyHieu() {
        return thuyHieu;
    }

    public String getNienHieu() {
        return nienHieu;
    }

    public String getTenHuy() {
        return tenHuy;
    }

    public String getTheThu() {
        return theThu;
    }
}
