package com.animalhaven.hansportable.myanimalhaven.Repository.Implementations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.animalhaven.hansportable.myanimalhaven.Config.DBConstants;
import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.AnimalRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class AnimalRepositoryImpl extends SQLiteOpenHelper implements AnimalRepository {

    public static final String TABLE_NAME = "animals";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BREED = "breed";
    public static final String COLUMN_SPACE = "space_required";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_SCHEDULE_ID = "schedule_id";
    public static final String COLUMN_ADOPTION_ID = "adoption_id";

    private static final String TABLE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_BREED + " TEXT  NOT NULL , "
            + COLUMN_SPACE + " INTEGER NOT NULL, "
            + COLUMN_WEIGHT + " INTEGER NOT NULL, "
            + COLUMN_AGE + " INTEGER NOT NULL, "
            + COLUMN_SCHEDULE_ID + " INTEGER, "
            + COLUMN_ADOPTION_ID + " INTEGER );";

    public AnimalRepositoryImpl(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Animal findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_BREED,
                        COLUMN_SPACE,
                        COLUMN_WEIGHT,
                        COLUMN_AGE,
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
            final Animal animal = new Animal.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .breed(cursor.getString(cursor.getColumnIndex(COLUMN_BREED)))
                    .adoption(cursor.getInt(cursor.getColumnIndex(COLUMN_ADOPTION_ID)))
                    .schedules(cursor.getInt(cursor.getColumnIndex(COLUMN_SCHEDULE_ID)))
                    .spaceRequired(cursor.getInt(cursor.getColumnIndex(COLUMN_SPACE)))
                    .weight(cursor.getInt(cursor.getColumnIndex(COLUMN_WEIGHT)))
                    .age(cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }

    @Override
    public Animal save(Animal entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getAnimalId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_BREED, entity.getBreed());
        values.put(COLUMN_ADOPTION_ID, entity.getAdoption());
        values.put(COLUMN_SCHEDULE_ID, entity.getSchedules());
        values.put(COLUMN_SPACE, entity.getSpaceRequired());
        values.put(COLUMN_WEIGHT, entity.getWeight());
        values.put(COLUMN_AGE, entity.getAge());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Animal insertedEntity = new Animal.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Animal update(Animal entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getAnimalId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_BREED, entity.getBreed());
        values.put(COLUMN_ADOPTION_ID, entity.getAdoption());
        values.put(COLUMN_SCHEDULE_ID, entity.getSchedules());
        values.put(COLUMN_SPACE, entity.getSpaceRequired());
        values.put(COLUMN_WEIGHT, entity.getWeight());
        values.put(COLUMN_AGE, entity.getAge());

        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getAnimalId())}
        );
        return entity;
    }

    @Override
    public Animal delete(Animal entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getAnimalId())});
        return entity;
    }

    @Override
    public Set<Animal> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Animal> animals;
        animals = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final Animal user = new Animal.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .breed(cursor.getString(cursor.getColumnIndex(COLUMN_BREED)))
                        .adoption(cursor.getInt(cursor.getColumnIndex(COLUMN_ADOPTION_ID)))
                        .schedules(cursor.getInt(cursor.getColumnIndex(COLUMN_SCHEDULE_ID)))
                        .spaceRequired(cursor.getInt(cursor.getColumnIndex(COLUMN_SPACE)))
                        .weight(cursor.getInt(cursor.getColumnIndex(COLUMN_WEIGHT)))
                        .age(cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)))
                        .build();
                animals.add(user);
            } while (cursor.moveToNext());
        }
        return animals;
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
