package com.animalhaven.hansportable.myanimalhaven.Repository.Implementations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.animalhaven.hansportable.myanimalhaven.Config.DBConstants;
import com.animalhaven.hansportable.myanimalhaven.Domain.ScheduleType;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.ScheduleTypeRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class ScheduleTypeRepositoryImpl extends SQLiteOpenHelper implements ScheduleTypeRepository {

    public static final String TABLE_NAME = "schedule_types";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_SCHEDULE_ID = "schedule_id";

    private static final String TABLE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_CODE + " TEXT NOT NULL ,"
            + COLUMN_SCHEDULE_ID + " INTEGER NOT NULL );";

    public ScheduleTypeRepositoryImpl(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);//Declared in Config package
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }


    @Override
    public ScheduleType findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_NAME,
                        COLUMN_SCHEDULE_ID
                },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final ScheduleType scheduleType = new ScheduleType.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .scheduleId(cursor.getInt(cursor.getColumnIndex(COLUMN_SCHEDULE_ID)))
                    .build();
            return scheduleType;
        } else {
            return null;
        }
    }

    @Override
    public ScheduleType save(ScheduleType entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getScheduleTypeId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_SCHEDULE_ID, entity.getSchedules());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        ScheduleType insertedEntity = new ScheduleType.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public ScheduleType update(ScheduleType entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getScheduleTypeId());
        values.put(COLUMN_SCHEDULE_ID, entity.getSchedules());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_NAME, entity.getName());
        db.update(
        TABLE_NAME,
        values,
        COLUMN_ID + " =? ",
            new String[]{String.valueOf(entity.getScheduleTypeId())}
    );
    return entity;
    }

    @Override
    public ScheduleType delete(ScheduleType entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getScheduleTypeId())});
        return entity;
    }

    @Override
    public Set<ScheduleType> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<ScheduleType> scheduleTypes;
        scheduleTypes = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final ScheduleType scheduleType = new ScheduleType.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .scheduleId(cursor.getInt(cursor.getColumnIndex(COLUMN_SCHEDULE_ID)))
                        .build();
                scheduleTypes.add(scheduleType);
            } while (cursor.moveToNext());
        }
        return scheduleTypes;
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
