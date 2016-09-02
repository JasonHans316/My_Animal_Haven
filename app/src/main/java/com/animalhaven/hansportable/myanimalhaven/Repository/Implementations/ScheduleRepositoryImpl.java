package com.animalhaven.hansportable.myanimalhaven.Repository.Implementations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.animalhaven.hansportable.myanimalhaven.Config.DBConstants;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.ScheduleRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class ScheduleRepositoryImpl  extends SQLiteOpenHelper implements ScheduleRepository {

    public static final String TABLE_NAME = "schedules";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ACTIVITY = "activity";
    public static final String COLUMN_TIME_REQUIRED = "time_required";

    private static final String TABLE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ACTIVITY + " TEXT  NOT NULL , "
            + COLUMN_TIME_REQUIRED + " INTEGER NOT NULL );";

    public ScheduleRepositoryImpl(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);//Declared in Config package
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Schedule findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_ACTIVITY,
                        COLUMN_TIME_REQUIRED
                },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Schedule schedule = new Schedule.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .activity(cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY)))
                    .timeRequired(cursor.getInt(cursor.getColumnIndex(COLUMN_TIME_REQUIRED)))
                    .build();
            return schedule;
        } else {
            return null;
        }
    }

    @Override
    public Schedule save(Schedule entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getScheduleId());
        values.put(COLUMN_ACTIVITY, entity.getActivity());
        values.put(COLUMN_TIME_REQUIRED, entity.getTimeRequired());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Schedule insertedEntity = new Schedule.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Schedule update(Schedule entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getScheduleId());
        values.put(COLUMN_ACTIVITY, entity.getActivity());
        values.put(COLUMN_TIME_REQUIRED, entity.getTimeRequired());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getScheduleId())}
        );
        return entity;
    }

    @Override
    public Schedule delete(Schedule entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getScheduleId())});
        return entity;
    }

    @Override
    public Set<Schedule> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Schedule> schedules;
        schedules = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final Schedule schedule = new Schedule.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .activity(cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY)))
                        .timeRequired(cursor.getInt(cursor.getColumnIndex(COLUMN_TIME_REQUIRED)))
                        .build();
                schedules.add(schedule);
            } while (cursor.moveToNext());
        }
        return schedules;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(TABLE_CREATE);
        }
        catch(Exception x)
        {
            return;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
