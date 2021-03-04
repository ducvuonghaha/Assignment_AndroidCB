package com.dcvg.sqlitesinhvien.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dcvg.sqlitesinhvien.R;
import com.dcvg.sqlitesinhvien.adapter.ClassAdapter;
import com.dcvg.sqlitesinhvien.dao.ClassDAO;
import com.dcvg.sqlitesinhvien.model.Class;

public class ClassActivity extends AppCompatActivity {

    private ClassDAO classDAO;
    public static EditText edtNameClass;
    public static EditText edtMajorClass;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDelete;
    private RecyclerView rcvListClass;
    private ClassAdapter classAdapter;
    private LinearLayoutManager linearLayoutManager;
    public static int id = 0;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        initView();
        classDAO = new ClassDAO(this);
        linearLayoutManager = new LinearLayoutManager(this);

        if (classDAO.getAllClasses().size() != 0) {
            classAdapter = new ClassAdapter(ClassActivity.this, classDAO.getAllClasses());
            rcvListClass.setAdapter(classAdapter);
            rcvListClass.setLayoutManager(linearLayoutManager);
            classAdapter.notifyDataSetChanged();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNameClass.getText().toString().trim().isEmpty() || edtMajorClass.getText().toString().trim().isEmpty()) {
                    Toast.makeText(ClassActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (classDAO.getAllClasses().size() != 0) {
                        for (int i = 0; i < classDAO.getAllClasses().size(); i++) {
                            if (edtNameClass.getText().toString().trim().equals(classDAO.getAllClasses().get(i).getName())) {
                                Toast.makeText(ClassActivity.this, "Lớp đã tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else if (classDAO.insertClass(new Class(edtNameClass.getText().toString().trim(), edtMajorClass.getText().toString().trim())) > 0) {
                        Toast.makeText(ClassActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        classAdapter = new ClassAdapter(ClassActivity.this, classDAO.getAllClasses());
                        rcvListClass.setAdapter(classAdapter);
                        rcvListClass.setLayoutManager(linearLayoutManager);
                        classAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ClassActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNameClass.getText().toString().trim().isEmpty() || edtMajorClass.getText().toString().trim().isEmpty()) {
                    Toast.makeText(ClassActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (classDAO.getAllClasses().size() != 0) {
                        for (int i = 0; i < classDAO.getAllClasses().size(); i++) {
                            if (edtNameClass.getText().toString().trim().equals(classDAO.getAllClasses().get(i).getName())) {
                                Toast.makeText(ClassActivity.this, "Lớp đã tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else if (classDAO.updateClass(new Class(edtNameClass.getText().toString().trim(), edtMajorClass.getText().toString().trim()), id) > 0) {
                        Toast.makeText(ClassActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        classAdapter = new ClassAdapter(ClassActivity.this, classDAO.getAllClasses());
                        rcvListClass.setAdapter(classAdapter);
                        rcvListClass.setLayoutManager(linearLayoutManager);
                        classAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ClassActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNameClass.getText().toString().trim().isEmpty() || edtMajorClass.getText().toString().trim().isEmpty()) {
                    Toast.makeText(ClassActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (classDAO.deleteClass(id) > 0) {
                        Toast.makeText(ClassActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        classAdapter = new ClassAdapter(ClassActivity.this, classDAO.getAllClasses());
                        rcvListClass.setAdapter(classAdapter);
                        rcvListClass.setLayoutManager(linearLayoutManager);
                        classAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ClassActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNameClass.setText("");
                edtMajorClass.setText("");
            }
        });

    }

    private void initView() {
        edtNameClass = (EditText) findViewById(R.id.edtNameClass);
        edtMajorClass = (EditText) findViewById(R.id.edtMajorClass);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        rcvListClass = (RecyclerView) findViewById(R.id.rcvListClass);
        btnClear = (Button) findViewById(R.id.btnClear);
    }
}