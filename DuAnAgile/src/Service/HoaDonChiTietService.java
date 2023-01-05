/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HoaDonChiTiet;
import Model.SanPham;
import Repository.HoaDonChiTietRespository;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietService {
    private HoaDonChiTietRespository hoaDonCTRepo;

    public HoaDonChiTietService() {
        hoaDonCTRepo = new HoaDonChiTietRespository();
    }
    
    public List<HoaDonChiTiet> timHDCTTheoMaHD(int maHD) {
        return this.hoaDonCTRepo.timHDCTTheoMaHD(maHD);
    }

    public SanPham timSanPhamTheoMa(int maSP) {
        return this.hoaDonCTRepo.timSanPhamTheoMa(maSP);
    }
    public void insertBH(ArrayList<Vector> list, Integer tongTien, Integer maKH, Integer maKM, int maNV, Integer soTienGiam, String ghiChu) {
        hoaDonCTRepo.insertBH(list, tongTien, maKH, maKM, maNV, soTienGiam, ghiChu);
    }
    public void updateDiemTV(int maKH) {
        hoaDonCTRepo.updateDiemTV(maKH);
    }
    
}
