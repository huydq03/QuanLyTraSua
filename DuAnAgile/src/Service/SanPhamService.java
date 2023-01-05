/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.SanPham;
import Repository.SanPhamRepository;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class SanPhamService {
    SanPhamRepository sanPhamRepo ;

    public SanPhamService() {
        sanPhamRepo = new SanPhamRepository();
    }
    public ArrayList<SanPham> getAllSP(){
        return sanPhamRepo.getAllSP();
    }
    public String getTenDM(int maDanhMuc){
        return sanPhamRepo.getTenDM(maDanhMuc);
    }
    public Integer getMaDM(String tenDM){
        return sanPhamRepo.getMaDM(tenDM);
    }
    public void insertSP(SanPham sp){
        sanPhamRepo.insertSP(sp);
    }
    public void updateSP(SanPham sp){
        sanPhamRepo.updateSP(sp);
    }
    public void deleteSP(int masp){
        sanPhamRepo.deleteSP(masp);
    }
    public void deleteSPER(int masp){
        sanPhamRepo.deleteSPER(masp);
    }
    public ArrayList<String> getDanhMuc(){
        return sanPhamRepo.getDanhMuc();
    }
    public ArrayList<SanPham> timKiemSP(String values){
        return sanPhamRepo.timKiemSP(values);
    }
}
