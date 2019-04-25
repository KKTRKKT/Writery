package com.example.writery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WriteDBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "WriteDB.db";
    private static final String TABLE_PEODUCT = "products";

    private static final String COL_ID = "_id";
    private static final String COL_COVER  = "cover";
    private static final String COL_TITLE = "title";
    private static final String COL_INFO = "info";

    public WriteDBHandler(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PEODUCT + "("
                + COL_ID + " INTEGER PRIMARY KEY," + COL_COVER
                + " INTEGER," + COL_TITLE + " TEXT," + COL_INFO + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEODUCT);
        onCreate(db);
    }

    public void addWrite(NobelItem product){
        ContentValues values = new ContentValues();
        values.put(COL_COVER, product.getImage());
        values.put(COL_TITLE, product.getTitle());
        values.put(COL_INFO, product.getInfo());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PEODUCT, null, values);
        db.close();
    }

    public NobelItem findProduct(String title){
        String query = "SELECT * FROM " + TABLE_PEODUCT + " WHERE "+
                COL_TITLE + " = \"" + title + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        NobelItem product = new NobelItem();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            product.setID(Integer.parseInt(cursor.getString(0)));
            product.setImage(Integer.parseInt(cursor.getString(1)));
            product.setTitle(cursor.getString(2));
            product.setInfo(cursor.getString(3));
            cursor.close();
        } else{
            product = null;
        }
        db.close();
        return  product;
    }
//여기부터
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
