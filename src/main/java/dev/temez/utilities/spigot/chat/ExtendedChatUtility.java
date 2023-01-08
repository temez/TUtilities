package dev.temez.utilities.spigot.chat;

import org.bukkit.entity.Player;

/**
 * @author temez
 * @since 0.1
 */
public class ExtendedChatUtility extends ChatUtility {

    public ExtendedChatUtility(String prefix) {
        super(prefix);
    }

    public void sendNotification(Player player, String message) {
        sendColoredMessage(player, " &7(&#f2edaa&l!&r&7) " + message);
    }

    public void sendErrorNotification(Player player, String message) {
        sendColoredMessage(player, " &7(&#fa6666&l!&r&7) " + message);
    }

    public void sendSuccessNotification(Player player, String message) {
        sendColoredMessage(player, " &7(&#a7fcab&l!&r&7) " + message);
    }
}