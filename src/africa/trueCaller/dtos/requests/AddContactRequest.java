package africa.trueCaller.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddContactRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email ;
    private String userEmail;

}
