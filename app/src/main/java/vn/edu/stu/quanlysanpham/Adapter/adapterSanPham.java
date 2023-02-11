package vn.edu.stu.quanlysanpham.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.stu.quanlysanpham.QuanLySanPhamActivity;
import vn.edu.stu.quanlysanpham.R;
import vn.edu.stu.quanlysanpham.model.SanPham;

public class adapterSanPham extends BaseAdapter {
    QuanLySanPhamActivity context;
    ImageView imgSanPham;
    ArrayList<SanPham> arrSanPham;

    public adapterSanPham(QuanLySanPhamActivity context, ArrayList<SanPham> arrSanPham) {
        this.context = context;
        this.arrSanPham = arrSanPham;
    }

    @Override
    public int getCount() {
        return arrSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.option_listview_row, null);

        TextView txtTenSP = convertView.findViewById(R.id.txtTenSp);
        TextView txtGiaSP = convertView.findViewById(R.id.txtGiaSP);
        TextView txtLoaiSP = convertView.findViewById(R.id.txtOPLoaiSp);
        imgSanPham = convertView.findViewById(R.id.imgAnh);

        Button btnSuaSP = convertView.findViewById(R.id.btnSuaSP);
        Button btnXoaSP = convertView.findViewById(R.id.btnXoaSP);
        Button btnChiTietSP = convertView.findViewById(R.id.btnChiTietSP);

        SanPham sanPham = arrSanPham.get(position);

        txtTenSP.setText(sanPham.getTenSP());
        txtGiaSP.setText(sanPham.getGia());
        txtLoaiSP.setText(sanPham.getMaLSP());

        int id = sanPham.getId();

        Bitmap bitmap = BitmapFactory.decodeByteArray(sanPham.getHinh() , 0 , sanPham.getHinh().length);
        imgSanPham.setImageBitmap(bitmap);
        btnXoaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteSanPham(id);
            }
        });
        btnSuaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.updateSanPham(id);
            }
        });
        btnChiTietSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.detailSanPham(id);
            }
        });
        return convertView;
    }
}
