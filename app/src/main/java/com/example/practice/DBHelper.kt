package com.example.practice
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DBHelper(    context: Context, name:  String = database, factory:SQLiteDatabase.CursorFactory? = null ,version: Int = v) : SQLiteOpenHelper(context,name, factory, version) {
    companion object {
        private const val database = "myDatabase"
        private  const val v = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE myTable(StockNumber TEXT PRIMARY KEY, StockName TEXT, TradeVolume INTEGER, StockTotalPrice INTEGER,OpeningPrice DECIMAL, HighestPrice DECIMAL,LowestPrice DECIMAL,ClosingPrice DECIMAL,ChangePrice TEXT, TradeCount INTEGER)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}