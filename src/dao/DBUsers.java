package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> uList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int userId = rs.getInt("User ID");
                String userName = rs.getString("Name");
                String password = rs.getString("Password");
                Users u = new Users(userId, userName, password);
                uList.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return uList;
    }
}
