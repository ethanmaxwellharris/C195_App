package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import java.sql.*;

public class DBCountries {
    public static ObservableList<Countries> getAllCountries(){
        ObservableList<Countries> clist = FXCollections.observableArrayList();

        try {
            //The SQL Statement which selects data to pull down
            String sql = "SELECT * FROM countries";
            //Prepared Statement
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            //Execute query and get results which display themselves in the result set
            ResultSet rs = ps.executeQuery();
            //Iterates through result set, one row at a time
            while(rs.next()){
                //get results data
                int country_id = rs.getInt("Country ID");
                String country_name = rs.getString("Country");
                Countries c = new Countries(country_id, country_name);
                clist.add(c); //adds new country object to list
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return clist;
    }

    public static void checkDateConversion() {
        System.out.println("CREATE DATE TEST");
        String sql = "select Create_Date from countries";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Timestamp ts = rs.getTimestamp("Create Date");
                System.out.println("CD: " + ts.toLocalDateTime().toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
