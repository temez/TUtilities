package dev.temez.utilities.shared.exception;


/**
 * @author temez
 * @since 0.1.1
 */
public class UndefinedGroupException extends Exception {

    public UndefinedGroupException() {
        super("Group not found in LuckPerms");
    }
}
