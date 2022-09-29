package dev.temez.utilities.shared.database;

import dev.temez.utilities.shared.mysql.MySQL;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author temez
 * @since 0.1.1
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public abstract class DaoBase<T> {

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
     * Получает обьект из бд
     *
     * @param id id
     * @since 0.1.1
     */
    public abstract T get(int id);

    /**
     * Проверяет существует ли сущность в бд
     *
     * @param id id
     * @since 0.1.1
     */
    public boolean exists(int id) {
        return get(id) != null;
    }

    /**
     * Записывает новую сущность в бд, и возвращает её
     *
     * @param args аругменты
     * @since 0.1.1
     */
    public abstract T createNew(Object... args);

    /**
     * Сохраняет обьект в бд
     *
     * @param entity игрок
     * @since 0.1.1
     */
    public abstract void save(T entity);

    /**
     * Создаёт таблицу в бд, если её нет
     *
     * @since 0.1.1
     */
    public abstract void createTable();
}
