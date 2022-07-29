package africa.trueCaller.services;

import africa.trueCaller.data.models.Contact;
import africa.trueCaller.data.models.User;
import africa.trueCaller.data.repositories.ContactRepository;
import africa.trueCaller.data.repositories.IUserRepository;
import africa.trueCaller.data.repositories.UserRepository;
import africa.trueCaller.dtos.requests.AddContactRequest;
import africa.trueCaller.dtos.requests.RegisterRequest;
import africa.trueCaller.dtos.responses.AddContactResponse;
import africa.trueCaller.dtos.responses.RegisterResponse;
import africa.trueCaller.exceptions.UserExistsException;
import africa.trueCaller.utils.Mapper;

import java.util.List;

public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IContactService contactService;

    public UserService(IUserRepository userRepository, IContactService contactService){
        this.userRepository=userRepository;
        this.contactService=contactService;
    }

    public UserService(){
        this.userRepository=new UserRepository();
        ContactRepository contactRepository=new ContactRepository();
        this.contactService=new ContactService();

    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        //check if email exist in repository and throw exception
        isExist(request.getEmail());
        User user = new User();

        Mapper.map(request,user);

        userRepository.save(user);
        RegisterResponse response= new RegisterResponse();
        response.setMessage("Congratulations!!!");
        return response;
    }

    private void isExist(String email) {
        User savedUser= userRepository.findByEmail(email);
        if(savedUser!=null)throw new UserExistsException(email+" already exist.");
    }

    @Override
    public AddContactResponse addContactResponse(AddContactRequest addRequest) {
        //1->create a contact
        //2->saved contact to repository
        //3->find user by email
        //4->add contact to user contact lists
        //5->save user

        //1.
        Contact contact=new Contact();
        Mapper.map(addRequest,contact);

        //2.
        Contact savedContact=contactService.addNewContact(contact);

        //3.
        User user=userRepository.findByEmail(addRequest.getUserEmail());

        //4.
        user.getContacts().add(savedContact);

        //5.
        userRepository.save(user);
        return null;
    }

    @Override
    public int getNumberOfUsers() {
        return userRepository.count();
    }

    @Override
    public List<Contact> findContactsBelongingTo(String userEmail) {
        User user=userRepository.findByEmail(userEmail);
        return user.getContacts();
    }
}
