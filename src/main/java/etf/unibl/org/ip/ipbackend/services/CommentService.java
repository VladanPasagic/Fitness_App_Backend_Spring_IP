package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.requests.CommentRequest;

public interface CommentService {
    void addComment(CommentRequest commentRequest);
}
