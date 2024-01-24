package findstudy.Service;

import findstudy.Domain.MainBoard;
import findstudy.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public MainBoard saveBoard(MainBoard mainBoard){
        return boardRepository.save(mainBoard);
    }
}
