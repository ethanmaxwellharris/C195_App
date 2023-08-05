package controller;

import dao.DBAppointments;
import dao.DBCustomers;
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
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML public Button addCustomerButton;
    @FXML public Button modifyCustomerButton;
    @FXML public Button deleteCustomerButton;
    @FXML public Button addAppointmentButton;
    @FXML public Button deleteAppointmentButton;
    @FXML public Button modifyAppointmentButton;
    @FXML public Button logoutButton;
    @FXML public Button userLogButton;
    @FXML public RadioButton weekViewRadio;
    @FXML public ToggleGroup viewToggleGroup;
    @FXML public RadioButton monthViewRadio;
    @FXML public RadioButton allViewRadio;
    @FXML public Label lambdaLabel;
    @FXML public TableView<Customers> customersTableView;
    @FXML public TableColumn<Customers, Integer> custIdCol;
    @FXML public TableColumn<Customers, String> custNameCol;
    @FXML public TableColumn<Customers, String> custAddressCol;
    @FXML public TableColumn<Customers, String> custZipCol;
    @FXML public TableColumn<Customers, String> custPhoneCol;
    @FXML public TableColumn<Customers, String> custDivisionCol;
    @FXML public TableView<Appointments> appointmentsTableView;
    @FXML public TableColumn<Appointments, Integer> apptIDCol;
    @FXML public TableColumn<Appointments, String> apptTitleCol;
    @FXML public TableColumn<Appointments, String> apptDescCol;
    @FXML public TableColumn<Appointments, String> apptLocCol;
    @FXML public TableColumn<Appointments, String> apptContactCol;
    @FXML public TableColumn<Appointments, String> apptTypeCol;
    @FXML public TableColumn<Appointments, LocalDateTime> apptStartCol;
    @FXML public TableColumn<Appointments, LocalDateTime> apptEndCol;
    @FXML public TableColumn<Appointments, Integer> apptCustIdCol;
    @FXML public TableColumn<Appointments, Integer> apptUserIdCol;
    public static Customers selectedModifyCustomer;
    public static Appointments selectedModifyApppointment;
    @FXML public Label localTimeLabel;
    @FXML public Label estTimeLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main screen has been initialized");
        /*Setting TimeZone stuff*/
        LocalDateTime localNow = LocalDateTime.now();
        String formattedLocalTime = localNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        localTimeLabel.setText("The local time is: " + formattedLocalTime);
        ZoneId estZone = ZoneId.of("America/New_York");
        ZonedDateTime estNow = ZonedDateTime.now(estZone);
        String formattedEstTime = estNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        estTimeLabel.setText("The time in EST is: " + formattedEstTime);
        ZoneId zoneId = ZoneId.systemDefault();
        /*Populating TableViews*/
        custIdCol.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("customerName"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
        custZipCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("postalCode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("phoneNumber"));
        custDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customersTableView.setItems(DBCustomers.getAllCustomers());
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appointmentsTableView.setItems(DBAppointments.getAllAppointments());

        /*Lambda #1 Label Generation*/
        lambdaLabel.setText("Select an Appointment");
        appointmentsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                LocalTime startTime = newSelection.getStart().toLocalTime();
                LocalTime endTime = newSelection.getEnd().toLocalTime();
                Duration duration = Duration.between(startTime, endTime);
                String location = newSelection.getLocation();
                lambdaLabel.setText("Appointment selected for a(n) " + newSelection + " session."
                        + "\n"
                        + "This is scheduled to last " + duration.toMinutes() + " minutes at " + location + ".");
            }
        });

        /*Lambda #2*/
        viewToggleGroup.selectedToggleProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection == allViewRadio) {
                appointmentsTableView.setItems(DBAppointments.getAllAppointments());
            } else if (newSelection == monthViewRadio) {
                appointmentsTableView.setItems(DBAppointments.getMonthAppointments());
            } else if (newSelection == weekViewRadio) {
                appointmentsTableView.setItems(DBAppointments.getWeekAppointments());
            }
        });

        /*Appointment in 15 Minutes*/
        ObservableList<Appointments> soonAppointments = DBAppointments.getAppointmentsIn15();
        LocalDateTime currentTime = LocalDateTime.now();
        for (Appointments appointment : soonAppointments) {
            LocalDateTime startTime = appointment.getStart();
            LocalDateTime endTime = appointment.getEnd();
            long timeDifference = ChronoUnit.MINUTES.between(currentTime, startTime); // Calculate time difference in minutes
            if (timeDifference > 0 && timeDifference <= 15) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "An appointment is in " + timeDifference + " minutes" + "\n" + "The appointment ID is: " + appointment.getAppointmentId() + " starting at " + startTime);
                alert.setTitle("Appointment Upcoming");
                alert.setHeaderText("Heads up!");
                alert.show();
            }
        }
        if (soonAppointments.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "There are no appointments within the next 15 minutes");
            alert.setTitle("No Upcoming Appointments");
            alert.setHeaderText("No worries");
            alert.show();
        }
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
        selectedModifyCustomer = customersTableView.getSelectionModel().getSelectedItem();

        if (selectedModifyCustomer != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyCustomer.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Modify Customer Menu");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have not selected a customer to modify yet.");
            alert.setTitle("No customer selected to modify");
            alert.showAndWait();
        }
    }

    public void onActionDeleteCustomer(ActionEvent actionEvent) {
        Customers selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();

        if(selectedCustomer != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This action will delete the selected customer and any associated appointments - do you wish to continue?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    DBCustomers.deleteCustomer(selectedCustomer.getCustomerId());
                    customersTableView.setItems(DBCustomers.getAllCustomers());
                    appointmentsTableView.setItems(DBAppointments.getAllAppointments());
                    Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION, "Customer " + selectedCustomer.getCustomerName() + " has been deleted successfully.");
                    newAlert.show();
                } catch (SQLException x) {
                    x.printStackTrace();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have not select a customer to delete yet.");
            alert.setTitle("No Customer Selected to Delete");
            alert.showAndWait();
        }
    }

    public static Customers getModifyCustomer() {
        return selectedModifyCustomer;
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

    public void onActionModifyAppointment(ActionEvent actionEvent) throws IOException{
        System.out.println("The modify appointment button has been clicked!");
        selectedModifyApppointment = appointmentsTableView.getSelectionModel().getSelectedItem();

        if (selectedModifyApppointment != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyAppointment.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Modify Appointment Menu");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have not selected an appointment to modify yet.");
            alert.setTitle("No appointment selected to modify");
            alert.showAndWait();
        }
    }

    public void onActionDeleteAppointment(ActionEvent actionEvent) throws IOException {
        Appointments selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();

        if(selectedAppointment != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This action will delete the selected appointments - do you wish to continue?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                DBAppointments.deleteAppointment(selectedAppointment.getAppointmentId());
                appointmentsTableView.setItems(DBAppointments.getAllAppointments());
                Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment ID " + selectedAppointment.getAppointmentId() + " which had a type of " + selectedAppointment.getType() + " has been deleted successfully.");
                newAlert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have not select an appointment to delete yet.");
            alert.setTitle("No Appointment Selected to Delete");
            alert.showAndWait();
        }
    }

    public static Appointments getModifyAppointment() {
        return selectedModifyApppointment;
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

    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Ending Program...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thanks for checking my project out, have a great day!");
        alert.setTitle("Give Me A 100%!");
        alert.showAndWait();
        System.exit(0);
    }
}
