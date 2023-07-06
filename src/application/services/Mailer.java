package application.services;

import application.services.dto.MailerDTO;

public abstract class Mailer {
    public abstract void sendConfirmationEmail(MailerDTO props);
}
