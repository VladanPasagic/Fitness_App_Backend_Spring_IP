package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.Comment;
import etf.unibl.org.ip.ipbackend.models.entities.CommentEntity;
import etf.unibl.org.ip.ipbackend.models.entities.TrainingProgramEntity;
import etf.unibl.org.ip.ipbackend.models.requests.CommentRequest;
import etf.unibl.org.ip.ipbackend.respositories.CommentRepository;
import etf.unibl.org.ip.ipbackend.respositories.TrainingProgramRepository;
import etf.unibl.org.ip.ipbackend.services.CommentService;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final TrainingProgramRepository trainingProgramRepository;
    private final LoggingService loggingService;

    @Override
    public void addComment(String id, CommentRequest commentRequest) {
        CommentEntity comment = modelMapper.map(commentRequest, CommentEntity.class);
        Optional<TrainingProgramEntity> trainingProgramEntity = trainingProgramRepository.findById(Integer.parseInt(id));
        comment.setId(null);
        if (trainingProgramEntity.isPresent()) {
            loggingService.log(LogLevel.INFO, "User " + commentRequest.getUserId() + " published a comment");
            comment.setTrainingProgram(trainingProgramEntity.get());
        } else {
            loggingService.log(LogLevel.WARN, "Training program not found");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        comment.setDate(new Date(System.currentTimeMillis()));
        commentRepository.saveAndFlush(comment);
    }

    @Override
    public List<Comment> getAllCommentsOnTrainingProgram(int id) {
        return commentRepository.getCommentEntitiesByTrainingProgramId(id).stream().map(c -> modelMapper.map(c, Comment.class)).toList();
    }
}
