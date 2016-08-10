package rishabh.agarwal.holmes.timetable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Holmes on 12/28/2015.
 */
public class controller {
    DBHelper helper;
    SQLiteDatabase db;
    Context cont;
    String year[]={"1","2","3","4"};
    String subject[]={"CSE","EC","ME","CIVIL","CHE"};
    String batch[]={"b1","b2","b3","b4","b5","b6","a1","a2"};
    String day[]={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    String time[]={"9-10","10-11","11-12","12-1","1-2","2-3","3-4","4-5","5-6"};
    String abc[]={"abc","qwe","zxc"};
    String a1="abc",b;
    int i,j,k,l,r,q,w;
    String table[]={DBHelper.TABLE_NAME1,DBHelper.TABLE_NAME2,DBHelper.TABLE_NAME3,DBHelper.TABLE_NAME4,DBHelper.TABLE_NAME5,
            DBHelper.TABLE_NAME6,DBHelper.TABLE_NAME7,DBHelper.TABLE_NAME8,DBHelper.TABLE_NAME9,DBHelper.TABLE_NAME10,
            DBHelper.TABLE_NAME11,DBHelper.TABLE_NAME12,DBHelper.TABLE_NAME13,DBHelper.TABLE_NAME14,DBHelper.TABLE_NAME15,
            DBHelper.TABLE_NAME16,DBHelper.TABLE_NAME17,DBHelper.TABLE_NAME18,DBHelper.TABLE_NAME19,DBHelper.TABLE_NAME20};




    public controller(Context cont)
    {
        this.cont=cont;

    }
   /* public void add() {
        helper=new DBHelper(cont,DBHelper.DATABASE_NAME,null,DBHelper.VERSION);
        db= helper.getWritableDatabase();

        for (q = 0; q < 4; q++) {
            for (w = 0; w < 5; w++) {
                db.execSQL("insert into '" + DBHelper.TABLE_NAME0 + "' values('" + year[q] + "','" + subject[w] + "');");
            }
        }


    }
    public void add1() {
          helper=new DBHelper(cont,DBHelper.DATABASE_NAME,null,DBHelper.VERSION);
          db= helper.getWritableDatabase();
        for (r = 0; r < 20; r++) {
            for (i = 0; i < 6; i++) {
                for (j = 0; j < 6; j++) {
                    for (k = 0, l = 0; k < 9; k++, l++) {

                        db.execSQL("insert into '" + table[r] + "' values('" + batch[i] + "','" + day[j] + "','" + time[k] + "','" + abc[l] + "');");
                        if (l == 2)
                            l = 0;


                    }
                }
            }
        }
    }*/



    public List<String> year()
    {
        List<String> ylist = new ArrayList<String>();
        helper = new DBHelper(cont, DBHelper.DATABASE_NAME, null, DBHelper.VERSION);
        db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("select distinct year from " + DBHelper.TABLE_NAME0, null);
        int a= c.getCount();
        while (c.moveToNext()) {
            String nm=c.getString(0);
            ylist.add(nm);
        }

        return ylist;
    }
    public List<String> allbranch()
    {
        List<String> brlist = new ArrayList<String>();
        helper = new DBHelper(cont, DBHelper.DATABASE_NAME, null, DBHelper.VERSION);
        db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("select distinct branch from " + DBHelper.TABLE_NAME0, null);
        int a= c.getCount();
        while (c.moveToNext()) {
            String nm=c.getString(0);
            brlist.add(nm);
        }

        return brlist;
    }

    public List<String> allbatch(String s6) {

        List<String> plist = new ArrayList<String>();
        helper = new DBHelper(cont, DBHelper.DATABASE_NAME, null, DBHelper.VERSION);
        db = helper.getWritableDatabase();
        Log.e("rishabh", "step111");


        Cursor c = db.rawQuery("select distinct batch from " + s6, null);

        int a= c.getCount();
        while (c.moveToNext()) {
            String nm=c.getString(0);
            plist.add(nm);
        }
        return plist;
    }
    public List<String> allday() {
        List<String> dlist = new ArrayList<String>();
        helper = new DBHelper(cont, DBHelper.DATABASE_NAME, null, DBHelper.VERSION);
        db = helper.getWritableDatabase();

        Cursor c = db.rawQuery("select distinct day from " + DBHelper.TABLE_NAME1, null);
        int a= c.getCount();
        while (c.moveToNext()) {
            String ab=c.getString(0);
            dlist.add(ab);

        }
        return dlist;

    }
    public List<String> alltime() {
        List<String> tlist = new ArrayList<String>();
        helper = new DBHelper(cont, DBHelper.DATABASE_NAME, null, DBHelper.VERSION);
        db = helper.getWritableDatabase();

        Cursor c = db.rawQuery("select distinct time from " + DBHelper.TABLE_NAME1, null);

        while (c.moveToNext()) {
            String ab=c.getString(0);
            tlist.add(ab);

        }
        return tlist;

    }
    public String  ans(String s1,String s2,String s3,String s4) {
        List<String> dlist = new ArrayList<String>();
        helper = new DBHelper(cont, DBHelper.DATABASE_NAME, null, DBHelper.VERSION);
        db = helper.getWritableDatabase();
        Log.e("rishabh","step1");
        Log.e("rishabh",s1);
        Log.e("rishabh",s2);
        Log.e("rishabh",s3);

        Cursor c = db.rawQuery("select ans from " + s4 + " where batch='" + s1 + "'and day='" + s2 + "'and time='" + s3 + "'", null);
        Log.e("rishabh","step2");
        int a= c.getCount();
        while(c.moveToNext()) {
            Log.e("rishabh", "" + a);

            b = c.getString(0);
            Log.e("rishabh", b);

        }
        return b;
    }
    public boolean updateProduct(String s1,String s2,String s3,String s4,String s6)
    {
        helper=new DBHelper(cont, DBHelper.DATABASE_NAME, null, DBHelper.VERSION);
        db=helper.getWritableDatabase();
        Log.e("rishabh", "step11111");
        db.execSQL("update " +s6+ " set ans='" + s4 + "' where batch='"+s1+"' and day='" + s2 + "' and time='" + s3 + "' ");
        Log.e("rishabh", "step20002");


        return true;
    }
}