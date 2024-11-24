package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper component for converting between {@link Training} entities and {@link TrainingDto} objects.
 */
@Component
@RequiredArgsConstructor
public class TrainingMapper {

    /**
     * The {@link UserMapper} used for mapping user-related data.
     */
    private final UserMapper userMapper;

    /**
     * Converts a {@link Training} entity to a {@link TrainingDto}.
     *
     * @param training the {@link Training} entity to convert
     * @return the corresponding {@link TrainingDto} object
     */
    public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    /**
     * Converts a {@link TrainingDto} to a {@link Training} entity.
     *
     * @param trainingDto the {@link TrainingDto} to convert
     * @return the corresponding {@link Training} entity
     */
    public Training toEntity(TrainingDto trainingDto) {
        return new Training(
                userMapper.toEntity(trainingDto.user()),
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed()
        );
    }

    /**
     * Updates an existing {@link Training} entity with the values from a {@link TrainingDto}.
     * If a field in the DTO is null or has a default value, the corresponding field in the entity is not updated.
     *
     * @param trainingDto the {@link TrainingDto} containing the updated values
     * @param training    the {@link Training} entity to update
     * @return the updated {@link Training} entity
     */
    public Training toUpdateEntity(TrainingDto trainingDto, Training training) {
        training.setStartTime(trainingDto.startTime() != null ? trainingDto.startTime() : training.getStartTime());
        training.setEndTime(trainingDto.endTime() != null ? trainingDto.endTime() : training.getEndTime());
        training.setActivityType(trainingDto.activityType() != null ? trainingDto.activityType() : training.getActivityType());
        training.setDistance(trainingDto.distance() != 0 ? trainingDto.distance() : training.getDistance());
        training.setAverageSpeed(trainingDto.averageSpeed() != 0 ? trainingDto.averageSpeed() : training.getAverageSpeed());

        return training;
    }
}
