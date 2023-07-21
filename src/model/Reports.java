package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Reports {
    private String type;
    private String month;
    private Integer total;

    public Reports(String type, String month, int total) {
        this.type = type;
        this.month = month;
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotal() { return total; }

    public void setTotal(int total) {
        this.total = total;
    }

    public static ObservableList<Reports> reportA = FXCollections.observableArrayList();

}
