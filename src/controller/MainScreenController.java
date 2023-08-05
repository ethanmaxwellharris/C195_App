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

/**
 * The controller class for the main screen of the application.
 *
 * This class handles user interactions and manages the behavior of the main screen.
 * It controls the display of customer and appointment data, provides navigation to
 * various functionalities, and handles events such as adding, modifying, and deleting
 * customers and appointments.
 *
 * @author      Ethan Harris
 * @version     %I%
 * @since       1.0
 */
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

    /**
     * Initializes the Main Screen controller.
     *
     * This method is invoked when the Main Screen is loaded. It performs the following tasks:
     * - Displays a message indicating the initialization of the Main Screen.
     * - Sets and displays the current local time and Eastern Standard Time (EST) using the ZoneId class and LocalDateTime.
     * - Populates the TableView for displaying customer and appointment data, specifying cell value factories for columns.
     * - Defines two lambda expressions:
     *   - Lambda #1: Updates the lambdaLabel with information about the selected appointment's duration and location.
     *   - Lambda #2: Dynamically updates the appointmentsTableView based on the selected view toggle (All, Month, Week).
     * - Notifies the user about appointments scheduled within the next 15 minutes using Alerts.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
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
    /**
     * Handles the action when the "Add Customer" button is clicked.
     *
     * This method is invoked when the "Add Customer" button is clicked on the main screen.
     * It performs the following tasks:
     * - Displays a message in the console indicating that the "Add Customer" button has been clicked.
     * - Loads the "AddCustomer.fxml" view using the FXMLLoader.
     * - Retrieves the current stage from the action event and sets its scene to the "AddCustomer.fxml" view.
     * - Sets the title of the stage to "Add Customer Menu" and displays the stage.
     *
     * @param actionEvent The ActionEvent triggered by clicking the "Add Customer" button.
     * @throws IOException If an I/O exception occurs during loading of the FXML file.
     */
    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("The add customer button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Customer Menu");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Handles the action when the "Modify Customer" button is clicked.
     *
     * This method is invoked when the "Modify Customer" button is clicked on the main screen.
     * It performs the following tasks:
     * - Displays a message in the console indicating that the "Modify Customer" button has been clicked.
     * - Retrieves the selected customer from the table view.
     * - If a customer is selected:
     *   - Loads the "ModifyCustomer.fxml" view using the FXMLLoader.
     *   - Retrieves the current stage from the action event and sets its scene to the "ModifyCustomer.fxml" view.
     *   - Sets the title of the stage to "Modify Customer Menu" and displays the stage.
     * - If no customer is selected:
     *   - Displays an information alert indicating that no customer has been selected for modification.
     *
     * @param actionEvent The ActionEvent triggered by clicking the "Modify Customer" button.
     * @throws IOException If an I/O exception occurs during loading of the FXML file.
     */
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
    /**
     * Handles the action when the "Delete Customer" button is clicked.
     *
     * This method is invoked when the "Delete Customer" button is clicked on the main screen.
     * It performs the following tasks:
     * - Retrieves the selected customer from the table view.
     * - If a customer is selected:
     *   - Displays a confirmation alert to confirm the deletion of the selected customer and its associated appointments.
     *   - If the user confirms deletion:
     *     - Attempts to delete the selected customer and its associated appointments from the database.
     *     - Updates the customers and appointments table views.
     *     - Displays a success alert indicating that the customer has been deleted.
     *   - If the user cancels deletion, no action is taken.
     * - If no customer is selected:
     *   - Displays an information alert indicating that no customer has been selected for deletion.
     *
     * @param actionEvent The ActionEvent triggered by clicking the "Delete Customer" button.
     */
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
    /**
     * Retrieves the customer selected for modification.
     *
     * This method returns the customer object that has been selected for modification in the application.
     * The customer object represents the customer whose details are intended to be modified.
     *
     * @return The customer object selected for modification, or null if no customer has been selected.
     */
    public static Customers getModifyCustomer() {
        return selectedModifyCustomer;
    }
    /**
     * Handles the "Add Appointment" button action event.
     *
     * This method is invoked when the "Add Appointment" button is clicked. It performs the following tasks:
     * - Loads the AddAppointment.fxml view to allow the user to add a new appointment.
     * - Sets up a new stage and scene to display the Add Appointment menu.
     * - Opens the Add Appointment menu for the user to input appointment details.
     *
     * @param actionEvent The action event triggered by clicking the "Add Appointment" button.
     * @throws IOException If an I/O exception occurs during the loading of the AddAppointment.fxml view.
     */
    public void onActionAddAppointment(ActionEvent actionEvent) throws IOException {
        System.out.println("The add appointment button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Handles the "Modify Appointment" button action event.
     *
     * This method is invoked when the "Modify Appointment" button is clicked. It performs the following tasks:
     * - Retrieves the selected appointment from the appointmentsTableView.
     * - Loads the ModifyAppointment.fxml view to allow the user to modify the selected appointment's details.
     * - Sets up a new stage and scene to display the Modify Appointment menu.
     * - If an appointment is selected, opens the Modify Appointment menu.
     * - If no appointment is selected, displays an information alert indicating the need for selection.
     *
     * @param actionEvent The action event triggered by clicking the "Modify Appointment" button.
     * @throws IOException If an I/O exception occurs during the loading of the ModifyAppointment.fxml view.
     */
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
    /**
     * Handles the "Delete Appointment" button action event.
     *
     * This method is invoked when the "Delete Appointment" button is clicked. It performs the following tasks:
     * - Retrieves the selected appointment from the appointmentsTableView.
     * - Displays a confirmation dialog to confirm the deletion of the selected appointment.
     * - If the user confirms the deletion, deletes the selected appointment from the database using DBAppointments.deleteAppointment.
     * - Updates the appointmentsTableView with the latest appointment data after deletion.
     * - Displays a confirmation alert indicating the successful deletion of the appointment.
     * - If no appointment is selected, displays an information alert indicating the need for selection.
     *
     * @param actionEvent The action event triggered by clicking the "Delete Appointment" button.
     * @throws IOException If an I/O exception occurs during the execution of the method.
     */
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
    /**
     * Retrieves the selected appointment for modification.
     *
     * This method returns the appointment that has been selected by the user for modification.
     * The selected appointment is stored in the static variable "selectedModifyApppointment."
     * This method allows other parts of the application to access the selected appointment for modification.
     *
     * @return The appointment selected for modification, or null if no appointment has been selected.
     */
    public static Appointments getModifyAppointment() {
        return selectedModifyApppointment;
    }
    /**
     * Handles the event when the "Reports" button is clicked.
     *
     * This method is called when the user clicks the "Reports" button to navigate to the reports menu.
     * It loads the "Reports.fxml" view and displays it in a new stage with the title "Reports Menu."
     *
     * @param actionEvent The action event triggered by clicking the "Reports" button.
     * @throws IOException If an I/O error occurs while loading the "Reports.fxml" view.
     */
    public void onActionReports(ActionEvent actionEvent) throws IOException {
        System.out.println("The reports button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Handles the event when the "Logout" button is clicked.
     *
     * This method is called when the user clicks the "Logout" button to end the program.
     * It displays an information alert with a thank-you message and exits the application.
     *
     * @param actionEvent The action event triggered by clicking the "Logout" button.
     */
    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Ending Program...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thanks for checking my project out, have a great day!");
        alert.setTitle("Give Me A 100%!");
        alert.showAndWait();
        System.exit(0);
    }
}
