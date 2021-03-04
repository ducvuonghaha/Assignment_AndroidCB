package com.dcvg.sqlitesinhvien.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dcvg.sqlitesinhvien.R;

public class HomeActivity extends AppCompatActivity {

    private TextView tvTitleHome;
    private LinearLayout llClassManage;
    private LinearLayout llStudentManage;

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

            llStudentManage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, StudentActivity.class));
                }
            });
    }

    private void initView() {
        tvTitleHome = (TextView) findViewById(R.id.tvTitleHome);
        llClassManage = (LinearLayout) findViewById(R.id.llClassManage);
        llStudentManage = (LinearLayout) findViewById(R.id.llStudentManage);
    }
}