package dev.temez.utilities.spigot.bungee;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

/**
 * @author temez
 * @since 0.1
 */
@RequiredArgsConstructor
public class BungeeUtility implements PluginMessageListener {
    private final JavaPlugin plugin;

    /**
     * Регистрирует каналы для отправки сообщений
     *
     * @since 0.1
     */
    public void enable() {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);
    }

    /**
     * Отменяет регистрацию каналов
     * Делайте это, для избежания перегрузок
     *
     * @since 0.1
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
     * @since 0.1
     */
    @SneakyThrows
    public void sendToServer(Player p, String server) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF("Connect");
        dataOutputStream.writeUTF(server);
        p.sendPluginMessage(plugin, "BungeeCord", byteArrayOutputStream.toByteArray());
    }

    public CompletableFuture<ServerFullness> getServerFullnessAsync(String address, int port) {
        return CompletableFuture.completedFuture(getServerFullness(address, port));
    }

    public ServerFullness getServerFullness(String address, int port) {
        try (Socket socket = new Socket(address, port);
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             DataInputStream input = new DataInputStream(socket.getInputStream())) {
            socket.setSoTimeout(50);
            output.write(0xFE);
            StringBuilder result = new StringBuilder();
            int next;
            while ((next = input.read()) != -1) {
                if (next != 0 && next != 16 && next != 255 && next != 23 && next != 24) {
                    result.append((char) next);
                }
            }
            String[] data = result.toString().split("§");
            return new ServerFullness(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        } catch (Exception exception) {
            System.out.println(exception);
            return new ServerFullness(0, 0);
        }
    }

    @Override
    @SneakyThrows
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
    }
}
