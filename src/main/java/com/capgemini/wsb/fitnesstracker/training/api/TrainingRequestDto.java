package com.capgemini.wsb.fitnesstracker.training.api;

import lombok.Getter;

import java.util.Date;

/**
 * A Data Transfer Object (DTO) for creating or updating a training session.
 */
@Getter
public class TrainingRequestDto {

    /**
     * The ID of the user associated with the training session.
     */
    private Long userId;

    /**
     * The start time of the training session.
     */
    private Date startTime;

    /**
     * The end time of the training session.
     */
    private Date endTime;

    /**
     * The type of activity performed during the training session (e.g., RUNNING, TENNIS).
     */
    private String activityType;

    /**
     * The distance covered during the training session (in kilometers).
     */
    private Double distance;

    /**
     * The average speed during the training session (in kilometers per hour).
     */
    private Double averageSpeed;

}
