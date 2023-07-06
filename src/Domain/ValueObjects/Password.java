package Domain.ValueObjects;

import config.ValueObjectsConfig;
import errors.ValueObjectError;

public class Password {
    public String value;

    public Password(String password) throws ValueObjectError {
        boolean isPasswordTooShort = password.length() < ValueObjectsConfig.password.minLength;

        if (isPasswordTooShort) throw new ValueObjectError("Password too short");

        this.value = password;
    }
}
