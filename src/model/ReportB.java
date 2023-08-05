package model;

import java.time.LocalDateTime;

/**
 * Represents a report containing appointment details along with contact and customer information.
 */
public class ReportB {
    private String contactName;
    private String title;
    private String description;
    private int appointmentId;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;

    /**
     * Constructs a ReportB object with the specified appointment details.
     *
     * @param contactName The name of the contact associated with the appointment.
     * @param title The title of the appointment.
     * @param description The description of the appointment.
     * @param appointmentId The ID of the appointment.
     * @param type The type of the appointment.
     * @param start The start date and time of the appointment.
     * @param end The end date and time of the appointment.
     * @param customerId The ID of the customer associated with the appointment.
     */
    public ReportB(String contactName, String title, String description, int appointmentId, String type, LocalDateTime start, LocalDateTime end, int customerId) {
        this.contactName = contactName;
        this.title = title;
        this.description = description;
        this.appointmentId = appointmentId;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
    }
    /**
     * Gets the contact name.
     *
     * @return The contact name.
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * Sets the contact name.
     *
     * @param contactName The contact name to set.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**
     * Gets the title name.
     *
     * @return The title name.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title name.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Gets the description.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Gets the appointment id.
     *
     * @return The appointment id.
     */
    public int getAppointmentId() {
        return appointmentId;
    }
    /**
     * Sets the appointment id.
     *
     * @param appointmentId The appointment id to set.
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    /**
     * Gets the appointment type.
     *
     * @return The appointment type.
     */
    public String getType() {
        return type;
    }
    /**
     * Sets the appointment type.
     *
     * @param type The appointment type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Gets the appointment start date and time.
     *
     * @return The appointment start date and time.
     */
    public LocalDateTime getStart() {
        return start;
    }
    /**
     * Sets the appointment start date and time.
     *
     * @param start The appointment start date and time to set.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    /**
     * Gets the appointment end date and time.
     *
     * @return The appointment end date and time.
     */
    public LocalDateTime getEnd() {
        return end;
    }
    /**
     * Sets the appointment end date and time.
     *
     * @param end The appointment end date and time to set.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    /**
     * Gets the customer ID.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * Sets the customer ID.
     *
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
