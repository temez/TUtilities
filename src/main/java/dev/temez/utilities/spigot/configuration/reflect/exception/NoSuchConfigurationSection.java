package dev.temez.utilities.spigot.configuration.reflect.exception;

/**
 * @author temez
 * @since 0.1.2
 */
public class NoSuchConfigurationSection extends Throwable{

    public NoSuchConfigurationSection(String path, String file) {
        super("No such section by path \"" + path + "\" found in " + file + ".yml");
    }
}
