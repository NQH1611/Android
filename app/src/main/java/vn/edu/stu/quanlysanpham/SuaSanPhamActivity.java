package vn.edu.stu.quanlysanpham;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import vn.edu.stu.quanlysanpham.database.database;
import vn.edu.stu.quanlysanpham.model.SanPham;

public class SuaSanPhamActivity extends AppCompatActivity {
    EditText etUpdateMaSanPham, etUpdateTenSanPham, etUpdateGiaSanPham, etUpdateHanSuDung, etUpdateTrongLuong;
    ImageView imgUpdateSanPham;
    Button btnUpdateSP;
    Spinner spinnerUpdate;
    String maLSP = "";
    int id = 0;
    ArrayList<String> arrSanPham;
    ArrayAdapter<String> adapter;
    final int REQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    vn.edu.stu.quanlysanpham.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham);
        addControls();
        hienThiDuLieuSpinner();
        hienThiDuLieu();
        addEvents();
    }

    private void addEvents() {
        imgUpdateSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
        btnUpdateSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ma = etUpdateMaSanPham.getText().toString().trim();
                String ten = etUpdateTenSanPham.getText().toString().trim();
                String gia = etUpdateGiaSanPham.getText().toString().trim();
                String hansd = etUpdateHanSuDung.getText().toString().trim();
                String trongluong = etUpdateTrongLuong.getText().toString().trim();
                byte[] img = convertToArrayByte(imgUpdateSanPham);
                Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                imgUpdateSanPham.setImageBitmap(bitmap);
                String malsp = maLSP;
                if(ma.equals("")||ten.equals("")||gia.equals("")||hansd.equals("")||trongluong.equals("")||malsp.equals("")){
                    return;
                }else{
                    SanPham sanPham = new SanPham(ma, ten, gia, hansd, trongluong, img, malsp);
                    hienThiDialogSuaSP(sanPham, id);
                }
            }
        });
    }

    private void hienThiDialogSuaSP(SanPham sanPham, int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua_sanpham);
        dialog.setCanceledOnTouchOutside(false);
        Button btnUpdateAccept = dialog.findViewById(R.id.btnUpdateSPAccept);
        Button btnUpdateUnAccept = dialog.findViewById(R.id.btnUpdateSPUnAccept);
        btnUpdateAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new database(getApplicationContext());
                database.UpdateDataSanPham(sanPham, id);
                if (id == -1) {
                    return;
                } else {
                    Intent newIntent = new Intent(SuaSanPhamActivity.this, QuanLySanPhamActivity.class);
                    startActivity(newIntent);
                    Toast.makeText(SuaSanPhamActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
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

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgUpdateSanPham.setImageBitmap(bitmap);
            }
        }
        SuaSanPhamActivity.super.onActivityResult(requestCode, resultCode, data);
    }
    private byte[] convertToArrayByte(ImageView imageView){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void addControls() {
        btnUpdateSP = findViewById(R.id.btnUpdateSP);
        etUpdateMaSanPham = findViewById(R.id.etUpdateMaSanPham);
        etUpdateTenSanPham = findViewById(R.id.etUpdateTenSanPham);
        etUpdateGiaSanPham = findViewById(R.id.etUpdateGiaSanPham);
        etUpdateHanSuDung = findViewById(R.id.etUpdateHanSuDung);
        etUpdateTrongLuong = findViewById(R.id.etUpdateTrongLuong);
        imgUpdateSanPham = findViewById(R.id.imgUpdateSanPham);
        spinnerUpdate = findViewById(R.id.spinnerUpdate);
    }

    private void hienThiDuLieuSpinner() {
        arrSanPham = new ArrayList<>();
        database = new database(getApplicationContext());
        Cursor cursor = database.getDataLoaiSanPham();
        while (cursor.moveToNext()) {
            String ma = cursor.getString(1);

            arrSanPham.add(ma);

        }
        adapter = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arrSanPham
                );
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spinnerUpdate.setAdapter(adapter);
        spinnerUpdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLSP = (String) arrSanPham.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void hienThiDuLieu() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        String ma = intent.getStringExtra("ma");
        String ten = intent.getStringExtra("ten");
        String gia = intent.getStringExtra("gia");
        String hansd = intent.getStringExtra("hansd");
        String trongLuong = intent.getStringExtra("trongluong");
        byte[] img = intent.getByteArrayExtra("img");
        String maLSP = intent.getStringExtra("maLSP");

        etUpdateMaSanPham.setText(ma);
        etUpdateTenSanPham.setText(ten);
        etUpdateGiaSanPham.setText(gia);
        etUpdateHanSuDung.setText(hansd);
        etUpdateTrongLuong.setText(trongLuong);
        int index = selectSpinnerValue(arrSanPham, maLSP);
        spinnerUpdate.setSelection(index, true);
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        imgUpdateSanPham.setImageBitmap(bitmap);
    }

    private int selectSpinnerValue(ArrayList<String> arrSanPham, String myString) {
        int index = 0;
        for (int i = 0; i < arrSanPham.size(); i++) {
            if (arrSanPham.get(i).equals(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SuaSanPhamActivity.this, QuanLySanPhamActivity.class);
        startActivity(intent);
    }
}