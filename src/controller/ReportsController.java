package controller;

import dao.DBReports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ReportB;
import model.ReportC;
import model.Reports;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * This controller class is responsible for populating the Reports screen with relevant data.
 * The reports page displays three unique reports which pull down data from the database to fulfill each reports intended request.
 * The controller contains an exit which returns you to the main menu.
 *
 * @author Ethan Harris
 * @version %I%
 * @since 1.0
 */
public class ReportsController implements Initializable {
    @FXML public TableView<Reports> reportATableView;
    @FXML public TableColumn<Reports, Integer> reportAMonthCol;
    @FXML public TableColumn<Reports, String> reportATypeCol;
    @FXML public TableColumn<Reports, Integer> reportAAmountCol;
    @FXML public TableView<ReportB> reportBTableView;
    @FXML public TableColumn<Reports, Integer> reportBIdCol;
    @FXML public TableColumn<Reports, String> reportBTitleCol;
    @FXML public TableColumn<Reports, String> reportBContactCol;
    @FXML public TableColumn<Reports, String> reportBTypeCol;
    @FXML public TableColumn<Reports, String> reportBCustomerCol;
    @FXML public TableColumn<Reports, Timestamp> reportBStartCol;
    @FXML public TableColumn<Reports, Timestamp> reportBEndCol;
    @FXML public TableColumn<Reports, String> reportBDescriptionCol;
    @FXML public TableView<ReportC> reportCTableView;
    @FXML public TableColumn<Reports, Integer> reportCCustIdCol;
    @FXML public TableColumn<Reports, String> reportCNCol;
    @FXML public TableColumn<Reports, String> reportCDivisionCol;
    @FXML public TableColumn<Reports, String> reportCCountryCol;

    private final ObservableList<String> months = FXCollections.observableArrayList( "January", "February", "March", "April", "May", "June", "July","August","September"
            ,"October","November","December");
    private final ObservableList<String> appointmentTypes = FXCollections.observableArrayList("Planning Session", "De-Briefing", "Execution", "Monitor & Control");

    /**
     * Initializes the Reports screen.
     *
     * This method is automatically invoked when the Reports screen is loaded. It performs the following tasks:
     * - Logs a message indicating that the Reports screen has been initialized.
     * - Configures cell value factories to populate the TableView columns for each report.
     * - Populates the TableView for each report by retrieving data from the database using the DBReports class.
     *
     * The Reports screen displays three different reports:
     * - Report A: Shows the total number of appointments per month and appointment type.
     * - Report B: Lists appointment details including the contact, title, description, type, start, end, and customer information.
     * - Report C: Provides a summary of customers including customer ID, name, associated division, and country.
     *
     * Note: The data displayed in the reports is retrieved from the database using the corresponding methods in the DBReports class.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resource bundle to be used by this controller, or null if the controller doesn't require it.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports screen has been initialized");
        reportATypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportAMonthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        reportAAmountCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        reportATableView.setItems(DBReports.getReportA());
        reportBContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        reportBTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        reportBDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        reportBIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        reportBTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportBStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        reportBEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        reportBCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        reportBTableView.setItems(DBReports.getReportB());
        reportCCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        reportCNCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        reportCDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        reportCCountryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        reportCTableView.setItems(DBReports.getReportC());
    }
    /**
     * Handles the cancel action, returning to the main menu.
     *
     * @param actionEvent The ActionEvent triggered by clicking the cancel button.
     * @throws IOException If an I/O error occurs during loading the Main Menu.
     */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
