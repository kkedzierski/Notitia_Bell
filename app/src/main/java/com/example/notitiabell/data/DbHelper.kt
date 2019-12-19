package com.example.notitiabell.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.notitiabell.ui.another.Message
import java.sql.SQLException

class DbHelper:SQLiteOpenHelper {

    //Use companion to see variables on the another class
    companion object{

        internal const val DATABASE_NAME : String = "SpeachDB"
        internal const val TABLE_NAME : String = "SpeachText"
        internal const val DATABASE_VERSION : Int = 1
        internal const val UID : String = "_id"
        internal const val TITLE : String = "Title"
        internal const val DESCRIPTION : String = "Des"
        internal const val CREATE_TABLE : String = "Create Table $TABLE_NAME ($UID integer primary key autoincrement, $TITLE varchar(255), $DESCRIPTION varchar(255));"
        internal const val DROP_TABLE : String = "Drop table if exists $TABLE_NAME"
    }

    internal var context: Context

    constructor(context: Context):super(context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ){

        this.context = context
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db!!.execSQL(CREATE_TABLE)
        }catch (e: SQLException){
            Message.message(context, "$e")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try {
            db!!.execSQL(DROP_TABLE)
            onCreate(db)
        }catch (e: SQLException){
            Message.message(context, "$e")
        }
    }

}