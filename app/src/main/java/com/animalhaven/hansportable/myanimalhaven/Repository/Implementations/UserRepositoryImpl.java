package com.animalhaven.hansportable.myanimalhaven.Repository.Implementations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.animalhaven.hansportable.myanimalhaven.Config.DBConstants;
import com.animalhaven.hansportable.myanimalhaven.Domain.User;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.UserRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class UserRepositoryImpl extends SQLiteOpenHelper implements UserRepository {


    public static final String TABLE_NAME = "users";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ID_NUMBER = "id_number";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_DONATION_ID = "donation_id";
    public static final String COLUMN_SCHEDULE_ID = "schedule_id";
    public static final String COLUMN_ADOPTION_ID = "adoption_id";

    private static final String TABLE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ID_NUMBER + " TEXT  NOT NULL , "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_SURNAME + " TEXT  NOT NULL , "
            + COLUMN_DONATION_ID + " INTEGER, "
            + COLUMN_SCHEDULE_ID + " INTEGER, "
            + COLUMN_ADOPTION_ID + " INTEGER );";


    public UserRepositoryImpl(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);//Declared in Config package
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public User findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_ID_NUMBER,
                        COLUMN_NAME,
                        COLUMN_SURNAME,
                        COLUMN_DONATION_ID,
                        COLUMN_SCHEDULE_ID,
                        COLUMN_ADOPTION_ID
                },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final User user = new User.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .idNumber(cursor.getString(cursor.getColumnIndex(COLUMN_ID_NUMBER)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .adoptionId(cursor.getInt(cursor.getColumnIndex(COLUMN_ADOPTION_ID)))
                    .scheduleId(cursor.getInt(cursor.getColumnIndex(COLUMN_SCHEDULE_ID)))
                    .donationId(cursor.getInt(cursor.getColumnIndex(COLUMN_DONATION_ID)))
                    .build();

            return user;
        } else {
            return null;
        }
    }

    @Override
    public User save(User entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getUserId());
        values.put(COLUMN_ID_NUMBER, entity.getIdNumber());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_ADOPTION_ID, entity.getAdoption());
        values.put(COLUMN_DONATION_ID, entity.getDonation());
        values.put(COLUMN_SCHEDULE_ID, entity.getSchedule());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        User insertedEntity = new User.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public User update(User entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getUserId());
        values.put(COLUMN_ID_NUMBER, entity.getIdNumber());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_ADOPTION_ID, entity.getAdoption());
        values.put(COLUMN_DONATION_ID, entity.getDonation());
        values.put(COLUMN_SCHEDULE_ID, entity.getSchedule());

        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getUserId())}
        );
        return entity;
    }

    @Override
    public User delete(User entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getUserId())});
        return entity;
    }

    @Override
    public Set<User> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<User> users;
        users = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final User user = new User.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .idNumber(cursor.getString(cursor.getColumnIndex(COLUMN_ID_NUMBER)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .adoptionId(cursor.getInt(cursor.getColumnIndex(COLUMN_ADOPTION_ID)))
                        .scheduleId(cursor.getInt(cursor.getColumnIndex(COLUMN_SCHEDULE_ID)))
                        .donationId(cursor.getInt(cursor.getColumnIndex(COLUMN_DONATION_ID)))
                        .build();
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
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
