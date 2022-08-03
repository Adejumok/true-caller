package africa.trueCaller.data.repositories;

import africa.trueCaller.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IUserRepository extends MongoRepository<User,String> {
     User findUserByEmail(String email);
}
