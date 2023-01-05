/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.HoaDon;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class HoaDonRespository {
    
    public List<HoaDon> layTatCaDanhSachHoaDon() {
        String sql = "SELECT * FROM dbo.HoaDon ORDER BY MaHD DESC";
        List<HoaDon> listHD = new ArrayList<>();
        ResultSet rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                HoaDon hd = layThongTinHoaDon(rs);
                listHD.add(hd);
            }
            return listHD;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<HoaDon> timKiemHoaDonTheoMaKH(int maKhachHang) {
        String sql = "select * from hoadon where maKH = ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, maKhachHang);
        List<HoaDon> listHD = new ArrayList<>();
        try {
            while (rs.next()) {
                HoaDon hd = layThongTinHoaDon(rs);
                listHD.add(hd);
            }
            return listHD;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonRespository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public HoaDon layThongTinHoaDon(ResultSet rs) throws SQLException {
        HoaDon hd = new HoaDon(rs.getInt("MaHD"),
                rs.getDate("NgayTao"),
                rs.getDouble("TongTien"),
                rs.getInt("MaKH"),
                rs.getInt("MaKM"),
                rs.getInt("MaNV"), rs.getInt("SoTienGiam"));
        return hd;
    }
    
    public List<HoaDon> timKiemHoaDonTheoMaHD(int maHoaDon) {
        String sql = "SELECT * FROM HoaDon WHERE maHD = ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, maHoaDon);
        List<HoaDon> listHD = new ArrayList<>();
        try {
            while (rs.next()) {
                HoaDon hd = layThongTinHoaDon(rs);
                listHD.add(hd);
            }
            return listHD;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonRespository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
