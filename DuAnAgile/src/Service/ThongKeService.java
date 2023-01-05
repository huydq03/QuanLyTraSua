/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.BieuDoDanhThu;
import Model.ThongKeDoanhThu;
import Model.ThongKeSanPham;
import Repository.ThongKeRespository;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeService {

    private ThongKeRespository thongKeRespository;

    public ThongKeService() {
        thongKeRespository = new ThongKeRespository();
    }

    public List<ThongKeDoanhThu> layDanhSachHoaDonTheoNgay(String dateBeginString, String dateEndString) {
        return this.thongKeRespository.layDanhSachHoaDonTheoNgay(dateBeginString, dateEndString);
    }

    public List<Model.ThongKeSanPham> layDanhSachSPTheoNgay(String dateBeginString, String dateEndString) {
        return this.thongKeRespository.layDanhSachSPTheoNgay(dateBeginString, dateEndString);
    }

    public List<ThongKeDoanhThu> getAllDoanhThu() {
        return this.thongKeRespository.getAllDoanhThu();
    }

    public List<ThongKeSanPham> getAllSanPham() {
        return this.thongKeRespository.getAllSanPham();
    }

    public List<ThongKeSanPham> laySanPhamKhongBanDuoc(String dateBeginString, String dateEndString) {
        return this.thongKeRespository.laySanPhamKhongBanDuoc(dateBeginString, dateEndString);
    }

    public List<ThongKeSanPham> getAllSanPhamKhongBanDuoc() {
        return this.thongKeRespository.getAllSanPhamKhongBanDuoc();
    }

    public List<BieuDoDanhThu> layDoanhThuThang() {
        return this.thongKeRespository.layDoanhThuThang();
    }

}
