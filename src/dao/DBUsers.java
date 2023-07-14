package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.io.IOException;
import java.sql.Connection;
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

//    @Override
//    public String toString(){
//        return("#" + Integer.toString(userId));
//    }

//    public static int confirmUser(String userName, String password) throws IOException {
//
//        String sql = "SELECT * FROM client_schedule.users WHERE User_Name '" + userName + "' AND Password = '" + password + "'";
//        try {
//            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            if (rs.getString("User_Name").equals(userName)){
//                if(rs.getString("Password").equals(password)) {
//                    return rs.getInt("User_ID");
//                }
//
//            }
//        } catch (Exception ignored) {
//        }
//        return -1;
//    }
}
