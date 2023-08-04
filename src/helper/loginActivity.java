package helper;

import javafx.fxml.Initializable;

import java.io.*;
import java.time.*;
import java.util.*;


public class loginActivity {

//    public static void log(String userName, boolean success) {
//
//        try {
//            LocalDate nowDate = LocalDate.now();
//            LocalTime nowTime = LocalTime.now();
//            LocalDateTime nowDateTime = LocalDateTime.of(nowDate, nowTime);
//            File file = new File("activity_log.txt");
//            Scanner scanner = new Scanner(file);
//            StringBuilder content = new StringBuilder();
//            while (scanner.hasNext()) {
//                content.append(scanner.nextLine()).append("\n");
//            }
//            scanner.close();
//            int existingLoginCount = (int) content.chars().filter(ch -> ch == '\n').count();
//            String userName = usernameTextField.getText();
//            String password = passwordPasswordField.getText();
//            String loginAttempt = "Login Attempt " + (existingLoginCount + 1) + " || Login Successful "
//                    + " || Username: " + userName + " || Password: " + password + " || Timestamp: " + nowDateTime + "\n";
//            FileWriter fw = new FileWriter(file, true);
//            fw.write(loginAttempt);
//            fw.flush();
//            fw.close();
//        } catch (Exception x) {
//            x.printStackTrace();
//        }
//    }

}
