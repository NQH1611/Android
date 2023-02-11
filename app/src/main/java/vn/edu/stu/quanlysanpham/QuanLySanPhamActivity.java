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

import java.util.ArrayList;

import vn.edu.stu.quanlysanpham.Adapter.adapterSanPham;
import vn.edu.stu.quanlysanpham.database.database;
import vn.edu.stu.quanlysanpham.model.SanPham;

public class QuanLySanPhamActivity extends AppCompatActivity {
    Button btnToAcThemSp;
    ListView lvSanPham;
    ArrayList<SanPham> arrSanPham;
    vn.edu.stu.quanlysanpham.database.database database;
    vn.edu.stu.quanlysanpham.Adapter.adapterSanPham adapterSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);
        addControls();
        hienThiDuLieuSanPham();
        addEvents();
    }

    private void hienThiDuLieuSanPham() {
        database = new database(this);
        Cursor cursor = database.getDataSanPham();
        arrSanPham = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ma = cursor.getString(1);
            String ten = cursor.getString(2);
            String gia = cursor.getString(3);
            String hanSD = cursor.getString(4);
            String trongLuong = cursor.getString(5);
            byte[] anh = cursor.getBlob(6);
            String loaiSP = cursor.getString(7);
            arrSanPham.add(new SanPham(id, ma, ten, gia, hanSD, trongLuong, anh, loaiSP));
        }
        adapterSanPham = new adapterSanPham(QuanLySanPhamActivity.this, arrSanPham);
        lvSanPham.setAdapter(adapterSanPham);
        cursor.moveToFirst();
        cursor.close();

    }

    private void addControls() {
        btnToAcThemSp = findViewById(R.id.btnToAcThemSP);
        lvSanPham = findViewById(R.id.lvSanPham);
    }

    private void addEvents() {
        btnToAcThemSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySanPhamActivity.this, ThemSanPhamActivity.class);
                startActivity(intent);
            }
        });
    }

    public void deleteSanPham(int id) {
        database = new database(this);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_xoa_sanpham);
        dialog.setCanceledOnTouchOutside(false);
        Button btnAccept = dialog.findViewById(R.id.btnDeleteSPAccept);
        Button btnUnAccept = dialog.findViewById(R.id.btnDeleteSPUnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.DeleteSanPham(id);
                Intent intent = new Intent(QuanLySanPhamActivity.this, QuanLySanPhamActivity.class);
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

    public void updateSanPham(int id) {
        Cursor cursor = database.getSanPhamByID(id);
        while (cursor.moveToNext()) {
            Intent intent = new Intent(QuanLySanPhamActivity.this, SuaSanPhamActivity.class);
            int idSP = cursor.getInt(0);
            String ma = cursor.getString(1);
            String ten = cursor.getString(2);
            String gia = cursor.getString(3);
            String hansd = cursor.getString(4);
            String trongluong = cursor.getString(5);
            byte[] img = cursor.getBlob(6);
            String maLSP = cursor.getString(7);
            intent.putExtra("id", idSP);
            intent.putExtra("ma", ma);
            intent.putExtra("ten", ten);
            intent.putExtra("gia", gia);
            intent.putExtra("hansd", hansd);
            intent.putExtra("trongluong", trongluong);
            intent.putExtra("img", img);
            intent.putExtra("maLSP", maLSP);
            startActivity(intent);
        }
    }

    public void detailSanPham(int id) {
        Cursor cursor = database.getSanPhamByID(id);
        while (cursor.moveToNext()) {
            Intent intent = new Intent(QuanLySanPhamActivity.this, ChiTietSanPhamActivity.class);
            int idSP = cursor.getInt(0);
            String ma = cursor.getString(1);
            String ten = cursor.getString(2);
            String gia = cursor.getString(3);
            String hansd = cursor.getString(4);
            String trongluong = cursor.getString(5);
            byte[] img = cursor.getBlob(6);
            String maLSP = cursor.getString(7);
            intent.putExtra("id", idSP);
            intent.putExtra("ma", ma);
            intent.putExtra("ten", ten);
            intent.putExtra("gia", gia);
            intent.putExtra("hansd", hansd);
            intent.putExtra("trongluong", trongluong);
            intent.putExtra("img", img);
            intent.putExtra("maLSP", maLSP);
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
                Intent intent = new Intent(QuanLySanPhamActivity.this, AboutActivity.class);
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
        Intent intent = new Intent(QuanLySanPhamActivity.this, TrangChinhActivity.class);
        startActivity(intent);
    }
}