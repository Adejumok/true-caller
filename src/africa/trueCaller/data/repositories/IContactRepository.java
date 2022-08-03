package africa.trueCaller.data.repositories;

import africa.trueCaller.data.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IContactRepository extends MongoRepository<Contact, String> {
}
