package com.capgemini.wsb.fitnesstracker.user.internal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * @return List<UserDto>
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * @return List<UserSimpleDto>
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getSimpleAllUser() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimple)
                .toList();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userMapper.toDto(userService.getUser(userId).orElseThrow());
    }

    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toEmail)
                .toList();
    }

    @GetMapping("/older/{time}")
    public List<UserOlderDto> getUsersOlderThan(@PathVariable @JsonFormat(pattern = "yyyy-MM-dd") LocalDate time) {
        return userService.findAllUsers()
                .stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .map(userMapper::toOlder)
                .toList();
    }


}
