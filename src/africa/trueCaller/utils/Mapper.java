package africa.trueCaller.utils;

import africa.trueCaller.data.models.Contact;
import africa.trueCaller.data.models.User;
import africa.trueCaller.dtos.requests.AddContactRequest;
import africa.trueCaller.dtos.requests.RegisterRequest;

public class Mapper {
    public static void map(RegisterRequest request, User user) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
    }
    public static void map(AddContactRequest addRequest, Contact contact) {
        contact.setFirstName(addRequest.getFirstName());
        contact.setLastName(addRequest.getLastName());
        contact.setPhoneNumber(addRequest.getPhoneNumber());
        contact.setEmail(addRequest.getEmail());}

}
