package africa.trueCaller.services;

import africa.trueCaller.data.models.Contact;
import africa.trueCaller.data.models.User;
import africa.trueCaller.data.repositories.IUserRepository;
import africa.trueCaller.dtos.requests.AddContactRequest;
import africa.trueCaller.dtos.requests.RegisterRequest;
import africa.trueCaller.dtos.requests.UpdateContactRequest;
import africa.trueCaller.dtos.requests.UpdateUserRequest;
import africa.trueCaller.dtos.responses.*;
import africa.trueCaller.exceptions.UserExistsException;
import africa.trueCaller.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IContactService contactService;

    public UserService(IUserRepository userRepository, IContactService contactService){
        this.userRepository=userRepository;
        this.contactService=contactService;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        //check if email exist in repository and throw exception
        isExist(request.getEmail());
        User user = new User();

        Mapper.map(request,user);

        userRepository.save(user);
        RegisterResponse response= new RegisterResponse();
        response.setMessage(String.format("%s registered successfully", request.getEmail()));
        return response;
    }

    private void isExist(String email) {
        User savedUser= userRepository.findUserByEmail(email);
        if(savedUser!=null)throw new UserExistsException(email+" already exist.");
    }

    public User findUser(String userEmail){
        User foundUser = userRepository.findUserByEmail(userEmail);
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
        User user=userRepository.findUserByEmail(addRequest.getUserEmail());
        //4.
        user.getContacts().add(savedContact);
        //5.
        userRepository.save(user);
        AddContactResponse response= new AddContactResponse();
        response.setMessage(String.format("%s registered successfully", addRequest.getEmail()));
        return response;
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
    public List<AllContactResponse> findContactsBelongingTo(String userEmail) {
        User user=userRepository.findUserByEmail(userEmail);
        List<Contact> allUserContacts = user.getContacts();
        List<AllContactResponse> response=new ArrayList<>();
        allUserContacts.forEach(contact ->{
            AllContactResponse singleResponse=new AllContactResponse();
            Mapper.map(contact,singleResponse);
            response.add(singleResponse);
        });
        return response;
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
