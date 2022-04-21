package br.com.viniciusmorici.appbeberagua.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHandler(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
        val CREATE_TABLE = "CREATE TABLE $TABLE_TOTAL" +
                "($TOTAL INTEGER " +
                ")"

        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_TOTAL;"
        p0?.execSQL(DROP_TABLE)
        onCreate(p0)
    }



    companion object{
        private val DB_VERSION = 1
        private val DB_NAME = "DB_TOTAL"
        private val TABLE_TOTAL = "TB_TOTAL"
        private val TOTAL = "TOTALlITROS"
    }


}
