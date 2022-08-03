package africa.trueCaller.controllers;

import africa.trueCaller.data.models.Contact;
import africa.trueCaller.dtos.requests.AddContactRequest;
import africa.trueCaller.dtos.requests.RegisterRequest;
import africa.trueCaller.dtos.responses.AddContactResponse;
import africa.trueCaller.dtos.responses.AllContactResponse;
import africa.trueCaller.dtos.responses.RegisterResponse;
import africa.trueCaller.exceptions.UserExistsException;
import africa.trueCaller.services.IUserService;
import africa.trueCaller.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        try {
            RegisterResponse serviceResponse = userService.register(registerRequest);
            return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
        }
        catch (UserExistsException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
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
