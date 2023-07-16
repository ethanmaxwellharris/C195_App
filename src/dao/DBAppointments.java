package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBAppointments {
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> aList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM client_schedule.appointments;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start"); //Pulls the Timestamp value from DB
                LocalDateTime starting = start.toLocalDateTime(); //Converts timestamp value to LocalDateTime to play nice
                Timestamp end = rs.getTimestamp("End"); //Does same as above
                LocalDateTime ending = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments ap = new Appointments(appointmentID, title, description, location, type, starting, ending, customerId, userId, contactId);
                aList.add(ap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return aList;
    }

    public static ObservableList<Appointments> getMonthAppointments() {
        ObservableList<Appointments> mList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM client_schedule.appointments WHERE yearweek(start)=yearweek(now());";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start"); //Pulls the Timestamp value from DB
                LocalDateTime starting = start.toLocalDateTime(); //Converts timestamp value to LocalDateTime to play nice
                Timestamp end = rs.getTimestamp("End"); //Does same as above
                LocalDateTime ending = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments ap = new Appointments(appointmentID, title, description, location, type, starting, ending, customerId, userId, contactId);
                mList.add(ap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mList;
    }

    public static ObservableList<Appointments> getWeekAppointments() {
        ObservableList<Appointments> wList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE month(start) = month(current_date()) AND YEAR(start) = YEAR(current_date());";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start"); //Pulls the Timestamp value from DB
                LocalDateTime starting = start.toLocalDateTime(); //Converts timestamp value to LocalDateTime to play nice
                Timestamp end = rs.getTimestamp("End"); //Does same as above
                LocalDateTime ending = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments ap = new Appointments(appointmentID, title, description, location, type, starting, ending, customerId, userId, contactId);
                wList.add(ap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return wList;
    }

    public static void deleteAppointment(int appointmentId) {
        try {
            String sql = "DELETE FROM client_schedule.appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ps.executeUpdate();
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

//    public static void deleteCustomerAppointment(int customerId) {
//        try {
//            String sql = "DELETE FROM client_schedule.appointments WHERE Customer_ID = ?";
//            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
//            ps.setInt(1, customerId);
//            ps.executeUpdate();
//        } catch (SQLException x) {
//            x.printStackTrace();
//        }
//    }

    //method to delete customers associated appointments and then the customer can be deleted

//    public static void modifyAppointmentStart(LocalDateTime ldt){
//        String sql = "UPDATE appointments SET starting = ? WHERE Appointment_ID = 1";
//        try{
//            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
//            Timestamp ts = Timestamp.valueOf(ldt);
//            ps.setTimestamp(1, ts);
//            ps.execute();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}
