package com.animalhaven.hansportable.myanimalhaven.Repository.Implementations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.animalhaven.hansportable.myanimalhaven.Config.DBConstants;
import com.animalhaven.hansportable.myanimalhaven.Domain.LivingArea;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.LivingAreaRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class LivingAreaRepositoryImpl extends SQLiteOpenHelper implements LivingAreaRepository {

    public static final String TABLE_NAME = "living_areas";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_SPACE = "space_available";
    public static final String COLUMN_ANIMAL_ID = "animal_id";

    private static final String TABLE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_CODE + " TEXT NOT NULL ,"
            + COLUMN_SPACE + " INTEGER NOT NULL, "
            + COLUMN_ANIMAL_ID + " INTEGER );";


    public LivingAreaRepositoryImpl(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }


    @Override
    public LivingArea findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_NAME,
                        COLUMN_ANIMAL_ID,
                        COLUMN_SPACE
                },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final LivingArea area = new LivingArea.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .animalId(cursor.getInt(cursor.getColumnIndex(COLUMN_ANIMAL_ID)))
                    .spaceAvailable(cursor.getInt(cursor.getColumnIndex(COLUMN_SPACE)))
                    .build();
            return area;
        } else {
            return null;
        }
    }

    @Override
    public LivingArea save(LivingArea entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getLivingAreaId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_ANIMAL_ID, entity.getAnimal());
        values.put(COLUMN_SPACE, entity.getSpaceAvailable());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        LivingArea insertedEntity = new LivingArea.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public LivingArea update(LivingArea entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getLivingAreaId());
        values.put(COLUMN_ANIMAL_ID, entity.getAnimal());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SPACE, entity.getSpaceAvailable());

        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getLivingAreaId())}
        );
        return entity;
    }

    @Override
    public LivingArea delete(LivingArea entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getLivingAreaId())});
        return entity;
    }

    @Override
    public Set<LivingArea> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<LivingArea> roles;
        roles = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final LivingArea role = new LivingArea.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .animalId(cursor.getInt(cursor.getColumnIndex(COLUMN_ANIMAL_ID)))
                        .spaceAvailable(cursor.getInt(cursor.getColumnIndex(COLUMN_SPACE)))
                        .build();
                roles.add(role);
            } while (cursor.moveToNext());
        }
        return roles;
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
