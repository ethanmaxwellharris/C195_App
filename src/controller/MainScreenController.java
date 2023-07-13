package controller;

import dao.DBAppointments;
import dao.DBCustomers;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    public Button addCustomerButton;
    public Button modifyCustomerButton;
    public Button deleteCustomerButton;
    public Button addAppointmentButton;
    public Button deleteAppointmentButton;
    public Button modifyAppointmentButton;
    public Button logoutButton;
    public Button userLogButton;
    public RadioButton weekViewRadio;
    public ToggleGroup viewToggleGroup;
    public RadioButton monthViewRadio;
    public RadioButton allViewRadio;
    public Label lambdaLabel;
    @FXML
    public TableView<Customers> customersTableView;
    @FXML
    public TableColumn<Customers, Integer> custIdCol;
    @FXML
    public TableColumn<Customers, String> custNameCol;
    @FXML
    public TableColumn<Customers, String> custAddressCol;
    @FXML
    public TableColumn<Customers, String> custZipCol;
    @FXML
    public TableColumn<Customers, String> custPhoneCol;
    @FXML
    public TableView<Appointments> appointmentsTableView;
    @FXML
    public TableColumn<Appointments, Integer> apptIDCol;
    @FXML
    public TableColumn<Appointments, String> apptTitleCol;
    @FXML
    public TableColumn<Appointments, String> apptLocCol;
    @FXML
    public TableColumn<Appointments, String> apptContactCol;
    @FXML
    public TableColumn<Appointments, String> apptTypeCol;
    @FXML
    public TableColumn<Appointments, LocalDateTime> apptStartCol;
    @FXML
    public TableColumn<Appointments, LocalDateTime> apptEndCol;
    @FXML
    public TableColumn<Appointments, Integer> apptCustIdCol;
    @FXML
    public TableColumn<Appointments, Integer> apptUserIdCol;

//    private static ObservableList<Customers> getAllCustomers = FXCollections.observableArrayList();
//    ObservableList<Appointments> getAllAppointments = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main screen has been initialized");
        //Confirmed Insert Success
//        int rowsAffected = 0;
//        try {
//            rowsAffected = DBCustomers.deleteCustomer(4);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        if(rowsAffected > 0){
//            System.out.println("Delete Success");
//        } else {
//            System.out.println("Delete Failed");
//        }

        //Filling Content of TableViews
        //Filling Customer TableView
        custIdCol.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("customerName"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
        custZipCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("postalCode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("phoneNumber"));
        customersTableView.setItems(DBCustomers.getAllCustomers());
        //Filling Appointment TableView
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptLocCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appointmentsTableView.setItems(DBAppointments.getAllAppointments());


        //Lambda for label generation
//        customersTableView.setItems(Customers.customersObservableList);
//
//        appointmentsTableView.setItems(Appointments.appointmentsObservableList);
//
//        lambdaLabel.setText("Select an Appointment");
//
//        appointmentsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//                System.out.println("Appointment Selection Made");
//                //lambdaLabel.setText("Appointment Selected for: " + newAppointment.getAppointmentLambda());
//            }
//        });
    }

    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("The add customer button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Customer Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionModifyCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("The modify customer button has been clicked!");
        //selectedModifyCustomer = customersTableView.getSelectionModel().getSelectedItem();

        //if (selectedModifyCustomer != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyCustomer.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Modify Customer Menu");
            stage.setScene(scene);
            stage.show();
        //} else {
            //Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have not selected a customer to modify yet.");
            //alert.setTitle("No customer selected to modify");
            //alert.showAndWait();
        //}
    }

    public void onActionAddAppointment(ActionEvent actionEvent) throws IOException {
        System.out.println("The add appointment button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionModifyAppointment(ActionEvent actionEvent) throws IOException {
        System.out.println("The modify appointment button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyAppointment.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionReports(ActionEvent actionEvent) throws IOException {
        System.out.println("The reports button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionUserLog(ActionEvent actionEvent) throws IOException {
        System.out.println("The user log button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/UserLog.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("User Log Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Ending Program...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thanks for checking my project out, have a great day!");
        alert.setTitle("Give Me A 100%!");
        alert.showAndWait();
        System.exit(0);
    }

}
