package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StudentInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        TextView txtMSSV = findViewById(R.id.txt_mssv);
        TextView txtHoten = findViewById(R.id.txt_hoten);
        TextView txtDob = findViewById(R.id.txt_dob);
        TextView txtEmail = findViewById(R.id.txt_email);
        TextView txtAddress = findViewById(R.id.txt_address);

        txtMSSV.setText(bundle.getString("mssv"));
        txtHoten.setText(bundle.getString("name"));
        txtDob.setText(bundle.getString("dob"));
        txtEmail.setText(bundle.getString("email"));
        txtAddress.setText(bundle.getString("address"));

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}