package dev.temez.utilities.spigot.storage.manager.user.impl;

import dev.temez.utilities.shared.exception.UserAlreadyLoadedException;
import dev.temez.utilities.shared.exception.UserNotLoadedException;
import dev.temez.utilities.spigot.storage.UserBase;
import dev.temez.utilities.spigot.storage.dao.user.UserDataStorage;
import dev.temez.utilities.spigot.storage.manager.user.UserManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author temez
 * @since 0.1.3
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
@Getter
@RequiredArgsConstructor
public abstract class UserManagerBase<P extends JavaPlugin, E extends UserBase, D extends UserDataStorage<E>> implements UserManager<P,E,D> {


    P plugin;
    D userDao;
    HashMap<UUID, E> loadedUsers = new HashMap<>();

    @Override
    public void init() {
        loadData();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::saveData, 600, 30);
    }

    @Override
    public void disable() {
        saveData();
    }

    @Override
    public void loadData() {
        userDao.getAll().forEach(user -> loadedUsers.put(user.getUniqueId(), user));
    }

    @Override
    public void saveData() {
        loadedUsers.values().forEach(userDao::save);
    }

    @Override
    public void loadUser(Player player) throws UserAlreadyLoadedException {
        if(loadedUsers.containsKey(player.getUniqueId())) throw new UserAlreadyLoadedException();
        E user;
        if(userDao.exists(player.getUniqueId())) user = userDao.get(player.getUniqueId());
        else {
            user = createBaseEntity(player.getUniqueId());
            userDao.createNew(user);
        }
        loadedUsers.put(user.getUniqueId(), user);
    }

    @Override
    public void unloadUser(Player player) throws UserNotLoadedException {
        if(!loadedUsers.containsKey(player.getUniqueId())) throw new UserNotLoadedException();
        userDao.save(loadedUsers.get(player.getUniqueId()));
        loadedUsers.remove(player.getUniqueId());
    }

    @Override
    public E get(Player player) {
        return get(player.getUniqueId());
    }

    @Override
    public E get(UUID uuid) {
        return loadedUsers.get(uuid);
    }
}
