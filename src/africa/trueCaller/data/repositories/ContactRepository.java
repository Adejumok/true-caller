package africa.trueCaller.data.repositories;

import africa.trueCaller.data.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository implements IContactRepository {
    private int counter;

    private List<Contact> contacts=new ArrayList<>();
    @Override
    public Contact save(Contact contact) {
        for (var foundContact : contacts) {
            if (contact.getId() == foundContact.getId()) {
                foundContact = contact;
                return foundContact;
            }

        }
        counter++;
        contact.setId(counter);
        contacts.add(contact);
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        Contact contactDel=findById(contact.getId());
        contacts.remove(contactDel);
    }

    @Override
    public void delete(int id) {
        Contact foundContact=findById(id);
        contacts.remove(foundContact);
    }

    @Override
    public Contact findById(int id) {
        for (var contact:contacts){
            if (contact.getId()==id){
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> findAll() {
        return contacts;
    }

    @Override
    public int count() {
        return contacts.size();
    }
}
