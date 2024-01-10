package com.example.project_client.view.controller.Quyen.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DobFormatter {

    public static String toString(LocalDate dob){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dob.format(formatter);
    }
    //public static LocalDate toDate(String dob){
     //   return LocalDate.parse(dob);
    //}
    public static LocalDate toDate(String dob) {
        // Adjust the pattern to include time information
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return LocalDate.parse(dob, formatter);
    }
}
