package com.example.writery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class NobelDBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "WriteDB.db";
    private static final String TABLE_PEODUCT = "write";

    private static final String COL_ID = "_id";
    private static final String COL_COVER  = "cover";
    private static final String COL_TITLE = "title";
    private static final String COL_INFO = "info";

    public NobelDBHandler(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PEODUCT + "("
                + COL_ID + " INTEGER PRIMARY KEY," + COL_COVER
                + " BLOB," + COL_TITLE + " TEXT," + COL_INFO + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEODUCT);
        onCreate(db);
    }

    public void addWrite(NobelItem product){
        ContentValues values = new ContentValues();
//        values.put(COL_COVER, product.getImage());
        values.put(COL_TITLE, product.getTitles());
        values.put(COL_INFO, product.getInfo());
        values.put(COL_COVER, product.getImage());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PEODUCT, null, values);

        db.close();
    }

    public void update(int code, String title, String info, byte[] cover){
        SQLiteDatabase db = getWritableDatabase();
//
//        String sql = "UPDATE "+ TABLE_PEODUCT + " SET " + COL_COVER + "='?'" +
//                "," + COL_TITLE + "='"+ title + "',"+ COL_INFO + "='"+ info +
//                "' WHERE " + COL_ID + "=" + code;

//        String sql = "UPDATE "+ TABLE_PEODUCT + " SET " + COL_COVER + "=(?)" +
//                ", " + COL_TITLE + "=(?), "+ COL_INFO + "=(?)" +
//                " WHERE " + COL_ID + "=(?);";

        SQLiteStatement p = db.compileStatement("update write set cover =(?), title =(?), info=(?) where _id = (?);");

        p.bindBlob(1, cover);
        p.bindString(2, title);
        p.bindString(3, info);
        p.bindLong(4, code);
        p.execute();

        db.close();
    }
    public void update(int code, byte img){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "UPDATE "+ TABLE_PEODUCT + " SET "+ COL_COVER + "='"+ img + "' WHERE " + COL_ID + "=" + code;

        db.execSQL(sql);

        db.close();
    }

    public ArrayList<NobelItem> showList(){
        String query = "SELECT * FROM " + TABLE_PEODUCT ;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<NobelItem> product = new ArrayList<>();

//        Bitmap bitmap = ((BitmapDrawable)).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] data = stream.toByteArray();

        while(cursor.moveToNext()) {
            NobelItem nobelItem = new NobelItem();
            nobelItem.setID(Integer.parseInt(cursor.getString(0)));
            nobelItem.setImage(cursor.getBlob(1));
            nobelItem.setTitle(cursor.getString(2));
            nobelItem.setInfo(cursor.getString(3));
            product.add(nobelItem);
        }

        cursor.close();
        db.close();
        return  product;
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

    public NobelItem findNobel(int id){
        String query = "SELECT * FROM " + TABLE_PEODUCT + " WHERE "+
                COL_ID + " = \"" + id + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        NobelItem product = new NobelItem();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            product.setID(Integer.parseInt(cursor.getString(0)));
            product.setImage(cursor.getBlob(1));
            product.setTitle(cursor.getString(2));
            product.setInfo(cursor.getString(3));

        } else{
            product = null;
        }
        cursor.close();
        db.close();
        return  product;
    }


    public boolean deleteProduct(int code){
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_PEODUCT + " WHERE "+
                COL_ID + " = \"" + code + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        NobelItem nobelItem = new NobelItem();

        if(cursor.moveToFirst()){
            nobelItem.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PEODUCT, COL_ID + "= ?", new String[]{String.valueOf(nobelItem.getID())});
            cursor.close();
            result = true;
        }

        db.close();
        return  result;
    }
}
