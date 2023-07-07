package model;

public class Countries {
    private int country_id;
    private String country_name;

    public Countries(int country_id, String country_name) {
        this.country_id = country_id;
        this.country_name = country_name;
    }

    public int getId() {return country_id;}
    public String getName() {return country_name;}
}
