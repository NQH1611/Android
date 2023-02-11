package vn.edu.stu.quanlysanpham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.stu.quanlysanpham.Adapter.adapterLoaiSanPham;
import vn.edu.stu.quanlysanpham.database.database;
import vn.edu.stu.quanlysanpham.model.LoaiSanPham;
import vn.edu.stu.quanlysanpham.model.SanPham;

public class QuanLyLoaiSanPhamActivity extends AppCompatActivity {
    Button btnToAcThemLSP;
    private ListView lvLoaiSP;
    ArrayList<LoaiSanPham> arrLoaiSanPham;
    vn.edu.stu.quanlysanpham.database.database database;
    vn.edu.stu.quanlysanpham.Adapter.adapterLoaiSanPham adapterLoaiSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_loai_san_pham);

        addControls();
        hienThiDuLieuLoaiSP();
        addEvents();
    }

    private void hienThiDuLieuLoaiSP() {
        database = new database(this);
        arrLoaiSanPham = new ArrayList<>();
        Cursor cursor = database.getDataLoaiSanPham();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ma = cursor.getString(1);
            String ten = cursor.getString(2);
            arrLoaiSanPham.add(new LoaiSanPham(id, ma, ten));
        }
        adapterLoaiSanPham = new adapterLoaiSanPham(QuanLyLoaiSanPhamActivity.this, arrLoaiSanPham);
        lvLoaiSP.setAdapter(adapterLoaiSanPham);
        cursor.moveToFirst();
        cursor.close();
    }

    private void addControls() {
        lvLoaiSP = findViewById(R.id.lvLoaiSP);
        btnToAcThemLSP = findViewById(R.id.btnToAcThemLSP);
    }

    private void addEvents() {
        btnToAcThemLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLyLoaiSanPhamActivity.this, ThemLoaiSanPhamActivity.class);
                startActivity(intent);
            }
        });

    }

    public void deleteLoaiSP(final String maLSP,int id) {
        Cursor cursor =  database.getSanPhamByMaLSP(maLSP);
        ArrayList<SanPham> temp = new ArrayList<>();
        while (cursor.moveToNext()){
            String tempMa = cursor.getString(0);
            temp.add(new SanPham(tempMa));
        }
        if(temp.size() == 0){
            xuLyXoaKhongCoDataRangBuoc(id);
        }else{
            xuLyXoaCoDataRangBuoc(maLSP, id);
        }
    }

    public void xuLyXoaCoDataRangBuoc(String ma, int id){
        Dialog dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.dialog_xoa_contrains_lsp);
        dialog1.setCanceledOnTouchOutside(false);
        Button btnConstransAccept = dialog1.findViewById(R.id.btnDeleteContrainsAccept);
        Button btnConstransUnAccept = dialog1.findViewById(R.id.btnDeleteContrainsUnAccept);
        btnConstransAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteSanPhamByMaLSP(ma);
                database.DeleteLoaiSanPham(id);
                Intent intent = new Intent(QuanLyLoaiSanPhamActivity.this, QuanLyLoaiSanPhamActivity.class);
                startActivity(intent);
            }
        });
        btnConstransUnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.cancel();
            }
        });
        dialog1.show();
    }
    public  void xuLyXoaKhongCoDataRangBuoc(int id){
        database = new database(this);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_xoa_loaisp);
        dialog.setCanceledOnTouchOutside(false);
        Button btnAccept = dialog.findViewById(R.id.btnDeleteAccept);
        Button btnUnAccept = dialog.findViewById(R.id.btnDeleteUnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.DeleteLoaiSanPham(id);
                Intent intent = new Intent(QuanLyLoaiSanPhamActivity.this, QuanLyLoaiSanPhamActivity.class);
                startActivity(intent);
            }
        });
        btnUnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void updateLoaiSP(int id) {
        Cursor cursor = database.getLoaiSanPhamById(id);
        while (cursor.moveToNext()) {
            Intent intent = new Intent(QuanLyLoaiSanPhamActivity.this, SuaLoaiSanPhamActivity.class);
            intent.putExtra("idlsp", id);
            String ma = cursor.getString(1);
            String ten = cursor.getString(2);
            intent.putExtra("ma", ma);
            intent.putExtra("ten", ten);
            startActivity(intent);
        }
    }

    public void detailLoaiSP(int id) {
        Cursor cursor = database.getLoaiSanPhamById(id);
        while (cursor.moveToNext()) {
            Intent intent = new Intent(QuanLyLoaiSanPhamActivity.this, ChiTietLoaiSanPhamActivity.class);
            String ma = cursor.getString(1);
            String ten = cursor.getString(2);
            intent.putExtra("ma", ma);
            intent.putExtra("ten", ten);
            startActivity(intent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuAbout:
                Intent intent = new Intent(QuanLyLoaiSanPhamActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(QuanLyLoaiSanPhamActivity.this, TrangChinhActivity.class);
        startActivity(intent);
    }
}