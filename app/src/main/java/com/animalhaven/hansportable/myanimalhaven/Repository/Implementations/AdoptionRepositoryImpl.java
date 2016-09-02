package com.animalhaven.hansportable.myanimalhaven.Repository.Implementations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.animalhaven.hansportable.myanimalhaven.Config.DBConstants;
import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.AdoptionRepository;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class AdoptionRepositoryImpl extends SQLiteOpenHelper implements AdoptionRepository {

    public static final String TABLE_NAME = "adoption";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_ADOPTION_DATE = "adoption_date";

    private static final String TABLE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COMMENT + " TEXT  NOT NULL , "
            + COLUMN_ADOPTION_DATE + " TEXT NOT NULL );";

    public AdoptionRepositoryImpl(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Adoption findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_COMMENT,
                        COLUMN_ADOPTION_DATE
                        },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Adoption adoption = new Adoption.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .comment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)))
                    .adoptionDate(new Date(2016,05,02))
                    .build();
            return adoption;
        } else {
            return null;
        }
    }

    @Override
    public Adoption save(Adoption entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getAdoptionId());
        values.put(COLUMN_COMMENT, entity.getComment());
        values.put(COLUMN_ADOPTION_DATE, String.valueOf(entity.getAdoptionDate()));
        long id = db.insertOrThrow(TABLE_NAME, null, values);;
        Adoption insertedEntity = new Adoption.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Adoption update(Adoption entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getAdoptionId());
        values.put(COLUMN_ADOPTION_DATE, String.valueOf(entity.getAdoptionDate()));
        values.put(COLUMN_COMMENT, entity.getComment());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getAdoptionId())}
        );
        return entity;
    }

    @Override
    public Adoption delete(Adoption entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getAdoptionId())});
        return entity;
    }

    @Override
    public Set<Adoption> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Adoption> Adoptions;
        Adoptions = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final Adoption adoption = new Adoption.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .comment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)))
                        .adoptionDate(new Date(2016, 05, 02))
                        .build();
                Adoptions.add(adoption);
            } while (cursor.moveToNext());
        }
        return Adoptions;
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
        db.execSQL(TABLE_CREATE);
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
