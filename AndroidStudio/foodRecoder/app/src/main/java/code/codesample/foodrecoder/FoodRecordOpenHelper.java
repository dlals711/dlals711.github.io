package code.codesample.foodrecoder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import code.codesample.foodrecoder.data.FoodRecord;

public class FoodRecordOpenHelper extends SQLiteOpenHelper {
    public FoodRecordOpenHelper(@Nullable Context context,
                                @Nullable String name,
                                @Nullable SQLiteDatabase.CursorFactory factory,
                                int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table foods (id integer primary key autoincrement, food text, time text)";
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table foods";
        db.execSQL(sql);
        onCreate(db);
    }

    /**
     * DB에 데이터 추가하기
     * @param record to store
     * @return created RowId
     */
    public long addRecord(FoodRecord record){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("food", record.getFood());
        values.put("time", record.getTime());
        return db.insert("foods", null, values);
    }

    public ArrayList<FoodRecord> getRecords() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("foods",
                                null, // String[] Columns
                                null, // Where, "food=?"
                                null, // String[], where clause's args
                                null, // GroupBy
                                null, // Having
                                null); // OrderBy, "time desc”

        ArrayList<FoodRecord> result = new ArrayList<>();
        while (c.moveToNext()) {
            FoodRecord r = new FoodRecord
                    (c.getInt(c.getColumnIndex("id")), c.getString(c.getColumnIndex("food")),
                            c.getString(c.getColumnIndex("time")));result.add(r);
        }
        c.close();
        return result;
    }
}