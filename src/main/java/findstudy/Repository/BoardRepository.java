package findstudy.Repository;

import findstudy.Domain.MainBoard;
import findstudy.Domain.Member;
import org.jboss.jandex.Main;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BoardRepository extends JpaRepository<MainBoard, Long> {
    public MainBoard save(MainBoard mainBoard);
    public List<MainBoard> findBySortOrderByNoDesc(Integer sort);

    public MainBoard findByNo(Integer no);

    @Query("SELECT b FROM MainBoard b WHERE (b.tag1 like %:search% or b.tag2 like %:search% or b.tag3 like %:search% or b.title like %:search%) and b.sort = :sort ORDER BY b.no DESC")
    List<MainBoard> findSearchedBoards(@Param("sort")int sort, @Param("search") String search);

    @Modifying
    @Query("UPDATE MainBoard m set m.views = m.views+1 where m.no = :no")
    public void updateView(@Param("no") int no);
}
