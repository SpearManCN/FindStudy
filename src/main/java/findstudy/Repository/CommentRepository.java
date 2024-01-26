package findstudy.Repository;

import findstudy.Domain.Comment;
import findstudy.Domain.MainBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public Comment save(Comment comment);

    public List<Comment> findByBoardNo(Integer boardNo);
}
