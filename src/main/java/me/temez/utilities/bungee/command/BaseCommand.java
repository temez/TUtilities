package me.temez.utilities.bungee.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import me.temez.utilities.bungee.chat.BungeeChatUtility;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author temez
 * @since 0.1
 */
@Getter
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class BaseCommand<T extends Plugin> extends Command {

    final T proxy;
    final List<String> availableServers = new ArrayList<>();
    boolean canBeUsedFromConsole = true;

    public BaseCommand(String cmd, T proxy) {
        super(cmd);
        this.proxy = proxy;
    }

    public BaseCommand(String cmd, T proxy, String... aliases) {
        super(cmd, "", aliases);
        this.proxy = proxy;
    }

    /**
     * Регистрирует команду на прокси
     */
    private void register() {
        proxy.getProxy().getPluginManager().registerCommand(proxy, this);
    }

    /**
     * Стандартная обработка команды
     *
     * @param sender the executor of this command
     * @param args   arguments used to invoke this command
     */
    @Override
    public final void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer) && !this.canBeUsedFromConsole) {
            sender.sendMessage(TextComponent.fromLegacyText("&cТолько игроки могут выполнять эту команду!"));
            return;
        }
        handle(sender, Arrays.stream(args).toList());
    }

    /**
     * Делает команду недоступной из консоли
     */
    protected void unavailableFromConsole() {
        this.canBeUsedFromConsole = false;
    }

    /**
     * Задаёт серверы, на которых может быть использована команда
     *
     * @param servers список серверов
     */
    protected void setAvailableServers(String... servers) {
        this.availableServers.addAll(Arrays.stream(servers).toList());
    }

    /**
     * Дальнейшая обработка команды
     *
     * @param commandSender отправитель
     * @param args          список аргументов
     */
    public abstract void handle(CommandSender commandSender, List<String> args);
}