package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> {
                    assert training.getUser().getId() != null;
                    return training.getUser().getId().equals(userId);
                })
                .toList();
    }

    default List<Training> findByActivityType(String activityType) {
        return findAll().stream()
                .filter(training -> {
                    assert training.getActivityType() != null;
                    return training.getActivityType().toString().equals(activityType);
                })
                .toList();
    }

    default List<Training> findByDateBefore(Date time) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(time))
                .toList();
    }
}
