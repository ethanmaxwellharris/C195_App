package controller;

import dao.DBCountries;
import dao.DBCustomers;
import dao.DBFirstLevelDivisions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Countries;
import model.FirstLevelDivisions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This controller class manages the addition of new customer information.
 * It handles user interactions and inserts customer data into the database.
 *
 * @author      Ethan Harris
 * @version     %I%
 * @since       1.0
 */
public class AddCustomerController implements Initializable {
    public TextField customerIdTextField;
    public TextField customerNameTextField;
    public TextField customerPhoneTextField;
    public TextField customerAddressTextField;
    public TextField customerPostalTextField;
    public ComboBox<Countries> customerCountryComboBox;
    public ComboBox<FirstLevelDivisions> customerDivisionComboBox;
    public Button saveCustomerButton;
    public Button cancelCustomerButton;
    private static final int maxTextLength50 = 50;
    private static final int maxTextLength100 = 100;
    /**
     * Initializes the "Add Customer" screen.
     *
     * This method is automatically called when the "Add Customer" screen is loaded. It performs the following tasks:
     * - Retrieves a list of countries from the database and populates the customer country combo box.
     * - Sets a default prompt text for the customer country combo box.
     * - Sets a default prompt text for the customer division combo box to indicate that a country must be selected first.
     *
     * @param url            The URL location of the FXML file.
     * @param resourceBundle The resources for the FXML file.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Countries> countries = DBCountries.getAllCountries();
        customerCountryComboBox.setItems(countries);
        customerCountryComboBox.setPromptText("Select a Country");
        customerDivisionComboBox.setPromptText("Select Country First");
    }
    /**
     * Handles the "Save" button action for adding a new customer.
     *
     * This method is called when the user clicks the "Save" button to add a new customer. It performs the following tasks:
     * - Retrieves customer data from input fields (name, address, postal code, phone number, division ID).
     * - Validates input data, checking for character length limits and empty fields.
     * - Adds the new customer to the database if input data is valid.
     * - Navigates back to the main menu screen upon successful customer addition.
     * - Displays relevant alerts for invalid or missing input.
     *
     * @param actionEvent The event triggered by clicking the "Save" button.
     * @throws SQLException If a database access error occurs.
     * @throws IOException  If an I/O error occurs when loading the main menu screen.
     */
    public void saveCustomerOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        try {
            String customerName = customerNameTextField.getText();
            String address = customerAddressTextField.getText();
            String postalCode = customerPostalTextField.getText();
            String phoneNumber = customerPhoneTextField.getText();
            int divisionId = customerDivisionComboBox.getSelectionModel().getSelectedItem().getDivision_id();
            if (customerNameTextField.getText().length() > maxTextLength50){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Name must be " + maxTextLength50 + " characters or less. The current length is " + customerNameTextField.getText().length());
                alert.setTitle("Excessive characters in name field!");
                alert.showAndWait();
            }else if(customerNameTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Description cannot be left blank.");
                alert.showAndWait();
            }else if (customerPhoneTextField.getText().length() > maxTextLength50){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Phone number must be " + maxTextLength50 + " characters or less. The current length is " + customerPhoneTextField.getText().length());
                alert.setTitle("Excessive characters in phone field!");
                alert.showAndWait();
            }else if(customerPhoneTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Phone number cannot be left blank.");
                alert.showAndWait();
            }else if(customerAddressTextField.getText().length() > maxTextLength100){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Address must be " + maxTextLength100 + " characters or less. The current length is " + customerAddressTextField.getText().length());
                alert.setTitle("Excessive characters in address field!");
                alert.showAndWait();
            }else if(customerAddressTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Address cannot be left blank.");
                alert.showAndWait();
            }else if (customerPostalTextField.getText().length() > maxTextLength50){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Postal code must be " + maxTextLength50 + " characters or less. The current length is " + customerPostalTextField.getText().length());
                alert.setTitle("Excessive characters in postal field!");
                alert.showAndWait();
            }else if(customerPostalTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Postal code cannot be left blank.");
                alert.showAndWait();
            }else {
                DBCustomers.addCustomer(customerName, address, postalCode, phoneNumber, divisionId);
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Drop down menu's cannot be unselected");
            alert.showAndWait();
        }
    }
    /**
     * Handles the "Cancel" button action to go back to the main menu.
     *
     * @param actionEvent The action event triggered by the button.
     * @throws IOException If an I/O error occurs.
     */
    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Handles mouse event on the division combo box based on selected country.
     * Displays division options based on the selected country.
     *
     * @param actionEvent The mouse event triggered by the combo box.
     */
    public void divisionMouseEvent(MouseEvent actionEvent) {
        Countries selectedCountry = customerCountryComboBox.getValue();
        if (selectedCountry != null) {
            switch (selectedCountry.toString()) {
                case "U.S" -> customerDivisionComboBox.setItems(DBFirstLevelDivisions.getUSFirstLevelDivisions());
                case "Canada" -> customerDivisionComboBox.setItems(DBFirstLevelDivisions.getCanFirstLevelDivisions());
                case "UK" -> customerDivisionComboBox.setItems(DBFirstLevelDivisions.getUKFirstLevelDivisions());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have not select a country yet.");
            alert.setTitle("No Country Selected Yet");
            alert.showAndWait();
        }
    }

}
