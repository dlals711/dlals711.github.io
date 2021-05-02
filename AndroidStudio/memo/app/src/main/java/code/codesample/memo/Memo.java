package code.codesample.memo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Memo {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;

    public String body;

    public String time;

    public Memo(int id, String title, String body, String time) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.time = time;
    }

    public Memo() {
    }

    public Memo(String title, String body, String time) {
        this.title = title;
        this.body = body;
        this.time = time;
    }
}
