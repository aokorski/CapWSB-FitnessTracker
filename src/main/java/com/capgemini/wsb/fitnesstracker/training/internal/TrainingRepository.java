package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for managing {@link Training} entities.
 * Provides custom query methods in addition to the default {@link JpaRepository} methods.
 */
interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds all training sessions associated with a specific user ID.
     *
     * @param userId the ID of the user whose training sessions are to be retrieved
     * @return a list of training sessions for the specified user
     */
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> {
                    assert training.getUser().getId() != null;
                    return training.getUser().getId().equals(userId);
                })
                .toList();
    }

    /**
     * Finds all training sessions filtered by activity type.
     *
     * @param activityType the type of activity (e.g., RUNNING, TENNIS)
     * @return a list of training sessions with the specified activity type
     */
    default List<Training> findByActivityType(String activityType) {
        return findAll().stream()
                .filter(training -> {
                    assert training.getActivityType() != null;
                    return training.getActivityType().toString().equals(activityType);
                })
                .toList();
    }

    /**
     * Finds all training sessions that ended before the specified date.
     *
     * @param time the cutoff date; only training sessions that ended after this date will be retrieved
     * @return a list of training sessions that ended after the specified date
     */
    default List<Training> findByDateBefore(Date time) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(time))
                .toList();
    }
}
