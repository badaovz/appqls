package com.example.datvl.appqls;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemActivity extends AppCompatActivity {

    EditText tensach, theloai;
    Button them, huy;
    ImageButton cameras, files;
    ImageView hinhchon;

    final int RQ_CODE_CAMERA = 123;
    final int RQ_CODE_FILE = 143;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        ax();

        cameras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(ThemActivity.this,
                        new String[]{Manifest.permission.CAMERA}, RQ_CODE_CAMERA);

            }

        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(ThemActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RQ_CODE_FILE);

            }

        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bd = (BitmapDrawable) hinhchon.getDrawable();
                Bitmap bm = bd.getBitmap();
                ByteArrayOutputStream barr = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, barr);
                byte[] hinhanh = barr.toByteArray();

                String tens = tensach.getText().toString().trim();
                String theloais = theloai.getText().toString().trim();
                if (tens.equals("") || theloais.equals("") || hinhanh.length == 0) {
                    Toast.makeText(ThemActivity.this, "ban phai nhap du thong tin!", Toast.LENGTH_LONG).show();
                } else {
                    MainActivity.database.insertsach(hinhanh, tens, theloais);
                    startActivity(new Intent(ThemActivity.this,Main1Activity.class));
                }
            }
        });


        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemActivity.this,Main1Activity.class));
            }
        });
    }


    private void ax () {
        tensach = findViewById(R.id.ettens);
        theloai = findViewById(R.id.ettheloai);
        them    = findViewById(R.id.btthem1);
        huy     = findViewById(R.id.bthuy1);
        cameras  = findViewById(R.id.ibcamera);
        files   = findViewById(R.id.ibselectfile);
        hinhchon = findViewById(R.id.ivhinhsach);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case RQ_CODE_CAMERA:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(it,RQ_CODE_CAMERA);
                }else {
                    Toast.makeText(this,"ban khong cho mo camera!",Toast.LENGTH_LONG).show();
                }
                break;
            case RQ_CODE_FILE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent it = new Intent(Intent.ACTION_PICK);
                    it.setType("image/*");
                    startActivityForResult(it,RQ_CODE_FILE);
                }else {
                    Toast.makeText(this,"ban khong cho truy cap vao file!",Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == RQ_CODE_CAMERA && requestCode == RESULT_OK && data != null){
            Bitmap bm = (Bitmap) data.getExtras().get(String.valueOf(data));
            hinhchon.setImageBitmap(bm);
        }

        if(requestCode == RQ_CODE_FILE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(uri);
                Bitmap bm = BitmapFactory.decodeStream(inputStream);
                hinhchon.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
