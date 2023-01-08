package dev.temez.utilities.spigot.storage.manager.user.impl;

import dev.temez.utilities.shared.exception.UserAlreadyLoadedException;
import dev.temez.utilities.shared.exception.UserNotLoadedException;
import dev.temez.utilities.spigot.storage.UserBase;
import dev.temez.utilities.spigot.storage.dao.user.UserDataStorage;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author temez
 * @since 0.1.3
 */
public abstract class DynamicUserManagerBase<P extends JavaPlugin, E extends UserBase, D extends UserDataStorage<E>> extends UserManagerBase<P, E, D> implements Listener {

    public DynamicUserManagerBase(P plugin, D userDao) {
        super(plugin, userDao);
    }

    @Override
    public void init() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::saveData, 30, 600);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Обрабатывает подключение игрока к серверу
     *
     * @since 0.1.1
     */
    @EventHandler
    @SneakyThrows(UserAlreadyLoadedException.class)
    public void onConnect(PlayerJoinEvent event) {
        loadUser(event.getPlayer());
    }

    /**
     * Обрабатывает отключение игрока от сервера
     *
     * @since 0.1.1
     */
    @EventHandler
    @SneakyThrows(UserNotLoadedException.class)
    public void onDisconnect(PlayerQuitEvent event) {
        unloadUser(event.getPlayer());
    }
}
