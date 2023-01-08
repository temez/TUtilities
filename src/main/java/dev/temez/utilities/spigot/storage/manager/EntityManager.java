package dev.temez.utilities.spigot.storage.manager;

import dev.temez.utilities.spigot.storage.dao.DataStorage;

import java.util.UUID;

/**
 * @author temez
 * @since 0.1.3
 */
public interface EntityManager<P, E, D extends DataStorage<E>> {

    /**
     * Инициализирует менеджер
     *
     * @since 0.1.3
     */
    void init();

    /**
     * Проводит деиницилазиацию
     *
     * @since 0.1.3
     */
    void disable();

    /**
     * Загружает данные
     *
     * @since 0.1.3
     */
    void loadData();

    /**
     * Сохраняет данные
     *
     * @since 0.1.3
     */
    void saveData();

    /**
     * Создаёт базовую сущность для записи
     *
     * @param args
     * @return
     */
    E createBaseEntity(Object... args);

    /**
     * Получает, уже загруженую в менеджер, сущность
     *
     * @param uuid uuid
     * @since 0.1.1
     */
    E get(UUID uuid);

}
