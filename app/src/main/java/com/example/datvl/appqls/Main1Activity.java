package com.example.datvl.appqls;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Main1Activity extends AppCompatActivity {

    ListView listSach;

    public static ArrayList<Sach> arrsach;
    public static SachAdapter sachAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        listSach = findViewById(R.id.lvsach);

        arrsach = new ArrayList<>();
        sachAdapter =new SachAdapter(this,R.layout.dong_sach,arrsach);
        listSach.setAdapter(sachAdapter);

        selectdata();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_sach, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem it) {
            startActivity(new Intent(this, ThemActivity.class));
        return super.onOptionsItemSelected(it);
    }

    public static void selectdata(){
        Cursor dataCongViec = MainActivity.database.GetData("SELECT * FROM sach");
        arrsach.clear();
        while (dataCongViec.moveToNext()){

            arrsach.add(new Sach(dataCongViec.getInt(0),
                    dataCongViec.getBlob(1),
                    dataCongViec.getString(2),
                    dataCongViec.getString(3)));
        }

        sachAdapter.notifyDataSetChanged();
    }
}
