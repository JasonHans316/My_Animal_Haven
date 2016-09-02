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
import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.DonationRepository;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class DonationRepositoryImpl extends SQLiteOpenHelper implements DonationRepository {

    public static final String TABLE_NAME = "donation";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_DONATION_DATE = "donation_date";
    public static final String COLUMN_DONATION_AMOUNT = "donation_amount";


    private static final String TABLE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DONATION_AMOUNT + " INTEGER, "
            + COLUMN_COMMENT + " TEXT  NOT NULL , "
            + COLUMN_DONATION_DATE + " TEXT NOT NULL );";


    public DonationRepositoryImpl(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Donation findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_COMMENT,
                        COLUMN_DONATION_DATE,
                        COLUMN_DONATION_AMOUNT
                },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Donation adoption = new Donation.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .comment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)))
                    .donationDate(new Date(2016,05,02))
                    .amount(200)
                    .build();
            return adoption;
        } else {
            return null;
        }
    }

    @Override
    public Donation save(Donation entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getDonationId());
        values.put(COLUMN_COMMENT, entity.getComment());
        values.put(COLUMN_DONATION_DATE, String.valueOf(entity.getDonationDate()));
        values.put(COLUMN_DONATION_AMOUNT, entity.getAmount());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Donation insertedEntity = new Donation.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Donation update(Donation entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getDonationId());
        values.put(COLUMN_DONATION_AMOUNT, entity.getAmount());
        values.put(COLUMN_DONATION_DATE, String.valueOf(entity.getDonationDate()));
        values.put(COLUMN_COMMENT, entity.getComment());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getDonationId())}
        );
        return entity;
    }

    @Override
    public Donation delete(Donation entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getDonationId())});
        return entity;
    }

    @Override
    public Set<Donation> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Donation> donations;
        donations = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final Donation donation = new Donation.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .comment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)))
                        .donationDate(new Date(2016, 05, 02))
                        .amount(cursor.getInt(cursor.getColumnIndex(COLUMN_DONATION_AMOUNT)))
                        .build();
                donations.add(donation);
            } while (cursor.moveToNext());
        }
        return donations;
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
