package me.temez.utilities.spigot.chat;

import lombok.RequiredArgsConstructor;
import me.temez.utilities.shared.TextUtility;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * @author temez
 * @since 0.1
 */
@RequiredArgsConstructor
public class ChatUtility {

    private final String prefix;

    /**
     * Отправляет цветное сообщение с префиксом игроку
     *
     * @param player  обьект игрока
     * @param message сообщение
     * @since 0.1
     */
    public void sendPrefixedMessage(Player player, String message) {
        sendColoredMessage(player, prefix + message);
    }

    /**
     * Отправляет цветное сообщение игроку
     *
     * @param player  обьект игрока
     * @param message сообщение
     * @since 0.1
     */
    public void sendColoredMessage(Player player, String message) {
        player.sendMessage(TextUtility.colorize(message));
    }
}