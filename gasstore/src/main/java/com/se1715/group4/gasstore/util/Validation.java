/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class Validation {

    Connection connection = DBUtil.makeConnection();

    public static String getNowDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }

    public static boolean containsAlphabetCharacters(String inputString) {
        for (char c : inputString.toCharArray()) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    public static int encodeCustomer(String sequence, int limit) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] sequenceBytes = sequence.getBytes();

            byte[] hashBytes = digest.digest(sequenceBytes);

            int encodedValue = Math.abs(hashBytes.hashCode()) % limit;

            return encodedValue;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return -1;
        }
    }
    private static final String PHONE_REGEX = "^[0-9]{10}$";
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{4,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)@[\\w-]+(\\.[\\w]+)(\\.[a-z]{2,})";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    /**
     * The function takes a phone number as a string and returns true if the
     * phone number is valid and false if it is not
     *
     * @param phone The phone number to validate.
     * @return A boolean value.
     */
    public static boolean validate(String phone) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * It checks if the username and password are valid
     *
     * @param username the username to be checked
     * @param password
     * @return A boolean value.
     */
    
    
    
    
    public static boolean checkUsernamePassword(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Cannot null or empty");
        } else {
            Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);
            Matcher usernameMatcher = usernamePattern.matcher(username);
            Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher passwordMatcher = passwordPattern.matcher(password);
            return usernameMatcher.matches() && passwordMatcher.matches();
        }
    }

    /**
     * It checks if the email address is in the correct format
     *
     * @param email The email address to validate.
     * @return A boolean value.
     */
    public static boolean validateEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    /**
     * It returns true if the customerName parameter contains only letters,
     * spaces, and/or tabs
     *
     * @param customerName The name of the customer.
     * @return A boolean value.
     */
    public boolean validateCustomerName(String customerName) {
        Pattern pattern = Pattern.compile("^[\\p{L} \\s]+$");
        return pattern.matcher(customerName).matches();
    }

    /**
     * If the input date is after the current date, then the input date is
     * valid.
     *
     * @param dateString The date string to be validated.
     */
    public void validatRequiredDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar now = Calendar.getInstance();

        System.out.print("Enter a date and time (dd-mm-yyyy hh:mm:ss): ");
        String inputString = "12-23-2023 12:00:00";

        Date inputDate = null;
        try {
            inputDate = dateFormat.parse(inputString);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        boolean isValid = inputDate.after(now.getTime());
        System.out.println("Valid: " + isValid);
    }
}
