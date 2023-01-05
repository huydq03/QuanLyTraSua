package Service;

import java.util.List;
import Model.DanhMuc;
import Repository.DanhMucRespository;

public class DanhMucService {

    private final DanhMucRespository danhMucRespository;

    public DanhMucService() {
        this.danhMucRespository = new DanhMucRespository();
    }

    public List<DanhMuc> getAllDM() {
        return this.danhMucRespository.getAllDM();
    }

    public Integer insertDM(DanhMuc dm) {
        return danhMucRespository.insertDM(dm);
    }

    public Integer upDateDM(DanhMuc dm) {
        return danhMucRespository.upDateDM(dm);
    }

    public void deleteDM(int maDanhMuc) {
        this.danhMucRespository.deleteDM(maDanhMuc);
    }

    public DanhMuc searchDM(int maDanhMuc) {
       return this.danhMucRespository.searchDM(maDanhMuc);
    }
}
