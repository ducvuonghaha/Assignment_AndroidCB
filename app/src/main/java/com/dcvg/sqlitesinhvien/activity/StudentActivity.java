package com.dcvg.sqlitesinhvien.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dcvg.sqlitesinhvien.R;
import com.dcvg.sqlitesinhvien.adapter.StudentAdapter;
import com.dcvg.sqlitesinhvien.dao.ClassDAO;
import com.dcvg.sqlitesinhvien.dao.StudentDAO;
import com.dcvg.sqlitesinhvien.model.Class;
import com.dcvg.sqlitesinhvien.model.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class StudentActivity extends AppCompatActivity {

    public static Spinner spnClass;
    public static EditText edtNameStudent;
    public static EditText edtBirthStudent;
    final Calendar myCalendar = Calendar.getInstance();
    List<Class> classList = new ArrayList<>();
    List<String> nameClasses = new ArrayList<>();
    private Button btnAddStudent;
    private Button btnUpdateStudent;
    private Button btnDeleteStudent;
    private RecyclerView rcvListStudent;
    private StudentAdapter studentAdapter;
    private LinearLayoutManager linearLayoutManager;
    public static int idStudent = 0;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initView();
        StudentDAO studentDAO = new StudentDAO(this);
        linearLayoutManager = new LinearLayoutManager(this);
        if (studentDAO.getAllStudents().size() != 0) {
            studentAdapter = new StudentAdapter(StudentActivity.this, studentDAO.getAllStudents());
            rcvListStudent.setAdapter(studentAdapter);
            rcvListStudent.setLayoutManager(linearLayoutManager);
            studentAdapter.notifyDataSetChanged();
        }

        classList = new ClassDAO(this).getAllClasses();
        nameClasses.clear();
        nameClasses.add("Chọn lớp");
        for (int i = 0; i < classList.size(); i++) {
            String name = classList.get(i).getName();
            nameClasses.add(name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                nameClasses
        );

        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);

        spnClass.setAdapter(adapter);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        edtBirthStudent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(StudentActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtBirthStudent.getText().toString().trim().isEmpty() || edtNameStudent.getText().toString().trim().isEmpty() || spnClass.getSelectedItem().toString().equals("Chọn lớp")) {
                    Toast.makeText(StudentActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    String majorClass = "";
                    for (int i = 0; i < classList.size(); i++) {
                        if (spnClass.getSelectedItem().toString().trim() == classList.get(i).getName()) {
                            majorClass = classList.get(i).getMajor().trim();
                        }
                    }
                    if (studentDAO.insertStudent(new Student(edtNameStudent.getText().toString().trim(), spnClass.getSelectedItem().toString().trim(), majorClass, edtBirthStudent.getText().toString().trim())) > 0) {
                        Toast.makeText(StudentActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        studentAdapter = new StudentAdapter(StudentActivity.this, studentDAO.getAllStudents());
                        rcvListStudent.setAdapter(studentAdapter);
                        rcvListStudent.setLayoutManager(linearLayoutManager);
                        studentAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(StudentActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNameStudent.getText().toString().trim().isEmpty() || edtBirthStudent.getText().toString().trim().isEmpty() || spnClass.getSelectedItem().toString().trim().isEmpty()) {
                    Toast.makeText(StudentActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (studentDAO.deleteStudent(idStudent) > 0) {
                        Toast.makeText(StudentActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        studentAdapter = new StudentAdapter(StudentActivity.this, studentDAO.getAllStudents());
                        rcvListStudent.setAdapter(studentAdapter);
                        rcvListStudent.setLayoutManager(linearLayoutManager);
                        studentAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(StudentActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNameStudent.getText().toString().trim().isEmpty() || edtBirthStudent.getText().toString().trim().isEmpty() || spnClass.getSelectedItem().toString().trim().isEmpty()) {
                    Toast.makeText(StudentActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    String majorClass = "";
                    for (int i = 0; i < classList.size(); i++) {
                        if (spnClass.getSelectedItem().toString().trim() == classList.get(i).getName()) {
                            majorClass = classList.get(i).getMajor().trim();
                        }
                    }
                    if (studentDAO.updateStudent(new Student(edtNameStudent.getText().toString().trim(), spnClass.getSelectedItem().toString().trim(), majorClass, edtBirthStudent.getText().toString().trim()), idStudent) > 0) {
                        Toast.makeText(StudentActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        studentAdapter = new StudentAdapter(StudentActivity.this, studentDAO.getAllStudents());
                        rcvListStudent.setAdapter(studentAdapter);
                        rcvListStudent.setLayoutManager(linearLayoutManager);
                        studentAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(StudentActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnClass.setSelection(0);
                edtNameStudent.setText("");
                edtBirthStudent.setText("");
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtBirthStudent.setText(sdf.format(myCalendar.getTime()));
    }


    private void initView() {
        spnClass = (Spinner) findViewById(R.id.spnClass);
        edtNameStudent = (EditText) findViewById(R.id.edtNameStudent);
        edtBirthStudent = (EditText) findViewById(R.id.edtBirthStudent);
        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);
        btnUpdateStudent = (Button) findViewById(R.id.btnUpdateStudent);
        btnDeleteStudent = (Button) findViewById(R.id.btnDeleteStudent);
        rcvListStudent = (RecyclerView) findViewById(R.id.rcvListStudent);
        btnClear = (Button) findViewById(R.id.btnClear);
    }
}