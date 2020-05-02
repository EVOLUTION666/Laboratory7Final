package com.example.laba6;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "magazine";
    private static final String TABLE_STORE = "store";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COUNT = "count";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_STORE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_PRICE + " TEXT,"
                + KEY_COUNT + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORE);

        onCreate(db);
    }

    public void addProduct(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, model.getName());
        values.put(KEY_DESCRIPTION, model.getDescription());
        values.put(KEY_PRICE, model.getPrice());
        values.put(KEY_COUNT, model.getCount());

        db.insert(TABLE_STORE, null, values);
        db.close();
    }

   public Model getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STORE, new String[]{KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_PRICE, KEY_COUNT}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Model model = new Model(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));

        return  model;
    }

    public List<Model> getAllProducts() {
        List<Model> productsList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_STORE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()) {
            do {
                Model contact = new Model();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setDescription(cursor.getString(2));
                contact.setPrice(Integer.parseInt(cursor.getString(3)));
                contact.setCount(Integer.parseInt(cursor.getString(4)));

                productsList.add(contact);

            } while (cursor.moveToNext());
        }
        return productsList;
    }

    public int updateProduct(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, model.getName());
        values.put(KEY_DESCRIPTION, model.getDescription());
        values.put(KEY_COUNT, model.getCount());
        values.put(KEY_PRICE, model.getPrice());

        return db.update(TABLE_STORE, values, KEY_ID + "=?",
                new String[]{String.valueOf(model.getId())});
    }

    public void deleteItem(int position){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STORE + " WHERE " +
                KEY_ID + " = " + position + ";");
        db.execSQL("UPDATE " + TABLE_STORE + " SET " + KEY_ID + " = " +
                KEY_ID + " -1 " + " WHERE " + KEY_ID + " > " + position + ";");
        db.close();
    }

    public void deleteProduct(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STORE, KEY_ID + "=?",
                new String[]{String.valueOf(model.getId())});
        db.close();
    }

    public int getProductsCount() {
        String countQuery = "SELECT * FROM " + TABLE_STORE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int contactsCount = cursor.getCount();
        cursor.close();

        return contactsCount;
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_STORE);
        db.close();
    }

}

