package dev.temez.utilities.shared.exception;

/**
 * @author temez
 * @since 0.1.1
 */
public class UserAlreadyLoadedException extends Exception {

    public UserAlreadyLoadedException() {
        super("User is already loaded to manager");
    }
}
