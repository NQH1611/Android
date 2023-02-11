package vn.edu.stu.quanlysanpham.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.stu.quanlysanpham.AboutActivity;
import vn.edu.stu.quanlysanpham.QuanLyLoaiSanPhamActivity;
import vn.edu.stu.quanlysanpham.QuanLySanPhamActivity;
import vn.edu.stu.quanlysanpham.R;
import vn.edu.stu.quanlysanpham.model.LoaiSanPham;

public class adapterLoaiSanPham extends BaseAdapter {
    QuanLyLoaiSanPhamActivity context;
    ArrayList<LoaiSanPham> arrLoaiSanPham;

    public adapterLoaiSanPham(QuanLyLoaiSanPhamActivity context, ArrayList<LoaiSanPham> arrLoaiSanPham) {
        this.context = context;
        this.arrLoaiSanPham = arrLoaiSanPham;
    }

    @Override
    public int getCount() {
        return arrLoaiSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return arrLoaiSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.option_listview_qllsp, null);

        TextView txtMaLSP = convertView.findViewById(R.id.txtMaLSP);
        TextView txtTenLSP = convertView.findViewById(R.id.txtTenLSP);


        Button btnSua = convertView.findViewById(R.id.btnSuaLSP);
        Button btnXoa = convertView.findViewById(R.id.btnXoaLSP);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTietLSP);

        LoaiSanPham loaiSanPham = arrLoaiSanPham.get(position);
        txtMaLSP.setText(loaiSanPham.getMaLSP());
        txtTenLSP.setText(loaiSanPham.getTenLSP());

        int id = loaiSanPham.getId();
        String ma = loaiSanPham.getMaLSP();
        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.detailLoaiSP(id);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteLoaiSP(ma, id);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.updateLoaiSP(id);
            }
        });
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, AboutActivity.class);
//                intent.putExtra("malsp", ma);
//                context.startActivity(intent);
//            }
//        });
        return convertView;
    }
}

