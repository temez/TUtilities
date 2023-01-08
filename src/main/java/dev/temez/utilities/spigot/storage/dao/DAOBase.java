package dev.temez.utilities.spigot.storage.dao;

import dev.temez.utilities.shared.mysql.MySQL;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author temez
 * @since 0.1.3
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public abstract class DAOBase<T> implements DataStorage<T> {

    MySQL mySQL;

    @Override
    public void init() {
        createTable();
    }


}
