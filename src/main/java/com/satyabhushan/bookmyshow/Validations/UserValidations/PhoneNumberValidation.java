package com.satyabhushan.bookmyshow.Validations.UserValidations;

public class PhoneNumberValidation {
    public static boolean isValid(String phone) {
        if (phone == null) {
            return false;
        }
        String phoneRegex = "^[0-9]{10}$";
        return phone.matches(phoneRegex);
    }
}
