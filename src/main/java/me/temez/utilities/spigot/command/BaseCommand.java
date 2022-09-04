package me.temez.utilities.spigot.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * @author temez
 * @since 0.1
 */
public abstract class BaseCommand implements CommandExecutor {

    public abstract void execute(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        execute(sender, command, label, args);
        return true;
    }
}