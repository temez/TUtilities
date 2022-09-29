package dev.temez.utilities.spigot.hologram.line;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.inventory.ItemStack;

/**
 * @author temez
 * @since 0.1.1
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class HologramLineWrapper {

    final HologramLineType type;
    ItemStack itemContext;
    String stringContext;

    public HologramLineWrapper itemContext(ItemStack itemContext) throws InvalidLineContextException {
        if(type != HologramLineType.ITEM) throw new InvalidLineContextException(type);
        this.itemContext = itemContext;
        return this;
    }

    public HologramLineWrapper stringContext(String stringContext) throws InvalidLineContextException {
        if(type != HologramLineType.TEXT) throw new InvalidLineContextException(type);
        this.stringContext = stringContext;
        return this;
    }
}
