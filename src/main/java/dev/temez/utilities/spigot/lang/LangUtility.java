package dev.temez.utilities.spigot.lang;

import com.meowj.langutils.lang.LanguageHelper;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

/**
 * @author temez
 * @since 0.1.1
 */
@UtilityClass
public class LangUtility {

    /**
     * Получает перевод из файла локализации
     *
     * @param itemStack
     * @return
     */
    public String translate(ItemStack itemStack) {
        return LanguageHelper.getItemName(itemStack, "ru_ru");
    }

    /**
     * Получает перевод из файла локализации
     *
     * @param effectType
     * @return
     */
    public String translate(PotionEffectType effectType) {
        return LanguageHelper.getPotionEffectName(effectType, "ru_ru");
    }
}
