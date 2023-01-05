
package Model;

public class KhachHang {
    private int maKH;
    private String tenKH;
    private int gioiTinh;
    private String sdt;
    private String diaChi;
    private int loaiKhachHang;
    private float diemThanhVien;
    private int daXoa;
    public KhachHang() {
    }

    public KhachHang(int maKH, String tenKH, int gioiTinh, String sdt, String diaChi, int loaiKhachHang, float diemThanhVien, int daXoa) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.loaiKhachHang = loaiKhachHang;
        this.diemThanhVien = diemThanhVien;
        this.daXoa = daXoa;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(int loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }

    public float getDiemThanhVien() {
        return diemThanhVien;
    }

    public void setDiemThanhVien(float diemThanhVien) {
        this.diemThanhVien = diemThanhVien;
    }

    public int getDaXoa() {
        return daXoa;
    }

    public void setDaXoa(int daXoa) {
        this.daXoa = daXoa;
    }

    
}
