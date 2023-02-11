package vn.edu.stu.quanlysanpham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TrangChinhActivity extends AppCompatActivity {
    Button btnQLSP, btnQLLSP, btnAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chinh);
        addControls();
        addEvents();
    }

    private void addControls() {
        btnQLSP = findViewById(R.id.btnQLSP);
        btnQLLSP = findViewById(R.id.btnQLLSP);
        btnAbout = findViewById(R.id.btnAbout);
    }

    private void addEvents() {
        btnQLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChinhActivity.this, QuanLySanPhamActivity.class);
                startActivity(intent);
            }
        });
        btnQLLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChinhActivity.this, QuanLyLoaiSanPhamActivity.class);
                startActivity(intent);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChinhActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(TrangChinhActivity.this, AboutActivity.class);
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
        Intent intent = new Intent(TrangChinhActivity.this, MainActivity.class);
        startActivity(intent);
    }
}