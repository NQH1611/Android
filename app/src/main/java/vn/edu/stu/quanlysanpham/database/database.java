package vn.edu.stu.quanlysanpham.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import vn.edu.stu.quanlysanpham.model.LoaiSanPham;
import vn.edu.stu.quanlysanpham.model.SanPham;

public class database extends SQLiteOpenHelper {
    private static String DATABASE_NAME ="quan_ly_san_pham";

    private static String BANG_SP = "SanPham";
    private static String ID_SP = "IdSanPham";
    private static String HINH="Hinh";
    private static String MA_SP="MaSP";
    private static String TEN_SP="TenSP ";
    private static String GIA ="Gia";
    private static String HAN_SD="HanSD";
    private static String TRONG_LUONG = "TrongLuong";
    private static int VERSION=1;

    private static String BANG_LSP="LoaiSanPham";
    private static String ID_LSP = "IdLoaiSanPham";
    private static String MA_LSP="MaLSP ";
    private static String TEN_LSP="TenLSP";

    private String SQLQuery1 = " CREATE TABLE " + BANG_LSP + " (" + ID_LSP + " integer primary key autoincrement," + MA_LSP + " TEXT, " + TEN_LSP + " TEXT)";

    private String SQLQuery2=" CREATE TABLE " + BANG_SP + " ( " + ID_SP + " integer primary key autoincrement," + MA_SP + " TEXT, " + TEN_SP + " TEXT, " + GIA + " TEXT, " + HAN_SD + " TEXT, " + TRONG_LUONG + " TEXT, " + HINH + " Blob, " + MA_LSP + " INTEGER, FOREIGN KEY ( " + MA_LSP + " ) REFERENCES " + BANG_LSP + " ( " + MA_LSP + " ))";

    public database(@Nullable Context context) {
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery1);
        sqLiteDatabase.execSQL(SQLQuery2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertDataSanPham(SanPham sanPham){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_SP,sanPham.getMaSp());
        values.put(TEN_SP,sanPham.getTenSP());
        values.put(GIA,sanPham.getGia());
        values.put(HAN_SD,sanPham.getHanSD());
        values.put(TRONG_LUONG,sanPham.getTrongLuong());
        values.put(HINH,sanPham.getHinh());
        values.put(MA_LSP, sanPham.getMaLSP());
        db.insert(BANG_SP, null, values);
        db.close();
    }

    public boolean UpdateDataSanPham(SanPham sanPham, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_SP, sanPham.getMaSp());
        values.put(TEN_SP,sanPham.getTenSP());
        values.put(GIA,sanPham.getGia());
        values.put(HAN_SD,sanPham.getHanSD());
        values.put(HINH,sanPham.getHinh());
        values.put(TRONG_LUONG,sanPham.getTrongLuong());
        values.put(MA_LSP, sanPham.getMaLSP());
        db.update(BANG_SP,values, ID_SP + "=" + id, null);
        return true;
    }
    public Cursor getDataSanPham(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BANG_SP, null);
        return cursor;
    }
    public Cursor getSanPhamByID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SanPham WHERE " + ID_SP +" = " + id, null);
        return cursor;
    }
    public int DeleteSanPham(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(BANG_SP,ID_SP + "=" + id, null);
        return res;
    }
    public int deleteSanPhamByMaLSP(String maLSP){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(BANG_SP,MA_LSP + "= ?", new String[]{maLSP});
        return res;
    }
    public Cursor getSanPhamByMaLSP(String maLSP){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BANG_SP + " WHERE " + MA_LSP + "= ?", new String[]{maLSP});
        return cursor;
    }
    public void insertDataLoaiSanPham(LoaiSanPham loaiSanPham){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_LSP,loaiSanPham.getMaLSP());
        values.put(TEN_LSP,loaiSanPham.getTenLSP());
        db.insert(BANG_LSP, null, values);
        db.close();
    }

    public boolean UpdateDataLoaiSanPham(LoaiSanPham loaiSanPham, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_LSP, loaiSanPham.getMaLSP());
        values.put(TEN_LSP,loaiSanPham.getTenLSP());
        db.update(BANG_LSP,values, ID_LSP + "=" + id, null);
        return true;
    }
    public Cursor getDataLoaiSanPham(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BANG_LSP, null);
        return cursor;
    }
    public Cursor getLoaiSanPhamById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BANG_LSP + " WHERE " + ID_LSP + " = " + id, null);
        return cursor;
    }
    public int DeleteLoaiSanPham(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(BANG_LSP,ID_LSP + "=" + id, null);
        return res;
    }


}
