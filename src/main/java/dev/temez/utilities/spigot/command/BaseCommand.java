package dev.temez.utilities.spigot.command;


import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author temez
 * @since 0.1
 */
@RequiredArgsConstructor
public abstract class BaseCommand<T extends JavaPlugin> implements CommandExecutor {

    protected final T plugin;

    public abstract void handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        handle(sender, command, label, args);
        return true;
    }

    /**
     * Регистрирует команду на сервере
     */
    protected void register(String command) {
        Objects.requireNonNull(plugin.getCommand(command)).setExecutor(this);
    }
}