package dev.temez.utilities.spigot.hologram.line;

/**
 * @author temez
 * @since 0.1.1
 */
public class InvalidLineContextException extends Throwable{

    public InvalidLineContextException(HologramLineType type){
        super("Invalid hologram line context: " + type);
    }
}
