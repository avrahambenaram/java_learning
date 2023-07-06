package application.useCases.User;

import Domain.Entities.User;
import application.repositories.UserRepository;
import application.services.*;
import application.services.dto.MailerDTO;
import application.services.dto.UserCodeDTO;
import application.useCases.User.dto.CreateUserDTO;
import errors.UseCaseError;
import java.util.Timer;
import java.util.TimerTask;

public class CreateUser {
    private final UsersCode usersCode;
    private final UserRepository userRepository;
    private final Mailer mailer;
    private final PasswordChecker passwordChecker;

    public CreateUser(
            UsersCode usersCode,
            UserRepository userRepository,
            Mailer mailer,
            PasswordChecker passwordChecker
    ) {
        this.usersCode = usersCode;
        this.userRepository = userRepository;
        this.mailer = mailer;
        this.passwordChecker = passwordChecker;
    }

    public void execute(CreateUserDTO props) throws Exception {
        this.verifyUserExistence(props.email);
        if (this.passwordChecker.isPasswordChecker(props.password))
            throw new UseCaseError("Password too weak", 403 );

        User user = new User(
                "",
                props.name,
                props.email,
                props.password
        );
        this.generateUserCode(user, props);
        this.deleteUserCodeAfter10Minutes(user.email);
    }

    private void verifyUserExistence(String userEmail) throws Exception {
        try {
            User userFound = this.userRepository.findByEmail(userEmail);
            if (userFound != null) throw new UseCaseError("User already exists", 403);
        } catch (Exception err) {
            if (err instanceof UseCaseError) {
                throw err;
            }
        }
    }

    private void generateUserCode(User user, CreateUserDTO props) {
        String code = this.usersCode.generateCode();
        MailerDTO mailProps = new MailerDTO();
        mailProps.name = user.name;
        mailProps.email = user.email;
        mailProps.code = code;
        this.mailer.sendConfirmationEmail(mailProps);

        UserCodeDTO userCodeProps = new UserCodeDTO();
        userCodeProps.code = code;
        userCodeProps.user = user;
        userCodeProps.address = props.address;
        userCodeProps.redirect = props.redirect;
        userCodeProps.failureRedirect = props.failureRedirect;
        this.usersCode.save(userCodeProps);
    }

    private void deleteUserCodeAfter10Minutes(String userEmail) {
        UsersCode usersCode = this.usersCode;
        long minutes10 = 1000 * 60 * 10;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                usersCode.delete(userEmail);
            }
        };

        timer.schedule(task, minutes10);
    }
}
