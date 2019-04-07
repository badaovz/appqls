package com.example.datvl.appqls;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Database database;
    EditText ten, matkhau;
    Button dangnhap;
    String name ="";
    String pass ="";
    List<User> listuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();

        database = new Database(this,"tbmonhoc.sqlite",null,1);
        //TAO BANG CONG VIEC
        database.QueryData("CREATE TABLE IF NOT EXISTS sach(Id INTEGER PRIMARY KEY AUTOINCREMENT, HinhAnh BLOB, TenSach VARCHAR(200), TheLoai VARCHAR(200))");
        database.QueryData("CREATE TABLE IF NOT EXISTS user(Id INTEGER PRIMARY KEY AUTOINCREMENT,  TaiKhoan VARCHAR(200), MatKhau VARCHAR(200))");
        //insert data
        //database.QueryData("INSERT INTO user VALUES(null, 'dao', '12345678')");


        Cursor datauser = database.GetData("SELECT * FROM user");
        //datauser.

        while (datauser.moveToNext()){
            name = datauser.getString(1);
            pass = datauser.getString(2);
        }



        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((ten.getText().toString()).equals(name) && (matkhau.getText().toString()).equals(pass)) {
                    startActivity(new Intent(MainActivity.this, Main1Activity.class));
                }else {
                    Toast.makeText(MainActivity.this, "wrong name or password!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void anhxa() {
        ten      = findViewById(R.id.etten);
        matkhau  = findViewById(R.id.etpassword);
        dangnhap = findViewById(R.id.btdangnhap);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_taikhoan,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            startActivity(new Intent(MainActivity.this, DangKyActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
