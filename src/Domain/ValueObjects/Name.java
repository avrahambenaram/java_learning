package Domain.ValueObjects;
import config.ValueObjectsConfig;
import errors.ValueObjectError;

public class Name {
    public String value;

    public Name(String name) throws ValueObjectError {
        boolean isNameTooShort = name.length() < ValueObjectsConfig.name.minLength;
        boolean isNameTooLong = name.length() > ValueObjectsConfig.name.maxLength;

        if (isNameTooShort) throw new ValueObjectError("Name too short");
        if (isNameTooLong) throw new ValueObjectError("Name too long");

        this.value = name;
    }
}
