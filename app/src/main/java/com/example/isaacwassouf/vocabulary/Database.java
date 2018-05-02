package com.example.isaacwassouf.vocabulary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by isaacwassouf on 2/24/18.
 */

public class Database {


    Db db;
    Context context;

    public Database(Context context){
        this.context=context;
        db=  new Db(context);
    }

    private boolean checkExist(String engWord){
        boolean result= false;
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String[] columns= {db.IdCol,db.EngCol,db.AraCol};
        Cursor cursor = sqLiteDatabase.query(db.Tbname,columns,null,null,null,null,null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                if (cursor.getString(cursor.getColumnIndexOrThrow(db.EngCol)).equals(engWord)) {
                    result = true;
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return result;

    }

    public void add(String eng,String ara){
        try{
            if (!checkExist(eng)) {
                SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(db.EngCol, eng);
                contentValues.put(db.AraCol, ara);
                sqLiteDatabase.insert(db.Tbname, null, contentValues);
            }
            else {
                Toast.makeText(context, "Word Already Exist", Toast.LENGTH_LONG).show();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }



    public Cursor show(){
        try{

            SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
            String[] columns= {db.IdCol,db.EngCol,db.AraCol};
            Cursor cursor = sqLiteDatabase.query(db.Tbname,columns,null,null,null,null,null);
            return cursor;


        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Cursor getEnglish(){
        try{

            SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
            String[] columns= {db.IdCol,db.EngCol};
            Cursor cursor = sqLiteDatabase.query(db.Tbname,columns,null,null,null,null,null,null);
            return cursor;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    public void delete(ArrayList<String> arrayList){

        try{
            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
           // String[] words = new String[arrayList.size()];
            //words = arrayList.toArray(words);
            String[] words= new String[1];
            for (String st:arrayList) {
                 words[0] = st;
                 sqLiteDatabase.delete(db.Tbname, "English=? ", words);
            }
        }
        catch (SQLException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


     static private class Db extends SQLiteOpenHelper{

         private final static String Dbname="Vocabulary";
         private final static String Tbname="Vocabulary";
         private final static String IdCol ="_id";
         private final static String EngCol ="English";
         private final static String AraCol ="Arabic";
         private final static int version =1;
         private Context context;


        private final static String CreateSt="CREATE TABLE IF NOT EXISTS " + Tbname + "("
                +IdCol+" INTEGER  PRIMARY KEY, " + EngCol + " TEXT UNIQUE ," + AraCol + " TEXT" +");";



         public Db(Context context) {
             super(context, Dbname, null, version);
             this.context=context;
         }

         @Override
         public void onCreate(SQLiteDatabase sqLiteDatabase) {

             try{
                 sqLiteDatabase.execSQL(CreateSt);
             }
             catch(SQLException e){
                 System.out.println(e.getMessage());
             }


         }

         @Override
         public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
         }
     }


}
