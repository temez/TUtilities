package dev.temez.utilities.spigot.bungee;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author temez
 * @since 0.1.3
 */
@RequiredArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ServerFullness {

    int players;
    int slots;

}
