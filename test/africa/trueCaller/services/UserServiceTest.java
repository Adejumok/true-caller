package africa.trueCaller.services;

import africa.trueCaller.data.models.Contact;
import africa.trueCaller.data.models.User;
import africa.trueCaller.data.repositories.ContactRepository;
import africa.trueCaller.data.repositories.IUserRepository;
import africa.trueCaller.data.repositories.UserRepository;
import africa.trueCaller.dtos.requests.AddContactRequest;
import africa.trueCaller.dtos.requests.RegisterRequest;
import africa.trueCaller.dtos.requests.UpdateContactRequest;
import africa.trueCaller.dtos.requests.UpdateUserRequest;
import africa.trueCaller.exceptions.UserExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private IUserService userService;
    private IContactService contactService;
    private IUserRepository userRepository;
    @BeforeEach
    public void setUp(){
        ContactRepository contactRepository=new ContactRepository();
        contactService=new ContactService();
        userRepository=new UserRepository();
        userService=new UserService(userRepository,contactService);

    }
    @Test
    public void registerTest(){
        //given
        //there is a request form
        RegisterRequest request=new RegisterRequest();
        request.setPassword("0998");
        request.setFirstName("Lois");
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
        request.setFirstName("Lois");
        request.setPhoneNumber("0909090988");
        request.setEmail("ki@gmail.com");
        userService.register(request);
        assertEquals(1,userService.getNumberOfUsers());

        RegisterRequest request2=new RegisterRequest();
        request2.setEmail("ki@gmail.com");
        request2.setFirstName("Gbemi");
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
        request.setFirstName("Lois");
        request.setPhoneNumber("0909090988");
        request.setEmail("ki@gmail.com");
        userService.register(request);

        RegisterRequest request2 = new RegisterRequest();
        request2.setEmail("gbemi@gmail.com");
        request2.setFirstName("Gbemi");
        request2.setPhoneNumber("23455586675");
        request2.setPassword("pss34");
        userService.register(request2);

        assertEquals(2, userService.getNumberOfUsers());
    }

    @Test
    public void addContactTest(){
        //given that there is a user
        //when i add contact
        //check that contact size increases
        RegisterRequest request=new RegisterRequest();
        request.setPassword("0998");
        request.setFirstName("Lois");
        request.setPhoneNumber("0909090988");
        request.setEmail("ki@gmail.com");
        userService.register(request);

        AddContactRequest addContactRequest=new AddContactRequest();
        addContactRequest.setUserEmail("ki@gmail.com");
        addContactRequest.setFirstName("Lois");
        addContactRequest.setPhoneNumber("0909090988");
        addContactRequest.setEmail("me@gmail.com");
        userService.addContactResponse(addContactRequest);

        assertEquals(1,userService.findContactsBelongingTo("ki@gmail.com").size());
    }

    @Test
    public void updateUserTest() {
        //given
        //I register
        RegisterRequest request = new RegisterRequest();
        request.setPassword("0998");
        request.setFirstName("Lois");
        request.setLastName("Jummy");
        request.setPhoneNumber("0909090988");
        request.setEmail("ki@gmail.com");

        userService.register(request);

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setPassword(" ");
        updateUserRequest.setFirstName("Lois");
        updateUserRequest.setLastName("Adewale");
        updateUserRequest.setPhoneNumber("0909090988");
        updateUserRequest.setEmail("koko@gmail.com");

        userService.updateUser("ki@gmail.com",updateUserRequest);
        User user = userRepository.findByEmail("koko@gmail.com");
        assertEquals("Adewale",user.getLastName());

    }

    @Test
    void userUpdateContactTest(){
        RegisterRequest request=new RegisterRequest();
        request.setPassword("0998");
        request.setFirstName("Lois");
        request.setPhoneNumber("0909090988");
        request.setEmail("ki@gmail.com");
        userService.register(request);

        AddContactRequest addContactRequest=new AddContactRequest();
        addContactRequest.setUserEmail("ki@gmail.com");
        addContactRequest.setFirstName("Lois");
        addContactRequest.setPhoneNumber("0909090988");
        addContactRequest.setEmail("me@gmail.com");
        userService.addContactResponse(addContactRequest);

        assertEquals(1,userService.findContactsBelongingTo("ki@gmail.com").size());
        assertEquals("0909090988",userService.findContactsBelongingTo("ki@gmail.com").get(0).getPhoneNumber());


        UpdateContactRequest updateContactRequest=new UpdateContactRequest();
        updateContactRequest.setFirstName("Lade");
        updateContactRequest.setLastName("Awe");
        updateContactRequest.setPhoneNumber("009-987-987-66");
        updateContactRequest.setEmail("ui@gmail.com");
        updateContactRequest.setUserEmail("ki@gmail.com");
        userService.updateContact("ki@gmail.com","me@gmail.com",updateContactRequest);

        assertEquals("009-987-987-66",userService.findContactsBelongingTo("ki@gmail.com").get(0).getPhoneNumber());
    }

    @Test
    void userDeleteContactTest() {
        RegisterRequest request = new RegisterRequest();
        request.setPassword("0998");
        request.setFirstName("Lois");
        request.setPhoneNumber("0909090988");
        request.setEmail("ki@gmail.com");
        userService.register(request);

        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setUserEmail("ki@gmail.com");
        addContactRequest.setFirstName("Lois");
        addContactRequest.setPhoneNumber("0909090988");
        addContactRequest.setEmail("me@gmail.com");
        userService.addContactResponse(addContactRequest);

        assertEquals(1, userService.findContactsBelongingTo("ki@gmail.com").size());

        userService.deleteContact("ki@gmail.com","me@gmail.com");
        assertEquals(0,userService.findContactsBelongingTo("ki@gmail.com").size());
    }
}