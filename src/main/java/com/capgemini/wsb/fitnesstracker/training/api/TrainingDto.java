package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;

import java.util.Date;

public record TrainingDto(
        Long id,
        UserDto user,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed) {
    public static UserMapper userMapper;
}