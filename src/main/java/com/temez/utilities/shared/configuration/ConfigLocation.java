package com.temez.utilities.shared.configuration;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

/**
 * @author temez
 * @since 0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfigLocation implements Cloneable {

    int x;
    int y;
    int z;
    String world;
    float yaw;
    float pitch;

    public ConfigLocation(int x, int y, int z, String world) {
        new ConfigLocation(x, y, z, world, 0, 0);
    }

    /**
     * Получает ConfigLocation из секции
     *
     * @param section секция
     * @since 0.1
     */
    public static ConfigLocation parse(ConfigurationSection section) {
        if (section == null) return null;
        ItemStack itemStack;
        return new ConfigLocation(section.getInt("x", 0),
                section.getInt("y", 0),
                section.getInt("z", 0),
                section.getString("world", ""),
                section.getInt("yaw", 0),
                section.getInt("pitch", 0)
        );
    }

    /**
     * Сохраняет ConfigLocation в секцию конфига
     *
     * @param section секция
     * @since 0.1.1
     */
    public void save(ConfigurationSection section) {
        if (section == null) return;
        section.set("x", x);
        section.set("y", y);
        section.set("z", z);
        section.set("world", world);
        section.set("yaw", yaw);
        section.set("pitch", pitch);
    }

    /**
     * Получает ConfigLocation из Location
     *
     * @param location локация
     * @since 0.1
     */
    public static ConfigLocation fromBukkitLocation(Location location) {
        return new ConfigLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getWorld().getName(), location.getYaw(), location.getPitch());
    }

    /**
     * Конвертирует обьект в Location
     *
     * @since 0.1
     */
    public Location getBukkitLocation() {
        if (Bukkit.getWorld(world) == null) return null;
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    /**
     * Клонирует ConfigLocation
     *
     * @since 0.1
     */
    @Override
    public ConfigLocation clone() {
        return new ConfigLocation(x, y, z, world, yaw, pitch);
    }
}