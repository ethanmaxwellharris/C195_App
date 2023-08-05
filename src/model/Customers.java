package model;

/**
 * Represents a customer with their details and properties.
 */
public class Customers {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private int divisionId;
    /**
     * Constructs a Customer object with specified details.
     *
     * @param customerId The ID of the customer.
     * @param customerName The name of the customer.
     * @param address The address of the customer.
     * @param postalCode The postal code of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param divisionId The ID of the division associated with the customer.
     */
    public Customers(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
    }
    /**
     * Returns a string representation of the customer's ID and name.
     *
     * @return A formatted string containing the customer's ID and name.
     */
    @Override
    public String toString(){
        return "#" + String.valueOf(customerId) + " " + customerName;
    }

    /**
     * Returns the id of the customer.
     *
     * @return The id of the customer.
     */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * Sets the id of the customer.
     *
     * @param customerId The id of the customer to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**
     * Returns the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Sets the name of the customer.
     *
     * @param customerName The name of the customer to set.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * Returns the address of the customer.
     *
     * @return The address of the customer.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Sets the address of the customer.
     *
     * @param address The address of the customer to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Returns the postal code of the customer.
     *
     * @return The postal code of the customer.
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**
     * Sets the postal code of the customer.
     *
     * @param postalCode The postal code of the customer to set.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**
     * Returns the phone number of the customer.
     *
     * @return The phone number of the customer.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Sets the phone number of the customer.
     *
     * @param phoneNumber The phone number of the customer to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Returns the first-level division id of the customer.
     *
     * @return The first-level division id of the customer.
     */
    public int getDivisionId() { return divisionId; }
    /**
     * Sets the division id of the customer.
     *
     * @param divisionId The division id of the customer to set.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}
