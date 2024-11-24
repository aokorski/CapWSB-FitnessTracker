package com.capgemini.wsb.fitnesstracker.training.api;

import lombok.Getter;

import java.util.Date;
@Getter
public class TrainingRequestDto {
    private Long userId;
    private Date startTime;
    private Date endTime;
    private String activityType;
    private Double distance;
    private Double averageSpeed;

}
