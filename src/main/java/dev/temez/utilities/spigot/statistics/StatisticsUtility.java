package dev.temez.utilities.spigot.statistics;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

/**
 * @author temez
 * @since 0.1
 */
@UtilityClass
public class StatisticsUtility {

    /**
     * @param uuid      ююайди игрока
     * @param statistic тип статистики
     * @since 0.1
     * Получает статистику оффлайн игрока из папки мира
     */
    @SneakyThrows({IOException.class, ParseException.class})
    public long getOfflinePlayerStatistic(UUID uuid, Statistic statistic) {
        File worldFolder = new File(Bukkit.getServer().getWorlds().get(0).getWorldFolder(), "stats");
        File playerStatistics = new File(worldFolder, uuid + ".json");
        if (playerStatistics.exists()) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = null;
            jsonObject = (JSONObject) parser.parse(new FileReader(playerStatistics));
            StringBuilder statisticNmsName = new StringBuilder("stat.");
            for (char character : statistic.name().toCharArray()) {
                if (statisticNmsName.charAt(statisticNmsName.length() - 1) == '_') {
                    statisticNmsName.setCharAt(statisticNmsName.length() - 1, Character.toUpperCase(character));
                    continue;
                }
                statisticNmsName.append(Character.toLowerCase(character));
            }
            if (jsonObject.containsKey(statisticNmsName.toString())) {
                return (long) jsonObject.get(statisticNmsName.toString());
            }
            return 0;
        }
        return -1;
    }

    /**
     * @param player    обьект оффлайн игрока
     * @param statistic тип статистики
     * @since 0.1
     * Получает статистику оффлайн игрока из папки мира
     */
    public long getOfflinePlayerStatistic(OfflinePlayer player, Statistic statistic) {
        return getOfflinePlayerStatistic(player.getUniqueId(), statistic);
    }
}