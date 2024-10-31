package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

    UserSimpleDto toSimple(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    UserEmailDto toEmail(User user) {
        return new UserEmailDto(user.getId(),
                user.getEmail());
    }

    UserOlderDto toOlder(User user) {
        return new UserOlderDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate());
    }

}
