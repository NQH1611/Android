package vn.edu.stu.quanlysanpham;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.stu.quanlysanpham.database.database;
import vn.edu.stu.quanlysanpham.model.LoaiSanPham;

public class ThemLoaiSanPhamActivity extends AppCompatActivity {
    Button btnThemLSP;
    EditText etMaLoaiSP, etTenLoaiSP;
    vn.edu.stu.quanlysanpham.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_san_pham);
        addControls();
        addEvents();
    }

    private void addControls() {
        btnThemLSP = findViewById(R.id.btnThemLSP);
        etMaLoaiSP = findViewById(R.id.etMaLoaiSP);
        etTenLoaiSP = findViewById(R.id.etTenLoaiSP);
    }

    private void addEvents() {
        btnThemLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThiDialogThemLSP();
            }
        });
    }
    public  void hienThiDialogThemLSP(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_loaisp);
        dialog.setCanceledOnTouchOutside(false);
        Button btnAccept = dialog.findViewById(R.id.btnAccept);
        Button btnUnAccept = dialog.findViewById(R.id.btnUnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = etMaLoaiSP.getText().toString().trim();
                String ten = etTenLoaiSP.getText().toString().trim();
                if(ma.equals("")||ten.equals("")){
                    Toast.makeText(ThemLoaiSanPhamActivity.this, "Nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    database = new database(getApplicationContext());
                    LoaiSanPham loaiSanPham = createLoaiSP();
                    database.insertDataLoaiSanPham(loaiSanPham);
                    Intent intent = new Intent(ThemLoaiSanPhamActivity.this, QuanLyLoaiSanPhamActivity.class);
                    startActivity(intent);
                    Toast.makeText(ThemLoaiSanPhamActivity.this, "Thành Công", Toast.LENGTH_SHORT).show();
                }
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
    public LoaiSanPham createLoaiSP(){
        String ma = etMaLoaiSP.getText().toString().trim();
        String ten = etTenLoaiSP.getText().toString().trim();
        LoaiSanPham loaiSanPham = new LoaiSanPham(0, ma, ten);
        return loaiSanPham;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ThemLoaiSanPhamActivity.this, QuanLyLoaiSanPhamActivity.class);
        startActivity(intent);
    }
}