package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides methods to retrieve and manipulate user data from the database.
 */
public class DBUsers {
    /**
     * Retrieves a list of all users from the database.
     *
     * @return An ObservableList of Users, each containing user details.
     */
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> uList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users u = new Users(userId, userName, password);
                uList.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return uList;
    }
/*Unsure if I need this still - hanging on to it.*/
//    public String toString(){
//        return("#" + Integer.toString(userId));
}
