package com.example.writery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EpisodeDBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "EpisodeDB.db";
    private static final String TABLE_PEODUCT = "episode";

    private static final String COL_ID = "_id";
    private static final String COL_TITLE  = "title";
    private static final String COL_EPISODETITLE = "episodetitle";
    private static final String COL_CONTENTS = "contents";

    public EpisodeDBHandler(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PEODUCT + "("
                + COL_ID + " INTEGER PRIMARY KEY," + COL_TITLE + " TEXT," +
                COL_EPISODETITLE + " TEXT," + COL_CONTENTS + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEODUCT);
        onCreate(db);
    }

    public void addEpisode(WriteItem product){
        ContentValues values = new ContentValues();
        values.put(COL_EPISODETITLE, product.getEpisodeTitle());
        values.put(COL_CONTENTS, product.getContents());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PEODUCT, null, values);
        db.close();
    }

    public WriteItem findProduct(String title){
        String query = "SELECT * FROM " + TABLE_PEODUCT + " WHERE "+
                COL_TITLE + " = \"" + title + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        WriteItem product = new WriteItem();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            product.setID(Integer.parseInt(cursor.getString(0)));
            product.setTitle(cursor.getString(1));
            product.setEpisodeTitle(cursor.getString(2));
            product.setContents(cursor.getString(3));
            cursor.close();
        } else{
            product = null;
        }
        db.close();
        return product;
    }

    public boolean deleteProduct(String title){
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_PEODUCT + " WHERE " +
                COL_TITLE + " = \"" + title + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        NobelItem product = new NobelItem();

        if(cursor.moveToFirst()){
            product.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PEODUCT, COL_ID + "= ?", new String[]{String.valueOf(product.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return  result;
    }
}
