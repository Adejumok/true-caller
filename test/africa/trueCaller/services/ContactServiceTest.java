package africa.trueCaller.services;

import africa.trueCaller.data.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {
    private IContactService contactService;

    @BeforeEach
    public void setUp(){

        contactService=new ContactService();
    }

    @Test
    void addNewContact(){
        Contact contact=new Contact();
        contact.setEmail("rfj@gmail.com");
        contact.setFirstName("Sake");
        contact.setLastName("Bread");
        contact.setPhoneNumber("086-785-978-98");
    }

}