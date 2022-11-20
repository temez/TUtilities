package dev.temez.utilities.spigot.module.impl;

import dev.temez.utilities.spigot.module.GameModule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author temez
 * @since 0.1.2
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class ListenableGameModule<T extends JavaPlugin> implements GameModule, Listener {

    @Getter
    T plugin;

    public void enable(){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
