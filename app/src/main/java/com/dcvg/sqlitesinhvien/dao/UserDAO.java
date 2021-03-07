package com.dcvg.sqlitesinhvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent;
import com.dcvg.sqlitesinhvien.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.USER_PASSWORD;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.USER_TABLE;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.USER_USERNAME;

public class UserDAO {

    private SQLiteHelperManageStudent sqLiteHelperManageStudent;

    public UserDAO(Context context) {
        this.sqLiteHelperManageStudent = new SQLiteHelperManageStudent(context);
    }

    public long insertUser(User user) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_USERNAME, user.getUsername());
        contentValues.put(USER_PASSWORD, user.getPassword());
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        result = sqLiteDatabase.insert(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.clear();
        String query = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    User user = new User(
                            cursor.getString(cursor.getColumnIndex(USER_USERNAME)),
                            cursor.getString(cursor.getColumnIndex(USER_PASSWORD))
                    );
                    userList.add(user);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return userList;
    }
}
