package Repository;

import java.util.ArrayList;
import java.util.List;
import Model.DanhMuc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Repository.JDBCHelper;

public class DanhMucRespository {

    public List<DanhMuc> getAllDM() {
        List<DanhMuc> danhMucs = new ArrayList<>();
        String sql = "select*from DanhMuc where DaXoa=0";
        ResultSet rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                int maDanhMuc = rs.getInt("MaDanhMuc");
                String tenDanhMuc = rs.getString("TenDanhMuc");
                String moTa = rs.getString("Mota");
                int daXoa = rs.getInt("DaXoa");
                danhMucs.add(new DanhMuc(maDanhMuc, tenDanhMuc, moTa, daXoa));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucRespository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return danhMucs;
    }

    public Integer insertDM(DanhMuc dm) {
        String sql = "insert into DanhMuc values (?,?,?)";
        Integer row = JDBCHelper.excuteUpdate(sql, dm.getTenDanhMuc(), dm.getMoTa(), dm.getDaXoa());
        return row;
    }

    public void deleteDM(int maDanhMuc) {
        String sql = "update DanhMuc set DaXoa=1 where MaDanhMuc=?";
        JDBCHelper.excuteUpdate(sql, maDanhMuc);
    }

    public Integer upDateDM(DanhMuc dm) {
        String sql = "update DanhMuc set TenDanhMuc=?,Mota=?,DaXoa=? where MaDanhMuc=?";
        Integer row = JDBCHelper.excuteUpdate(sql, dm.getTenDanhMuc(), dm.getMoTa(), dm.getDaXoa(), dm.getMaDanhMuc());
        return row;
    }

    public DanhMuc searchDM(int maDanhMuc) {
        String sql = "SELECT * FROM DanhMuc WHERE MaDanhMuc = ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, maDanhMuc);
        try {
            while (rs.next()) {
                return new DanhMuc(rs.getInt("MaDanhMuc"), rs.getString("TenDanhMuc"), rs.getString("MoTa"), rs.getInt("DaXoa"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucRespository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
