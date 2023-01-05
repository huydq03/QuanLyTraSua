/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author PHAM DAC THANG
 */
public class NhanVien {
  public int manv;
    public String tennv;
    public int gioitinh,chucvu;
    public  String email;
    public  String quequan;
    public String ngaysinh;
    public int luong;
    public String taikhoan;
    public String matkhau;
    public int DaXoa;
    public NhanVien() {
    }

    public NhanVien(int manv, String tennv, int gioitinh, int chucvu, String email, String quequan, String ngaysinh, int luong, String taikhoan, String matkhau, int DaXoa) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.chucvu = chucvu;
        this.email = email;
        this.quequan = quequan;
        this.ngaysinh = ngaysinh;
        this.luong = luong;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.DaXoa = DaXoa;
    }

    public NhanVien( String tennv, int gioitinh, int chucvu, String email, String quequan, String ngaysinh, int luong, String taikhoan, String matkhau) {
       
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.chucvu = chucvu;
        this.email = email;
        this.quequan = quequan;
        this.ngaysinh = ngaysinh;
        this.luong = luong;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
    }

    public NhanVien(int manv, String tennv, int gioitinh, int chucvu, String email, String quequan, String ngaysinh, int luong, String taikhoan, String matkhau) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.chucvu = chucvu;
        this.email = email;
        this.quequan = quequan;
        this.ngaysinh = ngaysinh;
        this.luong = luong;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public int getDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(int DaXoa) {
        this.DaXoa = DaXoa;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getChucvu() {
        return chucvu;
    }

    public void setChucvu(int chucvu) {
        this.chucvu = chucvu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
    
    
    
    public String chuyengioitinh(){
    if(this.gioitinh==0){
    return "NAM";
    
    }else{
    return "NỮ";
    }
    }
    
        public String chuyenchucvu(){
    if(this.chucvu==0){
    return "Quản Lý";
    
    }else{
    return "Nhân Viên";
    }
    }
        
        
        public String  chuyenngaysinh(){
        
  
      try {
            Date date=new SimpleDateFormat("yyyy-MM-dd").parse(this.ngaysinh);
             SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
              String ns = format.format(date);
           return ns;
       } catch (Exception e) {
           return "";
        }

  
        }
}
