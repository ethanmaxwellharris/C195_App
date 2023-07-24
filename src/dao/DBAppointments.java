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
            String sql = "SELECT * FROM client_schedule.appointments WHERE MONTH(Start) = MONTH(CURRENT_DATE()) AND YEAR(Start) = YEAR(CURRENT_DATE());";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                LocalDateTime starting = start.toLocalDateTime();
                Timestamp end = rs.getTimestamp("End");
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
            String sql = "SELECT * FROM client_schedule.appointments WHERE YEARWEEK(Start) = YEARWEEK(CURRENT_DATE()) AND MONTH(Start) = MONTH(CURRENT_DATE());";
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

    public static ObservableList<Appointments> getAppointmentTypes() {
        ObservableList<Appointments> typeList = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT appointments.type FROM client_schedule.appointments;";

        try {
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
                typeList.add(ap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return typeList;
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

    public static void addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId){
        try {
            String sql = "INSERT INTO client_schedule.appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
            ps.executeUpdate();
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    public static void modifyAppointment(int id, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId){
        try{
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
            ps.setInt(10, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
