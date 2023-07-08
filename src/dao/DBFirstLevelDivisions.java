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
            String sql = "SELECT * FROM firstleveldivisions";
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
}
