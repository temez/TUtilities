package dev.temez.utilities.spigot.storage.dao.user;

import dev.temez.utilities.spigot.storage.UserBase;
import dev.temez.utilities.spigot.storage.dao.DataStorage;

import java.util.UUID;

/**
 * @author temez
 * @since 0.1.3
 */
public interface UserDataStorage<T extends UserBase> extends DataStorage<T> {

    /**
     * Получает обьект игрока
     *
     * @param uuid uuid
     * @since 0.1.3
     */
    T get(UUID uuid);

    /**
     * Получает обьект игрока
     *
     * @param username имя
     * @since 0.1.3
     */
    T get(String username);

    /**
     * Проверяет создана ли сущность игрока в бд
     *
     * @param uuid uuid
     * @since 0.1.3
     */
    boolean exists(UUID uuid);

    /**
     * Проверяет создана ли сущность игрока в бд
     *
     * @param username имя
     * @since 0.1.3
     */
    boolean exists(String username);
}
