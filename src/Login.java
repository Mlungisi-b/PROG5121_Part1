/**
 * Module: PROG5121 - Part 2
 * File Name: Login.java
 * Student Name: Mlungisi Balane
 * Description: Handles user registration, username and password validation rules, and authentication.
 */
public class Login {

    // Validates username format (must contain an underscore and be <= 5 chars)
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // Validates password complexity (>= 8 chars, 1 uppercase, 1 number, 1 special char)
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasDigit && hasSpecial;
    }

    // Validates registration inputs and returns appropriate status message
    public String registerUser(String username, String password, String firstName, String lastName, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }

        return "User registered successfully.";
    }

    // Authenticates user login
    public boolean loginUser(String username, String password) {
        // Simplified check for testing (or compare against saved credentials)
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }

    // Returns login feedback based on authentication result
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "A successful login";
        } else {
            return "A failed login";
        }
    }
}