package application.services.dto;

import Domain.Entities.User;

public class UserCodeDTO {
    public String code;
    public User user;
    public String redirect;
    public String failureRedirect;
    public String address;
}
