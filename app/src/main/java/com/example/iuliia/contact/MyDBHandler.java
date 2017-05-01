package com.example.iuliia.contact;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.ContentResolver;

import com.example.iuliia.contact.provider.MyContentProvider;

public class MyDBHandler extends SQLiteOpenHelper {

    private ContentResolver myCR;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactDB.db";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PNUMBER = "phone";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        myCR = context.getContentResolver();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, " + COLUMN_PNUMBER + " INTEGER" + " )";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addContact(Contact contact) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getContactName());
        values.put(COLUMN_PNUMBER, contact.getPhoneNumber());

        myCR.insert(MyContentProvider.CONTENT_URI, values);

    }

    public Contact findContact(String contactName) {
        String[] projection = {COLUMN_ID, COLUMN_NAME, COLUMN_PNUMBER};

        String selection = "name = \"" + contactName + "\"";

        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI, projection, selection, null, null);

        Contact contact = new Contact();

        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            contact.setID(Integer.parseInt(cursor.getString(0)));
            contact.setContactName(cursor.getString(1));
            contact.setPnoneNumber(Long.parseLong(cursor.getString(2)));
            cursor.close();
        } else {
            contact = null;
        }
        return contact;

    }

    public boolean deleteContact(String contactName) {

        boolean result = false;
        int rowsDeleted = 0;
        String selection = "name = \"" + contactName + "\"";
        rowsDeleted = myCR.delete(MyContentProvider.CONTENT_URI, selection, null);
        if (rowsDeleted > 0)
            result = true;

        return result;

    }

}

