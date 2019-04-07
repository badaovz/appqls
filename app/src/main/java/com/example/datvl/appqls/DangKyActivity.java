package com.example.datvl.appqls;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class DangKyActivity extends AppCompatActivity {
    EditText ten, taikhoan, matkhau, sdt;
    ImageButton back;
    Button tao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        anhxa();

        tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk = taikhoan.getText().toString().trim();
                String mk = matkhau.getText().toString().trim();
                MainActivity.database.inserttk(tk, mk);

                startActivity(new Intent(DangKyActivity.this, MainActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKyActivity.this, MainActivity.class));
            }
        });




    }

    private void anhxa() {
        ten      = findViewById(R.id.ettendk);
        taikhoan = findViewById(R.id.ettaikhoandk);
        matkhau  = findViewById(R.id.etmatkhaudk);
        sdt      = findViewById(R.id.etsdtdk);
        back     = findViewById(R.id.ibbackdk);
        tao      = findViewById(R.id.bttaodk);

    }
}
