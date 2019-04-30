package com.example.writery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class EpisodeDBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "EpisodeDB.db";
    private static final String TABLE_PEODUCT = "episode";

    private static final String COL_ID = "_id";
    private static final String COL_CODE  = "code";
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
                + COL_ID + " INTEGER PRIMARY KEY," + COL_CODE + " INTEGER," +
                COL_EPISODETITLE + " TEXT," + COL_CONTENTS + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEODUCT);
        onCreate(db);
    }

    public void addEpisode(EpisodeItem product){
        ContentValues values = new ContentValues();
        values.put(COL_CODE, product.getCode());
        values.put(COL_EPISODETITLE, product.getEpisodeTitle());
        values.put(COL_CONTENTS, product.getContents());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PEODUCT, null, values);
        db.close();
    }

    public void update(int id, String title, String content){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "UPDATE "+ TABLE_PEODUCT + " SET "+ COL_EPISODETITLE + "='"+ title + "'," +
                ""+ COL_CONTENTS + "='"+ content + "' WHERE " + COL_ID + "=" + id;

        db.execSQL(sql);

        db.close();
    }

    public ArrayList<EpisodeItem> showEpisode(int code){
            String query = "SELECT * FROM " + TABLE_PEODUCT + " WHERE "+
                    COL_CODE + " = \"" + code + "\"";

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            ArrayList<EpisodeItem> product = new ArrayList<>();

            while (cursor.moveToNext()) {
                EpisodeItem episodeItem = new EpisodeItem();
                episodeItem.setID(Integer.parseInt(cursor.getString(0)));
                episodeItem.setCode(Integer.parseInt(cursor.getString(1)));
                episodeItem.setEpisodeTitle(cursor.getString(2));
                episodeItem.setContents(cursor.getString(3));
//                Log.d("episdeitem", Integer.toString(episodeItem.getID()));
//                Log.d("episdeitem", Integer.toString(episodeItem.getCode()));
//                Log.d("episdeitem", episodeItem.getContents());
//                Log.d("episdeitem", episodeItem.getEpisodeTitle());
                product.add(episodeItem);
//                Log.d("episdeite", Integer.toString(product.get(0).getID()));
            }
        cursor.close();
        db.close();
        return product;
    }

    public EpisodeItem findEpisode(int id){
        String query = "SELECT * FROM " + TABLE_PEODUCT + " WHERE "+
                COL_ID + " = \"" + id + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        EpisodeItem product = new EpisodeItem();

        if(cursor.moveToFirst()) {
            product.setID(Integer.parseInt(cursor.getString(0)));
            product.setCode(Integer.parseInt(cursor.getString(1)));
            product.setEpisodeTitle(cursor.getString(2));
            product.setContents(cursor.getString(3));
        }else{
            product = null;
        }

        cursor.close();
        db.close();
        return product;
    }

    public int getID(){
        String query = "SELECT * FROM " + TABLE_PEODUCT ;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        int ID;

        if(cursor.moveToLast()) {
            ID = Integer.parseInt(cursor.getString(0));
        }else{
            ID = 0;
        }

        cursor.close();
        db.close();
        return ID;
    }

    public void deleteProduct(int code){
        String query1 = "SELECT * FROM " + "episode" + " WHERE "+
                COL_CODE + " = \"" + code + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query1, null);

        EpisodeItem nobelItem = new EpisodeItem();

        while(cursor.moveToNext()) {
            nobelItem.setCode(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PEODUCT, COL_ID + "= ?", new String[]{String.valueOf(nobelItem.getCode())});
        }

        cursor.close();
        db.close();
    }
}
