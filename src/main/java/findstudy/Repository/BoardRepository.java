package findstudy.Repository;

import findstudy.Domain.MainBoard;
import findstudy.Domain.Member;
import org.jboss.jandex.Main;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BoardRepository extends JpaRepository<MainBoard, Long> {
    public MainBoard save(MainBoard mainBoard);
}
