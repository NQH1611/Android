package vn.edu.stu.quanlysanpham.model;

public class SanPham {
    private int id;
    private String maSp;
    private String tenSP;
    private String gia;
    private String hanSD;
    private String trongLuong;
    private byte[] hinh;
    private String maLSP;

    public SanPham() {
    }

    public SanPham(String maLSP) {
        this.maLSP = maLSP;
    }

    public SanPham(String maSp, String tenSP, String gia, String hanSD, String trongLuong, byte[] hinh, String maLSP) {
        this.maSp = maSp;
        this.tenSP = tenSP;
        this.gia = gia;
        this.hanSD = hanSD;
        this.trongLuong = trongLuong;
        this.hinh = hinh;
        this.maLSP = maLSP;
    }

    public SanPham(int id, String maSp, String tenSP, String gia, String hanSD, String trongLuong, byte[] hinh, String maLSP ) {
        this.id = id;
        this.maSp = maSp;
        this.tenSP = tenSP;
        this.gia = gia;
        this.hanSD = hanSD;
        this.trongLuong = trongLuong;
        this.hinh = hinh;
        this.maLSP = maLSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getHanSD() {
        return hanSD;
    }

    public void setHanSD(String hanSD) {
        this.hanSD = hanSD;
    }

    public String getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(String trongLuong) {
        this.trongLuong = trongLuong;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(String maLSP) {
        this.maLSP = maLSP;
    }
}
