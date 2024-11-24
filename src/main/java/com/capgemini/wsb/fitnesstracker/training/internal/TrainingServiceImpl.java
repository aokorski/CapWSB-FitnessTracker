package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingRequestDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TrainingProvider} for managing training sessions.
 * Provides methods for retrieving, creating, and updating training sessions.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    /**
     * Retrieves a training session by its ID.
     *
     * @param trainingId the ID of the training session to retrieve
     * @return an {@link Optional} containing the training session, or empty if not found
     */
    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    /**
     * Retrieves all training sessions.
     *
     * @return a list of all training sessions
     */
    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Retrieves all training sessions for a specific user.
     *
     * @param userId the ID of the user whose training sessions are to be retrieved
     * @return a list of training sessions associated with the specified user
     */
    @Override
    public List<Training> getTrainingsByUserId(final Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    /**
     * Retrieves all training sessions filtered by activity type.
     *
     * @param activityType the type of activity (e.g., RUNNING, TENNIS)
     * @return a list of training sessions with the specified activity type
     */
    @Override
    public List<Training> getTrainingsByActivityType(final String activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    /**
     * Retrieves all training sessions that ended before the specified date.
     *
     * @param time the cutoff date; only training sessions that ended before this date will be retrieved
     * @return a list of training sessions that ended before the specified date
     */
    @Override
    public List<Training> getTrainingsOlderThan(Date time) {
        return trainingRepository.findByDateBefore(time);
    }

    /**
     * Updates an existing training session.
     *
     * @param training the training session with updated data
     * @return the updated training session
     * @throws IllegalArgumentException if the training session has no ID
     */
    @Override
    public Training updateTraining(final Training training) {
        log.info("Updating Training {}", training);
        if (training.getId() == null) {
            throw new IllegalArgumentException("Training has no DB ID, create is not permitted!");
        }
        return trainingRepository.save(training);
    }

    /**
     * Creates a new training session.
     *
     * @param requestDto the data for the new training session
     * @param user       the user associated with the training session
     * @return the created training session
     */
    @Override
    public Training createTraining(TrainingRequestDto requestDto, User user) {
        Training training = Training.create();
        training.setUser(user);
        training.setStartTime(requestDto.getStartTime());
        training.setEndTime(requestDto.getEndTime());
        training.setActivityType(ActivityType.valueOf(requestDto.getActivityType()));
        training.setDistance(requestDto.getDistance());
        training.setAverageSpeed(requestDto.getAverageSpeed());

        return trainingRepository.save(training);
    }
}
