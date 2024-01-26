package findstudy.Service;

import findstudy.Domain.Comment;
import findstudy.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> findByBoardNo(Integer boardNo){
        return commentRepository.findByBoardNo(boardNo);
    }
}
