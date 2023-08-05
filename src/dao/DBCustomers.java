package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This abstract class provides methods to retrieve, add, modify, and delete customer data from the database.
 */
public abstract class DBCustomers {

    /**
     * Retrieves a list of all customers from the database.
     *
     * @return An ObservableList of Customers, each containing customer details.
     */
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> cuList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers;";
                    //"SELECT cu.Customer_ID, cu.Customer_Name, cu.Phone, cu.Address, cu.Postal_Code, fld.Division_ID, fld.Division FROM client_schedule.customers cu JOIN client_schedule.first_level_divisions fld ON fld.Division_ID = cu.Division_ID;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phoneNumber = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                //String division = rs.getString("Division");
                Customers cu = new Customers(customerId, customerName, address, postalCode, phoneNumber, divisionId);
                cuList.add(cu);
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return cuList;
    }

    /**
     * Adds a new customer to the database.
     *
     * @param customerName The name of the customer.
     * @param address The address of the customer.
     * @param postalCode The postal code of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param divisionId The ID of the division associated with the customer.
     * @throws SQLException If a SQL exception occurs during database interaction.
     */
    public static void addCustomer(String customerName, String address, String postalCode, String phoneNumber, int divisionId) throws SQLException {
        try {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNumber);
            ps.setInt(5, divisionId);
            ps.executeUpdate();
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    /**
     * Modifies an existing customer's data in the database.
     *
     * @param customerName The updated name of the customer.
     * @param address The updated address of the customer.
     * @param postalCode The updated postal code of the customer.
     * @param phoneNumber The updated phone number of the customer.
     * @param divisionId The updated ID of the division associated with the customer.
     * @param customerId The ID of the customer to be modified.
     * @throws SQLException If a SQL exception occurs during database interaction.
     */
    public static void modifyCustomer(String customerName, String address, String postalCode, String phoneNumber, int divisionId, int customerId) throws SQLException {
        try {
            String sql = "UPDATE client_schedule.customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNumber);
            ps.setInt(5, divisionId);
            ps.setInt(6, customerId);
            ps.executeUpdate();
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    /**
     * Deletes a customer and their associated appointments from the database.
     *
     * @param customerId The ID of the customer to be deleted.
     * @throws SQLException If a SQL exception occurs during database interaction.
     */
    public static void deleteCustomer(int customerId) throws SQLException {
            String sqla = "DELETE FROM client_schedule.appointments WHERE Customer_ID = ?";
            String sqlc = "DELETE FROM client_schedule.customers WHERE Customer_ID = ?";
            PreparedStatement psa = DBConnection.connection.prepareStatement(sqla);
            PreparedStatement psc = DBConnection.connection.prepareStatement(sqlc);
            psa.setInt(1, customerId);
            psa.executeUpdate();
            psc.setInt(1, customerId);
            psc.executeUpdate();
    }
}
