package africa.trueCaller.data.repositories;

import africa.trueCaller.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private User user;
    private User user1;
    private IUserRepository userRepository;

    @BeforeEach
    void setUp() {
        user=new User();
        user1=new User();
        userRepository=new UserRepository();
    }

    @Test
    public void saveUser_countTest(){
        user.setName("Alex");
        user.setPassword("ios");
        user.setPhoneNumber("121212135");
        user.setEmail("alex@gmail.com");
        userRepository.save(user);
        assertEquals(1,userRepository.count());
    }

    @Test
    public void saveUser_findByIdTest(){
        user.setName("Alex");
        user.setPassword("ios");
        user.setPhoneNumber("121212135");
        user.setEmail("alex@gmail.com");
        userRepository.save(user);
        assertEquals(1,userRepository.count());

        User savedUser=userRepository.findByEmail("alex@gmail.com");
        assertEquals("Alex",savedUser.getName());
    }

    @Test
    public void saveUser_removeUserTest(){
        user.setName("Alex");
        user.setPassword("ios");
        user.setPhoneNumber("121212135");
        user.setEmail("alex@gmail.com");
        userRepository.save(user);

        userRepository.delete(user);
        assertEquals(0,userRepository.count());
    }

    @Test
    public void saveUser_removeUserByIdTest(){
        user.setName("Alex");
        user.setPassword("ios");
        user.setPhoneNumber("121212135");
        user.setEmail("alex@gmail.com");
        userRepository.save(user);
        userRepository.delete("alex@gmail.com");
        assertEquals(0,userRepository.count());
    }

    @Test
    public void updateUser_findByIdTest(){
        user.setName("Alex");
        user.setPassword("ios");
        user.setPhoneNumber("121212135");
        user.setEmail("alex@gmail.com");
        userRepository.save(user);

        user.setName("Collins");
        user.setPassword("mks");
        user.setPhoneNumber("122343135");
        user.setEmail("alex@gmail.com");
        userRepository.save(user);
        assertEquals(1,userRepository.count());

        User savedUser=userRepository.findByEmail("alex@gmail.com");
        assertEquals("Collins",savedUser.getName());
    }

    @Test
    public void findAllUsersTest() {
        user.setName("Alex");
        user.setPassword("ios");
        user.setPhoneNumber("121212135");
        user.setEmail("alex@gmail.com");
        userRepository.save(user);
        user1.setName("Yeye");
        user1.setPassword("mks");
        user1.setPhoneNumber("122343135");
        user1.setEmail("yeye@gmail.com");
        userRepository.save(user1);
        List<User> savedUser=userRepository.findAll();
        assertEquals("""
                [Name: Alex
                Phone Number: 121212135
                Email: alex@gmail.com
                , Name: Yeye
                Phone Number: 122343135
                Email: yeye@gmail.com
                ]""",savedUser.toString());
    }
}