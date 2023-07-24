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
import model.Customers;
import model.FirstLevelDivisions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {
    public TextField customerIdTextField;
    public TextField customerNameTextField;
    public TextField customerPhoneTextField;
    public TextField customerAddressTextField;
    public TextField customerPostalTextField;
    public ComboBox<Countries> customerCountryComboBox;
    public ComboBox<FirstLevelDivisions> customerDivisionComboBox;
    public Button saveCustomerButton;
    public Button cancelCustomerButton;
    private FirstLevelDivisions selectedDivision;
    private static final int maxTextLength50 = 50;
    private static final int maxTextLength100 = 100;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Modify Customer Screen Initialized");
        Customers selectedModifyCustomer = MainScreenController.getModifyCustomer();
        ObservableList<Countries> countries = DBCountries.getAllCountries();

        try {
            customerIdTextField.setText(String.valueOf(selectedModifyCustomer.getCustomerId()));
            customerNameTextField.setText(String.valueOf(selectedModifyCustomer.getCustomerName()));
            customerPhoneTextField.setText(String.valueOf(selectedModifyCustomer.getPhoneNumber()));
            customerAddressTextField.setText(String.valueOf(selectedModifyCustomer.getAddress()));
            customerPostalTextField.setText(String.valueOf(selectedModifyCustomer.getPostalCode()));

            for (FirstLevelDivisions fld :
                    DBFirstLevelDivisions.getAllFirstLevelDivisions()) {
                if (selectedModifyCustomer.getDivisionId() == fld.getDivision_id()) {
                    this.selectedDivision = fld;
                }
            }
            customerDivisionComboBox.setValue(selectedDivision);
            int divisionID = customerDivisionComboBox.getValue().getDivision_id();
            customerCountryComboBox.setItems(countries);

            Countries selectedCountry1;
            if (divisionID <= 54) {
                selectedCountry1 = DBCountries.getAllCountries().get(0);
                customerCountryComboBox.setValue(selectedCountry1);
            }
            if (divisionID >= 60 && divisionID <= 72) {
                selectedCountry1 = DBCountries.getAllCountries().get(2);
                customerCountryComboBox.setValue(selectedCountry1);
            } else if (divisionID >= 101 && divisionID <= 104) {
                selectedCountry1 = DBCountries.getAllCountries().get(1);
                customerCountryComboBox.setValue(selectedCountry1);
            }
            {
                Countries selectedCountry = customerCountryComboBox.getValue();
                if (selectedCountry.toString().equals("U.S")) {
                    ObservableList<FirstLevelDivisions> usfld =
                            DBFirstLevelDivisions.getUSFirstLevelDivisions();
                    customerDivisionComboBox.setItems(usfld);
                }
                if (selectedCountry.toString().equals("UK")) {
                    ObservableList<FirstLevelDivisions> ukfld =
                            DBFirstLevelDivisions.getUKFirstLevelDivisions();
                    customerDivisionComboBox.setItems(ukfld);
                }
                if (selectedCountry.toString().equals("Canada")) {
                    ObservableList<FirstLevelDivisions> canfld =
                            DBFirstLevelDivisions.getCanFirstLevelDivisions();
                    customerDivisionComboBox.setItems(canfld);
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveCustomerOnAction(ActionEvent actionEvent) {
        try {
            Integer customerId = Integer.parseInt(customerIdTextField.getText());
            String customerName = customerNameTextField.getText();
            String address = customerAddressTextField.getText();
            String postalCode = customerPostalTextField.getText();
            String phoneNumber = customerPhoneTextField.getText();
            Integer divisionId = customerDivisionComboBox.getSelectionModel().getSelectedItem().getDivision_id();

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
            } else {
                DBCustomers.modifyCustomer(customerName, address, postalCode, phoneNumber, divisionId, customerId);

                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            }
        } catch(Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Drop down menu's cannot be unselected");
            alert.showAndWait();
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

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
            alert.setTitle("No Country Selected");
            alert.showAndWait();
        }
    }
}
