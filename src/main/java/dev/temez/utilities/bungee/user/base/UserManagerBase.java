package dev.temez.utilities.bungee.user.base;

import dev.temez.utilities.shared.exception.UserAlreadyLoadedException;
import dev.temez.utilities.shared.exception.UserNotLoadedException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author temez
 * @since 0.1.1
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
@Getter
@RequiredArgsConstructor
public abstract class UserManagerBase<T extends UserBase, D extends UserDaoBase<T>, P extends Plugin> implements Listener {

    P proxy;
    D userDao;
    HashMap<UUID, T> loadedUsers = new HashMap<>();

    /**
     * Инициализирует менеджер: запускает автосейв данных, регистрирует события и создаёт таблицы в DAO (если нужны)
     *
     * @since 0.1.1
     */
    public void init() {
        userDao.createTable();
        proxy.getProxy().getScheduler().schedule(proxy, this::saveData, 300, 300, TimeUnit.SECONDS);
        proxy.getProxy().getPluginManager().registerListener(proxy, this);
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
    public void addUser(ProxiedPlayer player) throws UserAlreadyLoadedException {
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
    public void removeUser(ProxiedPlayer player) throws UserNotLoadedException {
        if (!loadedUsers.containsKey(player.getUniqueId())) throw new UserNotLoadedException();
        userDao.save(loadedUsers.get(player.getUniqueId()));
        loadedUsers.remove(player.getUniqueId());
        if (player.isConnected()) player.disconnect(TextComponent.fromLegacyText("Вы были отключены от сервера"));
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
    public T get(ProxiedPlayer player) {
        return get(player.getUniqueId());
    }

    /**
     * Обрабатывает подключение игрока к прокси
     *
     * @since 0.1.1
     */
    @EventHandler
    @SneakyThrows(UserAlreadyLoadedException.class)
    public void onConnect(PostLoginEvent event) {
        addUser(event.getPlayer());
    }

    /**
     * Обрабатывает отключение игрока от прокси
     *
     * @since 0.1.1
     */
    @EventHandler
    @SneakyThrows(UserNotLoadedException.class)
    public void onDisconnect(PlayerDisconnectEvent event) {
        removeUser(event.getPlayer());
    }

}
