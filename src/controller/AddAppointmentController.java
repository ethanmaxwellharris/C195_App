package controller;

import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    public ComboBox<Appointments> apptTypeComboBox;
    public Button saveAppointmentButton;
    public Button cancelAppointmentButton;
    public ComboBox<Users> userIdComboBox;
    public ComboBox<Customers> custIdComboBox; //Error needs to be displayed for overlapping customer appointments
    public ComboBox<Contacts> contactIdComboBox;
    public DatePicker apptEndDatePicker;
    public ComboBox apptEndTimeComboBox;
    public ComboBox apptStartTimeComboBox;
    public DatePicker apptStartDatePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add appointments screen initialized");

        //Method 1: Populate with DBAppointment data
        ObservableList<Appointments> type = DBAppointments.getAllAppointments();
        apptTypeComboBox.setItems(type);
        apptTypeComboBox.setPromptText("Select a Type");


        //Method 2: Pre-fill with additional selections
        /*apptTypeComboBox.getItems().addAll("Planning", "De-briefing", "Vision Session");
        ObservableList<Appointments> types = FXCollections.observableArrayList();
        for (Appointments appointment : DBAppointments.getAllAppointments()) {
            String appointmentType = appointment.getType();
            if (!type.isEmpty() && !types.contains(type)) {
                types.add(appointmentType);
            }
        }
        apptTypeComboBox.setItems(types);*/

        ObservableList<Users> users = DBUsers.getAllUsers();
        userIdComboBox.setItems(users);
        //userIdComboBox.getSelectionModel().selectFirst();
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

        apptStartTimeComboBox.getSelectionModel().select(LocalTime.of(8,0)); //Change this to the time restriction of 8:00AM - 10:00PM EST
        apptEndTimeComboBox.getSelectionModel().select(LocalTime.of(8,0)); //Change this to the time restriction of 8:00AM - 10:00PM EST
    }

    public void saveAppointmentOnAction(ActionEvent actionEvent) throws IOException {
        try{
            String title = apptTitleTextField.getText();
            String description = apptDescriptionTextField.getText();
            String location = apptLocationTextField.getText();
            String type = apptTypeComboBox.getSelectionModel().getSelectedItem().getType();
            LocalDate stDate = apptStartDatePicker.getValue();
            LocalTime stTime = (LocalTime) apptStartTimeComboBox.getSelectionModel().getSelectedItem();
            LocalDateTime start = LocalDateTime.of(stDate, stTime);
            LocalDate endDate = apptEndDatePicker.getValue();
            LocalTime endTime = (LocalTime) apptEndTimeComboBox.getSelectionModel().getSelectedItem();
            LocalDateTime end = LocalDateTime.of(endDate, endTime);
            int customerId = custIdComboBox.getSelectionModel().getSelectedItem().getCustomerId();
            int userId = userIdComboBox.getSelectionModel().getSelectedItem().getUserId();
            int contactId = contactIdComboBox.getSelectionModel().getSelectedItem().getContactId();
        if(title.isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Title cannot be left blank.");
            alert.showAndWait();
        }else if (description.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Description cannot be left blank.");
            alert.showAndWait();
        }else if (location.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Location cannot be left blank.");
            alert.showAndWait();
        }else if (type == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Type cannot be left blank.");
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

//Attempting to pre-fill values below so that errors for no selection can't be thrown?
//    public void comboMouseEvent(MouseEvent actionEvent) {
//        Appointments selectedType = apptTypeComboBox.getValue();
//        Contacts selectedContact = contactIdComboBox.getValue();
//        Users selectedUser = userIdComboBox.getValue();
//        Customers selectedCustomer = custIdComboBox.getValue();
//        if (selectedType!=null || selectedContact!=null || selectedUser!=null || selectedCustomer!=null) {
//            selectedType = apptTypeComboBox.setItems(DBAppointments.getAllAppointments());
//            selectedContact;
//            selectedUser;
//            selectedCustomer;
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You cannot leave drop-down's empty.");
//            alert.setTitle("Nothing Selected Yet");
//            alert.showAndWait();
//        }
//    }
}
