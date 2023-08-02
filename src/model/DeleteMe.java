package model;

import dao.DBAppointments;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class DeleteMe implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        ObservableList<Appointments> getAllAppointments = DBAppointments.getAllAppointments();
        LocalDateTime currentTimeMinus15Min = LocalDateTime.now().minusMinutes(15);
        LocalDateTime currentTimePlus15Min = LocalDateTime.now().plusMinutes(15);
        LocalDateTime startTime;
        int getAppointmentID = 0;
        LocalDateTime displayTime = null;
        boolean appointmentWithin15Min = false;

        for (Appointments appointment: getAllAppointments) {
            startTime = appointment.getStart();
            if ((startTime.isAfter(currentTimeMinus15Min) || startTime.isEqual(currentTimeMinus15Min)) && (startTime.isBefore(currentTimePlus15Min) || (startTime.isEqual(currentTimePlus15Min)))) {
                getAppointmentID = appointment.getAppointmentId();
                displayTime = startTime;
                appointmentWithin15Min = true;
            }
        }
        if (appointmentWithin15Min !=false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "You have an appointment in " + "15" + " minutes.\n"
                            + "The ID is: " + getAppointmentID + " at " + displayTime + ".");
            alert.setTitle("Appointment Coming Up");
            alert.show();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "There are no appointments within the next 15 minutes");
            alert.setTitle("No Upcoming Appointments");
            alert.show();
        }
    }
}
