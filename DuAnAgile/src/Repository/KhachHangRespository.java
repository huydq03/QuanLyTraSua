package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import Model.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Repository.JDBCHelper;
import java.util.Vector;

public class KhachHangRespository {

    public List<KhachHang> getAllKH() {
        List<KhachHang> kh = new ArrayList<>();
        String sql = "Select * from KhachHang where DaXoa=0";
        ResultSet rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                int maKH = rs.getInt("MaKH");
                String tenKH = rs.getString("TenKH");
                int gioiTinh = rs.getInt("GioiTinh");
                String sdt = rs.getString("SDT");
                String diaChi = rs.getString("DiaChi");
                int loaiKH = rs.getInt("LoaiKhachHang");
                float diemThanhVien = rs.getFloat("DiemThanhVien");
                int daXoa = rs.getInt("DaXoa");
                kh.add(new KhachHang(maKH, tenKH, gioiTinh, sdt, diaChi, loaiKH, diemThanhVien, daXoa));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRespository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kh;

    }

    public Integer insertKH(KhachHang kh) {
        String sql = "insert into KhachHang values (?,?,?,?,?,?,?)";
        Integer row = JDBCHelper.excuteUpdate(sql, kh.getTenKH(), kh.getGioiTinh(), kh.getSdt(), kh.getDiaChi(), kh.getLoaiKhachHang(), kh.getDiemThanhVien(), kh.getDaXoa());
        return row;
    }

    public Integer upDateKH(KhachHang kh) {
        String sql = "update KhachHang set TenKH=?,GioiTinh=?,SDT=?,DiaChi=?,LoaiKhachHang=?,DiemThanhVien=?,DaXoa=? where MaKH=?";
        Integer row = JDBCHelper.excuteUpdate(sql, kh.getTenKH(), kh.getGioiTinh(), kh.getSdt(), kh.getDiaChi(), kh.getLoaiKhachHang(), kh.getDiemThanhVien(), kh.getDaXoa(), kh.getMaKH());
        return row;
    }

    public void deleteKH(int id) {
        String sql = "update KhachHang set DaXoa=1 where MaKH=?";
        JDBCHelper.excuteUpdate(sql, id);
    }

    public void searchKH(int id) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH = ?";
        JDBCHelper.excuteQuery(sql, id);
    }

    public Vector getKH(int maKH) {
        try {
            Vector v = new Vector();
            String sql = "SELECT MAKH, tenKH,DiemThanhVien FROM dbo.KhachHang WHERE MAKH = "+maKH;
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {                
                v.add(rs.getInt(1));
                v.add(rs.getString(2));
                v.add(rs.getFloat(3));
                return v;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
