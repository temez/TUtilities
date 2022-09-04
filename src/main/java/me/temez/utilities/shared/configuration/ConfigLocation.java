package me.temez.utilities.shared.configuration;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

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
     */
    public static ConfigLocation parse(ConfigurationSection section) {
        if (section == null) return null;
        return new ConfigLocation(section.getInt("x", 0),
                section.getInt("y", 0),
                section.getInt("z", 0),
                section.getString("world", ""),
                section.getInt("yaw", 0),
                section.getInt("pitch", 0)
        );
    }

    /**
     * Получает ConfigLocation из Location
     *
     * @param location локация
     */
    public static ConfigLocation fromBukkitLocation(Location location) {
        return new ConfigLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getWorld().getName(), location.getYaw(), location.getPitch());
    }

    /**
     * Конвертирует обьект в Location
     */
    public Location getBukkitLocation() {
        if (Bukkit.getWorld(world) == null) return null;
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    /**
     * Клонирует ConfigLocation
     */
    @Override
    public ConfigLocation clone() {
        return new ConfigLocation(x, y, z, world, yaw, pitch);
    }
}