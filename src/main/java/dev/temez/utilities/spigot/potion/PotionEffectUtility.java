package dev.temez.utilities.spigot.potion;

import lombok.experimental.UtilityClass;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

/**
 * @author temez
 * @since 0.1.1
 */
@UtilityClass
public class PotionEffectUtility {

    private static final List<PotionEffectType> positiveTypes = List.of(
            PotionEffectType.ABSORPTION,
            PotionEffectType.DAMAGE_RESISTANCE,
            PotionEffectType.DOLPHINS_GRACE,
            PotionEffectType.FAST_DIGGING,
            PotionEffectType.FIRE_RESISTANCE,
            PotionEffectType.GLOWING,
            PotionEffectType.HEAL,
            PotionEffectType.HEALTH_BOOST,
            PotionEffectType.HERO_OF_THE_VILLAGE,
            PotionEffectType.INCREASE_DAMAGE,
            PotionEffectType.INVISIBILITY,
            PotionEffectType.JUMP,
            PotionEffectType.LEVITATION,
            PotionEffectType.LUCK,
            PotionEffectType.NIGHT_VISION,
            PotionEffectType.REGENERATION,
            PotionEffectType.SATURATION,
            PotionEffectType.SLOW_FALLING,
            PotionEffectType.SPEED,
            PotionEffectType.WATER_BREATHING
    );

    public boolean isPositive(PotionEffectType effectType){
        return positiveTypes.contains(effectType);
    }

    public boolean isNegative(PotionEffectType effectType){
        return !isPositive(effectType);
    }

}

