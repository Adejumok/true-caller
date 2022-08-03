package africa.trueCaller.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email ;
    @Setter
    public String password;
}
