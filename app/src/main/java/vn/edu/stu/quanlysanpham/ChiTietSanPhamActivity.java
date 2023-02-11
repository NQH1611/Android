package vn.edu.stu.quanlysanpham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    TextView txtMaSP, txtTenSP, txtGiaSP, txtHanSD, txtTrongLuong;
    ImageView imageDetail;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        addControls();
        hienThiDuLieu();
        addEvents();
    }

    private void addControls() {
        txtMaSP = findViewById(R.id.txtMaSP);
        txtTenSP = findViewById(R.id.txtTenSP);
        txtGiaSP = findViewById(R.id.txtGiaSP);
        txtHanSD = findViewById(R.id.txtHanSD);
        txtTrongLuong = findViewById(R.id.txtTrongLuong);
        imageDetail = findViewById(R.id.imageDetail);
        btnBack = findViewById(R.id.btnBack);
    }

    private void hienThiDuLieu() {
        Intent intent = getIntent();
        String ma = intent.getStringExtra("ma");
        String ten = intent.getStringExtra("ten");
        String gia = intent.getStringExtra("gia");
        String hansd = intent.getStringExtra("hansd");
        String trongLuong = intent.getStringExtra("trongluong");
        byte[] img = intent.getByteArrayExtra("img");
        txtMaSP.setText(ma);
        txtTenSP.setText(ten);
        txtGiaSP.setText(gia);
        txtHanSD.setText(hansd);
        txtTrongLuong.setText(trongLuong);
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        imageDetail.setImageBitmap(bitmap);
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSanPhamActivity.this, QuanLySanPhamActivity.class);
                startActivity(intent);
            }
        });
    }
}