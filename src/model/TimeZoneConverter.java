package model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeZoneConverter {
        // Convert UTC LocalDateTime to Local timezone LocalDateTime
        public static LocalDateTime convertUtcToLocal(LocalDateTime utcDateTime, ZoneId localZone) {
            ZonedDateTime zonedDateTime = utcDateTime.atZone(ZoneId.of("UTC"));
            ZonedDateTime localZonedDateTime = zonedDateTime.withZoneSameInstant(localZone);
            return localZonedDateTime.toLocalDateTime();
        }

        // Convert Local timezone LocalDateTime to UTC LocalDateTime
        public static LocalDateTime convertLocalToUtc(LocalDateTime localDateTime, ZoneId localZone) {
            ZonedDateTime zonedDateTime = localDateTime.atZone(localZone);
            ZonedDateTime utcZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
            return utcZonedDateTime.toLocalDateTime();
        }

        // Convert UTC LocalDateTime to Eastern timezone LocalDateTime
        public static LocalDateTime convertUtcToEastern(LocalDateTime utcDateTime) {
            return convertUtcToLocal(utcDateTime, ZoneId.of("America/New_York"));
        }

        // Convert Eastern timezone LocalDateTime to UTC LocalDateTime
        public static LocalDateTime convertEasternToUtc(LocalDateTime easternDateTime) {
            return convertLocalToUtc(easternDateTime, ZoneId.of("America/New_York"));
        }

        // Convert System default timezone LocalDateTime to UTC LocalDateTime
        public static LocalDateTime convertSystemToUtc(LocalDateTime systemDateTime) {
            return convertLocalToUtc(systemDateTime, ZoneId.systemDefault());
        }

        // Convert UTC LocalDateTime to System default timezone LocalDateTime
        public static LocalDateTime convertUtcToSystem(LocalDateTime utcDateTime) {
            return convertUtcToLocal(utcDateTime, ZoneId.systemDefault());
        }

            // Test the methods
            LocalDateTime utcDateTime = LocalDateTime.now(ZoneId.of("UTC"));
            //System.out.println("UTC DateTime: " + utcDateTime);

            LocalDateTime localDateTime = convertUtcToLocal(utcDateTime, ZoneId.of("America/New_York"));
            //System.out.println("Local DateTime: " + localDateTime);

            LocalDateTime easternDateTime = convertUtcToEastern(utcDateTime);
            //S//ystem.out.println("Eastern DateTime: " + easternDateTime);

            LocalDateTime systemDateTime = convertUtcToSystem(utcDateTime);
            //System.out.println("System Default DateTime: " + systemDateTime);

}
