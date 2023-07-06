package Domain.Entities;
import Domain.ValueObjects.*;
import core.*;
import errors.ValueObjectError;

public class User extends Entity {
    public final String avatar;
    public final String name;
    public final String email;
    public final String password;
    public User(String avatar, String name, String email, String password, EntityProps entityProps) throws ValueObjectError {
        super(entityProps);
        this.avatar = avatar;
        this.name = new Name(name).value;
        this.email = new Email(email).value;
        this.password = new Password(password).value;
    }

    public User(String avatar, String name, String email, String password) throws ValueObjectError {
        this(avatar, name, email, password, new EntityProps());
    }
}
