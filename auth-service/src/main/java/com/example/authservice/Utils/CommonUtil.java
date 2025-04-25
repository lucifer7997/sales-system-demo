package com.example.authservice.Utils;

public class CommonUtil {
    public static final String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public boolean isValidEmail(String email) {
        return email != null && email.matches(regexEmail);
    }
}
