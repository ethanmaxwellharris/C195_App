package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

/**
 * Represents an appointment with its details and properties.
 */
public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;
    private int contactId;

    public static ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

    public static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    public static ObservableList<Appointments> getAllAppointments() {
        return allAppointments;
    }

    /**
     * Constructs an Appointment object with specified details.
     *
     * @param appointmentID The ID of the appointment.
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
    public Appointments(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }
    /**
     * Returns the appointment ID.
     *
     * @return The appointment ID.
     */
    public int getAppointmentId() {
        return appointmentId;
    }
    /**
     * Sets the appointment ID.
     *
     * @param appointmentId The new appointment ID.
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    /**
     * Returns the title of the appointment.
     *
     * @return The title of the appointment.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title of the appointment.
     *
     * @param title The new title of the appointment.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Returns the description of the appointment.
     *
     * @return The description of the appointment.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the appointment.
     *
     * @param description The new description of the appointment.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Returns the location of the appointment.
     *
     * @return The location of the appointment.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Sets the location of the appointment.
     *
     * @param location The new location of the appointment.
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * Returns the type of the appointment.
     *
     * @return The type of the appointment.
     */
    public String getType() {
        return type;
    }
    /**
     * Sets the type of the appointment.
     *
     * @param type The new type of the appointment.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Returns the start date and time of the appointment.
     *
     * @return The start date and time of the appointment.
     */
    public LocalDateTime getStart() {
        return start;
    }
    /**
     * Sets the start date and time of the appointment.
     *
     * @param start The new start date and time of the appointment.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    /**
     * Returns the end date and time of the appointment.
     *
     * @return The end date and time of the appointment.
     */
    public LocalDateTime getEnd() {
        return end;
    }
    /**
     * Sets the end date and time of the appointment.
     *
     * @param end The new end date and time of the appointment.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    /**
     * Returns the customer ID associated with the appointment.
     *
     * @return The customer ID associated with the appointment.
     */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * Sets the customer ID associated with the appointment.
     *
     * @param customerId The new customer ID associated with the appointment.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**
     * Returns the user ID associated with the appointment.
     *
     * @return The user ID associated with the appointment.
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Sets the user ID associated with the appointment.
     *
     * @param userId The new user ID associated with the appointment.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Returns the contact ID associated with the appointment.
     *
     * @return The contact ID associated with the appointment.
     */
    public int getContactId() {
        return contactId;
    }
    /**
     * Sets the contact ID associated with the appointment.
     *
     * @param contactId The new contact ID associated with the appointment.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Returns a string representation of the appointment's type.
     *
     * @return The type of the appointment.
     */
    @Override
    public String toString(){
        return (type);
    }

}


