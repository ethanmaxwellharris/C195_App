package controller;

import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    public TextField apptIdTextField;
    public TextField apptTitleTextField;
    public TextField apptDescriptionTextField;
    public TextField apptLocationTextField;
    public ComboBox<Appointments> apptContactComboBox;
    public ComboBox<String> apptTypeComboBox;
    public Button saveAppointmentButton;
    public Button cancelAppointmentButton;
    public ComboBox<Users> userIdComboBox;
    public ComboBox<Customers> custIdComboBox; //Error needs to be displayed for overlapping customer appointments
    public ComboBox<Contacts> contactIdComboBox;
    public DatePicker apptEndDatePicker;
    public ComboBox apptEndTimeComboBox;
    public ComboBox apptStartTimeComboBox;
    public DatePicker apptStartDatePicker;
    private final ObservableList<String> appointmentTypes = FXCollections.observableArrayList("Planning Session", "De-Briefing", "Execution", "Monitor & Control");
    private static final int maxTextLength = 50;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add appointments screen initialized");
        apptTypeComboBox.setItems(appointmentTypes);
        apptTypeComboBox.setPromptText("Select a Type");
        ObservableList<Users> users = DBUsers.getAllUsers();
        userIdComboBox.setItems(users);
        userIdComboBox.setPromptText("Select a User ID");
        ObservableList<Customers> customers = DBCustomers.getAllCustomers();
        custIdComboBox.setItems(customers);
        custIdComboBox.setPromptText("Select a Customer ID");
        ObservableList<Contacts> contacts = DBContacts.getAllContacts();
        contactIdComboBox.setItems(contacts);
        contactIdComboBox.setPromptText("Select a Contact ID");
        apptStartDatePicker.setValue(LocalDate.now());
        apptEndDatePicker.setValue(LocalDate.now());
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(17,30);
        while(start.isBefore(end.plusSeconds(1))){
            apptStartTimeComboBox.getItems().add(start);
            apptEndTimeComboBox.getItems().add(start);
            start = start.plusMinutes(30);
        }
        apptStartTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));
        apptEndTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));
    }


    public void saveAppointmentOnAction(ActionEvent actionEvent) throws IOException {
        try{
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

                for (Appointments a : DBAppointments.getAllAppointments()) {
                    LocalDateTime appointmentStartTime = a.getStart();
                    LocalDateTime appointmentEndTime = a.getEnd();
                    if (a.getCustomerId() == custIdComboBox.getValue().getCustomerId()) {
                        if ((start.isBefore(appointmentStartTime) || start.isEqual(appointmentStartTime)) && (end.isAfter(appointmentEndTime)|| end.isEqual(appointmentEndTime))){
                            /*without equal edge cases:start.isBefore(appointmentStartTime) && end.isAfter(appointmentEndTime)*/
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
        }else {
            DBAppointments.addAppointment(title, description, location, type, start, end, customerId, userId, contactId);
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
            x.printStackTrace();
            }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}
