package africa.trueCaller;

import africa.trueCaller.controllers.UserController;
import africa.trueCaller.data.models.Contact;
import africa.trueCaller.dtos.requests.AddContactRequest;
import africa.trueCaller.dtos.requests.RegisterRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Scanner;
@SpringBootApplication
public class Main {
    private static UserController userController=new UserController();
    private static final Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        //prompt user with menu
            //if user selects a, b, c,d direct them
        SpringApplication.run(Main.class, args);
        //displayMainManu();
    }

//    private static void findContactBelongingToUser() {
//        var contacts=userController.contacts(scanner("Enter your email: "));
//        contacts.forEach(contact -> display(contact.toString()));
//        displayMainManu();
//
//    }
//
//    private static void addContactToUser() {
//        AddContactRequest contactRequest=new AddContactRequest();
//        contactRequest.setFirstName(scanner("Enter contact's first name: "));
//        contactRequest.setLastName(scanner("Enter contact's last name: "));
//        contactRequest.setPhoneNumber(scanner("Enter contact's phone number: "));
//        contactRequest.setEmail(scanner("Enter contact's email: "));
//        contactRequest.setUserEmail(scanner("Enter your email: "));
//        userController.addContact(contactRequest);
//        displayMainManu();
//    }
//
//    private static void displayMainManu() {
//        String mainMenuPrompt= """
//                Welcome to True Caller
//                1 -> Create an Account
//                2 -> Add Contact to a User
//                3 -> Find Contact Belonging to User
//                """;
//        System.out.println(mainMenuPrompt);
//        String userInput=scanner(mainMenuPrompt);
//
//        switch (userInput.charAt(0)){
//            case '1'->createAccount();
//            case '2'->addContactToUser();
//            case '3'->findContactBelongingToUser();
//        }
//    }
//
//    private static void createAccount(){
//        RegisterRequest request=new RegisterRequest();
//        request.setFirstName(scanner("Enter first name: "));
//        request.setLastName(scanner("Enter last name: "));
//        request.setPhoneNumber(scanner("Enter phone number: "));
//        request.setPassword(scanner("Enter password: "));
//        request.setEmail(scanner("Enter email: "));
//        userController.registerUser(request);
//        display("Done!");
//        displayMainManu();
//    }
//
//    private static void display(String message) {
//        System.out.println(message);
//    }
//
//
//    public static String scanner(String prompt){
//        System.out.println(prompt);
//        return scanner.nextLine();
//    }
}
