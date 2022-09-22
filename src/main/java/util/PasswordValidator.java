package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Secure Password requirements
 * Password must contain at least one digit [0-9].
 * Password must contain at least one lowercase Latin character [a-z].
 * Password must contain at least one uppercase Latin character [A-Z].
 * Password must contain at least one special character like ! @ # & ( ).
 * Password must contain a length of at least 8 characters and a maximum of 20 characters.
 */
public class PasswordValidator {
	
	private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
	
}