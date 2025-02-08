package com.example.encartados

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_USERS = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_NAME = "name"
        const val COLUMN_PASSWORD = "password"

        const val TABLE_STOCK = "stock"
        const val COLUMN_STOCK_ID = "stock_id"
        const val COLUMN_ITEM_NAME = "item_name"
        const val COLUMN_ITEM_QUANTITY = "item_quantity"
        const val COLUMN_ITEM_PRICE = "item_price"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUserTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_EMAIL TEXT,"
                + "$COLUMN_NAME TEXT,"
                + "$COLUMN_PASSWORD TEXT)")
        db.execSQL(createUserTable)

        val createStockTable = ("CREATE TABLE $TABLE_STOCK ("
                + "$COLUMN_STOCK_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_ITEM_NAME TEXT,"
                + "$COLUMN_ITEM_QUANTITY INTEGER,"
                + "$COLUMN_ITEM_PRICE REAL)")
        db.execSQL(createStockTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STOCK")
        onCreate(db)
    }
}