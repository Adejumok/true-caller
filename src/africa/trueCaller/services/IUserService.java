package africa.trueCaller.services;

import africa.trueCaller.data.models.Contact;
import africa.trueCaller.data.models.User;
import africa.trueCaller.dtos.responses.UpdateContactResponse;
import africa.trueCaller.dtos.requests.AddContactRequest;
import africa.trueCaller.dtos.requests.UpdateContactRequest;
import africa.trueCaller.dtos.requests.UpdateUserRequest;
import africa.trueCaller.dtos.responses.AddContactResponse;
import africa.trueCaller.dtos.requests.RegisterRequest;
import africa.trueCaller.dtos.responses.RegisterResponse;
import africa.trueCaller.dtos.responses.UpdateUserResponse;

import java.util.List;

public interface IUserService {
    RegisterResponse register(RegisterRequest request);
    User findUser(String userEmail);

    AddContactResponse addContactResponse(AddContactRequest addRequest);
    UpdateUserResponse updateUser(String email,UpdateUserRequest request);

    int getNumberOfUsers();

    List <Contact> findContactsBelongingTo(String userEmail);

    UpdateContactResponse updateContact(String userEmail, String contactEmail, UpdateContactRequest updateContactRequest);

    Contact findContactByEmail(String userEmail, String contactEmail);

    void deleteContact(String userEmail, String contactEmail);
}
