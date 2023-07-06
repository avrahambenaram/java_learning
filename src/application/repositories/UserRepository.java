package application.repositories;

import Domain.Entities.User;
import application.repositories.dto.UserUpdateDTO;

public abstract class UserRepository {
    public abstract User findById(String userId);
    public abstract User findByEmail(String email);
    public abstract void update(String userId, UserUpdateDTO props);
    public abstract void save(User user);
    public abstract void delete(String userId);
}
