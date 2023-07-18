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
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    public void saveCustomerOnAction(ActionEvent actionEvent) throws SQLException, IOException {
            try {
                String customerName = customerNameTextField.getText();
                String address = customerAddressTextField.getText();
                String postalCode = customerPostalTextField.getText();
                String phoneNumber = customerPhoneTextField.getText();
                Integer divisionId = customerDivisionComboBox.getSelectionModel().getSelectedItem().getDivision_id();

                if (customerName.isBlank() || address.isBlank() || postalCode.isBlank() || phoneNumber.isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have not filled all forms.");
                    alert.setTitle("No Form Can be Left Blank");
                    alert.showAndWait();
                } else {
                    DBCustomers.addCustomer(customerName, address, postalCode, phoneNumber, divisionId);

                    Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.show();
                }
            } catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Field(s) must have valid values - try again");
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
            alert.setTitle("No Country Selected Yet");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Countries> countries = DBCountries.getAllCountries();
        customerCountryComboBox.setItems(countries);
        customerCountryComboBox.setPromptText("Select a Country");
        customerDivisionComboBox.setPromptText("Select Country First");
    }

}
