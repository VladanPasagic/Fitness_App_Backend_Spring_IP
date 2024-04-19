package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.models.dtos.SingleTrainingProgram;
import etf.unibl.org.ip.ipbackend.models.dtos.TrainingProgram;
import etf.unibl.org.ip.ipbackend.models.requests.CommentRequest;
import etf.unibl.org.ip.ipbackend.models.requests.TrainingProgramRequest;
import etf.unibl.org.ip.ipbackend.services.CommentService;
import etf.unibl.org.ip.ipbackend.services.TrainingProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/training-programs")
public class TrainingProgramController {
    private final TrainingProgramService trainingProgramService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<TrainingProgram> getTrainingPrograms() {
        return trainingProgramService.getAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public SingleTrainingProgram getTrainingProgram(@PathVariable int id) {
        return trainingProgramService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainingProgram(@ModelAttribute @Valid TrainingProgramRequest trainingProgramRequest) throws IOException {
        trainingProgramService.save(trainingProgramRequest);
    }

    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCommentToTrainingProgram(@RequestBody @Valid CommentRequest commentRequest, @PathVariable String id) {
        commentService.addComment(commentRequest);
    }

    @GetMapping
    @RequestMapping("/creator/{id}")
    public List<TrainingProgram> getTrainingPrograms(@PathVariable String id) {
        return trainingProgramService.getAllFromTrainee(Integer.parseInt(id));
    }

}
