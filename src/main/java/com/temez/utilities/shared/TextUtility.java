package com.temez.utilities.shared;


import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author temez
 * @since 0.1
 */
@UtilityClass
public class TextUtility {

    /**
     * Окрашивает сообщение
     *
     * @param message сообщение
     * @return окрашеное сообщение
     * @since 0.1
     */
    public String colorize(String message) {
        if (message == null) {
            return null;
        }
        return translateColorCodes(message);
    }

    /**
     * Окрашивает коллекцию сообщений
     *
     * @param lines коллекция
     * @return окрашеный список
     * @since 0.1
     */
    public static List<String> colorize(Collection<String> lines) {
        List<String> coloredLines = new ArrayList<>();
        for (String line : lines) {
            coloredLines.add(colorize(line));
        }
        return coloredLines;
    }

    /**
     * Убирает цвета в сообщении
     *
     * @param message сообщение
     * @since 0.1
     */
    public static String uncolorize(String message) {
        return message == null ? null : ChatColor.stripColor(colorize(message));
    }

    /**
     * Убирает цвета в коллекции сообщений
     *
     * @param lines коллекция
     * @return список сообщений
     * @since 0.1
     */
    public static List<String> uncolorize(Collection<String> lines) {
        List<String> uncoloredLines = new ArrayList<>();
        for (String line : lines) {
            uncoloredLines.add(uncolorize(line));
        }
        return uncoloredLines;
    }


    /**
     * Применяет к строке цветовые коды майнкрафта, а так-же hex коды
     *
     * @param text строка
     * @since 0.1
     */
    @SneakyThrows(ArrayIndexOutOfBoundsException.class)
    public static String translateColorCodes(String text) {
        String[] texts = text.split(String.format("((?<=%1$s)|(?=%1$s))", "&"));
        StringBuilder finalText = new StringBuilder();
        for (int i = 0; i < texts.length; i++) {
            if (texts[i].equalsIgnoreCase("&")) {
                i++;
                if (texts[i].charAt(0) == '#') {
                    finalText.append(ChatColor.of(texts[i].substring(0, 7))).append(texts[i].substring(7));
                } else {
                    finalText.append(ChatColor.translateAlternateColorCodes('&', "&" + texts[i]));
                }
                continue;
            }
            finalText.append(texts[i]);
        }
        return finalText.toString();
    }

    /**
     * Получает многострочное сообщение из коллекции
     *
     * @param message сообщение
     * @since 0.1
     */
    public static String getMultilineMessage(Collection<String> message) {
        StringBuilder builder = new StringBuilder();
        boolean firstLine = true;
        for (String line : message) {
            if (line.isEmpty()) {
                line = " ";
            }
            if (!firstLine) {
                builder.append("\n§r").append(line);
            } else {
                firstLine = false;
                builder.append(line);
            }
        }
        return builder.toString();
    }

    /**
     * Центрирует сообщение в чате
     *
     * @param message сообщение
     * @since 0.1
     */
    public static String getCenteredColoredMessage(String message) {
        int maxWidth = 80, spaces = (int) Math.round((maxWidth - 1.4 * ChatColor.stripColor(TextUtility.colorize(message)).length()) / 2);
        return StringUtils.repeat(" ", spaces) + TextUtility.colorize(message);
    }
}
