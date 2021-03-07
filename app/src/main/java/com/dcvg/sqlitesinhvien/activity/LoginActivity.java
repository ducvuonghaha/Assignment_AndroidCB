package com.dcvg.sqlitesinhvien.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dcvg.sqlitesinhvien.R;
import com.dcvg.sqlitesinhvien.dao.UserDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private CheckBox cbRememberLogin;
    private Button btnLogin;
    private TextView tvSignUp;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        if (sharedPreferences.getBoolean("login",false) == true) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < userDAO.getAllUsers().size(); i++) {
                        if (userDAO.getAllUsers().get(i).getUsername().equals(username) && userDAO.getAllUsers().get(i).getPassword().equals(password)) {
                            editor.putString("username", username);
                            if (cbRememberLogin.isChecked()) {
                                editor.putBoolean("login", true);
                            }
                            editor.commit();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            break;
                        } else if (i == userDAO.getAllUsers().size() -1 ) {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }

            }
        });
    }

    private void initView() {
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        cbRememberLogin = (CheckBox) findViewById(R.id.cbRememberLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        userDAO = new UserDAO(this);
    }
}