package model;

public class Customers {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private int divisionId;

    public Customers(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
    }

    @Override
    public String toString(){
        return "#" + String.valueOf(customerId) + " " + customerName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDivisionId() { return divisionId; }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /*public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }*/
}
