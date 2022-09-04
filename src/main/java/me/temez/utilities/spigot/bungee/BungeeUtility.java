package me.temez.utilities.spigot.bungee;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/**
 * @author temez
 */
@RequiredArgsConstructor
public class BungeeUtility implements PluginMessageListener {
    private final JavaPlugin plugin;

    /**
     * Регистрирует каналы для отправки сообщений
     */
    public void enable() {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);
    }

    /**
     * Отменяет регистрацию каналов
     * Делайте это, для избежания перегрузок
     */
    public void disable() {
        plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(plugin);
        plugin.getServer().getMessenger().unregisterIncomingPluginChannel(plugin);
    }

    /**
     * Отправляет запрос на подключение игрока к серверу
     *
     * @param p      обьект игрока
     * @param server сервер
     */
    @SneakyThrows
    public void sendToServer(Player p, String server) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF("Connect");
        dataOutputStream.writeUTF(server);
        p.sendPluginMessage(plugin, "BungeeCord", byteArrayOutputStream.toByteArray());
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
    }
}
