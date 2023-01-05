package Service;

import java.util.List;
import Model.KhachHang;
import Repository.KhachHangRespository;
import java.util.Vector;

public class KhachHangService {

    private final KhachHangRespository khachHangRepo;

    public KhachHangService() {
        this.khachHangRepo = new KhachHangRespository();
    }

    public List<KhachHang> getAllKH() {
        return this.khachHangRepo.getAllKH();
    }

    public Integer insertKH(KhachHang kh) {
        return khachHangRepo.insertKH(kh);
    }

    public Integer upDateKH(KhachHang kh) {
        return khachHangRepo.upDateKH(kh);
    }

    public void deleteKH(int id) {
        this.khachHangRepo.deleteKH(id);
    }

    public void searchKH(int id) {
        this.khachHangRepo.searchKH(id);
    }
    public Vector getKH(int id){
        return khachHangRepo.getKH(id);
    }
}
