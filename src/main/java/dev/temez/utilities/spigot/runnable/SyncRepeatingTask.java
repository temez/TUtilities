package dev.temez.utilities.spigot.runnable;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author temez
 * @since 0.1.1
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class SyncRepeatingTask implements Runnable{
    int taskId;

    public SyncRepeatingTask(JavaPlugin plugin, int delay, int period) {
        taskId = Bukkit.getScheduler().runTaskTimer(plugin, this, delay, period).getTaskId();
    }

    public void cancel() {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}
