/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author Admin
 */
public class ThongKeSanPham {
    private int stt;
    private String tenSP;
    private int soLuongBan;

    public ThongKeSanPham() {
    }

    public ThongKeSanPham(int stt, String tenSP, int soLuongBan) {
        this.stt = stt;
        this.tenSP = tenSP;
        this.soLuongBan = soLuongBan;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }
    
}
