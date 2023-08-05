package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides a method to retrieve information about contacts from the database.
 */
public class DBContacts {

    /**
     * Retrieves a list of all contacts from the database.
     *
     * @return An ObservableList of Contacts, each containing contact details.
     */
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
