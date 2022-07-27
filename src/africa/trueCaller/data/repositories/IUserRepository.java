package africa.trueCaller.data.repositories;

import africa.trueCaller.data.models.User;

import java.util.List;

public interface IUserRepository {
    User save(User user);
    void delete(User user);
    void delete(String email);
    List <User> findAll();
    int count();

    User findByEmail(String email);
}
