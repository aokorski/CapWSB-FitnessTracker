package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor

public class TrainingController {
    private final TrainingServiceImpl trainingService;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final TrainingMapper trainingMapper;
    private final UserServiceImpl userService;

    @GetMapping()
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activity/activityType")
    public ResponseEntity<List<TrainingDto>> getTrainingsByActivityType(@RequestParam String activityType) {
        List<Training> trainings = trainingService.getTrainingsByActivityType(activityType);
        List<TrainingDto> response = trainings.stream()
                .map(trainingMapper::toDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/finished/{dateString}")
    public List<TrainingDto> getTrainingsOlderThan(@PathVariable String dateString) throws ParseException {
        Date date = formatter.parse(dateString);
        return trainingService.getTrainingsOlderThan(date)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}
