package com.example.datvl.appqls;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends BaseAdapter {

    private Main1Activity context;
    private int layout;
    private List<Sach> listsach;

    public SachAdapter(Main1Activity context, int layout, List<Sach> listsach) {
        this.context = context;
        this.layout = layout;
        this.listsach = listsach;
    }

    @Override
    public int getCount() {
        return listsach.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tensach,theloai;
        ImageView avatar,delete,edit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(layout,null);
            holder.avatar = (ImageView) convertView.findViewById(R.id.ivavatar);
            holder.tensach  = (TextView) convertView.findViewById(R.id.tvtensach);
            holder.theloai   = (TextView) convertView.findViewById(R.id.tvtheloai);
            holder.delete = (ImageView) convertView.findViewById(R.id.ivdelete);
            holder.edit   = (ImageView)  convertView.findViewById(R.id.ivedit);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Sach sach = listsach.get(position);

        //chuyen tu byte[] -> bitmap
        byte[] hinhanh = sach.getAvatar();
        Bitmap bm = BitmapFactory.decodeByteArray(hinhanh,0, hinhanh.length);

        holder.avatar.setImageBitmap(bm);
        holder.tensach.setText(sach.getTens());
        holder.theloai.setText(sach.getTheloai());


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suasach(sach.getTens(), sach.getTheloai(), sach.getIdsach());
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(sach.getTens(),sach.getIdsach());
            }
        });


        return convertView;
    }

    private void suasach(final String ten, final String theloai, final int id) {
        final Dialog dg = new Dialog(context);
        dg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dg.setContentView(R.layout.sua_sach);
        dg.show();

        final EditText suat  = dg.findViewById(R.id.etsuaten);
        final EditText suatl = dg.findViewById(R.id.etsuatheloai);
        Button xacnhan       = dg.findViewById(R.id.btxacnhan);
        Button huyv          = dg.findViewById(R.id.bthuy);

        suat.setText(ten);
        suatl.setText(theloai);
       xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String tens = suat.getText().toString().trim();
               String thel = suatl.getText().toString().trim();
               MainActivity.database.QueryData("UPDATE Sach SET TenSach = '"+tens+"', TheLoai = '"+thel+"' WHERE Id = '"+id+"'");
               dg.dismiss();
               Main1Activity.selectdata();

            }
        });

        huyv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dg.dismiss();
            }
        });
    }

    private void delete(String ten, final int id){
        final Dialog dg = new Dialog(context);
        dg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dg.setContentView(R.layout.delete_sach);
        dg.show();

        TextView tens        = dg.findViewById(R.id.tvdelete);
        Button  xndelete = dg.findViewById(R.id.btxndelete);
        Button  huydelete     = dg.findViewById(R.id.btxnhuy);

        tens.setText(ten);

        xndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.QueryData("DELETE FROM sach WHERE Id = '"+id+"'");
                Log.d("BBB", id+"");
                dg.dismiss();
                Main1Activity.selectdata();
            }
        });

        huydelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dg.dismiss();
            }
        });
    }
}
