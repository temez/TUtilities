package dev.temez.utilities.bungee.chat;

import dev.temez.utilities.shared.TextUtility;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

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
     * @param message собщение
     * @since 0.1
     */
    public void sendPrefixedMessage(ProxiedPlayer player, String message) {
        sendColoredMessage(player, prefix + message);
    }

    /**
     * Отправляет цветное сообщение игроку
     *
     * @param player  обьект игрока
     * @param message сообщение
     * @since 0.1
     */
    public void sendColoredMessage(ProxiedPlayer player, String message) {
        player.sendMessage(TextComponent.fromLegacyText(TextUtility.colorize(message)));
    }

    /**
     * Отправляет ошибку с префиксом игроку
     *
     * @param player  обьект игрока
     * @param message сообщение
     * @since 0.1.1
     */
    public void sendPrefixedError(ProxiedPlayer player, String message) {
        sendPrefixedMessage(player, "&#fcb1a7" + message);
    }

    /**
     * Отправляет цветное сообщение игроку
     *
     * @param player  обьект игрока
     * @param message сообщение
     * @since 0.1.1
     */
    public void sendPrefixedSuccess(ProxiedPlayer player, String message) {
        sendPrefixedMessage(player, "&#a7fcab" + message);
    }
}
