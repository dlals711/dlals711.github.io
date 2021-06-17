package yju.wdb.finaltermm1601205.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yju.wdb.finaltermm1601205.entity.Friend;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select b, w from Friend b left join b.writer w where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);


}
