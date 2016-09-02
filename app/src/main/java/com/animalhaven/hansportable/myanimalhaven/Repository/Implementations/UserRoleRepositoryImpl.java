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
import com.animalhaven.hansportable.myanimalhaven.Domain.UserRole;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.UserRoleRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class UserRoleRepositoryImpl extends SQLiteOpenHelper implements UserRoleRepository {

    public static final String TABLE_NAME = "user_roles";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_USER_ID = "user_id";

    private static final String TABLE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_CODE + " TEXT NOT NULL ,"
            + COLUMN_USER_ID + " INTEGER NOT NULL );";

    public UserRoleRepositoryImpl(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);//Declared in Config package
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public UserRole findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_NAME,
                        COLUMN_USER_ID
                },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final UserRole scheduleType = new UserRole.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .userId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)))
                    .build();
            return scheduleType;
        } else {
            return null;
        }
    }

    @Override
    public UserRole save(UserRole entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getUserRoleId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_USER_ID, entity.getUserId());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        UserRole insertedEntity = new UserRole.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public UserRole update(UserRole entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getUserRoleId());
        values.put(COLUMN_USER_ID, entity.getUserId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_NAME, entity.getName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getUserRoleId())}
        );
        return entity;
    }

    @Override
    public UserRole delete(UserRole entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getUserRoleId())});
        return entity;
    }

    @Override
    public Set<UserRole> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<UserRole> roles;
        roles = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final UserRole role = new UserRole.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .userId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)))
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
