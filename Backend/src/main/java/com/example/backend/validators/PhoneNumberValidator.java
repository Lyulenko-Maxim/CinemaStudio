package com.example.backend.validators;

public class PhoneNumberValidator {
    public static boolean isValid(String phoneNumber) {
        return phoneNumber.matches("^\\d{12}$");
    }

    public static String normalize(String phoneNumber) {
        return phoneNumber.replaceAll("\\+", "");
    }
}
