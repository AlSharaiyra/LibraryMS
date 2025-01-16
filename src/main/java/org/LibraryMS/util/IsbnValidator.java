package org.LibraryMS.util;

import java.util.regex.Pattern;

public class IsbnValidator {
    private static final Pattern ISBN_10_PATTERN = Pattern.compile("^\\d{9}[\\dX]$");
    private static final Pattern ISBN_13_PATTERN = Pattern.compile("^(978|979)\\d{10}$");

    public static boolean isValidIsbn13(String isbn) {
        if (isbn == null) {
            return false;
        }
        return ISBN_13_PATTERN.matcher(isbn).matches();
    }

    public static boolean isValidIsbn10(String isbn) {
        if (isbn == null) {
            return false;
        }
        return ISBN_10_PATTERN.matcher(isbn).matches();
    }

    public static boolean isValidIsbn(String isbn) {
        return isValidIsbn10(isbn) || isValidIsbn13(isbn);
    }

}
