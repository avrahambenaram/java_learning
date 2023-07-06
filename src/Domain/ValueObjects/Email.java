package Domain.ValueObjects;
import java.util.regex.Pattern;
import config.ValueObjectsConfig;
import errors.ValueObjectError;

public class Email {
    public String value;
    private final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public Email(String email) throws ValueObjectError {
        boolean isEmailValid = this.validateEmail(email);
        boolean isEmailTooShort = email.length() < ValueObjectsConfig.email.minLength;
        boolean isEmailTooLong = email.length() > ValueObjectsConfig.email.maxLength;

        if (!isEmailValid) throw new ValueObjectError("Invalid email");
        if (isEmailTooShort) throw new ValueObjectError("Email too short");
        if (isEmailTooLong) throw new ValueObjectError("Email too long");

        this.value = email;
    }

    private boolean validateEmail(String email) {
        return pattern.matcher(email).matches();
    }
}
