package com.example.stock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    // Database Version
    private static final int DATABASE_VERSION = 4;
    // Database Name
    private static final String DATABASE_NAME = "app";
    // Table name: Stock.
    private static final String TABLE_GOODS = "Goods";

    private static final String COLUMN_ID ="id";
    private static final String COLUMN_NAME ="name";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_SELLINGPRICE= "sellingPrice";
    private static final String COLUMN_PURCHASEPRICE = "purchasePrice";


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Script.
        String script = "CREATE TABLE " + TABLE_GOODS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_QUANTITY + " INTEGER,"
                + COLUMN_SELLINGPRICE + " INTEGER,"
                + COLUMN_PURCHASEPRICE + " INTEGER" + ")";

        // Execute Script.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOODS);

        // Create tables again
        onCreate(db);
    }

    public void addGood(String name, String quantity, String sellingPrice, String purchasePrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_QUANTITY, Integer.parseInt(quantity));
        values.put(COLUMN_SELLINGPRICE, Integer.parseInt(sellingPrice));
        values.put(COLUMN_PURCHASEPRICE, Integer.parseInt(purchasePrice));

        // Inserting Row
        db.insert(TABLE_GOODS, null, values);

        // Closing database connection
        db.close();
    }

    public List<Good> getAllGoods() {
        List<Good> goodList = new ArrayList<Good>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GOODS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Good good = new Good(cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
                // Adding note to list
                goodList.add(good);
            } while (cursor.moveToNext());
        }

        // return good list
        return goodList;
    }

    public int updateGood(String oldGoodName, String name, String quantity, String purchasePrice, String sellingPrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_QUANTITY, Integer.parseInt(quantity));
        values.put(COLUMN_SELLINGPRICE, Integer.parseInt(sellingPrice));
        values.put(COLUMN_PURCHASEPRICE, Integer.parseInt(purchasePrice));

        // Update Row
        return db.update(TABLE_GOODS, values, COLUMN_NAME + " =?", new String[]{oldGoodName});
    }

    public Good getGoodByName(String goodName) {
        Good good;

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GOODS + " WHERE name='" + goodName + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        cursor.moveToFirst();
        good = new Good(cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));

        // return note list
        return good;
    }

    public int saleGood(String goodName, String quantity)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String quantity_old = getGoodByName(goodName).getQuantity();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, goodName);
        values.put(COLUMN_QUANTITY, Integer.parseInt(quantity_old) - Integer.parseInt(quantity));

        // Update Row
        return db.update(TABLE_GOODS, values, COLUMN_NAME + " =?", new String[]{goodName});

    }

    public int purchaseGood(String goodName, String quantity)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String quantity_old = getGoodByName(goodName).getQuantity();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, goodName);
        values.put(COLUMN_QUANTITY, Integer.parseInt(quantity_old) + Integer.parseInt(quantity));

        // Update Row
        return db.update(TABLE_GOODS, values, COLUMN_NAME + " =?", new String[]{goodName});

    }

}
