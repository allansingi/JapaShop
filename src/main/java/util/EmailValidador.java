package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Email Regex Validation Logic
 * 
 * Local-part
 * uppercase and lowercase Latin letters A to Z and a to z
 * digits 0 to 9
 * Allow dot (.), underscore (_) and hyphen (-)
 * dot (.) is not the first or last character
 * dot (.) does not appear consecutively, e.g. japa..shop@example.com is not allowed
 * Max 64 characters
 * In email local-part, many special characters like #$%&'*+-/=? are technically valid, but most mail servers or web applications do not accept all of them. This email regex only accepts the general dot (.), underscore (_), and hyphen (-).
 * 
 * Domain
 * uppercase and lowercase Latin letters A to Z and a to z
 * digits 0 to 9
 * hyphen (-) is not the first or last character
 * dot (.) is not the first or last character
 * dot (.) does not appear consecutively
 * tld min 2 characters
 */
public class EmailValidador {
	
	private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValid(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}