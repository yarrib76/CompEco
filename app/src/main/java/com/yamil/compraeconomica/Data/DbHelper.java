package com.yamil.compraeconomica.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yamil on 4/12/16.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "compraEconomica.sqlite";
    public static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_USUARIOS = "CREATE TABLE IF NOT EXISTS " + Contract.Usuarios.TABLA_USUARIOS +
            "(" + Contract.Usuarios.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , "
            + Contract.Usuarios.COL_NOMBRE + " TEXT, "
            + Contract.Usuarios.COL_APELLIDO + " TEXT, "
            + Contract.Usuarios.COL_CLAVE + " TEXT, "
            + Contract.Usuarios.COL_EMAIL + " TEXT)";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Usuarios.TABLA_USUARIOS);
        onCreate(db);
    }
}
