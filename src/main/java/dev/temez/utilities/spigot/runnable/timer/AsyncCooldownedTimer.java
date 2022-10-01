package dev.temez.utilities.spigot.runnable.timer;

import dev.temez.utilities.spigot.runnable.AsyncRepeatingTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

/**
 * @author temez
 * @since 0.1.1
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AsyncCooldownedTimer {

    final JavaPlugin plugin;
    final UUID timerId;
    final int time;
    int timeLeft;

    public void run(){
        timeLeft = time;
        new AsyncRepeatingTask(plugin, 0, 20) {
            @Override
            public void run() {
                timeLeft -= 20;
                if(timeLeft <= 0){
                    cancel();
                    Bukkit.getPluginManager().callEvent(new TimerEvent(timerId));
                    return;
                }
            }
        };
    }
}
