package dev.temez.utilities.spigot.configuration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;


/**
 * @author temez
 * @since 0.1.1
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
public class ConfigLocation {

    double x;
    double y;
    double z;
    String world;
    double yaw;
    double pitch;

    public ConfigLocation(double x, double y, double z, String world) {
        this(x, y, z, world, 0, 0);
    }

    public static ConfigLocation parse(ConfigurationSection configurationSection){
        return new ConfigLocation(configurationSection.getDouble("x"),
                configurationSection.getDouble("y"),
                configurationSection.getDouble("z"),
                configurationSection.getString("world"),
                configurationSection.getDouble("yaw"),
                configurationSection.getDouble("pitch"));

    }

    public Location getBukkitLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z, (float) yaw, (float) pitch);
    }

    public World getBukkitWorld(){
        return Bukkit.getWorld(world);
    }
}
