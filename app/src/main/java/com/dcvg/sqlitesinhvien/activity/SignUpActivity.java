package com.dcvg.sqlitesinhvien.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dcvg.sqlitesinhvien.R;
import com.dcvg.sqlitesinhvien.dao.UserDAO;
import com.dcvg.sqlitesinhvien.model.User;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtUsernameSU;
    private EditText edtPasswordSU;
    private EditText edtRePasswordSU;
    private Button btnSignUp;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUsernameSU.getText().toString().trim().isEmpty() || edtPasswordSU.getText().toString().trim().isEmpty() || edtRePasswordSU.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!edtPasswordSU.getText().toString().trim().equals(edtRePasswordSU.getText().toString().trim())) {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu chưa trùng", Toast.LENGTH_SHORT).show();
                } else {
                    if (userDAO.insertUser(new User(edtUsernameSU.getText().toString().trim(), edtPasswordSU.getText().toString().trim())) > 0) {
                        Toast.makeText(SignUpActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        edtUsernameSU = (EditText) findViewById(R.id.edtUsernameSU);
        edtPasswordSU = (EditText) findViewById(R.id.edtPasswordSU);
        edtRePasswordSU = (EditText) findViewById(R.id.edtRePasswordSU);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        userDAO = new UserDAO(this);
    }
}