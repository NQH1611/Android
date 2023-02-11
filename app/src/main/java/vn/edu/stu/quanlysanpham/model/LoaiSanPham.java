package vn.edu.stu.quanlysanpham.model;

public class LoaiSanPham {
    private int id;
    private String maLSP;
    private String tenLSP;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String maLSP, String tenLSP) {
        this.maLSP = maLSP;
        this.tenLSP = tenLSP;
    }

    public LoaiSanPham(int id, String maLSP, String tenLSP) {
        this.id = id;
        this.maLSP = maLSP;
        this.tenLSP = tenLSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(String maLSP) {
        this.maLSP = maLSP;
    }

    public String getTenLSP() {
        return tenLSP;
    }

    public void setTenLSP(String tenLSP) {
        this.tenLSP = tenLSP;
    }
}
