/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHAM DAC THANG
 */
public class KhuyenMai {
     public  int MaKM;
    public  String TenKM;
     
    public  int SoTienGiam;
    public  String NgayBD;
     public  String NgayKT;
     public  int DaXoa;

   
     
    public KhuyenMai() {
    }

    public KhuyenMai(int MaKM, String TenKM, int SoTienGiam, String NgayBD, String NgayKT,  int DaXoa) {
        this.MaKM = MaKM;
        this.TenKM = TenKM;
        this.SoTienGiam = SoTienGiam;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
        
        this.DaXoa = DaXoa;
    }

    public KhuyenMai(int MaKM, String TenKM, int SoTienGiam, String NgayBD, String NgayKT) {
        this.MaKM = MaKM;
        this.TenKM = TenKM;
        this.SoTienGiam = SoTienGiam;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
    }

    public KhuyenMai(String TenKM, int SoTienGiam, String NgayBD, String NgayKT) {
        this.TenKM = TenKM;
        this.SoTienGiam = SoTienGiam;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
    }

  

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public int getSoTienGiam() {
        return SoTienGiam;
    }

    public void setSoTienGiam(int SoTienGiam) {
        this.SoTienGiam = SoTienGiam;
    }

    public String getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(String NgayBD) {
        this.NgayBD = NgayBD;
    }

    public String getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(String NgayKT) {
        this.NgayKT = NgayKT;
    }



    public int getDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(int DaXoa) {
        this.DaXoa = DaXoa;
    }

      public String  chuyenngayBD(){
        
  
      try {
            Date date=new SimpleDateFormat("yyyy-MM-dd").parse(this.NgayBD);
             SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
              String ns = format.format(date);
           return ns;
       } catch (Exception e) {
           return "";
        }

  
        }
      
      
      public String  chuyenngayKT(){
        
  
      try {
            Date date=new SimpleDateFormat("yyyy-MM-dd").parse(this.NgayKT);
             SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
              String ns = format.format(date);
           return ns;
       } catch (Exception e) {
           return "";
        }

  
        }
      
          public String chuyenngayhientai(){
            try {
                    java.util.Date c=new java.util.Date();   
                 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         String formattedDate = format.format(c);
       return formattedDate;
            } catch (Exception e) {
                return "";
            }
           
        }
     
       public int sosanhngaytrongkhoang() {
        
        try {
            Date c=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
              
           
            Date ngaybd=sdf.parse(this.NgayBD);
            Date ngaykt=sdf.parse(this.NgayKT);
            Date hientai=sdf.parse(chuyenngayhientai());
            
            
            if (ngaybd.before(hientai)==true && hientai.before(ngaykt)==true) {
                return  0;
            }else{
                return 1;
            }
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            return 3;
        }
        
        
      }
       
       
      public String chuyenTrangThai() {
           
          if (sosanhngaytrongkhoang()==0) {
              return "Còn khuyến mãi";
          }else if (sosanhngaytrongkhoang()==1) {
             return "Không Còn khuyến mãi";  
          }else{
          return "lỗi";
          }
     
      }
    
}
