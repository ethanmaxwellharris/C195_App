package controller;

import dao.DBAppointments;
import dao.DBContacts;
import dao.DBCustomers;
import dao.DBUsers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;

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
    public ComboBox apptContactComboBox;
    public ComboBox apptTypeComboBox;
    public Button saveAppointmentButton;
    public Button cancelAppointmentButton;
    public DatePicker apptDatePicker;
    public ComboBox apptTimeComboBox; //Restrict time to 8:00AM to 10:00PM EST including weekends, 7 days a week. Doesn't need a pop-up error. Needs timezone dispalyed as local
    public ComboBox userIdComboBox;
    public ComboBox custIdComboBox; //Error needs to be displayed for overlapping customer appointments
    public ComboBox contactIdComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add appointments screen initialized");
        ObservableList<Appointments> type = DBAppointments.getAllAppointments();
        apptTypeComboBox.setItems(type);
        apptTypeComboBox.setPromptText("Select a Type");

        apptDatePicker.setValue(LocalDate.now());
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(18,0);

        while(start.isBefore(end.plusSeconds(1))){
            apptTimeComboBox.getItems().add(start);
            start = start.plusMinutes(30);
        }
        apptTimeComboBox.getSelectionModel().select(LocalTime.of(8,0)); //Change this to the time restriction of 8:00AM - 10:00PM EST

        ObservableList<Users> users = DBUsers.getAllUsers();
        userIdComboBox.setItems(users);
        userIdComboBox.setPromptText("Select a User ID");

        ObservableList<Customers> customers = DBCustomers.getAllCustomers();
        custIdComboBox.setItems(customers);
        custIdComboBox.setPromptText("Select a Customer ID");

        ObservableList<Contacts> contacts = DBContacts.getAllContacts();
        contactIdComboBox.setItems(contacts);
        contactIdComboBox.setPromptText("Select a Contact ID");
    }

    public void saveAppointmentOnAction(ActionEvent actionEvent) {
////        LocalDate date = apptDatePicker.getValue();
////        String stvalue = apptTimeComboBox.getTime();
////        LocalTime sTime = LocalTime.parse(stvalue);
////        LocalDateTime sldt = LocalDateTime.of(date, sTime); In theory this is all taking the value from the date picker plus the value from the combobox for the time and then insterting the value into the sldt expression. This will then be placed back into the database for any add's of appointments. In theory. You can combine them.
//    try {
//        int id = Integer.parseInt(apptIdTextField.getText());
//        //the rest
//
//        if (id.isBlank()) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setContentText("ID field cannot be left blank - try again");
//            alert.showAndWait();
//        } else {
//            DBAppointments.addAppointment(new Appointments(id, location, etc.));
//        }
//        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setScene(scene);
//        stage.show();
//        } catch {
//        }
    }


    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
