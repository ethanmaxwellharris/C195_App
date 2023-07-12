package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class DBCustomers {
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> cuList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM client_schedule.customers;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phoneNumber = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                Customers cu = new Customers(customerId, customerName, address, postalCode, phoneNumber, divisionId);
                cuList.add(cu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cuList;
    }

    public static int modifyCustomer(String customerName, String address, String postalCode, String phoneNumber, int divisionId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?);";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setInt(5, divisionId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
