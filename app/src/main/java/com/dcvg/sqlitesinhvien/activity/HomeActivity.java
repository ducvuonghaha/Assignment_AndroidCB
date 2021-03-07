package com.dcvg.sqlitesinhvien.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dcvg.sqlitesinhvien.R;

public class HomeActivity extends AppCompatActivity {

    private TextView tvTitleHome;
    private LinearLayout llClassManage;
    private LinearLayout llStudentManage;
    private Button btnSignOut;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        llClassManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ClassActivity.class));
            }
        });
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
        tvName.setText("Xin chào " + sharedPreferences.getString("username",""));
        llStudentManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, StudentActivity.class));
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().commit();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        return;
    }

    private void initView() {
        tvTitleHome = (TextView) findViewById(R.id.tvTitleHome);
        llClassManage = (LinearLayout) findViewById(R.id.llClassManage);
        llStudentManage = (LinearLayout) findViewById(R.id.llStudentManage);
        btnSignOut = (Button) findViewById(R.id.btnSignOut);
        tvName = (TextView) findViewById(R.id.tvName);
    }
}