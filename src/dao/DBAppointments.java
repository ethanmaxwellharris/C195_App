package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBAppointments {
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> aList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getLocalDateTime("Start"); //Change to date
                LocalDateTime end = rs.getLocalDateTime("End"); //Change to time
                int customerId = rs.getInt("Customer ID");
                int userId = rs.getInt("User ID");
                int contactId = rs.getInt("Contact ID");
                Appointments ap = new Appointments(appointmentID, title, description, location, type, start, end, customerId, userId, contactId);
                aList.add(ap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return aList;
    }
}
