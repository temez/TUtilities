package dev.temez.utilities.shared.user;

import dev.temez.utilities.shared.exception.UndefinedGroupException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;

import java.util.Objects;
import java.util.UUID;

/**
 * Обёртка на группу игрока, облегчает получение данных из LuckPerms
 *
 * @author temez
 * @since 0.1.1
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@RequiredArgsConstructor
public class LPGroupWrapper {

    String display;
    String prefix;
    String suffix;
    int weight;

    /**
     * Получает обёртку игрока по uuid
     *
     * @param uuid uuid игрока
     * @since 0.1.1
     */
    public static LPGroupWrapper get(UUID uuid) throws UndefinedGroupException {
        Group group = LuckPermsProvider.get().getGroupManager().getGroup(Objects.requireNonNull(LuckPermsProvider.get().getUserManager().getUser(uuid)).getPrimaryGroup());
        if (group == null) throw new UndefinedGroupException();
        return new LPGroupWrapper(group.getDisplayName(), group.getCachedData().getMetaData().getPrefix(), group.getCachedData().getMetaData().getSuffix(), group.getWeight().getAsInt());
    }
}
