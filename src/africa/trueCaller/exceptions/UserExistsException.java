package africa.trueCaller.exceptions;

public class UserExistsException extends RuntimeException{

    public UserExistsException(String message){
        super(message);
    }
}