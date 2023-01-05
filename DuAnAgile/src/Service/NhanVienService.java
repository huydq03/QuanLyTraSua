/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.NhanVien;
import Repository.NhanVienRepository;

import java.util.List;

public class NhanVienService {

    NhanVienRepository nvrepo = new NhanVienRepository();

    public NhanVienService() {
    }

    public List<NhanVien> getAllNV() {
        return nvrepo.getListNV();
    }

    public String insertNV(NhanVien d) {
        return nvrepo.THEMNVDB(d);
    }

    public String updateNV(NhanVien d) {
        if (nvrepo.ktmasv(d.getManv())) {
            return nvrepo.CAPNHATNVDB(d);
        } else {
            return "Khong tim thay ma trong co so du lieu";
        }
    }

    public String deleteNV(int d) {
        if (nvrepo.ktmasv(d)) {
            return nvrepo.deleteToDbNV(d);
        } else {
            return "Khong tim thay ma trong co so du lieu";
        }
    }

    public boolean doiMatKhau(int maNV, String matKhauMoi) {
        return nvrepo.doiMatKhau(maNV, matKhauMoi);
    }

    public NhanVien timNVTheoMa(int maNV) {
        return nvrepo.timNVTheoMa(maNV);
    }
}
