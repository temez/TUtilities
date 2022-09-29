package dev.temez.utilities.shared.exception;

/**
 * @author temez
 * @since 0.1.1
 */
public class UserNotLoadedException extends Exception {

    public UserNotLoadedException() {
        super("User is not loaded to manager");
    }

}
