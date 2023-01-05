/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.SanPham;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class SanPhamRepository {

    public ArrayList<SanPham> getAllSP() {
        ArrayList<SanPham> listsp = new ArrayList<>();
        try {
            String select = "SELECT * FROM  SanPham where DaXoa = 0";
            PreparedStatement ps = DBContext.getConnection().prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt(1);
                String tenSp = rs.getString(2);
                long donGia = rs.getLong(3);
                boolean trangThai = rs.getInt(4) == 1;
                String moTa = rs.getString(5);
                String anh = rs.getString(6);
                int maDanhMuc = rs.getInt(8);
                listsp.add(new SanPham(ma, tenSp, donGia, trangThai, moTa, anh, maDanhMuc));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return listsp;
    }

    public ArrayList<SanPham> timKiemSP(String values) {
        String select = null;
        int ID = 0;
        try {
            ID = Integer.parseInt(values);
            select = "SELECT * FROM dbo.SanPham WHERE DaXoa = 0\n"
                    + "AND MaSP = " + values + " OR TenSP LIKE N'" + values + "'";
        } catch (Exception e) {
            select = "SELECT * FROM dbo.SanPham WHERE DaXoa = 0\n"
                    + "AND TenSP LIKE N'%" + values + "%'";
        }
        ArrayList<SanPham> listsp = new ArrayList<>();
        try {
            PreparedStatement ps = DBContext.getConnection().prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt(1);
                String tenSp = rs.getString(2);
                long donGia = rs.getLong(3);
                boolean trangThai = rs.getInt(4) == 1;
                String moTa = rs.getString(5);
                String anh = rs.getString(6);
                int maDanhMuc = rs.getInt(8);
                listsp.add(new SanPham(ma, tenSp, donGia, trangThai, moTa, anh, maDanhMuc));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return listsp;
    }

    public String getTenDM(int maDanhMuc) {
        try {
            String select = "SELECT TenDanhMuc FROM dbo.DanhMuc WHERE MaDanhMuc = ?";
            PreparedStatement ps = DBContext.getConnection().prepareStatement(select);
            ps.setInt(1, maDanhMuc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Integer getMaDM(String tenDM) {
        try {
            String select = "SELECT MaDanhMuc FROM dbo.DanhMuc WHERE TenDanhMuc = ?";
            PreparedStatement ps = DBContext.getConnection().prepareStatement(select);
            ps.setString(1, tenDM);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getDanhMuc() {
        try {
            String query = "SELECT TenDanhMuc FROM dbo.DanhMuc where DaXoa = 0";
            ArrayList<String> list = new ArrayList<>();
            Connection con = DBContext.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void insertSP(SanPham sp) {
        try {
            String query = "INSERT INTO dbo.SanPham (TenSP, DonGia,TrangThai, Mota, UrlAnh,MaDanhMuc)\n"
                    + "VALUES (?,?,?,?,?,?)";
            Connection con = DBContext.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, sp.getTenSanPham());
            ps.setDouble(2, sp.getDonGia());
            ps.setInt(3, sp.isTrangThai() ? 1 : 0);
            ps.setString(4, sp.getMota());
            ps.setString(5, sp.getAnh());
            ps.setInt(6, sp.getMaDanhMuc());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateSP(SanPham sp) {
        try {
            String query = "UPDATE [dbo].[SanPham]\n"
                    + "   SET [TenSP] = ?\n"
                    + "      ,[DonGia] = ?\n"
                    + "      ,[TrangThai] = ?\n"
                    + "      ,[Mota] = ?\n"
                    + "      ,[UrlAnh] = ?\n"
                    + "      ,[MaDanhMuc] = ?\n"
                    + "		WHERE MaSP = ?";
            Connection con = DBContext.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, sp.getTenSanPham());
            ps.setDouble(2, sp.getDonGia());
            ps.setInt(3, sp.isTrangThai() ? 1 : 0);
            ps.setString(4, sp.getMota());
            ps.setString(5, sp.getAnh());
            ps.setInt(6, sp.getMaDanhMuc());
            ps.setInt(7, sp.getMaSanPham());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteSP(int maSP) {
        try {
            String query = "DELETE [dbo].[SanPham]\n"
                    + "   WHERE MaSP = ?";
            Connection con = DBContext.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, maSP);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteSPER(int maSP) {
        try {
            String query = "UPDATE [dbo].[SanPham] SET DaXoa = 1\n"
                    + "   WHERE MaSP = ?";
            Connection con = DBContext.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, maSP);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
