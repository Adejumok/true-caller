package africa.trueCaller.services;

import africa.trueCaller.data.models.User;
import africa.trueCaller.data.repositories.IUserRepository;
import africa.trueCaller.data.repositories.UserRepository;
import africa.trueCaller.dtos.requests.RegisterRequest;
import africa.trueCaller.dtos.responses.RegisterResponse;
import africa.trueCaller.exceptions.UserExistsException;

public class UserService implements IUserService {
    private IUserRepository userRepository=new UserRepository();

    @Override
    public RegisterResponse register(RegisterRequest request) {
        //check if email exist in repository and throw exception
        User savedUser= userRepository.findByEmail(request.getEmail());
        if(savedUser!=null)throw new UserExistsException(request.getEmail()+" already exist.");

        User user = new User();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        userRepository.save(user);
        RegisterResponse response= new RegisterResponse();
        response.setMessage("Congratulations!!!");
        return response;
    }

    @Override
    public int getNumberOfUsers() {
        return userRepository.count();
    }
}
