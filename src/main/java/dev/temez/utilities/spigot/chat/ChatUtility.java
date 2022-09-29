package dev.temez.utilities.spigot.chat;

import dev.temez.utilities.shared.TextUtility;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

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

    /**
     * Отправляет ошибку с префиксом игроку
     *
     * @param player  обьект игрока
     * @param message сообщение
     * @since 0.1.1
     */
    public void sendPrefixedError(Player player, String message){
        sendPrefixedMessage(player, "&#fcb1a7" + message);
    }

    /**
     * Отправляет цветной тайтл игроку
     *
     * @param player  обьект игрока
     * @param upper верхняя строка
     * @param lower нижняя строка
     * @since 0.1.1
     */
    public void sendColoredTitle(Player player, String upper, String lower){
        player.sendTitle(TextUtility.colorize(upper), TextUtility.colorize(lower));
    }
}