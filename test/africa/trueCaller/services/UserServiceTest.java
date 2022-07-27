package africa.trueCaller.services;

import africa.trueCaller.dtos.requests.RegisterRequest;
import africa.trueCaller.exceptions.UserExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private IUserService userService;
    @BeforeEach
    public void setUp(){
        userService=new UserService();
    }
    @Test
    public void registerTest(){
        //given
        //there is a request form
        RegisterRequest request=new RegisterRequest();
        request.setPassword("0998");
        request.setName("Lois");
        request.setPhoneNumber("0909090988");
        request.setEmail("ki@gmail.com");
        //when
        //i try to register
        userService.register(request);
        //assert
        //repository size is one
        assertEquals(1,userService.getNumberOfUsers());

    }

    @Test
    public void duplicateEmailThrowsExceptionTest(){
        RegisterRequest request=new RegisterRequest();
        request.setPassword("0998");
        request.setName("Lois");
        request.setPhoneNumber("0909090988");
        request.setEmail("ki@gmail.com");
        userService.register(request);
        assertEquals(1,userService.getNumberOfUsers());

        RegisterRequest request2=new RegisterRequest();
        request2.setEmail("ki@gmail.com");
        request2.setName("Gbemi");
        request2.setPhoneNumber("23455586675");
        request2.setPassword("pss34");
//        userService.register(request2);

        assertEquals(1,userService.getNumberOfUsers());
        assertThrows(UserExistsException.class, ()-> userService.register(request2));

    }

    @Test
    public void registerMoreThanOne_getSizeTest() {
        RegisterRequest request = new RegisterRequest();
        request.setPassword("0998");
        request.setName("Lois");
        request.setPhoneNumber("0909090988");
        request.setEmail("ki@gmail.com");
        userService.register(request);

        RegisterRequest request2 = new RegisterRequest();
        request2.setEmail("gbemi@gmail.com");
        request2.setName("Gbemi");
        request2.setPhoneNumber("23455586675");
        request2.setPassword("pss34");
        userService.register(request2);

        assertEquals(2, userService.getNumberOfUsers());
    }
}