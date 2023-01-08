package dev.temez.utilities.spigot.storage.dao;

import java.util.List;

/**
 * @author temez
 * @since 0.1.3
 */
public interface DataStorage<T> {

    /**
     * Инициализирует хранилище данных
     *
     * @since 0.1.3
     */
    void init();

    /**
     * Возвращает список всех сохранённых в бд сущностей
     *
     * @since 0.1.3
     */
    List<T> getAll();

    /**
     * Создаёт таблицу в бд, если её нет
     *
     * @since 0.1.3
     */
    void createTable();

    /**
     * Сохраняет обьект в бд
     *
     * @param entity сущность
     * @since 0.1.3
     */
    void save(T entity);

    /**
     * Записывает новую сущность в бд
     *
     * @param entity сущность
     * @since 0.1.3
     */
    void createNew(T entity);
}
