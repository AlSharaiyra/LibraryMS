package org.LibraryMS.util;

public class ValidationUtil {
    public boolean isEmailValid(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        String phoneRegex = "^(\\+|00)?\\d{1,4}?\\d{9,}$";
        return phoneNumber.matches(phoneRegex);
    }
}
