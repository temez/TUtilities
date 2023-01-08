package dev.temez.utilities.spigot.storage;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

/**
 * Базовый класс игрока, необходим для работы с DAO и UserManager
 *
 * @author temez
 * @since 0.1.1
 */
@Getter
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@RequiredArgsConstructor
public abstract class UserBase {

    String username;
    UUID uniqueId;
}
