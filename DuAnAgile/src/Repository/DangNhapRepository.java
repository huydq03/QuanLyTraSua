/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class DangNhapRepository {
//    public static void main(String[] args) {
//        User u = getUser("admin","12345");
//        if (u==null) {
//            System.out.println("Không có");
//        }else{
//            System.out.println(u);
//        }
//    }

    public User getUser(String... values) {
        try {
            User u = null;
            Connection con = DBContext.getConnection();
            String query = "SELECT MaNV,TaiKhoan,MatKhau,ChucVu FROM dbo.NhanVien \n"
                    + "WHERE TaiKhoan = ? AND MatKhau = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maNv = rs.getInt(1);
                String taiKhoan = rs.getString(2);
                String matKhau = rs.getString(3);
                boolean chucVu = rs.getInt(4) == 1;
                u = new User(maNv, taiKhoan, matKhau, chucVu);
            }
            return u;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getTaiKhoan(String... values) {
        try {
            Connection con = DBContext.getConnection();
            String query = "SELECT Email,TaiKhoan FROM dbo.NhanVien "
                    + "WHERE Email = ? AND TaiKhoan = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ResultSet rs = ps.executeQuery();
            String email = null;
            String taiKhoan = null;
            while (rs.next()) {
                email = rs.getString(1);
                taiKhoan = rs.getString(2);
            }
            if (email != null || taiKhoan != null) {
                return taiKhoan;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void updatePass(String taiKhoan, String pass) {
        try {
            Connection con = DBContext.getConnection();
            String query = "UPDATE dbo.NhanVien\n"
                    + "SET MatKhau = ? WHERE TaiKhoan = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pass);
            ps.setString(2, taiKhoan);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
