package africa.trueCaller.services;

import africa.trueCaller.data.models.Contact;
import africa.trueCaller.data.repositories.ContactRepository;

public class ContactService implements IContactService {

    ContactRepository contactRepository=new ContactRepository();

    @Override
    public Contact addNewContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public int size() {
        return contactRepository.count();
    }
}
