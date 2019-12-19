package com.example.notitiabell.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class CustomAdapter{

    private var dbHelper: DbHelper

    constructor(context: Context){

        dbHelper = DbHelper(context)
    }

    fun insertData(title : String, des : String):Long{

        var db : SQLiteDatabase = dbHelper.writableDatabase
        var cv = ContentValues()
        cv.put(DbHelper.TITLE, title)
        cv.put(DbHelper.DESCRIPTION, des)

        return db.insert(DbHelper.TABLE_NAME,null,cv)

    }


    fun getAllData() : String{

        var db: SQLiteDatabase = dbHelper.writableDatabase
        var columns : Array<String> = arrayOf(
            DbHelper.UID,
            DbHelper.TITLE,
            DbHelper.DESCRIPTION
        )
        var cursor: Cursor = db.query(DbHelper.TABLE_NAME,columns,null,null,null,null,null)
        var sb = StringBuffer()

        while(cursor.moveToNext()){
            var cid : Int = cursor.getInt(0)
            var title: String = cursor.getString(1)
            var description: String = cursor.getString(2)
            sb.append("$description \n")
        }
        return sb.toString()

    }

    //region function to upgrade app
    fun getData(name :String):String{

        var db: SQLiteDatabase = dbHelper.writableDatabase
        var columns : Array<String> = arrayOf(
            DbHelper.UID,
            DbHelper.TITLE,
            DbHelper.DESCRIPTION
        )
        var cursor: Cursor = db.query(DbHelper.TABLE_NAME,columns,null,null,null,null,null)
        var sb = StringBuffer()

        while(cursor.moveToNext()){

            var index1 : Int = cursor.getColumnIndex(DbHelper.TITLE)
            var index2 : Int = cursor.getColumnIndex(DbHelper.DESCRIPTION)
            var title: String = cursor.getString(index1)
            var des: String = cursor.getString(index2)
            sb.append("$title, $des \n")
        }

        return sb.toString()

    }

    fun updateName(oldDes: String, newDes : String) : Int{
        var db : SQLiteDatabase = dbHelper.writableDatabase
        var cv = ContentValues()
        cv.put(DbHelper.DESCRIPTION,newDes)
        var whereargs: Array<String> = arrayOf(oldDes)
        var count: Int = db.update(
            DbHelper.TABLE_NAME,cv,
            DbHelper.DESCRIPTION +"+?",whereargs)
        return count
    }
    //endregion

    fun deleteAllRow(){
        var db: SQLiteDatabase = dbHelper.writableDatabase
        db.execSQL("delete from "+ DbHelper.TABLE_NAME);
        db.close()
    }


}