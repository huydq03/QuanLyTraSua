/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.HoaDonChiTiet;
import Model.SanPham;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietRespository {

    public List<HoaDonChiTiet> timHDCTTheoMaHD(int maHD) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHD = ?";
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        ResultSet rs = JDBCHelper.excuteQuery(sql, maHD);
        try {
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet(rs.getInt("MaHD"),
                        rs.getInt("MaSP"),
                        rs.getInt("SoLuongMua"),
                        rs.getDouble("DonGia"));
                listHDCT.add(hdct);
            }
            return listHDCT;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonChiTietRespository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SanPham timSanPhamTheoMa(int maSP) {
        String sql = "SELECT * FROM SanPham WHERE MaSP = ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, maSP);
        try {
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getInt("MaSP"),
                        rs.getString("TenSP"),
                        rs.getLong("DonGia"),
                        rs.getBoolean("TrangThai"),
                        rs.getString("Mota"),
                        rs.getString("urlAnh"),
                        rs.getInt("maDanhMuc"));
                return sp;
            }

        } catch (SQLException ex) {
            Logger.getLogger(HoaDonChiTietRespository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertBH(ArrayList<Vector> list, Integer tongTien, Integer maKH, Integer maKM, int maNV, Integer soTienGiam, String ghiChu) {
        try {
            String query = "INSERT INTO [dbo].[HoaDon]\n"
                    + "           ([NgayTao]\n"
                    + "           ,[TongTien]\n"
                    + "           ,[MaKH]\n"
                    + "           ,[MaKM]\n"
                    + "           ,[MaNV]\n"
                    + "           ,[SoTienGiam],GhiChu)\n"
                    + "     VALUES\n"
                    + "           (GETDATE()," + tongTien + "," + maKH + "," + maKM + "," + maNV + "," + soTienGiam + ",N'" + ghiChu + "')";
            PreparedStatement ps = DBContext.getConnection().prepareStatement(query);
            ps.executeUpdate();
            int maHoaDon = getMaHD();
            String queryHDCT = "INSERT INTO [dbo].[HoaDonChiTiet]\n"
                    + "           ([MaHD]\n"
                    + "           ,[MaSP]\n"
                    + "           ,[SoLuongMua]\n"
                    + "           ,[DonGia])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            for (Vector x : list) {
                ps = DBContext.getConnection().prepareStatement(queryHDCT);
                ps.setInt(1, maHoaDon);
                ps.setInt(2, Integer.parseInt(x.get(0).toString()));
                ps.setInt(3, Integer.parseInt(x.get(2).toString()));
                ps.setInt(4, Integer.parseInt(x.get(3).toString()));
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Integer getMaHD() {
        try {
            String query = "SELECT TOP 1 MaHD FROM hoaDon ORDER BY MAHD DESC";
            PreparedStatement ps = DBContext.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void updateDiemTV(int maKH) {
        try {
            String query = "UPDATE dbo.KhachHang \n"
                    + "	SET DiemThanhVien = DiemThanhVien +1000 WHERE MaKH = ?";
            PreparedStatement ps = DBContext.getConnection().prepareStatement(query);
            ps.setInt(1, maKH);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
