/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.NhanVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHAM DAC THANG
 */
public class NhanVienRepository {

    DBContext dbreporisitory;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pst = null;

    List<NhanVien> listnv = new ArrayList<>();

    public NhanVienRepository() {
    }

    public List<NhanVien> getListNV() {
        String select = " SELECT * FROM  NhanVien WHERE DAXOA = 0";
        listnv = new ArrayList<>();
        try {
            st = dbreporisitory.getConnection().createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                listnv.add(new NhanVien(rs.getInt(1),
                        rs.getNString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getNString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11)
                ));
            }

        } catch (Exception ex) {

            Logger.getLogger(NhanVienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listnv;

    }

    public String THEMNVDB(NhanVien nv) {
        try {
            String insert = "INSERT INTO [dbo].[NhanVien]\n"
                    + "           ([TenNV]\n"
                    + "           ,[GioiTinh]\n"
                    + "           ,[ChucVu]\n"
                    + "           ,[Email]\n"
                    + "           ,[QueQuan]\n"
                    + "           ,[NgaySinh]\n"
                    + "           ,[Luong]\n"
                    + "           ,[TaiKhoan]\n"
                    + "           ,[MatKhau])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?)";
            pst = dbreporisitory.getConnection().prepareStatement(insert);
            pst.setNString(1, nv.getTennv());
            pst.setInt(2, nv.getGioitinh());
            pst.setInt(3, nv.getChucvu());
            pst.setString(4, nv.getEmail());
            pst.setString(5, nv.getQuequan());
            pst.setString(6, nv.getNgaysinh());
            pst.setInt(7, nv.getLuong());
            pst.setString(8, nv.getTaikhoan());
            pst.setString(9, nv.getMatkhau());
            pst.executeUpdate();
            return "Thêm dữ liệu thành công";
        } catch (Exception ex) {
            Logger.getLogger(NhanVienRepository.class.getName()).log(Level.SEVERE, null, ex);
            return "Thêm dữ liệu thất bại";
        }
    }

    public String CAPNHATNVDB(NhanVien nv) {
        try {
            String update = "update NhanVien set TenNV =?,GioiTinh =?,ChucVu =?,Email =?,QueQuan =?,NgaySinh =?,Luong =?,TaiKhoan =?,MatKhau =? where   MaNV=?";
            pst = dbreporisitory.getConnection().prepareStatement(update);
            pst.setNString(1, nv.getTennv());
            pst.setInt(2, nv.getGioitinh());
            pst.setInt(3, nv.getChucvu());
            pst.setNString(4, nv.getEmail());
            pst.setNString(5, nv.getQuequan());
            pst.setNString(6, nv.getNgaysinh());
            pst.setInt(7, nv.getLuong());
            pst.setNString(8, nv.getTaikhoan());
            pst.setNString(9, nv.getMatkhau());
            pst.setInt(10, nv.getManv());

            pst.executeUpdate();
            return "Sửa dữ liệu thành công";
        } catch (Exception ex) {
            Logger.getLogger(NhanVienRepository.class.getName()).log(Level.SEVERE, null, ex);
            return "Sửa dữ liệu thất bại";
        }
    }

    public String deleteToDbNV(int mnv) {
        try {
            String delete = "UPDATE  NhanVien  set DaXoa=?  WHERE MaNV = ?";
            pst = dbreporisitory.getConnection().prepareStatement(delete);
            pst.setInt(1, 1);
            pst.setInt(2, mnv);
            pst.executeUpdate();
            return "Bạn đã xóa thành công";
        } catch (Exception ex) {

            return "Xóa dữ liệu thất bại ";
        }

    }

    // KIEM TRA ma sv co ko
    public boolean ktmasv(int manv) {
        for (int i = 0; i < listnv.size(); i++) {
            if (listnv.get(i).getManv() == manv) {
                return true;
            }
        }

        return false;
    }

    public boolean doiMatKhau(int maNV, String matKhauMoi) {
        String sql = "update NhanVien set MatKhau = ? where MaNV = ?";
        int result = JDBCHelper.excuteUpdate(sql, matKhauMoi, maNV);
        if (result > 0) {
            return true;
        }
        return false;
    }

    public NhanVien timNVTheoMa(int maNV) {
        String sql = "select * from NhanVien where maNV = ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, maNV);
        try {
            while (rs.next()) {
                return new NhanVien(rs.getInt(1),
                        rs.getNString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getNString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11)
                );

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
