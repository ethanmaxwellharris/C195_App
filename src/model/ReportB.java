package model;

import java.time.LocalDateTime;

public class ReportB {
    private String contactName;
    private String title;
    private String description;
    private int appointmentId;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;

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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
