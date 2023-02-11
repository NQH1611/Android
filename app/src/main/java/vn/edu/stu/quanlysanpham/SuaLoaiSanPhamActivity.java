package vn.edu.stu.quanlysanpham;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import vn.edu.stu.quanlysanpham.database.database;
import vn.edu.stu.quanlysanpham.model.LoaiSanPham;

public class SuaLoaiSanPhamActivity extends AppCompatActivity {
    EditText etMaLoaiSP, etTenLoaiSP;
    Button btnSuaLoaiSanPham;
    vn.edu.stu.quanlysanpham.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_loai_san_pham);
        addControls();
        hienThiDuLieu();
        addEvents();
    }

    private void hienThiDuLieu() {
        Intent intent = getIntent();

        String ma = intent.getStringExtra("ma");
        String ten = intent.getStringExtra("ten");
        etMaLoaiSP.setText(ma);
        etTenLoaiSP.setText(ten);

    }

    private void addControls() {
        etMaLoaiSP = findViewById(R.id.etMaLoaiSP);
        etTenLoaiSP = findViewById(R.id.etTenLoaiSP);
        btnSuaLoaiSanPham = findViewById(R.id.btnSuaLoaiSanPham);

    }

    private void addEvents() {
        btnSuaLoaiSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new database(getApplicationContext());
                Intent intent = getIntent();
                int id = intent.getIntExtra("idlsp", -1);
                String maLSP = etMaLoaiSP.getText().toString().trim();
                String tenLSP = etTenLoaiSP.getText().toString().trim();
                LoaiSanPham loaiSanPham = new LoaiSanPham(maLSP, tenLSP);
                if (maLSP.equals("") || tenLSP.equals("")) {
                    Toast.makeText(SuaLoaiSanPhamActivity.this, "Nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursorSP = database.getSanPhamByMaLSP(maLSP);
                    ArrayList<String> arrSP = new ArrayList<>();
                    while (cursorSP.moveToNext()) {
                        arrSP.add(cursorSP.getString(1));
                    }
                    if (arrSP.size() == 0) {
                        hienThiDialogSuaLoaiSP(loaiSanPham, id);
                    } else {
                        hienThiDialogContrain();
                    }
                }
            }
        });
    }

    private void hienThiDialogContrain() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua_contrains);
        dialog.setCanceledOnTouchOutside(false);
        Button btnUpdateAccept = dialog.findViewById(R.id.btnUpdateContrainAccept);
        btnUpdateAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void hienThiDialogSuaLoaiSP(LoaiSanPham loaiSanPham, int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua_loaisp);
        dialog.setCanceledOnTouchOutside(false);
        Button btnUpdateAccept = dialog.findViewById(R.id.btnUpdateAccept);
        Button btnUpdateUnAccept = dialog.findViewById(R.id.btnUpdateUnAccept);
        btnUpdateAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new database(getApplicationContext());
                database.UpdateDataLoaiSanPham(loaiSanPham, id);
                System.out.println(loaiSanPham);
                if (id == -1) {
                    return;
                } else {
                    Intent newIntent = new Intent(SuaLoaiSanPhamActivity.this, QuanLyLoaiSanPhamActivity.class);
                    startActivity(newIntent);
                    Toast.makeText(SuaLoaiSanPhamActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdateUnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SuaLoaiSanPhamActivity.this, QuanLyLoaiSanPhamActivity.class);
        startActivity(intent);
    }
}