/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HoaDon;
import Repository.HoaDonRespository;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonService {

    private HoaDonRespository hoaDonRespository;
    private int maKhachHang;

    public HoaDonService() {
        hoaDonRespository = new HoaDonRespository();
    }

    public List<HoaDon> layTatCaDanhSachHoaDon() {
        return this.hoaDonRespository.layTatCaDanhSachHoaDon();
    }

    public List<HoaDon> timKiemHoaDonTheoMaKH(int maKhachHang) {
        return this.hoaDonRespository.timKiemHoaDonTheoMaKH(maKhachHang);
    }

    public List<HoaDon> timKiemHoaDonTheoMaHD(int maHoaDon) {
        return this.hoaDonRespository.timKiemHoaDonTheoMaHD(maHoaDon);
    }

}
