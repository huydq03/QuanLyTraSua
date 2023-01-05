/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class HoaDonChiTiet {

    private int maHD;
    private int maSP;
    private int soLuongMua;
    private double donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHD, int maSP, int soLuongMua, double donGia) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuongMua = soLuongMua;
        this.donGia = donGia;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getTongTien() {
        return this.donGia * this.soLuongMua;
    }
}
