package africa.trueCaller.data.repositories;

import africa.trueCaller.data.models.Contact;

import java.util.List;

public interface IContactRepository {
    Contact save(Contact contact);
    void delete(Contact contact);
    void delete(int id);
    Contact findById(int id);
//    List <Contact> findByFirstName(String firstName);
//    List <Contact> findByLastName(String lastName);
    List <Contact> findAll();
    int count();
}
