package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Represents a training session associated with a user.
 */
@Entity
@Table(name = "trainings")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    /**
     * The unique identifier for the training.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /**
     * The user associated with the training session.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The start time of the training session.
     */
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    /**
     * The end time of the training session.
     */
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    /**
     * The type of activity performed during the training session.
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    /**
     * The distance covered during the training session (in kilometers).
     */
    @Column(name = "distance")
    private double distance;

    /**
     * The average speed during the training session (in kilometers per hour).
     */
    @Column(name = "average_speed")
    private double averageSpeed;

    /**
     * Constructs a new {@code Training} instance with the specified details.
     *
     * @param user          the user associated with the training session
     * @param startTime     the start time of the training session
     * @param endTime       the end time of the training session
     * @param activityType  the type of activity performed
     * @param distance      the distance covered (in kilometers)
     * @param averageSpeed  the average speed (in kilometers per hour)
     */
    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    /**
     * Factory method to create a new {@code Training} instance with default values.
     *
     * @return a new {@code Training} instance
     */
    public static Training create() {
        return new Training();
    }
}
