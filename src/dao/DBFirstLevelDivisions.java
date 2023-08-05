package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides methods to retrieve information about first-level divisions from the database.
 */
public class DBFirstLevelDivisions {
    /**
     * Retrieves a list of all first-level divisions.
     *
     * @return An ObservableList of FirstLevelDivisions, each containing division details.
     */
    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() {
        ObservableList<FirstLevelDivisions> fList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int division_id = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int country_id = rs.getInt("Country_ID");
                FirstLevelDivisions f = new FirstLevelDivisions(division_id, division, country_id);
                fList.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fList;
    }

    /**
     * Retrieves a list of first-level divisions in the United States.
     *
     * @return An ObservableList of FirstLevelDivisions, each containing US division details.
     */
    public static ObservableList<FirstLevelDivisions> getUSFirstLevelDivisions() {
        ObservableList<FirstLevelDivisions> usList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 1";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int division_id = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int country_id = rs.getInt("Country_ID");
                FirstLevelDivisions us = new FirstLevelDivisions(division_id, division, country_id);
                usList.add(us);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usList;
    }

    /**
     * Retrieves a list of first-level divisions in Canada.
     *
     * @return An ObservableList of FirstLevelDivisions, each containing Canadian division details.
     */
    public static ObservableList<FirstLevelDivisions> getCanFirstLevelDivisions() {
        ObservableList<FirstLevelDivisions> canList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 3";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int division_id = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int country_id = rs.getInt("Country_ID");
                FirstLevelDivisions can = new FirstLevelDivisions(division_id, division, country_id);
                canList.add(can);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return canList;
    }

    /**
     * Retrieves a list of first-level divisions in the United Kingdom.
     *
     * @return An ObservableList of FirstLevelDivisions, each containing UK division details.
     */
    public static ObservableList<FirstLevelDivisions> getUKFirstLevelDivisions() {
        ObservableList<FirstLevelDivisions> ukList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 2";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int division_id = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int country_id = rs.getInt("Country_ID");
                FirstLevelDivisions uk = new FirstLevelDivisions(division_id, division, country_id);
                ukList.add(uk);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ukList;
    }
}
