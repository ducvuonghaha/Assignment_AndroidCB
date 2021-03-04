package com.dcvg.sqlitesinhvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent;
import com.dcvg.sqlitesinhvien.model.Class;

import java.util.ArrayList;
import java.util.List;

import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.CLASS_ID;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.CLASS_MAJOR;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.CLASS_NAME;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.CLASS_TABLE;

public class ClassDAO {

    private SQLiteHelperManageStudent sqLiteHelperManageStudent;

    public ClassDAO(Context context) {
        this.sqLiteHelperManageStudent = new SQLiteHelperManageStudent(context);
    }

    public long insertClass(Class classModel) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLASS_NAME, classModel.getName());
        contentValues.put(CLASS_MAJOR, classModel.getMajor());
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        result = sqLiteDatabase.insert(CLASS_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updateClass(Class classModel, int idClass) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLASS_NAME, classModel.getName());
        contentValues.put(CLASS_MAJOR, classModel.getMajor());
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
         result = sqLiteDatabase.update(CLASS_TABLE, contentValues, CLASS_ID + "=?",
                new String[]{String.valueOf(idClass)});
        sqLiteDatabase.close();
        return result;
    }

    //Xóa lớp
    public long deleteClass(int class_id) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
         result = sqLiteDatabase.delete(CLASS_TABLE, CLASS_ID + "=?", new String[]{String.valueOf(class_id)});
        return result;
    }

    public List<Class> getAllClasses() {
        List<Class> classList = new ArrayList<>();
        classList.clear();
        String query = "SELECT * FROM " + CLASS_TABLE;
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Class classModel = new Class(
                            cursor.getString(cursor.getColumnIndex(CLASS_NAME)),
                            cursor.getString(cursor.getColumnIndex(CLASS_MAJOR)),
                            cursor.getInt(cursor.getColumnIndex(CLASS_ID))
                    );
                    classList.add(classModel);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return classList;
    }
}
