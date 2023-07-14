package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> coList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contacts co = new Contacts(contactId, contactName, email);
                coList.add(co);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return coList;
    }


}
