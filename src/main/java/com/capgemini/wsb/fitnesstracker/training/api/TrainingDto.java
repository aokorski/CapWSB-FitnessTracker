package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;

import java.util.Date;

/**
 * A Data Transfer Object (DTO) representing the details of a training session.
 *
 * @param id            the unique identifier of the training session
 * @param user          the user associated with the training session
 * @param startTime     the start time of the training session
 * @param endTime       the end time of the training session
 * @param activityType  the type of activity performed during the training session
 * @param distance      the distance covered during the training session (in kilometers)
 * @param averageSpeed  the average speed during the training session (in kilometers per hour)
 */
public record TrainingDto(
        Long id,
        UserDto user,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed) {

    /**
     * A mapper instance for converting between {@link UserDto} and other user-related objects.
     */
    public static UserMapper userMapper;
}
