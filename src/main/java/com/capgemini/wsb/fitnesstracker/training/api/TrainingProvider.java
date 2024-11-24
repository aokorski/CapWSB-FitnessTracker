package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interface for providing operations related to training sessions.
 */
public interface TrainingProvider {

    /**
     * Retrieves a training based on its ID.
     * If the training with the given ID is not found, {@link Optional#empty()} will be returned.
     *
     * @param trainingId the ID of the training to be retrieved
     * @return An {@link Optional} containing the found Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all training sessions.
     *
     * @return a list of all training sessions
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves all training sessions associated with a specific user ID.
     *
     * @param userId the ID of the user whose training sessions are to be retrieved
     * @return a list of training sessions for the specified user
     */
    List<Training> getTrainingsByUserId(final Long userId);

    /**
     * Retrieves all training sessions filtered by activity type.
     *
     * @param activityType the type of activity (e.g., RUNNING, TENNIS)
     * @return a list of training sessions with the specified activity type
     */
    List<Training> getTrainingsByActivityType(final String activityType);

    /**
     * Retrieves all training sessions that are older than a specified date.
     *
     * @param date the date used as a filter; only training sessions older than this date will be retrieved
     * @return a list of training sessions older than the specified date
     */
    List<Training> getTrainingsOlderThan(Date date);

    /**
     * Updates an existing training session.
     *
     * @param training the training session with updated information
     * @return the updated training session
     */
    Training updateTraining(Training training);

    /**
     * Creates a new training session for a specific user.
     *
     * @param trainingRequestDto the details of the training session to be created
     * @param user               the user for whom the training session is being created
     * @return the created training session
     */
    Training createTraining(@Valid TrainingRequestDto trainingRequestDto, User user);
}
