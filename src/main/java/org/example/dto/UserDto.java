package org.example.dto;

/**
 * ДТО для сущности пользователя
 *
 * @param id       ID идентификатор записи
 * @param username имя пользователя (уникальное)
 */
public record UserDto(Long id, String username) {
}
