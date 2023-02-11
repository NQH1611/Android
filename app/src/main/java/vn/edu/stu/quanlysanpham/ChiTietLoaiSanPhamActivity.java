package vn.edu.stu.quanlysanpham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChiTietLoaiSanPhamActivity extends AppCompatActivity {
    TextView txtDetailTenLSP, txtDetailMaLSP;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_loai_san_pham);
        addControls();
        hienThiDuLieu();
        addEvents();
    }



    private void addControls() {
        txtDetailTenLSP = findViewById(R.id.txtDetailTenLSP);
        txtDetailMaLSP = findViewById(R.id.txtDetailMaLSP);
        btnBack = findViewById(R.id.btnBack);
    }
    private void hienThiDuLieu() {
        Intent intent = getIntent();
        String ma = intent.getStringExtra("ma");
        String ten = intent.getStringExtra("ten");
        txtDetailMaLSP.setText(ma);
        txtDetailTenLSP.setText(ten);

    }
    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietLoaiSanPhamActivity.this, QuanLyLoaiSanPhamActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ChiTietLoaiSanPhamActivity.this, QuanLyLoaiSanPhamActivity.class);
        startActivity(intent);
    }
}