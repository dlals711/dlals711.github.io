package yju.wdb.finaltermm1601205.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yju.wdb.finaltermm1601205.entity.Board;
import yju.wdb.finaltermm1601205.entity.Friend;

public interface SearchBoardRepository {
    Friend search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
