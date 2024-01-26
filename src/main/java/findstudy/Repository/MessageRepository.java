package findstudy.Repository;

import findstudy.Domain.MainBoard;
import findstudy.Domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findByFromNickOrToNickOrderByNoDesc(String fromNick, String toNick);

    public Message findByNo(int no);

    public Message save(Message message);
}
