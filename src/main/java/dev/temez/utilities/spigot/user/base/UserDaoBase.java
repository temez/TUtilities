package dev.temez.utilities.spigot.user.base;

import dev.temez.utilities.shared.mysql.MySQL;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author temez
 * @since 0.1.1
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public abstract class UserDaoBase<T extends UserBase> {

    MySQL mySQL;

    /**
     * Инициализирует DAO
     *
     * @since 0.1.1
     */
    public void init() {
        createTable();
    }

    /**
     * Получает обьект игрока
     *
     * @param uuid uuid
     * @since 0.1.1
     */
    public abstract T get(UUID uuid);

    /**
     * Получает обьект игрока
     *
     * @param username имя
     * @since 0.1.1
     */
    public abstract T get(String username);

    /**
     * Проверяет создана ли сущность игрока в бд
     *
     * @param uuid uuid
     * @since 0.1.1
     */
    public boolean exists(UUID uuid) {
        return get(uuid) != null;
    }

    /**
     * Проверяет создана ли сущность игрока в бд
     *
     * @param username имя
     * @since 0.1.1
     */
    public boolean exists(String username) {
        return get(username) != null;
    }

    /**
     * Записывает новую сущность игрока в бд, и возвращает её
     *
     * @param player игрок
     * @param args   аругменты
     * @since 0.1.1
     */
    public abstract T createNew(Player player, Object... args);

    /**
     * Сохраняет обьект игрока в бд
     *
     * @param user игрок
     * @since 0.1.1
     */
    public abstract void save(T user);

    /**
     * Создаёт таблицу в бд, если её нет
     *
     * @since 0.1.1
     */
    public abstract void createTable();
}
