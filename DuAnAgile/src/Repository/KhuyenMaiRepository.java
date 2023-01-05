/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.KhuyenMai;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHAM DAC THANG
 */
public class KhuyenMaiRepository {

    DBContext dbreporisitory;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pst = null;

    List<KhuyenMai> listkm = new ArrayList<>();

    public KhuyenMaiRepository() {
    }

    public List<KhuyenMai> getListNV() {
        String select = " SELECT * FROM  KhuyenMai WHERE DAXOA = 0";
        listkm = new ArrayList<>();
        try {
            st = dbreporisitory.getConnection().createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                listkm.add(new KhuyenMai(rs.getInt(1),
                        rs.getNString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                ));
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listkm;

    }

    public Vector getGiamGia(int maGiamGia) {
        try {
            String query = "SELECT MaKM,TenKM, SoTienGiam,IIF(GETDATE()>=NGAYKT,'1','0') TrangThai FROM dbo.KhuyenMai WHERE MAKM = ?";
            pst = dbreporisitory.getConnection().prepareStatement(query);
            pst.setInt(1, maGiamGia);
            rs = pst.executeQuery();
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getInt(1));
                v.add(rs.getString(2));
                v.add(rs.getInt(3));
                v.add(rs.getString(4));
                return v;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
       KhuyenMaiRepository kh = new KhuyenMaiRepository();
       Vector v = kh.getGiamGia(100);
        System.out.println(v);
    }

    public String THEMNVDB(KhuyenMai nv) {
        try {
            String insert = "INSERT INTO KhuyenMai  VALUES (?,?,?,?)";
            pst = dbreporisitory.getConnection().prepareStatement(insert);
            pst.setNString(1, nv.getTenKM());
            pst.setInt(2, nv.getSoTienGiam());
            pst.setString(3, nv.getNgayBD());
            pst.setString(4, nv.getNgayKT());
            pst.executeUpdate();
            return "Thêm dữ liệu thành công";
        } catch (Exception ex) {
            Logger.getLogger(KhuyenMaiRepository.class.getName()).log(Level.SEVERE, null, ex);
            return "Thêm dữ liệu tất bại";
        }
    }

    public String CAPNHATNVDB(KhuyenMai nv) {
        try {
            String update = "update KhuyenMai set TenKM =?,SoTienGiam =?,NgayBD =?,NgayKT =? where   MaKM=?";
            pst = dbreporisitory.getConnection().prepareStatement(update);

            pst.setNString(1, nv.getTenKM());

            pst.setInt(2, nv.getSoTienGiam());
            pst.setString(3, nv.getNgayBD());
            pst.setString(4, nv.getNgayKT());
            pst.setInt(5, nv.getMaKM());

            pst.executeUpdate();
            return "Sửa dữ liệu thành công";
        } catch (Exception ex) {
            Logger.getLogger(KhuyenMaiRepository.class.getName()).log(Level.SEVERE, null, ex);
            return "Sửa dữ liệu thất bại";
        }
    }

    public String deleteToDbNV(int mnv) {
        try {
            String delete = "Update KhuyenMai set DaXoa=? WHERE MaKM = ?";
            pst = dbreporisitory.getConnection().prepareStatement(delete);
            pst.setInt(1, 1);
            pst.setInt(2, mnv);
            pst.executeUpdate();
            return "Xóa dữ liệu thành công";
        } catch (Exception ex) {
            Logger.getLogger(KhuyenMaiRepository.class.getName()).log(Level.SEVERE, null, ex);
            return "Xóa dữ liệu thất bại  ";
        }

    }

    // KIEM TRA ma sv co ko
    public boolean ktmasv(int manv) {
        for (int i = 0; i < listkm.size(); i++) {
            if (listkm.get(i).getMaKM() == manv) {
                return true;
            }
        }
        return false;
    }

}
