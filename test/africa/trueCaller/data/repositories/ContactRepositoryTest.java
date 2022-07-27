package africa.trueCaller.data.repositories;

import africa.trueCaller.data.models.Contact;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContactRepositoryTest {

    @Test
    public void saveContact_countTest(){
        IContactRepository contactRepository=new ContactRepository();
        Contact contact=new Contact();
        contact.setPhoneNumber("222087976");
        contact.setFirstName("Tobi");
        contact.setLastName("Awe");
        contact.setEmail("tobs@email.com");

        contactRepository.save(contact);
        assertEquals(1, contactRepository.count());
    }

    @Test
    public void saveContact_findByIdTest(){
        IContactRepository contactRepository=new ContactRepository();
        Contact contact=new Contact();
        contact.setPhoneNumber("222087976");
        contact.setFirstName("Tobi");
        contact.setLastName("Awe");
        contact.setEmail("tobs@email.com");
        contactRepository.save(contact);

        assertEquals(1, contactRepository.count());
        Contact savedContact=contactRepository.findById(1);
        assertEquals("Tobi",savedContact.getFirstName());
    }

    @Test
    public void deleteContact_confirmUsingCountTest(){
        IContactRepository contactRepository=new ContactRepository();
        Contact contact=new Contact();
        contact.setPhoneNumber("222087976");
        contact.setFirstName("Tobi");
        contact.setLastName("Awe");
        contact.setEmail("tobs@email.com");
        contactRepository.save(contact);

        contactRepository.delete(contact);
        assertEquals(0, contactRepository.count());
    }

    @Test
    public void deleteContactBy_confirmUsingCountTest(){
        IContactRepository contactRepository=new ContactRepository();
        Contact contact=new Contact();
        contact.setPhoneNumber("222087976");
        contact.setFirstName("Tobi");
        contact.setLastName("Awe");
        contact.setEmail("tobs@email.com");
        contactRepository.save(contact);

        contactRepository.delete(1);
        assertEquals(0, contactRepository.count());
        Contact savedContact=contactRepository.findById(1);
        assertNull(savedContact);
    }

    @Test
    public void updateContact_findByIdTest(){
        IContactRepository contactRepository=new ContactRepository();
        Contact contact=new Contact();
        contact.setPhoneNumber("222087976");
        contact.setFirstName("Tobi");
        contact.setLastName("Awe");
        contact.setEmail("tobs@email.com");
        contactRepository.save(contact);

        contact.setPhoneNumber("232087445");
        contact.setFirstName("Kabunmi");
        contact.setLastName("Doro");
        contact.setEmail("kabz@email.com");
        contact.setId(1);
        contactRepository.save(contact);

        assertEquals(1, contactRepository.count());
        Contact savedContact=contactRepository.findById(1);
        assertEquals("232087445",savedContact.getPhoneNumber());
    }

    @Test
    public void findAllContactsTest() {
        IContactRepository contactRepository = new ContactRepository();
        Contact contact = new Contact();
        contact.setPhoneNumber("222087976");
        contact.setFirstName("Tobi");
        contact.setLastName("Awe");
        contact.setEmail("tobs@email.com");
        contactRepository.save(contact);

        List<Contact> contacts=contactRepository.findAll();
        assertEquals("""
                [First Name: Tobi
                Last Name: Awe
                Phone Number: 222087976
                Email: tobs@email.com
                ]""",contacts.toString());
    }
    }