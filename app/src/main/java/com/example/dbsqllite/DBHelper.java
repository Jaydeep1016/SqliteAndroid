package com.example.dbsqllite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

        public static final String Dbname="dbStudent";

    public DBHelper(@Nullable Context context) {
        super(context,Dbname,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qr="create table tblStudent(Sno integer primary key,Sname text,Sage integer)";
        db.execSQL(qr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists tblStudent");
    }
    public void ShowAll(TextView SData){
        SData.setText("");
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cr=db.rawQuery("select * from tblStudent",null);
        Log.d("DBCHECK", "CURSOR DATA COUNT: "+cr.getCount());
        while (cr.moveToNext()){
            SData.append("SNO : "+cr.getString(0)+" NAME : "+cr.getString(1) +" S Age : "+cr.getString(2)+"\n");
        }

    }

    public Boolean insertDone(String Sno,String Sname,String Sage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("Sno", Sno);
        v.put("Sname", Sname);
        v.put("Sage", Sage);
        long F = db.insert("tblStudent", null, v);
        if (F == -1) {
            return  false;
        } else {
            return true;
        }
    }

    public Boolean Login(String Sno,String Sname,String Sage){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cr=db.rawQuery("select * from tblStudent where Sno="+Sno+" and Sname='"+Sname+"' and Sage="+Sage,null);
        cr.moveToFirst();
        if(cr.getCount()==1){
            return true;
        }
        else{
            return  false;
        }

    }
    public Boolean Update(String Sno,String Sname,String Sage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
//        v.put("Sno", Sno);
        v.put("Sname", Sname);
        v.put("Sage", Sage);
       long F = db.update("tblStudent", v,"Sno=?",new String[]{Sno});
        if (F == -1) {
            return  false;
        } else {
            return true;
        }
    }
    public Boolean Delete(String Sno){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        long F=db.delete("tblStudent","Sno=?",new String[]{Sno});
        if (F == -1) {
            return  false;
        } else {
            return true;
        }
    }
}
