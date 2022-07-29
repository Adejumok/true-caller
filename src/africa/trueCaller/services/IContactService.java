package africa.trueCaller.services;

import africa.trueCaller.data.models.Contact;

public interface IContactService {
    Contact addNewContact(Contact contact);
    int size();
}
