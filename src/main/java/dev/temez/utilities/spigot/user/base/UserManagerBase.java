package dev.temez.utilities.spigot.user.base;

import dev.temez.utilities.shared.exception.UserAlreadyLoadedException;
import dev.temez.utilities.shared.exception.UserNotLoadedException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author temez
 * @since 0.1.1
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
@Getter
@RequiredArgsConstructor
public abstract class UserManagerBase<T extends UserBase, D extends UserDaoBase<T>, P extends JavaPlugin> implements Listener {

    P plugin;
    D userDao;
    HashMap<UUID, T> loadedUsers = new HashMap<>();

    /**
     * Инициализирует менеджер: запускает автосейв данных, регистрирует события и создаёт таблицы в DAO (если нужны)
     *
     * @since 0.1.1
     */
    public void init() {
        userDao.createTable();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::saveData, 600, 30);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Сохраняет данные
     *
     * @since 0.1.1
     */
    public void disable() {
        saveData();
    }

    /**
     * Загружает игрока из бд в менеджер
     *
     * @param player игрок
     * @throws UserAlreadyLoadedException если игрок уже загружен
     * @since 0.1.1
     */
    public void addUser(Player player) throws UserAlreadyLoadedException {
        if (loadedUsers.containsKey(player.getUniqueId())) throw new UserAlreadyLoadedException();
        T user;
        if (userDao.exists(player.getUniqueId())) {
            user = userDao.get(player.getUniqueId());
        } else {
            user = userDao.createNew(player);
        }
        loadedUsers.put(player.getUniqueId(), user);
    }

    /**
     * Отгружает игрока из менеджера (в основном при выходе)
     *
     * @param player игрок
     * @throws UserNotLoadedException если игрок не загружен
     * @since 0.1.1
     */
    public void removeUser(Player player) throws UserNotLoadedException {
        if (!loadedUsers.containsKey(player.getUniqueId())) throw new UserNotLoadedException();
        userDao.save(loadedUsers.get(player.getUniqueId()));
        loadedUsers.remove(player.getUniqueId());
        if (Bukkit.getPlayer(player.getUniqueId()) != null) player.kick(Component.text("Вы были отключены от сервера"));
    }

    /**
     * Сохраняет данные всех игроков в бд
     *
     * @since 0.1.1
     */
    public void saveData() {
        loadedUsers.forEach((uuid, user) -> {
            userDao.save(user);
        });
    }

    /**
     * Получает, уже загруженого в менеджер, игрока
     *
     * @param uuid uuid
     * @since 0.1.1
     */
    public T get(UUID uuid) {
        return loadedUsers.get(uuid);
    }

    /**
     * Получает, уже загруженого в менеджер, игрока
     *
     * @param player игрок
     * @since 0.1.1
     */
    public T get(Player player) {
        return get(player.getUniqueId());
    }

    /**
     * Обрабатывает подключение игрока к серверу
     *
     * @since 0.1.1
     */
    @EventHandler
    @SneakyThrows(UserAlreadyLoadedException.class)
    public void onConnect(PlayerJoinEvent event) {
        addUser(event.getPlayer());
    }

    /**
     * Обрабатывает отключение игрока от сервера
     *
     * @since 0.1.1
     */
    @EventHandler
    @SneakyThrows(UserNotLoadedException.class)
    public void onDisconnect(PlayerQuitEvent event) {
        removeUser(event.getPlayer());
    }

}
