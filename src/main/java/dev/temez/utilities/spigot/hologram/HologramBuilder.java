package dev.temez.utilities.spigot.hologram;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import dev.temez.utilities.shared.TextUtility;
import dev.temez.utilities.spigot.hologram.line.HologramLineType;
import dev.temez.utilities.spigot.hologram.line.HologramLineWrapper;
import dev.temez.utilities.spigot.hologram.line.InvalidLineContextException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author temez
 * @since 0.1.1
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class HologramBuilder {

    final JavaPlugin plugin;
    Location location;
    ArrayList<HologramLineWrapper> lines;

    public HologramBuilder location(Location location) {
        this.location = location;
        return this;
    }

    public HologramBuilder line(HologramLineWrapper line){
        if(this.lines == null){
            this.lines = new ArrayList<>();
            this.lines.add(line);
        }
        else{
            this.lines.add(line);
        }
        return this;
    }


    public HologramBuilder lines(Collection<HologramLineWrapper> lines) {
        lines.forEach(this::line);
        return this;
    }

    public HologramBuilder line(String string) throws InvalidLineContextException {
        line(new HologramLineWrapper(HologramLineType.TEXT).stringContext(string));
        return this;
    }

    public HologramBuilder line(ItemStack itemStack) throws InvalidLineContextException {
        line(new HologramLineWrapper(HologramLineType.ITEM).itemContext(itemStack));
        return this;
    }

    public Hologram build() throws InvalidLineContextException {
        Hologram hologram = HologramsAPI.createHologram(plugin, location);
        for(HologramLineWrapper line : lines){
            if(line.getType() == HologramLineType.TEXT) line(TextUtility.colorize(line.getStringContext()));
            else line(line.getItemContext());
        }
        return hologram;
    }
}
