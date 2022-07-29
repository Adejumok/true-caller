package africa.trueCaller.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;



    public String toString(){
        return String.format("""
                ID: %d
                First Name: %s
                Last Name: %s
                Phone Number: %s
                Email: %s
                """,id,firstName,lastName,phoneNumber,email);
    }
}
