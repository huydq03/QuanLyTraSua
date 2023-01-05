/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.KhuyenMai;
import Repository.KhuyenMaiRepository;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author PHAM DAC THANG
 */
public class KhuyenMaiService {

    KhuyenMaiRepository khuyenMaiRepo = new KhuyenMaiRepository();

    public KhuyenMaiService() {
    }

    public List<KhuyenMai> getAllKM() {
        return khuyenMaiRepo.getListNV();
    }

    public String insertKM(KhuyenMai d) {

        return khuyenMaiRepo.THEMNVDB(d);

    }

    public String updateKM(KhuyenMai d) {
        if (khuyenMaiRepo.ktmasv(d.getMaKM())) {
            return khuyenMaiRepo.CAPNHATNVDB(d);
        } else {

            return "Khong tim thay ma trong co so du lieu";
        }
    }

    public String deleteKM(int d) {

        if (khuyenMaiRepo.ktmasv(d)) {
            return khuyenMaiRepo.deleteToDbNV(d);
        } else {

            return "Khong tim thay ma trong co so du lieu";
        }
    }
    public Vector getGiamGia(int ma){
        return khuyenMaiRepo.getGiamGia(ma);
    }

}
