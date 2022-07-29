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
    private static ArrayList<Contact> contacts=new ArrayList<>();
    private static final Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        //prompt user with menu
            //if user selects a, b, c,d direct them
        SpringApplication.run(Main.class, args);
        displayMainManu();
    }

    private static void findContactBelongingToUser() {
        var contacts=userController.contacts(input("Enter your email: "));
        contacts.forEach(contact -> display(contact.toString()));
        displayMainManu();

    }

    private static void addContactToUser() {
        AddContactRequest contactRequest=new AddContactRequest();
        contactRequest.setFirstName(input("Enter contact's first name: "));
        contactRequest.setLastName(input("Enter contact's last name: "));
        contactRequest.setPhoneNumber(input("Enter contact's phone number: "));
        contactRequest.setEmail(input("Enter contact's email: "));
        contactRequest.setUserEmail("Enter your email: ");
        userController.addContact(contactRequest);
        displayMainManu();
    }

    private static void displayMainManu() {
        String mainMenuPrompt= """
                Welcome to True Caller
                1 -> Create an Account
                2 -> Add Contact to a User
                3 -> Find Contact Belonging to User
                """;
        System.out.println(mainMenuPrompt);
        String userInput=scanner.nextLine();

        switch (userInput.charAt(0)){
            case '1'->createAccount();
            case '2'->addContactToUser();
            case '3'->findContactBelongingToUser();
        }
    }

    private static void createAccount(){
        RegisterRequest request=new RegisterRequest();
        request.setFirstName(input("Enter first name: "));
        request.setLastName(input("Enter last name: "));
        request.setPhoneNumber(input("Enter phone number: "));
        request.setPassword(input("Enter password: "));
        request.setEmail(input("Enter email: "));
        userController.registerUser(request);
        display("Done!");
        displayMainManu();
    }

    private static void display(String message) {
        System.out.println(message);
    }


    public static String input(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
