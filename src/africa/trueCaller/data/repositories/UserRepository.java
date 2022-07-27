package africa.trueCaller.data.repositories;

import africa.trueCaller.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private int counter;
    private List<User> users=new ArrayList<>();

    @Override
    public User save(User user) {
        for (User foundUser:users){
            if (user.getEmail().equals(foundUser.getEmail())){
                return foundUser;
            }
        }
        counter++;
        users.add(user);
        return user;
    }

    @Override
    public void delete(User user) {
        User userToDel=findByEmail(user.getEmail());
        users.remove(userToDel);
    }

    @Override
    public void delete(String email) {
        User userToDel=findByEmail(email);
        users.remove(userToDel);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public User findByEmail(String email) {
        for (var user: users){
            if (user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return null;
    }
}
