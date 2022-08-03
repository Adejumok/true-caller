package africa.trueCaller.controllers;

import africa.trueCaller.data.models.Contact;
import africa.trueCaller.dtos.requests.AddContactRequest;
import africa.trueCaller.dtos.requests.RegisterRequest;
import africa.trueCaller.dtos.responses.AddContactResponse;
import africa.trueCaller.dtos.responses.AllContactResponse;
import africa.trueCaller.dtos.responses.RegisterResponse;
import africa.trueCaller.services.IUserService;
import africa.trueCaller.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {
    private IUserService userService=new UserService();
    @PostMapping("/user")
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }
    @PatchMapping("/user")
    public AddContactResponse addContact(@RequestBody AddContactRequest addContactRequest){
        return userService.addContactResponse(addContactRequest);
    }
    @GetMapping("/user/{email}")
    public List<AllContactResponse> contacts(@PathVariable String email){
        return userService.findContactsBelongingTo(email);
    }
}
