package dev.temez.utilities.spigot.storage.manager.user;

import dev.temez.utilities.shared.exception.UserAlreadyLoadedException;
import dev.temez.utilities.shared.exception.UserNotLoadedException;
import dev.temez.utilities.spigot.storage.UserBase;
import dev.temez.utilities.spigot.storage.dao.user.UserDataStorage;
import dev.temez.utilities.spigot.storage.manager.EntityManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author temez
 * @since 0.1.3
 */
public interface UserManager<P extends JavaPlugin, E extends UserBase, D extends UserDataStorage<E>> extends EntityManager<P, E, D> {

    /**
     * Загружает игрока из бд в менеджер
     *
     * @param player игрок
     * @throws UserAlreadyLoadedException если игрок уже загружен
     * @since 0.1.1
     */
    void loadUser(Player player) throws UserAlreadyLoadedException;

    /**
     * Отгружает игрока из менеджера (в основном при выходе)
     *
     * @param player игрок
     * @throws UserNotLoadedException если игрок не загружен
     * @since 0.1.1
     */
    void unloadUser(Player player) throws UserNotLoadedException;

    /**
     * Получает, уже загруженого в менеджер, игрока
     *
     * @param player игрок
     * @since 0.1.1
     */
    E get(Player player);
}
