package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ReportB;
import model.ReportC;
import model.Reports;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBReports {
    public static ObservableList<Reports> getReportA() {
        ObservableList<Reports> aReport = FXCollections.observableArrayList();

        try {
            String sql = "SELECT a.Type, MONTHNAME(a.Start) AS Month, COUNT(*) AS TotalAppointments FROM client_schedule.appointments a GROUP BY a.Type, MONTH(a.Start) ORDER BY MONTH(a.Start), a.Type;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String type = rs.getString("Type");
                String month = rs.getString("Month");
                int total = rs.getInt("TotalAppointments");
                Reports rp = new Reports(type, month, total);
                aReport.add(rp);
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return aReport;
    }

    public static ObservableList<ReportB> getReportB() {
        ObservableList<ReportB> bReport = FXCollections.observableArrayList();
        try {
            String sql = "SELECT co.Contact_Name, ap.Title, ap.Description, ap.Appointment_ID, ap.Type, ap.Start, ap.End, ap.Customer_ID FROM client_schedule.appointments ap JOIN client_schedule.contacts co ON ap.Contact_ID = co.Contact_ID ORDER BY co.Contact_Name, ap.Start;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String contactName = rs.getString("Contact_Name");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                int appointmentId = rs.getInt("Appointment_ID");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                LocalDateTime starting = start.toLocalDateTime();
                Timestamp end = rs.getTimestamp("End");
                LocalDateTime ending = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                ReportB rpb = new ReportB(contactName, title, description, appointmentId, type, starting, ending, customerId);
                bReport.add(rpb);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bReport;
    }

    public static ObservableList<ReportC> getReportC() {
        ObservableList<ReportC> cReport = FXCollections.observableArrayList();
        try {
            String sql = "SELECT cu.Customer_ID, cu.Customer_Name, fld.Division, co.Country FROM client_schedule.customers cu JOIN client_schedule.first_level_divisions fld ON fld.Division_ID = cu.Division_ID JOIN client_schedule.countries co ON co.Country_ID = fld.Country_ID";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String divisionName = rs.getString("Division");
                String countryName = rs.getString("Country");
                ReportC rpc = new ReportC(customerId, customerName, divisionName, countryName);
                cReport.add(rpc);
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return cReport;
    }

}
