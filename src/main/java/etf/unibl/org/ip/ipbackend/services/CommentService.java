package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.Comment;
import etf.unibl.org.ip.ipbackend.models.requests.CommentRequest;

import java.util.List;

public interface CommentService {
    void addComment(String id, CommentRequest commentRequest);

    List<Comment> getAllCommentsOnTrainingProgram(int id);
}
