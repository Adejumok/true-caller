package africa.trueCaller.services;

import africa.trueCaller.dtos.requests.RegisterRequest;
import africa.trueCaller.dtos.responses.RegisterResponse;

public interface IUserService {
    RegisterResponse register(RegisterRequest request);

    int getNumberOfUsers();

}
