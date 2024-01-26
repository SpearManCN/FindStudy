package findstudy.Service;

import findstudy.Domain.Message;
import findstudy.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public List<Message> findMyMessage(String fromNick, String toNick){
         return messageRepository.findByFromNickOrToNickOrderByNoDesc(fromNick, toNick);
    }

    public Message findByNo(int no){
        return messageRepository.findByNo(no);
    }

    public Message save(Message message){
        return messageRepository.save(message);
    }
}
