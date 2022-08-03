package africa.trueCaller.services;

import africa.trueCaller.data.models.Contact;
import africa.trueCaller.data.repositories.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService implements IContactService {

    @Autowired
    private IContactRepository contactRepository;

    public ContactService (IContactRepository contactRepository){
        this.contactRepository=contactRepository;
    }

    @Override
    public Contact addNewContact(Contact contact) {
        return contactRepository.save(contact);
    }


}
