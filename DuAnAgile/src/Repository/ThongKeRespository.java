/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.BieuDoDanhThu;
import Model.ThongKeDoanhThu;
import Model.ThongKeSanPham;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ThongKeRespository {

    private JDBCHelper helper;
    private List<ThongKeDoanhThu> listHoaDonTheoNgay;

    public ThongKeRespository() {
        helper = new JDBCHelper();
        listHoaDonTheoNgay = new ArrayList<>();
    }

    public List<ThongKeDoanhThu> layDanhSachHoaDonTheoNgay(String dateBeginString, String dateEndString) {
        //tạo view ThongKeDoanhThu trong sql
        String sql = "SELECT * FROM ThongKeDoanhThu WHERE NgayTao BETWEEN ? AND ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, dateBeginString, dateEndString);
        List<ThongKeDoanhThu> listTK = new ArrayList<>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    ThongKeDoanhThu tk = layThongTin(rs);
                    if (tk != null) {
                        listTK.add(tk);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return listTK;
    }

    private ThongKeDoanhThu layThongTin(ResultSet rs) {
        if (rs != null) {
            try {
                Date date = rs.getDate("NgayTao");
                int tongHD = rs.getInt("TongHoaDon");
                double tongtien = rs.getDouble("TongDoanhThuNgay");
                ThongKeDoanhThu tk = new ThongKeDoanhThu(date, tongHD, tongtien);
                return tk;
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeRespository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<ThongKeSanPham> layDanhSachSPTheoNgay(String dateBeginString, String dateEndString) {
        String sql = "select TenSP, SUM(TongSoLuong) 'TongSoLuongSP' from ThongKeSanPham WHERE NgayTao between ? and ? group by TenSP";
        int stt = 0;
        List<ThongKeSanPham> listSP = new ArrayList<>();
        ResultSet rs = JDBCHelper.excuteQuery(sql, dateBeginString, dateEndString);
        if (rs != null) {
            try {
                while (rs.next()) {
                    stt++;
                    String tenSP = rs.getString("TenSP");
                    int soLuong = rs.getInt("TongSoLuongSP");
                    ThongKeSanPham tksp = new ThongKeSanPham(stt, tenSP, soLuong);
                    listSP.add(tksp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeRespository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listSP;
    }

    public List<ThongKeDoanhThu> getAllDoanhThu() {
        String sql = "select * from ThongKeDoanhThu";
        List<ThongKeDoanhThu> listDoanhThu = new ArrayList<>();
        ResultSet rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                ThongKeDoanhThu tkdt = layThongTin(rs);
                if (tkdt != null) {
                    listDoanhThu.add(tkdt);
                }
            }
            return listDoanhThu;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeRespository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ThongKeSanPham> getAllSanPham() {
        String sql = "select TenSP, SUM(TongSoLuong) 'TongSoLuongSP' from ThongKeSanPham group by TenSP";
        List<ThongKeSanPham> listTKSP = new ArrayList<>();
        ResultSet rs = JDBCHelper.excuteQuery(sql);
        int stt = 0;
        try {
            while (rs.next()) {
                stt++;
                String tenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("TongSoLuongSP");
                ThongKeSanPham tksp = new ThongKeSanPham(stt, tenSP, soLuong);
                listTKSP.add(tksp);
            }
            return listTKSP;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeRespository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ThongKeSanPham> laySanPhamKhongBanDuoc(String dateBeginString, String dateEndString) {
        String sql = "select * from SanPham where TenSP not in (select TenSP from ThongKeSanPham WHERE NgayTao between ? and ?)";
        int stt = 0;
        List<ThongKeSanPham> listSP = new ArrayList<>();
        ResultSet rs = JDBCHelper.excuteQuery(sql, dateBeginString, dateEndString);
        if (rs != null) {
            try {
                while (rs.next()) {
                    stt++;
                    String tenSP = rs.getString("TenSP");
                    ThongKeSanPham tksp = new ThongKeSanPham(stt, tenSP, 0);
                    listSP.add(tksp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeRespository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listSP;
    }

    public List<ThongKeSanPham> getAllSanPhamKhongBanDuoc() {
        String sql = "select * from SanPham where TenSP not in (select TenSP from ThongKeSanPham)";
        int stt = 0;
        List<ThongKeSanPham> listSP = new ArrayList<>();
        ResultSet rs = JDBCHelper.excuteQuery(sql);
        if (rs != null) {
            try {
                while (rs.next()) {
                    stt++;
                    String tenSP = rs.getString("TenSP");
                    ThongKeSanPham tksp = new ThongKeSanPham(stt, tenSP, 0);
                    listSP.add(tksp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeRespository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listSP;
    }

    public List<BieuDoDanhThu> layDoanhThuThang() {
        String sql = "select * from ThongKeDoanhThuThang";
        List<BieuDoDanhThu> listDoanhThuThang = new ArrayList<>();
        ResultSet rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                int soThang = rs.getInt("Thang");
                double tongDoanhThu = rs.getDouble("TongDoanhThu");
                BieuDoDanhThu bddt = new BieuDoDanhThu(soThang, tongDoanhThu);
                listDoanhThuThang.add(bddt);
            }
            return listDoanhThuThang;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeRespository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
