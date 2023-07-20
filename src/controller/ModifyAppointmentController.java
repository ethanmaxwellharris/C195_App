package controller;

import dao.DBAppointments;
import dao.DBContacts;
import dao.DBCustomers;
import dao.DBUsers;
import javafx.collections.FXCollections;
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
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {
    public TextField apptIdTextField;
    public TextField apptTitleTextField;
    public TextField apptDescriptionTextField;
    public TextField apptLocationTextField;
    public ComboBox<Contacts> apptContactComboBox;
    public ComboBox<Appointments> apptTypeComboBox;
    public Button saveAppointmentButton;
    public Button cancelAppointmentButton;
    public ComboBox<Customers> custIdComboBox;
    public ComboBox<Users> userIdComboBox;
    public ComboBox apptStartTimeComboBox;
    public ComboBox<Contacts> contactIdComboBox;
    public DatePicker apptEndDatePicker;
    public ComboBox apptEndTimeComboBox;
    public DatePicker apptStartDatePicker;

    public void saveAppointmentOnAction(ActionEvent actionEvent) {
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Appointments selectedModifyAppointment = MainScreenController.getModifyAppointment();
        ObservableList<Appointments> appointments = DBAppointments.getAllAppointments();
        ObservableList<Customers> customers = DBCustomers.getAllCustomers();
        ObservableList<Users> users = DBUsers.getAllUsers();
        ObservableList<Contacts> contacts = DBContacts.getAllContacts();

        try {
            apptIdTextField.setText(String.valueOf(selectedModifyAppointment.getCustomerId()));
            apptTitleTextField.setText(String.valueOf(selectedModifyAppointment.getTitle()));
            apptDescriptionTextField.setText(String.valueOf(selectedModifyAppointment.getDescription()));
            apptLocationTextField.setText(String.valueOf(selectedModifyAppointment.getLocation()));
            apptContactComboBox.setItems(DBContacts.getAllContacts());
            apptContactComboBox.getSelectionModel().select(selectedModifyAppointment.getContactId());
            apptStartDatePicker.setValue(selectedModifyAppointment.getStart().toLocalDate());
            apptStartTimeComboBox.setValue(selectedModifyAppointment.getStart().toLocalTime());
            apptEndDatePicker.setValue(selectedModifyAppointment.getStart().toLocalDate());
            apptEndTimeComboBox.setValue(selectedModifyAppointment.getStart().toLocalTime());
            custIdComboBox.setItems(DBCustomers.getAllCustomers());
            custIdComboBox.getSelectionModel().select(selectedModifyAppointment.getCustomerId());
            userIdComboBox.setItems(DBUsers.getAllUsers());
            userIdComboBox.getSelectionModel().select(selectedModifyAppointment.getUserId());
            contactIdComboBox.setItems(DBContacts.getAllContacts());
            contactIdComboBox.getSelectionModel().select(selectedModifyAppointment.getContactId());

            ObservableList<String> appointmentTypes = FXCollections.observableArrayList();
            for (Appointments appointment : DBAppointments.getAllAppointments()) {
                String type = appointment.getType();
                if (type != null && !type.isEmpty() && !appointmentTypes.contains(type)) {
                    appointmentTypes.add(type);
                }
            }
            appointmentTypes.addAll("Strategy Meeting", "Review Session");

            apptTypeComboBox.setItems(DBAppointments.getAllAppointments());

            //apptTypeComboBox.getSelectionModel().select(selectedModifyAppointment.getType());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
