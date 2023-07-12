package controller;

import dao.DBAppointments;
import dao.DBCustomers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    public Button addCustomerButton;
    public Button modifyCustomerButton;
    public Button deleteCustomerButton;
    public Button addAppointmentButton;
    public Button deleteAppointmentButton;
    public Button modifyAppointmentButton;
    public Button logoutButton;
    public TableView<Appointments> appointmentsTableView;
    public TableView<Customers> customersTableView;
    public Button userLogButton;
    public RadioButton weekViewRadio;
    public ToggleGroup viewToggleGroup;
    public RadioButton monthViewRadio;
    public RadioButton allViewRadio;
    public Label lambdaLabel;
    public TableColumn custIdCol;
    public TableColumn custNameCol;
    public TableColumn custAddressCol;
    public TableColumn custZipCol;
    public TableColumn custPhoneCol;
    public TableColumn apptIDCol;
    public TableColumn apptTitleCol;
    public TableColumn apptLocCol;
    public TableColumn apptContactCol;
    public TableColumn apptTypeCol;
    public TableColumn apptStartCol;
    public TableColumn apptEndCol;
    public TableColumn apptCustIdCol;
    public TableColumn apptUserIdCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main screen has been initialized");
        //Confirmed Insert Success
        int rowsAffected = 0;
        try {
            rowsAffected = DBCustomers.modifyCustomer("Dingle Fluven", "700 Club Lane", "24566", "555-555-5554", 5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(rowsAffected > 0){
            System.out.println("Insert Success");
        } else {
            System.out.println("Insert Failed");
        }

        //Filling Content of TableViews
        //Filling Customer TableView
        customersTableView.setItems(DBCustomers.getAllCustomers());
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        custZipCol.setCellValueFactory(new PropertyValueFactory<>("Postal Code"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        //Filling Appointment TableView
        appointmentsTableView.setItems(DBAppointments.getAllAppointments());
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        apptLocCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer ID"));
        apptUserIdCol.setCellValueFactory(new PropertyValueFactory<>("User ID"));


        //Lambda for label generation
        customersTableView.setItems(Customers.customersObservableList);

        appointmentsTableView.setItems(Appointments.appointmentsObservableList);

        lambdaLabel.setText("Select an Appointment");

        appointmentsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("Appointment Selection Made");
                //lambdaLabel.setText("Appointment Selected for: " + newAppointment.getAppointmentLambda());
            }
        });
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
