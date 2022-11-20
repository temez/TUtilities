package dev.temez.utilities.spigot.configuration.reflect;

import dev.temez.utilities.spigot.configuration.ConfigLocation;
import dev.temez.utilities.spigot.configuration.reflect.annotation.ConfigurationLocation;
import dev.temez.utilities.spigot.configuration.reflect.annotation.ConfigurationPath;
import dev.temez.utilities.spigot.configuration.reflect.exception.IncomparableFieldType;
import dev.temez.utilities.spigot.configuration.reflect.exception.NoSuchConfigurationSection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

/**
 * @author temez
 * @since 0.1.2
 */
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ReflectConfiguration<T> {

    @Getter
    T configuration;
    final FileConfiguration fileConfiguration;

    @SneakyThrows({InstantiationException.class, IllegalAccessException.class})
    public ReflectConfiguration<T> parse() throws NoSuchConfigurationSection, IncomparableFieldType {
        configuration = (T) configuration.getClass().newInstance();
        List<Field> fields = Arrays.stream(configuration.getClass().getDeclaredFields()).toList();
        for (Field field : fields) {
            String pathString = field.getName();
            ConfigurationPath pathAnnotation = field.getAnnotation(ConfigurationPath.class);
            if (pathAnnotation != null) pathString = pathAnnotation.path() + "." + pathString;
            if (!fileConfiguration.isSet(pathString)) {
                throw new NoSuchConfigurationSection(field.getName(), fileConfiguration.getName());
            }
            field.setAccessible(true);
            if (field.getAnnotation(ConfigurationLocation.class) != null) {
                if (field.getType().getName().equals("dev.temez.utilities.spigot.configuration.ConfigLocation")) {
                    field.set(configuration, ConfigLocation.parse(fileConfiguration.getConfigurationSection(pathString)));
                    continue;
                }
                field.set(configuration, ConfigLocation.parse(fileConfiguration.getConfigurationSection(pathString)).getBukkitLocation());
            }
            if (field.getType().isAssignableFrom(Integer.class)) {
                field.set(configuration, fileConfiguration.getInt(pathString));
            } else if (field.getType().isAssignableFrom(Double.class)) {
                field.set(configuration, fileConfiguration.getDouble(pathString));
            } else if (field.getType().isAssignableFrom(String.class)) {
                field.set(configuration, fileConfiguration.getString(pathString));
            } else if (field.getType().isAssignableFrom(List.class)) {
                ParameterizedType parametrizedType = (ParameterizedType) field.getGenericType();
                if (parametrizedType.getActualTypeArguments()[0].toString().contains("Integer")) {
                    field.set(configuration, fileConfiguration.getIntegerList(pathString));
                } else if (parametrizedType.getActualTypeArguments()[0].toString().contains("Double")) {
                    field.set(configuration, fileConfiguration.getDoubleList(pathString));
                } else if (parametrizedType.getActualTypeArguments()[0].toString().contains("String")) {
                    field.set(configuration, fileConfiguration.getStringList(pathString));
                } else throw new IncomparableFieldType(field.getName());
            } else throw new IncomparableFieldType(field.getName());
        }
        return this;
    }
}
