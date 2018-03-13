package com.kokutha.kcube.copydatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static SQLiteDatabase sqliteDb;
    private static DataBaseHelper instance;
    private static final int DATABASE_VERSION = 4;

    private Context context;
    static Cursor cursor = null;

    private DataBaseHelper(Context context, String name, CursorFactory factory,
                           int version) {
        super(context, name, factory, version);
        this.context = context;

    }

    private static void initialize(Context context, String databaseName) {
        if (instance == null) {

            if (!checkDatabase(context, databaseName)) {

                try {
                    copyDataBase(context, databaseName);
                } catch (IOException e) {

                    System.out.println(databaseName
                            + " does not exists ");
                }
            }

            instance = new DataBaseHelper(context, databaseName, null,
                    DATABASE_VERSION);
            sqliteDb = instance.getWritableDatabase();

            System.out.println("instance of  " + databaseName + " created ");
        }
    }

    public static DataBaseHelper getInstance(Context context,
                                             String databaseName) {
        initialize(context, databaseName);
        return instance;
    }

    public SQLiteDatabase getDatabase() {
        return sqliteDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void copyDataBase(Context aContext, String databaseName)
            throws IOException {

        InputStream myInput = aContext.getAssets().open(databaseName);

        String outFileName = getDatabasePath(aContext, databaseName);

        File f = new File("/data/data/" + aContext.getPackageName()
                + "/databases/");
        if (!f.exists())
            f.mkdir();

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

        System.out.println(databaseName + " copied");
    }

    private static boolean checkDatabase(Context aContext, String databaseName) {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = getDatabasePath(aContext, databaseName);

            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

            checkDB.close();
        } catch (SQLiteException e) {

            System.out.println(databaseName + " does not exists");
        }

        return checkDB != null ? true : false;
    }

    private static String getDatabasePath(Context aContext, String databaseName) {
        return "/data/data/" + aContext.getPackageName() + "/databases/"
                + databaseName;
    }

    //get data by arraylist
    public ArrayList<UserData> getDataList() {
        ArrayList<UserData> userDataArrayList = new ArrayList<>();
        try {
            if (sqliteDb.isOpen()) {
                sqliteDb.close();
            }
            sqliteDb = instance.getWritableDatabase();
            // Select All Query
            String selectQuery = "SELECT * FROM contacts";
            Cursor cursor = sqliteDb.rawQuery(selectQuery, null);
            Log.e("d size", cursor.getCount() + "");
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Log.e("name", cursor.getString(1));
                String id = cursor.getInt(0) + "";
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String address = cursor.getString(3);
                String imagedata = cursor.getString(4);
                UserData userData = new UserData(id, name, phone, address, imagedata);
                userDataArrayList.add(userData);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            System.out.println("DB ERROR is is " + e.getMessage());
            e.printStackTrace();
        }

        return userDataArrayList;
    }

}
