package dev.temez.utilities.spigot.storage.dao.user;

import dev.temez.utilities.shared.mysql.MySQL;
import dev.temez.utilities.spigot.storage.UserBase;
import dev.temez.utilities.spigot.storage.dao.DAOBase;

import java.util.UUID;

/**
 * @author temez
 * @since 0.1.3
 */
public abstract class UserDAOBase<T extends UserBase> extends DAOBase<T> implements UserDataStorage<T> {

    public UserDAOBase(MySQL mySQL) {
        super(mySQL);
    }

    @Override
    public boolean exists(UUID uuid) {
        return get(uuid) != null;
    }

    @Override
    public boolean exists(String username) {
        return get(username) != null;
    }
}
