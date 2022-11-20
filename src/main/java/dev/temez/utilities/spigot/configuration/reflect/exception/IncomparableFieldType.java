package dev.temez.utilities.spigot.configuration.reflect.exception;

/**
 * @author temez
 * @since 0.1.2
 */
public class IncomparableFieldType extends Throwable{

    public IncomparableFieldType(String field) {
        super("Found incomparable types while processing \"" + field + "\"");
    }
}
