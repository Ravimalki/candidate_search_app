package com.example.candidate_search_test_app.sqliteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.candidate_search_test_app.model.SelectedCandidates;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Koshini Bulathsinhala
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SelectedCandidate";
    public static final String TABLE_NAME = "Candidates";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_IMAGE = "Image";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AGE = "Age";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Image VARCHAR," +
                "Name VARCHAR," +
                "Age VARCHAR )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String img, String name, String age) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE, img);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);

        long result = database.insert(TABLE_NAME, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<SelectedCandidates> fetchAllData() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from Candidates", null);

        List<SelectedCandidates> candidatesList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                SelectedCandidates selectedCandidates = new SelectedCandidates();
                selectedCandidates.setId(Integer.parseInt(cursor.getString(0)));
                selectedCandidates.setImage(cursor.getString(1));
                selectedCandidates.setName(cursor.getString(2));
                selectedCandidates.setAge(cursor.getString(3));

                candidatesList.add(selectedCandidates);
            } while (cursor.moveToNext());
        }
        return candidatesList;
    }

    public boolean updateData(int id, String img, String name, String age) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE, img);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);

        database.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});
        return true;
    }

    public Integer deleteData(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME, "ID=?", new String[]{String.valueOf(id)});
    }
}
