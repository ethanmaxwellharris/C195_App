package model;

public class ReportC {
    private int customerId;
    private String customerName;
    private String divisionName;
    private String countryName;

    public ReportC(int customerId, String customerName, String divisionName, String countryName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.divisionName = divisionName;
        this.countryName = countryName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
