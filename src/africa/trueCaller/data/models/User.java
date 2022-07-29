package africa.trueCaller.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @Setter
    private String password;
    private List<Contact> contacts=new ArrayList<>();

    public String toString(){
        return String.format("""
                First Name: %s
                Last Name: %s
                Phone Number: %s
                Email: %s
                """,firstName,lastName,phoneNumber,email);
    }
}
