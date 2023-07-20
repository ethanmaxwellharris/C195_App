package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    @FXML public ComboBox reportAMonthComboBox;
    @FXML public ComboBox reportATypeComboBox;
    @FXML public ComboBox reportBContactComboBox;
    @FXML public ComboBox reportCMonthComboBox;
    @FXML public ComboBox reportCCountryComboBox;
    @FXML public TableView reportATableView;
    @FXML public TableColumn reportAMonthCol;
    @FXML public TableColumn reportATypeCol;
    @FXML public TableColumn reportAAmountCol;
    @FXML public TableView reportBTableView;
    @FXML public TableColumn reportBIdCol;
    @FXML public TableColumn reportBTitleCol;
    @FXML public TableColumn reportBLocationCol;
    @FXML public TableColumn reportBContactCol;
    @FXML public TableColumn reportBTypeCol;
    @FXML public TableColumn reportBDateCol;
    @FXML public TableColumn reportBTimeCol;
    @FXML public TableColumn reportBCustomerCol;
    @FXML public TableColumn reportBUserCol;
    @FXML public TableView reportCTableView;
    @FXML public TableColumn reportCIdCol;
    @FXML public TableColumn reportCTitleCol;
    @FXML public TableColumn reportCLocationCol;
    @FXML public TableColumn reportCContactCol;
    @FXML public TableColumn reportCTypeCol;
    @FXML public TableColumn reportCDateCol;
    @FXML public TableColumn reportCTimeCol;
    @FXML public TableColumn reportCCustomerCol;
    @FXML public TableColumn reportCUserCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports screen has been initialized");


    }

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
