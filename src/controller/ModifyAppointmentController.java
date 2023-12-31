package controller;

import dao.DBAppointments;
import dao.DBContacts;
import dao.DBCustomers;
import dao.DBUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This controller class manages the modification of appointment information.
 * It handles user interactions and updates appointment data accordingly.
 *
 * @author      Ethan Harris
 * @version     %I%
 * @since       1.0
 */
public class ModifyAppointmentController implements Initializable {
    @FXML public TextField apptIdTextField;
    @FXML public TextField apptTitleTextField;
    @FXML public TextField apptDescriptionTextField;
    @FXML public TextField apptLocationTextField;
    @FXML public ComboBox<Contacts> contactIdComboBox;
    @FXML public ComboBox<String> apptTypeComboBox;
    @FXML public Button saveAppointmentButton;
    @FXML public Button cancelAppointmentButton;
    @FXML public ComboBox<Customers> custIdComboBox;
    @FXML public ComboBox<Users> userIdComboBox;
    @FXML public ComboBox apptStartTimeComboBox;
    @FXML public DatePicker apptEndDatePicker;
    @FXML public ComboBox apptEndTimeComboBox;
    @FXML public DatePicker apptStartDatePicker;
    private final ObservableList<String> appointmentTypes = FXCollections.observableArrayList("Planning Session", "De-Briefing", "Execution", "Monitor & Control");
    private static final int maxTextLength = 50;
    public Label timeNotice;

    /**
     * Initializes the ModifyAppointment screen with pre-existing data for modification.
     *
     * This method is called when the modify Appointment screen is initialized for modifying an existing appointment.
     * It performs the following tasks:
     * - Retrieves the system's current time in the America/New_York (EST) time zone.
     * - Calculates the latest allowable time for scheduling an appointment.
     * - Formats and displays the current time in EST and the latest allowable scheduling time in the user's time zone.
     * - Retrieves the appointment to be modified from the MainScreenController.
     * - Populates input fields and selection boxes with data from the selected appointment.
     * - Populates the appointment type selection box with available appointment types.
     * - Sets default values for date and time selection boxes based on the selected appointment's start and end times.
     * - Populates user, customer, and contact selection boxes with data from the database.
     * - Sets the selection of user, customer, and contact based on the selected appointment's data.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId estZone = ZoneId.of("America/New_York");
        ZonedDateTime estNow = ZonedDateTime.now(estZone);
        LocalTime currentTimeInEST = estNow.toLocalTime();
        LocalTime latestAllowableTimeInEST = LocalTime.of(22, 0);
        ZoneId userZone = ZoneId.systemDefault();
        ZonedDateTime latestAllowableTimeInUserZone = estNow.toLocalDate().atTime(latestAllowableTimeInEST).atZone(estZone).withZoneSameInstant(userZone);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        timeNotice.setText("The current time at HQ is " +  currentTimeInEST.format(timeFormatter) + " EST." + "\n" + "The latest you can schedule an appointment is " + latestAllowableTimeInEST.format(timeFormatter) + " EST or " + latestAllowableTimeInUserZone.format(timeFormatter) + " your time.");

        Appointments selectedModifyAppointment = MainScreenController.getModifyAppointment();
        apptIdTextField.setText(String.valueOf(selectedModifyAppointment.getAppointmentId()));
        apptTitleTextField.setText(String.valueOf(selectedModifyAppointment.getTitle()));
        apptDescriptionTextField.setText(String.valueOf(selectedModifyAppointment.getDescription()));
        apptLocationTextField.setText(String.valueOf(selectedModifyAppointment.getLocation()));
        apptTypeComboBox.setItems(appointmentTypes);
        apptTypeComboBox.getSelectionModel().select(selectedModifyAppointment.getType());
        apptStartDatePicker.setValue(selectedModifyAppointment.getStart().toLocalDate());
        apptEndDatePicker.setValue(selectedModifyAppointment.getStart().toLocalDate());
        List<LocalTime> timeOptions = createTimeOptions();
        apptStartTimeComboBox.setItems(FXCollections.observableArrayList(timeOptions));
        apptEndTimeComboBox.setItems(FXCollections.observableArrayList(timeOptions));
        apptStartTimeComboBox.setValue(selectedModifyAppointment.getStart().toLocalTime());
        apptEndTimeComboBox.setValue(selectedModifyAppointment.getEnd().toLocalTime());

        custIdComboBox.setItems(DBCustomers.getAllCustomers());
        for (Customers customer: DBCustomers.getAllCustomers()) {
            if(customer.getCustomerId() == selectedModifyAppointment.getCustomerId()) {
                custIdComboBox.setValue(customer);
            }
        }
        userIdComboBox.setItems(DBUsers.getAllUsers());
        for (Users user: DBUsers.getAllUsers()) {
            if(user.getUserId() == selectedModifyAppointment.getUserId()) {
                userIdComboBox.setValue(user);
            }
        }
        contactIdComboBox.setItems(DBContacts.getAllContacts());
        for (Contacts contact: DBContacts.getAllContacts()) {
            if(contact.getContactId() == selectedModifyAppointment.getContactId()) {
                contactIdComboBox.setValue(contact);
            }
        }
    }
    /**
     * Handles the save action for modifying an existing appointment.
     *
     * This method is called when the user clicks the "Save" button to modify an existing appointment.
     * It performs the following tasks:
     * - Retrieves and validates input data including appointment ID, title, description, location, type, start and end times, customer, user, and contact.
     * - Checks if the appointment's start and end times fall within EST business hours.
     * - Checks for any overlapping appointments and displays an alert if overlaps are detected.
     * - Validates input data for length and empty fields, showing corresponding alerts if necessary.
     * - Calls the DBAppointments.modifyAppointment() method to update the appointment in the database.
     * - Navigates back to the main menu screen upon successful modification.
     *
     * @param actionEvent The event triggered by clicking the "Save" button.
     */
    public void saveAppointmentOnAction(ActionEvent actionEvent) {
        try{
            Integer id = Integer.parseInt(apptIdTextField.getText());
            String title = apptTitleTextField.getText();
            String description = apptDescriptionTextField.getText();
            String location = apptLocationTextField.getText();
            String type = apptTypeComboBox.getSelectionModel().getSelectedItem();
            LocalDate stDate = apptStartDatePicker.getValue();
            LocalTime stTime = (LocalTime) apptStartTimeComboBox.getSelectionModel().getSelectedItem();
            LocalDateTime start = LocalDateTime.of(stDate, stTime);
            LocalDate endDate = apptEndDatePicker.getValue();
            LocalTime endTime = (LocalTime) apptEndTimeComboBox.getSelectionModel().getSelectedItem();
            LocalDateTime end = LocalDateTime.of(endDate, endTime);
            int customerId = custIdComboBox.getSelectionModel().getSelectedItem().getCustomerId();
            int userId = userIdComboBox.getSelectionModel().getSelectedItem().getUserId();
            int contactId = contactIdComboBox.getSelectionModel().getSelectedItem().getContactId();

            ZoneId zoneEST = ZoneId.of("America/New_York");
            LocalTime open = LocalTime.of(8, 0);
            LocalTime close = LocalTime.of(22, 0);
            ZonedDateTime zonedStart = ZonedDateTime.of(start, ZoneId.systemDefault());
            ZonedDateTime zonedEnd = ZonedDateTime.of(end, ZoneId.systemDefault());
            ZonedDateTime startEST = zonedStart.withZoneSameInstant(zoneEST);
            ZonedDateTime endEST = zonedEnd.withZoneSameInstant(zoneEST);
            LocalTime openEST = startEST.toLocalTime();
            LocalTime closeEST = endEST.toLocalTime();

            if (openEST.isBefore(open) || openEST.isAfter(close) || closeEST.isBefore(open) || closeEST.isAfter(close)) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment cannot be saved - appointment is outside EST business hours");
                alert.setTitle("Appointment Outside EST Business Hours");
                alert.showAndWait();
                return;
            }

            for (Appointments a : DBAppointments.getAllAppointments()) {
                LocalDateTime appointmentStartTime = a.getStart();
                LocalDateTime appointmentEndTime = a.getEnd();
                if (a.getCustomerId() == custIdComboBox.getValue().getCustomerId() && (Integer.parseInt(apptIdTextField.getText()) != a.getAppointmentId())) {
                    if ((start.isBefore(appointmentStartTime) || start.isEqual(appointmentStartTime)) && (end.isAfter(appointmentEndTime)|| end.isEqual(appointmentEndTime))){
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment cannot be saved - there is an overlap with an existing appointment");
                        alert.setTitle("Appointment Overlap");
                        alert.showAndWait();
                        return;
                    }
                    else if ((start.isAfter(appointmentStartTime) || start.isEqual(appointmentStartTime)) && start.isBefore(appointmentEndTime)) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment cannot be saved - the start time results in an overlap with an existing appointment");
                        alert.setTitle("Appointment Start Overlap");
                        alert.showAndWait();
                        return;
                    }
                    else if ((end.isBefore(appointmentEndTime) || end.isEqual(appointmentEndTime)) && end.isAfter(appointmentStartTime)){
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment cannot be saved - the end time results in an overlap with an existing appointment");
                        alert.setTitle("Appointment End Overlap");
                        alert.showAndWait();
                        return;
                    }
                }
            }
            if(apptTitleTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Title cannot be left blank.");
                alert.showAndWait();
            }else if(apptTitleTextField.getText().length() > maxTextLength) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Title must be " + maxTextLength + " characters or less. The current length is " + apptTitleTextField.getText().length());
                alert.setTitle("Excessive characters in Title!");
                alert.showAndWait();
            }else if (apptDescriptionTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Description cannot be left blank.");
                alert.showAndWait();
            }else if(apptDescriptionTextField.getText().length() > maxTextLength) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Description must be " + maxTextLength + " characters or less. The current length is " + apptDescriptionTextField.getText().length());
                alert.setTitle("Excessive characters in Description!");
                alert.showAndWait();
            }else if (apptLocationTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Location cannot be left blank.");
                alert.showAndWait();
            }else if(apptLocationTextField.getText().length() > maxTextLength){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Location must be " + maxTextLength + " characters or less. The current length is " + apptLocationTextField.getText().length());
                alert.setTitle("Excessive characters in Location!");
                alert.showAndWait();
            }else if(start.isAfter(end)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Start time cannot be after the end time.");
                alert.showAndWait();
            }else if(end.isBefore(start)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "End time cannot be before the start time.");
                alert.showAndWait();
            }else if(stDate.isAfter(endDate)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Start date cannot be after the end date.");
                alert.showAndWait();
            }else if(endDate.isBefore(stDate)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "End date cannot be after the start date.");
                alert.showAndWait();
            }else if(apptTypeComboBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Type must be selected.");
                alert.showAndWait();
            } else {
                DBAppointments.modifyAppointment(id, title, description, location, type, start, end, customerId, userId, contactId);
                Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception x) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You cannot leave drop-down's empty.");
            alert.setTitle("Nothing Selected Yet");
            alert.showAndWait();
        }
    }
    /**
     * Creates a list of time options for the appointment start and end times.
     *
     * @return A list of LocalTime objects representing available time options.
     */
    private List<LocalTime> createTimeOptions() {
        List<LocalTime> timeOptions = new ArrayList<>();
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(22, 0);

        while (start.isBefore(end.plusSeconds(1))) {
            timeOptions.add(start);
            start = start.plusMinutes(30);
        }

        return timeOptions;
    }
    /**
     * Handles the "Cancel" button action to go back to the main menu.
     *
     * @param actionEvent The action event triggered by the button.
     */
    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
