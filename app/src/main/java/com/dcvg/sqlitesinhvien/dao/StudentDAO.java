package com.dcvg.sqlitesinhvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent;
import com.dcvg.sqlitesinhvien.model.Student;

import java.util.ArrayList;
import java.util.List;

import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.CLASS_ID;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.STUDENT_BIRTHDAY;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.STUDENT_CLASS;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.STUDENT_ID;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.STUDENT_MAJOR;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.STUDENT_NAME;
import static com.dcvg.sqlitesinhvien.helper.SQLiteHelperManageStudent.STUDENT_TABLE;

public class StudentDAO {

    private SQLiteHelperManageStudent sqLiteHelperManageStudent;

    public StudentDAO(Context context) {
        this.sqLiteHelperManageStudent = new SQLiteHelperManageStudent(context);
    }

    public long insertStudent(Student studentModel) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, studentModel.getName());
        contentValues.put(STUDENT_BIRTHDAY, studentModel.getBirth());
        contentValues.put(STUDENT_CLASS, studentModel.getClassStudent());
        contentValues.put(STUDENT_MAJOR, studentModel.getMajor());
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        result = sqLiteDatabase.insert(STUDENT_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updateStudent(Student studentModel, int idStudent) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, studentModel.getName());
        contentValues.put(STUDENT_BIRTHDAY, studentModel.getBirth());
        contentValues.put(STUDENT_CLASS, studentModel.getClassStudent());
        contentValues.put(STUDENT_MAJOR, studentModel.getMajor());
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        result = sqLiteDatabase.update(STUDENT_TABLE, contentValues, STUDENT_ID + "=?",
                new String[]{String.valueOf(idStudent)});
        sqLiteDatabase.close();
        return result;
    }

    //Xóa lớp
    public long deleteStudent(int student_id) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        result = sqLiteDatabase.delete(STUDENT_TABLE, STUDENT_ID + "=?", new String[]{String.valueOf(student_id)});
        return result;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList.clear();
        String query = "SELECT * FROM " + STUDENT_TABLE;
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Student studentModel = new Student(
                            cursor.getString(cursor.getColumnIndex(STUDENT_NAME)),
                            cursor.getString(cursor.getColumnIndex(STUDENT_CLASS)),
                            cursor.getString(cursor.getColumnIndex(STUDENT_MAJOR)),
                            cursor.getString(cursor.getColumnIndex(STUDENT_BIRTHDAY)),
                            cursor.getInt(cursor.getColumnIndex(STUDENT_ID))
                    );
                    studentList.add(studentModel);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return studentList;
    }

}
