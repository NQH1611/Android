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
import vn.edu.stu.quanlysanpham.model.LoaiSanPham;
import vn.edu.stu.quanlysanpham.model.SanPham;

public class ThemSanPhamActivity extends AppCompatActivity {
    Spinner spinner;
    Button btnThemSanPham;
    EditText etMaSanPham,etTenSanPham, etGiaSanPham, etHanSuDung, etTrongLuong;
    ImageView imgSanPham;
    String maLSP = "";
    ArrayList<String> arrSanPham;
    final int REQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    vn.edu.stu.quanlysanpham.database.database database;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        addControls();
        hienThiDuLieuSpinner();
        addEvents();
    }

    private void hienThiDuLieuSpinner() {
        arrSanPham=new ArrayList<>();
        database = new database(getApplicationContext());
        Cursor cursor = database.getDataLoaiSanPham();
        while (cursor.moveToNext()){
            String ma = cursor.getString(1);

            arrSanPham.add(ma);

        }
        adapter =new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arrSanPham
                );
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLSP = (String) arrSanPham.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addControls() {
        btnThemSanPham = findViewById(R.id.btnThemSanPham);
        etMaSanPham = findViewById(R.id.etMaSanPham);
        etTenSanPham = findViewById(R.id.etTenSanPham);
        etGiaSanPham = findViewById(R.id.etGiaSanPham);
        etHanSuDung = findViewById(R.id.etHanSuDung);
        etTrongLuong = findViewById(R.id.etTrongLuong);
        imgSanPham = findViewById(R.id.imgSanPham);
        spinner = findViewById(R.id.spinner);
    }

    private void addEvents() {
        imgSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
        btnThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSP = etMaSanPham.getText().toString().trim();
                String tenSP = etTenSanPham.getText().toString().trim();
                String giaSP = etGiaSanPham.getText().toString().trim();
                String hanSD = etHanSuDung.getText().toString().trim();
                String trongLuong = etTrongLuong.getText().toString().trim();
                byte[] image = convertToArrayByte(imgSanPham);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imgSanPham.setImageBitmap(bitmap);
                SanPham sanPham = new SanPham(maSP, tenSP, giaSP, hanSD, trongLuong, image, maLSP);
                if(maSP.equals("")||tenSP.equals("")||giaSP.equals("")||hanSD.equals("")||trongLuong.equals("")){
                    Toast.makeText(ThemSanPhamActivity.this, "Nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    hienThiDialogThemSanPham(sanPham);
                }

            }
        });

    }
    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }
    private  void hienThiDialogThemSanPham(SanPham sanPham){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_sanpham);
        dialog.setCanceledOnTouchOutside(false);
        Button btnAccept = dialog.findViewById(R.id.btnAddSPAccept);
        Button btnUnAccept = dialog.findViewById(R.id.btnAddSPUnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new database(getApplicationContext());
                database.insertDataSanPham(sanPham);
                Intent intent = new Intent(ThemSanPhamActivity.this, QuanLySanPhamActivity.class);
                startActivity(intent);
                Toast.makeText(ThemSanPhamActivity.this, "Thành Công", Toast.LENGTH_SHORT).show();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CHOOSE_PHOTO){
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgSanPham.setImageBitmap(bitmap);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }else  if(requestCode ==  REQUEST_TAKE_PHOTO){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgSanPham.setImageBitmap(bitmap);
            }
        }
        ThemSanPhamActivity.super.onActivityResult(requestCode, resultCode, data);
    }
    private byte[] convertToArrayByte(ImageView imageView){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ThemSanPhamActivity.this, QuanLySanPhamActivity.class);
        startActivity(intent);
    }
}