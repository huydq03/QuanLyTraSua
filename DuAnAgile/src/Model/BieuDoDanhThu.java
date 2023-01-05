/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author Admin
 */
public class BieuDoDanhThu {

    private int soThang;
    private double tongDoanhThu;

    public BieuDoDanhThu() {
    }

    public BieuDoDanhThu(int soThang, double tongDoanhThu) {
        this.soThang = soThang;
        this.tongDoanhThu = tongDoanhThu;
    }

    public int getSoThang() {
        return soThang;
    }

    public void setSoThang(int soThang) {
        this.soThang = soThang;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

}
