package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;

public class FormActivity extends AppCompatActivity {

    SQLiteDatabase db;

    EditText edtMssv;
    EditText edtHoten;
    EditText edtNgaysinh;
    EditText edtDiachi;
    EditText edtEmail;

    Button btnSelect;
    Button btnSubmit;

    DatePicker dob;

    TextView alertMSSV, alertHoten, alertDob, alertDiachi, alertEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        String dataPath = getFilesDir() + "/student_data";
        db = SQLiteDatabase.openDatabase(dataPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        init();

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dob.getVisibility() == View.GONE) {
                    dob.setVisibility(View.VISIBLE);
                } else {
                    dob.setVisibility(View.GONE);

                    int day = dob.getDayOfMonth();
                    int month = dob.getMonth() + 1;
                    int year = dob.getYear();

                    edtNgaysinh.setText(String.format("%d-%d-%d", year, month, day));
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv = edtMssv.getText().toString();
                String hoten = edtHoten.getText().toString();
                String ngaysinh = edtNgaysinh.getText().toString();
                String diachi = edtDiachi.getText().toString();
                String email = edtEmail.getText().toString();

                boolean flag = true;

                if (mssv.equals("")) {
                    if (alertMSSV.getVisibility() == View.GONE) {
                        alertMSSV.setVisibility(View.VISIBLE);
                    }
                    alertMSSV.setText("Nhập MSSV");
                    flag = false;
                }

                if (hoten.equals("")) {
                    if (alertHoten.getVisibility() == View.GONE) {
                        alertHoten.setVisibility(View.VISIBLE);
                    }
                    flag = false;
                }

                if (ngaysinh.equals("")) {
                    if (alertDob.getVisibility() == View.GONE) {
                        alertDob.setVisibility(View.VISIBLE);
                    }
                    flag = false;
                }

                if (diachi.equals("")) {
                    if (alertDiachi.getVisibility() == View.GONE) {
                        alertDiachi.setVisibility(View.VISIBLE);
                    }
                    flag = false;
                }

                if (email.equals("")) {
                    if (alertEmail.getVisibility() == View.GONE) {
                        alertEmail.setVisibility(View.VISIBLE);
                    }
                    flag = false;
                }

                if (flag == true) {
                    alertMSSV.setVisibility(View.GONE);
                    alertHoten.setVisibility(View.GONE);
                    alertDob.setVisibility(View.GONE);
                    alertDiachi.setVisibility(View.GONE);
                    alertEmail.setVisibility(View.GONE);

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mssv", mssv);
                    contentValues.put("hoten", hoten);
                    contentValues.put("ngaysinh", ngaysinh);
                    contentValues.put("email", email);
                    contentValues.put("diachi", diachi);

                    long rs = db.insert("sinhvien", null, contentValues);
                    if (rs == -1) {
                        alertMSSV.setVisibility(View.VISIBLE);
                        alertMSSV.setText("MSSV này đã tồn tại, nhập MSSV khác");
                    } else {
                        Toast.makeText(FormActivity.this, "Thêm thành công!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });

    }

    @Override
    protected void onStop() {
        db.close();
        super.onStop();
    }

    private void init() {
        edtMssv = findViewById(R.id.edt_mssv);
        edtHoten = findViewById(R.id.edt_hoten);
        edtNgaysinh = findViewById(R.id.edt_ngaysinh);
        edtDiachi = findViewById(R.id.edt_diachi);
        edtEmail = findViewById(R.id.edt_email);

        btnSelect = findViewById(R.id.btn_select_date);
        btnSubmit = findViewById(R.id.btn_submit);

        dob = findViewById(R.id.date_picker);

        alertMSSV = findViewById(R.id.txt_alert_mssv);
        alertHoten = findViewById(R.id.txt_alert_hoten);
        alertDob = findViewById(R.id.txt_alert_dob);
        alertDiachi = findViewById(R.id.txt_alert_diachi);
        alertEmail = findViewById(R.id.txt_alert_email);
    }
}