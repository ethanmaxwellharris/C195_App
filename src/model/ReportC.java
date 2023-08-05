package model;

/**
 * Represents a report containing customer information along with division and country details.
 */
public class ReportC {
    private int customerId;
    private String customerName;
    private String divisionName;
    private String countryName;

    /**
     * Constructs a ReportC object with the specified customer information.
     *
     * @param customerId The ID of the customer.
     * @param customerName The name of the customer.
     * @param divisionName The name of the division associated with the customer.
     * @param countryName The name of the country associated with the customer's division.
     */
    public ReportC(int customerId, String customerName, String divisionName, String countryName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.divisionName = divisionName;
        this.countryName = countryName;
    }
    /**
     * Gets the customer ID.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * Sets the customer ID.
     *
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**
     * Gets the customer name.
     *
     * @return The customer name.
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Sets the customer name.
     *
     * @param customerName The customer name to set.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * Gets the division name.
     *
     * @return The division name.
     */
    public String getDivisionName() {
        return divisionName;
    }
    /**
     * Sets the division name.
     *
     * @param divisionName The division name to set.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    /**
     * Gets the country name.
     *
     * @return The country name.
     */
    public String getCountryName() {
        return countryName;
    }
    /**
     * Sets the country name.
     *
     * @param countryName The country name to set.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
