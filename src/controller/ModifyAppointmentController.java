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
import java.util.ResourceBundle;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Appointments selectedModifyAppointment = MainScreenController.getModifyAppointment();
        apptIdTextField.setText(String.valueOf(selectedModifyAppointment.getAppointmentId()));
        apptTitleTextField.setText(String.valueOf(selectedModifyAppointment.getTitle()));
        apptDescriptionTextField.setText(String.valueOf(selectedModifyAppointment.getDescription()));
        apptLocationTextField.setText(String.valueOf(selectedModifyAppointment.getLocation()));
        apptTypeComboBox.setItems(appointmentTypes);
        apptTypeComboBox.getSelectionModel().select(selectedModifyAppointment.getType());
        apptStartDatePicker.setValue(selectedModifyAppointment.getStart().toLocalDate());
        apptStartTimeComboBox.setValue(selectedModifyAppointment.getStart().toLocalTime());
        apptEndDatePicker.setValue(selectedModifyAppointment.getStart().toLocalDate());
        apptEndTimeComboBox.setValue(selectedModifyAppointment.getStart().toLocalTime());
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

    public void saveAppointmentOnAction(ActionEvent actionEvent) {
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
