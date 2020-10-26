package com.example.otlob.services;

import android.text.TextUtils;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private final static Pattern PATTERN_NAME = Pattern.compile("[\\u0600-\\u065F\\u066A-\\u06EF\\u06FA-\\u06FFa-zA-Z ]+[\\u0600-\\u065F\\u066A-\\u06EF\\u06FA-\\u06FFa-zA-Z-_ ]");

    public static boolean validationFullName(String value, TextInputLayout layout) {
        if (TextUtils.isEmpty(value)) {
            layout.setError("Please Enter Your Name");
            return false;
        } else if (!PATTERN_NAME.matcher(value).matches()) {
            layout.setError("Please Enter Alphabet Letters Only");
            return false;
        } else {
            layout.setError(null);
            return true;
        }
    }

    public static boolean validationEmail(String value, TextInputLayout layout) {
        if (TextUtils.isEmpty(value)) {
            layout.setError("Please Enter Your Email");
            return false;
        } else if (!isEmailValid(value)) {
            layout.setError("Please Enter Correct Email example@example.com");
            return false;
        } else {
            layout.setError(null);
            return true;
        }
    }

    public static boolean validationPassword(String value, TextInputLayout layout) {
        if (TextUtils.isEmpty(value)) {
            layout.setError("Please Enter Your Password");
            return false;
        } else if (value.length() < 6) {
            layout.setError("The Password Should Be More Than 6 Digits");
            return false;
        } else {
            layout.setError(null);
            return true;
        }
    }

    public static boolean validationPhoneNumber(String value, TextInputLayout layout) {
        if (TextUtils.isEmpty(value)) {
            layout.setError("Please Enter Your Phone Number");
            return false;
        } else if (value.length() < 11) {
            layout.setError("Phone Number Must Be Equal 11 Digit");
            return false;
        } else {
            layout.setError(null);
            return true;
        }
    }

}
