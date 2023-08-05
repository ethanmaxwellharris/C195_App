package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class provides methods to retrieve, add, modify, and delete appointment data from the database.
 */
public class DBAppointments {
    /**
     * Retrieves a list of all appointments from the database.
     *
     * @return An ObservableList of Appointments, each containing appointment details.
     */
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
    /**
     * Retrieves a list of appointments for the current month from the database.
     *
     * @return An ObservableList of Appointments for the current month.
     */
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
    /**
     * Retrieves a list of appointments for the current week from the database.
     *
     * @return An ObservableList of Appointments for the current week.
     */
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
    /**
     * Retrieves a list of appointments scheduled within the next 15 minutes from the database.
     *
     * @return An ObservableList of Appointments scheduled within the next 15 minutes.
     */
    public static ObservableList<Appointments> getAppointmentsIn15() {
        ObservableList<Appointments> fifList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM client_schedule.appointments WHERE Start >= NOW() AND Start <= NOW() + INTERVAL 15 MINUTE;";
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
                fifList.add(ap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fifList;
    }
    /**
     * Checks if an appointment overlaps with existing appointments for a given customer.
     *
     * @param customerId The ID of the customer.
     * @param start The start time of the new appointment.
     * @param end The end time of the new appointment.
     * @return True if there is an overlap, false otherwise.
     * @throws SQLException If a SQL exception occurs during database interaction.
     */
    public static boolean checkAppointmentOverlap(int customerId, LocalDateTime start, LocalDateTime end) throws SQLException {
        String sql = "SELECT * FROM client_schedule.appointments WHERE Customer_ID = ?;";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            LocalDateTime existingStart = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime existingEnd = rs.getTimestamp("End").toLocalDateTime();
            if ((start.isAfter(existingStart.minusMinutes(1)) && start.isBefore(existingEnd.plusMinutes(1))) ||
                    (end.isAfter(existingStart.minusMinutes(1)) && end.isBefore(existingEnd.plusMinutes(1))) ||
                    (start.isBefore(existingStart.plusMinutes(1)) && end.isAfter(existingEnd.minusMinutes(1)))) {
                return true;
            }
        }
        return false;
    };
    /**
     * Retrieves a list of distinct appointment types from the database.
     *
     * @return An ObservableList of distinct appointment types.
     */
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
    /**
     * Deletes an appointment from the database.
     *
     * @param appointmentId The ID of the appointment to be deleted.
     */
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
    /**
     * Adds a new appointment to the database.
     *
     * @param title The title of the appointment.
     * @param description The description of the appointment.
     * @param location The location of the appointment.
     * @param type The type of the appointment.
     * @param start The start time of the appointment.
     * @param end The end time of the appointment.
     * @param customerId The ID of the customer associated with the appointment.
     * @param userId The ID of the user associated with the appointment.
     * @param contactId The ID of the contact associated with the appointment.
     */
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
    /**
     * Modifies an existing appointment in the database.
     *
     * @param id The ID of the appointment to be modified.
     * @param title The updated title of the appointment.
     * @param description The updated description of the appointment.
     * @param location The updated location of the appointment.
     * @param type The updated type of the appointment.
     * @param start The updated start time of the appointment.
     * @param end The updated end time of the appointment.
     * @param customerId The updated ID of the customer associated with the appointment.
     * @param userId The updated ID of the user associated with the appointment.
     * @param contactId The updated ID of the contact associated with the appointment.
     */
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
