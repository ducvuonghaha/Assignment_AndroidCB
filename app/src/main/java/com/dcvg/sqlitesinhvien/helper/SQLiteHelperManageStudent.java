package com.dcvg.sqlitesinhvien.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelperManageStudent extends SQLiteOpenHelper {

    public static final String DB_NAME = "QUANLYSINHVIEN";
    public static final int DB_VERSION = 1;

    public static final String CLASS_TABLE = "Lop";
    public static final String CLASS_ID = "IDLop";
    public static final String CLASS_NAME = "TenLop";
    public static final String CLASS_MAJOR = "ChuyenNganh";

    public static final String STUDENT_TABLE = "SinhVien";
    public static final String STUDENT_ID = "IDSinhVien";
    public static final String STUDENT_NAME = "TenSinhVien";
    public static final String STUDENT_MAJOR = "ChuyenNganhSinhVien";
    public static final String STUDENT_CLASS = "LopSinhVien";
    public static final String STUDENT_BIRTHDAY = "NgaySinhSinhVien";


    public SQLiteHelperManageStudent(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLASS_TABLE = "CREATE TABLE " + CLASS_TABLE + "(" +
                "" + CLASS_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "" + CLASS_NAME + " NVARCHAR(100) NOT NULL," +
                "" + CLASS_MAJOR + " NVARCHAR(100) NOT NULL" +
                ")";
        db.execSQL(CREATE_CLASS_TABLE);

        String CREATE_STUDENT_TABLE = "CREATE TABLE " + STUDENT_TABLE + "(" +
                "" + STUDENT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "" + STUDENT_NAME + " NVARCHAR(100) NOT NULL," +
                "" + STUDENT_MAJOR + " NVARCHAR(100) NOT NULL," +
                "" + STUDENT_CLASS + " NVARCHAR(50) NOT NULL," +
                "" + STUDENT_BIRTHDAY + " NVARCHAR(50) NOT NULL" +
                ")";

        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CLASS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
    }
}
