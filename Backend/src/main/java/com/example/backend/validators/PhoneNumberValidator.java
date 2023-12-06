package com.example.backend.validators;

public class PhoneNumberValidator {
    public static boolean isValid(String phoneNumber) {
        return PhoneNumberValidator
                .normalize(phoneNumber)
                .matches("^\\d{11}$");
    }

    public static String normalize(String phoneNumber) {
        return phoneNumber.replaceAll("\\+", "");
    }
}
