package africa.trueCaller.services;

import africa.trueCaller.data.models.Contact;
import africa.trueCaller.dtos.requests.AddContactRequest;
import africa.trueCaller.dtos.responses.AddContactResponse;
import africa.trueCaller.dtos.requests.RegisterRequest;
import africa.trueCaller.dtos.responses.RegisterResponse;

import java.util.List;

public interface IUserService {
    RegisterResponse register(RegisterRequest request);

    AddContactResponse addContactResponse(AddContactRequest addRequest);

    int getNumberOfUsers();

    List <Contact> findContactsBelongingTo(String userEmail);
}
