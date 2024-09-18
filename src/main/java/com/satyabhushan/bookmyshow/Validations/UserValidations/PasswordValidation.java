package com.satyabhushan.bookmyshow.Validations.UserValidations;

public class PasswordValidation {
    public static boolean isValid(String password) {
        if (password == null) {
            return false;
        }
        // Password should contain at least one digit [0-9] , one lowercase character [a-z] , one uppercase character [A-Z] ,
        // one special character [@#$%^&+=] , no whitespace and length should be between 8 to 20 characters.
        String passwordRegex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        /*
        `^(?=.*[0-9])`: Ensures that the password contains at least one digit (0-9).
        `(?=.*[a-z])`: Ensures that the password contains at least one lowercase letter (a-z).
        `(?=.*[A-Z])`: Ensures that the password contains at least one uppercase letter (A-Z).
        `(?=.*[@#$%^&+=])`: Ensures that the password contains at least one special character from the set `@#$%^&+=`.
        `(?=\\S+$)`: Ensures that the password does not contain any whitespace characters.
        `.{8,20}$`: Ensures that the password length is between 8 and 20 characters.
         */
        return password.matches(passwordRegex);
    }
}
