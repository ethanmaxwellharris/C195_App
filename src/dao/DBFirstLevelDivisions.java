package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFirstLevelDivisions {
    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() {
        ObservableList<FirstLevelDivisions> fList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int division_id = rs.getInt("Division ID");
                String division = rs.getString("Division");
                int country_id = rs.getInt("Country ID");
                FirstLevelDivisions f = new FirstLevelDivisions(division_id, division, country_id);
                fList.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fList;
    }

    public static ObservableList<FirstLevelDivisions> getUSFirstLevelDivisions() {
        ObservableList<FirstLevelDivisions> usList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 1";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int division_id = rs.getInt("Division ID");
                String division = rs.getString("Division");
                int country_id = rs.getInt("Country ID");
                FirstLevelDivisions us = new FirstLevelDivisions(division_id, division, country_id);
                usList.add(us);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usList;
    }


    public static ObservableList<FirstLevelDivisions> getCanFirstLevelDivisions() {
        ObservableList<FirstLevelDivisions> canList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 3";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int division_id = rs.getInt("Division ID");
                String division = rs.getString("Division");
                int country_id = rs.getInt("Country ID");
                FirstLevelDivisions can = new FirstLevelDivisions(division_id, division, country_id);
                canList.add(can);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return canList;
    }

    public static ObservableList<FirstLevelDivisions> getUKFirstLevelDivisions() {
        ObservableList<FirstLevelDivisions> ukList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 2";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int division_id = rs.getInt("Division ID");
                String division = rs.getString("Division");
                int country_id = rs.getInt("Country ID");
                FirstLevelDivisions uk = new FirstLevelDivisions(division_id, division, country_id);
                ukList.add(uk);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ukList;
    }


}
