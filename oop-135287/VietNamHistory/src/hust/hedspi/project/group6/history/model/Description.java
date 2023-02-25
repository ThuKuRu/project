package hust.hedspi.project.group6.history.model;

public abstract class Description {
    protected int id;
    protected String name;
    protected String description;

    public Description(){};
    public Description(String name, String description){
        this.name = name;
        this.description = description;
    };

    public String getName(){
        return this.name;
    };
    public String getDescription(){
        return this.description;
    };
    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
