package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBCustomers {
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> cuList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int customerId = rs.getInt("Customer ID");
                String customerName = rs.getString("Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal Code");
                String phoneNumber = rs.getString("Phone");
                int divisionId = rs.getInt("State/Province");
                Customers cu = new Customers(customerId, customerName, address, postalCode, phoneNumber, divisionId);
                cuList.add(cu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cuList;
    }
}
