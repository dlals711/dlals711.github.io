package code.codesample.memo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemoDAO {
    @Insert
    Long addMemo(Memo memo);

    @Delete
    void deleteMemo(Memo memo);

    @Update
    void updateMemo(Memo memo);

    @Query("select * from Memo order by id desc")
    List<Memo> getMemoList();

    @Query("select * from Memo where id=:id")
    Memo getMemo(int id);
}
