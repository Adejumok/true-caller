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
import africa.trueCaller.dtos.responses.AddContactResponse;
import africa.trueCaller.dtos.responses.RegisterResponse;
import africa.trueCaller.dtos.responses.UpdateContactResponse;
import africa.trueCaller.dtos.responses.UpdateUserResponse;
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

    public User findUser(String userEmail){
        User foundUser = userRepository.findByEmail(userEmail);
        if(foundUser==null){throw new RuntimeException("User not found!");}
        return foundUser;
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
    public UpdateUserResponse updateUser(String email, UpdateUserRequest request) {
        User foundUser = findUser(email);
        if(!request.getFirstName().equals(" ") && request.getFirstName()!= null){
            foundUser.setFirstName(request.getFirstName());
        }
        if(!request.getLastName().equals(" ") && request.getLastName()!= null){
            foundUser.setLastName(request.getLastName());
        }
        if(!request.getPhoneNumber().equals(" ") && request.getPhoneNumber()!= null){
            foundUser.setPhoneNumber(request.getPhoneNumber());
        }
        if(!request.getEmail().equals(" ") && request.getEmail()!= null){
            foundUser.setEmail(request.getEmail());
        }
        if(!request.getPassword().equals(" ") && request.getPassword()!= null){
            foundUser.setPassword(request.getPassword());
        }
        userRepository.save(foundUser);
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setMessage("Successfully updated");
        return updateUserResponse;

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

    @Override
    public UpdateContactResponse updateContact(String userEmail, String contactEmail, UpdateContactRequest updateContactRequest) {
        User user = findUser(userEmail);

        Contact contact= findContactByEmail(userEmail,contactEmail);
        user.getContacts().remove(contact);

        if(!updateContactRequest.getFirstName().equals("") && updateContactRequest.getFirstName()!= null){
            contact.setFirstName(updateContactRequest.getFirstName());
        }
        if(!updateContactRequest.getLastName().equals("") && updateContactRequest.getLastName()!= null){
            contact.setLastName(updateContactRequest.getLastName());
        }
        if(!updateContactRequest.getPhoneNumber().equals("") && updateContactRequest.getPhoneNumber()!= null){
            contact.setPhoneNumber(updateContactRequest.getPhoneNumber());
        }
        if(!updateContactRequest.getEmail().equals("") && updateContactRequest.getEmail()!= null){
            contact.setEmail(updateContactRequest.getEmail());
        }

        contactService.addNewContact(contact);
        user.getContacts().add(contact);
        userRepository.save(user);
        UpdateContactResponse updateContactResponse=new UpdateContactResponse();
        updateContactResponse.setMessage("Contact Successfully Updated");
        return updateContactResponse;
    }



    @Override
    public Contact findContactByEmail(String userEmail,String contactEmail) {
        User user=findUser(userEmail);
        for (Contact contact: user.getContacts()){
            if (contactEmail.equals(contact.getEmail())){
                return contact;
            }
        }
        return null;
    }

    @Override
    public void deleteContact(String userEmail, String contactEmail) {
        User user = findUser(userEmail);
        Contact contact= findContactByEmail(userEmail,contactEmail);
        user.getContacts().remove(contact);
    }
}
