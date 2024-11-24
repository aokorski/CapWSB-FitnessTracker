package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingRequestDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * REST controller for managing training sessions.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;
    private final UserServiceImpl userService;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Retrieves all training sessions.
     *
     * @return a list of all training sessions in {@link TrainingDto} format
     */
    @GetMapping()
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all training sessions for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of training sessions associated with the given user ID in {@link TrainingDto} format
     */
    @GetMapping("{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all training sessions filtered by activity type.
     *
     * @param activityType the type of activity (e.g., RUNNING, TENNIS)
     * @return a {@link ResponseEntity} containing a list of training sessions in {@link TrainingDto} format
     */
    @GetMapping("/activity/activityType")
    public ResponseEntity<List<TrainingDto>> getTrainingsByActivityType(@RequestParam String activityType) {
        List<Training> trainings = trainingService.getTrainingsByActivityType(activityType);
        List<TrainingDto> response = trainings.stream()
                .map(trainingMapper::toDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all training sessions that occurred before a specific date.
     *
     * @param dateString the date in "yyyy-MM-dd" format
     * @return a list of training sessions older than the specified date in {@link TrainingDto} format
     * @throws ParseException if the provided date string cannot be parsed
     */
    @GetMapping("/finished/{dateString}")
    public List<TrainingDto> getTrainingsOlderThan(@PathVariable String dateString) throws ParseException {
        Date date = formatter.parse(dateString);
        return trainingService.getTrainingsOlderThan(date)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Adds a new training session.
     *
     * @param trainingRequestDto the details of the training session to be created
     * @return a {@link ResponseEntity} containing the created training session in {@link TrainingDto} format
     */
    @PostMapping
    public ResponseEntity<TrainingDto> addTraining(@RequestBody @Valid TrainingRequestDto trainingRequestDto) {
        User user = userService.getUser(trainingRequestDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Training training = trainingService.createTraining(trainingRequestDto, user);
        TrainingDto responseDto = trainingMapper.toDto(training);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    /**
     * Updates an existing training session.
     *
     * @param id          the ID of the training session to update
     * @param trainingDto the updated training details
     * @return the updated {@link Training} object
     */
    @PutMapping("/{id}")
    public Training updateTraining(@PathVariable Long id, @RequestBody TrainingDto trainingDto) {
        try {
            Training mappedTraining = trainingService.getTraining(id).orElseThrow();
            Training updatedTraining = trainingMapper.toUpdateEntity(trainingDto, mappedTraining);
            return trainingService.updateTraining(updatedTraining);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
