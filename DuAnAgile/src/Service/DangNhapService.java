/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.User;
import Repository.DangNhapRepository;

/**
 *
 * @author DELL
 */
public class DangNhapService {
    DangNhapRepository dangNhapRepo;

    public DangNhapService() {
        dangNhapRepo = new DangNhapRepository();
    }
    
    public User getUser(String ...values){
        return dangNhapRepo.getUser(values);
    }
    public String getTaiKhoan(String ...values){
        return dangNhapRepo.getTaiKhoan(values);
    }
    public void updatePass(String taiKhoan,String pass){
        dangNhapRepo.updatePass(taiKhoan, pass);
    }
}
