/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class User implements Serializable{
    private int maNv;
    private String taiKhoan;
    private String matKhau;
    private boolean nhoMatKhau = false;
    private boolean chucVu ;

    public User() {
    }

    public User(int maNv, String taiKhoan, String matKhau, boolean chucVu) {
        this.maNv = maNv;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }


    public int getMaNv() {
        return maNv;
    }

    public void setMaNv(int maNv) {
        this.maNv = maNv;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isNhoMatKhau() {
        return nhoMatKhau;
    }

    public void setNhoMatKhau(boolean nhoMatKhau) {
        this.nhoMatKhau = nhoMatKhau;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    @Override
    public String toString() {
        return "User{" + "maNv=" + maNv + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", nhoMatKhau=" + nhoMatKhau + ", chucVu=" + chucVu + '}';
    }
    
    
    
}
