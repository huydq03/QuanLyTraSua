/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Model.BieuDoDanhThu;
import Model.DanhMuc;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.KhuyenMai;
import Model.NhanVien;
import Model.SanPham;
import Model.ThongKeDoanhThu;
import Model.ThongKeSanPham;
import Service.DanhMucService;
import Service.HoaDonChiTietService;
import Service.HoaDonService;
import Service.KhachHangService;
import Service.KhuyenMaiService;
import Service.NhanVienService;
import Service.SanPhamService;
import Service.ThongKeService;
import com.toedter.calendar.JDateChooser;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Admin
 */
public class BanHang extends javax.swing.JFrame {
//

    boolean chucVu;
    SanPhamService sanPhamService;
    DefaultTableModel modelSP;

    //Tuan Anh
    DefaultTableModel modelSPBH;
    DefaultTableModel modelGioHang;
    ArrayList<Vector> listGioHang = new ArrayList<>();
    static Vector thongTinKH = null;
    LayKhachHang khView = new LayKhachHang();
    //
    String anhSP = null;//SP
    //

    //PH???M ?????C TH???NG
    DefaultTableModel modelNV;
    NhanVienService nhanVienService = new NhanVienService();
    int indexNV = 0;
    DefaultTableModel defaultTable = new DefaultTableModel();
    List<NhanVien> listNV = new ArrayList<>();
    List<KhuyenMai> listKM = new ArrayList<>();

    NhanVienService nvservice = new NhanVienService();
    KhuyenMaiService khuyenMaiService = new KhuyenMaiService();
    int kt = 0;
    int km = 0;
    //H???T : Ph???m ?????C TH???NG

    //H???a ????nh T??
    private final KhachHangService khachHangService = new KhachHangService();
    DefaultTableModel modelKH;
    private final DanhMucService danhMucService = new DanhMucService();
    DefaultTableModel modelDM;

    DefaultTableModel tblModelHoaDon;
    DefaultTableModel tblModelHoaDonCT;
    private DateFormat dateFormat;
    private HoaDonService hoaDonService;
    private HoaDonChiTietService hoaDonChiTietService;
    private ThongKeService thongKeService;
    DefaultTableModel tblModelThongKe;
    DefaultTableModel tblModelThongKeSP;

    public BanHang() {
        initComponents();
        hoverMouse();
        setLocationRelativeTo(null);
        chucVu = true;
        sanPhamService = new SanPhamService();
        modelSP = (DefaultTableModel) tblQLSanPham.getModel();
        modelSPBH = (DefaultTableModel) tblDSSanpham.getModel();
        modelGioHang = (DefaultTableModel) tblgiohang.getModel();
        hoaDonService = new HoaDonService();
        hoaDonChiTietService = new HoaDonChiTietService();
        tblModelHoaDon = (DefaultTableModel) tblDSHoaDon.getModel();
        tblModelHoaDonCT = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        fillToHoaDon();
        modelKH = new DefaultTableModel();
        modelDM = new DefaultTableModel();
        fillToTableSP();
        fillComBoBoxSP();
        fillToTableBHSP();
        fillToTableKM();
        fillToTableNV();
        fillToTableDM();
        fillToTableKH();
        soGio();
        setDateChooser();
        thongKeService = new ThongKeService();
        tblModelThongKe = (DefaultTableModel) tblDoanhThu.getModel();
        tblModelThongKeSP = (DefaultTableModel) tblThongKeSP.getModel();
        thongTinThongKeThangHienTai();
        bieuDoDanhThu();

    }

    public BanHang(String maNv, boolean getChucVu) {
        initComponents();
        hoverMouse();
        setLocationRelativeTo(null);
        lblMaNV.setText(maNv);
        chucVu = getChucVu;
        sanPhamService = new SanPhamService();
        modelSP = (DefaultTableModel) tblQLSanPham.getModel();
        modelSPBH = (DefaultTableModel) tblDSSanpham.getModel();
        modelGioHang = (DefaultTableModel) tblgiohang.getModel();
        hoaDonService = new HoaDonService();
        hoaDonChiTietService = new HoaDonChiTietService();
        tblModelHoaDon = (DefaultTableModel) tblDSHoaDon.getModel();
        tblModelHoaDonCT = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        fillToHoaDon();
        modelKH = new DefaultTableModel();
        modelDM = new DefaultTableModel();
        fillToTableSP();
        fillComBoBoxSP();
        fillToTableBHSP();
        fillToTableKM();
        fillToTableNV();
        fillToTableDM();
        fillToTableKH();
        soGio();
        setDateChooser();
        thongKeService = new ThongKeService();
        tblModelThongKe = (DefaultTableModel) tblDoanhThu.getModel();
        tblModelThongKeSP = (DefaultTableModel) tblThongKeSP.getModel();
        thongTinThongKeThangHienTai();
        bieuDoDanhThu();
    }

    private void fillToTableDM() {
        modelDM = (DefaultTableModel) tblQLDanhMuc.getModel();
        modelDM.setRowCount(0);
        List<DanhMuc> danhMucs = danhMucService.getAllDM();
        for (DanhMuc dm : danhMucs) {
            modelDM.addRow(new Object[]{
                dm.getMaDanhMuc(), dm.getTenDanhMuc(), dm.getMoTa()
            });
        }
    }

    private void fillToTableKH() {
        modelKH = (DefaultTableModel) tblDSKhachHang.getModel();
        modelKH.setRowCount(0);
        List<KhachHang> khachHangs = khachHangService.getAllKH();
        for (KhachHang kh : khachHangs) {
            modelKH.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getGioiTinh() == 1 ? "Nam" : "N???", kh.getSdt(), kh.getDiaChi()});
        }
    }

    //tuananh
    void fillToTableSP() {
        modelSP.setRowCount(0);
        for (SanPham x : sanPhamService.getAllSP()) {
            modelSP.addRow(new Object[]{x.getMaSanPham(), x.getTenSanPham(), x.getDonGia(),
                sanPhamService.getTenDM(x.getMaDanhMuc()),
                x.isTrangThai() ? "C??n h??ng" : "H???t h??ng", x.getMota(), x.getAnh()
            });
        }
    }

    void soGio() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Date d = new Date();
                    lblNgayTaoBH.setText(sdf.format(d));
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("L???i");
                    }
                }
            }
        };
        thread.start();
    }

    void fillToTableBHSP() {
        modelSPBH.setRowCount(0);
        for (SanPham x : sanPhamService.getAllSP()) {
            modelSPBH.addRow(new Object[]{x.getMaSanPham(), x.getTenSanPham(), x.getDonGia(), x.isTrangThai() ? "C??n h??ng" : "H???t h??ng"});
        }
    }

    void timKiemBHSP(String values) {
        modelSPBH.setRowCount(0);
        for (SanPham x : sanPhamService.timKiemSP(values)) {
            modelSPBH.addRow(new Object[]{x.getMaSanPham(), x.getTenSanPham(), x.getDonGia(), x.isTrangThai() ? "C??n h??ng" : "H???t h??ng"});
        }
    }

    void fillComBoBoxSP() {
        cbbDanhmuc.removeAllItems();
        for (String x : sanPhamService.getDanhMuc()) {
            cbbDanhmuc.addItem(x);
        }
    }

    SanPham getValuesSP() {
        String maSP = txtMasp.getText().trim();
        String tenSanPham = txtTensp.getText().trim();
        String donGiaStr = txtDongia.getText().trim();
        String danhMucStr = cbbDanhmuc.getSelectedItem().toString();
        boolean trangThai = rdoConhang.isSelected();
        String mota = txtMota.getText().trim();
        if (tenSanPham.isEmpty() || donGiaStr.isEmpty() || mota.isEmpty()) {
            JOptionPane.showMessageDialog(this, "B???n kh??ng ???????c ????? tr???ng th??ng tin");
            return null;
        }
        long donGia = 0;
        try {
            donGia = Long.parseLong(donGiaStr);
            if (donGia < 0) {
                JOptionPane.showMessageDialog(this, "????n gi?? ph???i l???n h??n 0");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "????n gi?? ph???i l?? s???");
            return null;
        }
        int maDanhMuc = sanPhamService.getMaDM(danhMucStr);
        try {
            return new SanPham(Integer.parseInt(maSP), tenSanPham, donGia, trangThai, mota, anhSP, maDanhMuc);
        } catch (Exception e) {
            return new SanPham(0, tenSanPham, donGia, trangThai, mota, anhSP, maDanhMuc);
        }
    }

    void showDetailSP(int index) {
        String maSp = tblQLSanPham.getValueAt(index, 0).toString();
        String tenSp = tblQLSanPham.getValueAt(index, 1).toString();
        String donGia = tblQLSanPham.getValueAt(index, 2).toString();
        String danhMuc = tblQLSanPham.getValueAt(index, 3).toString();
        if (tblQLSanPham.getValueAt(index, 4).toString().equalsIgnoreCase("C??n h??ng")) {
            rdoConhang.setSelected(true);
        } else {
            rdoHethang.setSelected(true);
        }
        String moTa;
        try {
            moTa = tblQLSanPham.getValueAt(index, 5).toString();
        } catch (Exception e) {
            moTa = null;
        }
        try {
            anhSP = tblQLSanPham.getValueAt(index, 6).toString();
        } catch (Exception e) {
            anhSP = null;
        }
        txtMasp.setText(maSp);
        txtTensp.setText(tenSp);
        txtDongia.setText(donGia);
        cbbDanhmuc.setSelectedItem(danhMuc);
        txtMota.setText(moTa);
        lblAnhSPSP.setText("");
        lblAnhSPSP.setIcon(null);
        if (anhSP == null) {
            return;
        }
        File file = new File(anhSP);
        try {
            Image img = ImageIO.read(file);
            lblAnhSPSP.setIcon(new ImageIcon(img.getScaledInstance(lblAnhSPSP.getWidth(), lblAnhSPSP.getHeight(), 0)));
        } catch (IOException ex) {
            lblAnhSPSP.setText("???nh ???? b??? xo??");
            ex.printStackTrace();
        }

    }
//H???t tu???n anh

    //PH???M ?????c Th???ng
    //// KhuyenMai
    public void fillToTableKM() {
        listKM = khuyenMaiService.getAllKM();
        defaultTable = (DefaultTableModel) tblKhuyenMai.getModel();
        loadTableKM();
    }

    public void loadTableKM() {
        defaultTable.setRowCount(0);

        for (KhuyenMai nv : listKM) {
            defaultTable.addRow(new Object[]{
                nv.getMaKM(), nv.getTenKM(), nv.chuyenngayBD(),
                nv.chuyenngayKT(), nv.getSoTienGiam(),
                nv.chuyenTrangThai()
            });

        }
    }

    public String ngayBDF() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = format.format(dcNgayBatDauKM.getDate());
            return formattedDate;
        } catch (Exception e) {
            return "";
        }

    }

    public String ngayKTF() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = format.format(dcNgayKetThucKM.getDate());
            return formattedDate;
        } catch (Exception e) {
            return "";
        }

    }

    public boolean sosanhngaybtvskt() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date ngaybd = sdf.parse(ngayBDF());
        Date ngaykt = sdf.parse(ngayKTF());
        return ngaybd.before(ngaykt);

    }

    public int checkchongKM() throws ParseException {

        if (txtTenKM.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "T??n khuy???n m??i kh??ng ????? ch???ng");
            return km = 1;
        }

        if (txtMucGiamGiaPT.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "M???c gi???m gi?? kh??ng ????? ch???ng");
            return km = 1;
        }

        if (null == dcNgayBatDauKM.getDate()) {
            JOptionPane.showMessageDialog(this, "Ng??y b???t ?????u ph???i ????ng ?????nh d???ng :  dd-MM-yyyy");
            return km = 1;
        }

        if (null == dcNgayKetThucKM.getDate()) {
            JOptionPane.showMessageDialog(this, "Ng??y k???t th??c ph???i ????ng ?????nh d???ng :  dd-MM-yyyy");
            return kt = 1;
        } else {

            if (sosanhngaybtvskt()) {
                return checkgiamgia();
            } else {
                JOptionPane.showMessageDialog(this, "Ng??y B???t ?????u ph???i b?? h??n Ng??y k???t th??c");
                return km = 1;
            }

        }

    }

    public int checkgiamgia() {

        try {
            Integer MGG = Integer.parseInt(txtMucGiamGiaPT.getText());

            return km = 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "M???c gi???m gi?? kh??ng ???????c ghi ch???");
            return km = 1;
        }

    }

    KhuyenMai dulieuformKM() {
        return new KhuyenMai(txtTenKM.getText(), Integer.parseInt(txtMucGiamGiaPT.getText()),
                ngayBDF(), ngayKTF());
    }

    KhuyenMai dulieuformKM1() {
        return new KhuyenMai(Integer.parseInt(txtMaKM.getText()), txtTenKM.getText(), Integer.parseInt(txtMucGiamGiaPT.getText()),
                ngayBDF(), ngayKTF());
    }

    public void clickKM() {
        int rowIndex = tblKhuyenMai.getSelectedRow();
        if (rowIndex == -1) {
            return;
        }

        txtMaKM.setText(tblKhuyenMai.getValueAt(rowIndex, 0).toString());
        txtTenKM.setText(tblKhuyenMai.getValueAt(rowIndex, 1).toString());

        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(tblKhuyenMai.getValueAt(rowIndex, 2).toString());
            dcNgayBatDauKM.setDate(date);
            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(tblKhuyenMai.getValueAt(rowIndex, 3).toString());
            dcNgayKetThucKM.setDate(date1);
        } catch (Exception e) {
        }
        txtMucGiamGiaPT.setText(tblKhuyenMai.getValueAt(rowIndex, 4).toString());

    }

    public void timKM() {
        int t = 0;
        if (txtTimKiemKM.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "H??y nh???p m?? khuy???n m???i");
            return;
        }

        try {
            int MKM = Integer.parseInt(txtTimKiemKM.getText());
            for (int i = 0; i < listKM.size(); i = i + 1) {
                if (listKM.get(i).getMaKM() == MKM) {

                    t = 1;
                    txtMaKM.setText(tblKhuyenMai.getValueAt(i, 0).toString());
                    txtTenKM.setText(tblKhuyenMai.getValueAt(i, 1).toString());

                    try {
                        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(tblKhuyenMai.getValueAt(i, 2).toString());
                        dcNgayBatDauKM.setDate(date);
                        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(tblKhuyenMai.getValueAt(i, 3).toString());
                        dcNgayKetThucKM.setDate(date1);
                    } catch (Exception e) {
                    }
                    txtMucGiamGiaPT.setText(tblKhuyenMai.getValueAt(i, 4).toString());
                    tblKhuyenMai.setRowSelectionInterval(i, i);

                }

            }
            if (t == 0) {

                JOptionPane.showMessageDialog(this, "Kh??ng c?? m?? khuy???n m???i ????");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? m?? khuy???n m???i ????");
        }

    }

    // Nhan Vien
    public void fillToTableNV() {
        listNV = nvservice.getAllNV();
        defaultTable = (DefaultTableModel) tbbang.getModel();
        loadTableNV();
    }

    public void loadTableNV() {
        defaultTable.setRowCount(0);
        for (NhanVien nv : listNV) {
            defaultTable.addRow(new Object[]{
                nv.getManv(), nv.getTennv(), nv.chuyengioitinh(),
                nv.chuyenchucvu(), nv.getEmail(), nv.getQuequan(),
                nv.chuyenngaysinh(), nv.getLuong(), nv.getTaikhoan(),
                nv.getMatkhau()
            });
        }
    }

    ;
     
   public int checkluong() {
        String dodai = "[0-9]{1,10}";

        if (tfluong.getText().matches(dodai) == false) {
            kt = 1;
            JOptionPane.showMessageDialog(this, "????? d??i L????NG <=10 ch??? s??? v?? kh??ng ghi ch???");
        } else {
            try {
                Integer LG = Integer.parseInt(tfluong.getText());
                kt = 0;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "L????ng ch??? ???????c ghi s??? nguy??n d????ng");
                kt = 1;
            }
        }
        return kt;
    }

    public int checkemail() {

        String ntn = "([a-zA-Z0-9])+\\@gmail.com";
        String ntn1 = "([a-zA-Z0-9])+\\@fpt.edu.vn";
        if (tfemail.getText().matches(ntn) == true || tfemail.getText().matches(ntn1) == true) {
            return kt = 0;

        } else {
            return kt = 1;
        }

    }

    public int checkchong() {

        if (tfhoten.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "H??? t??n kh??ng ????? ch???ng");
            return kt = 1;
        }

        if (tfemail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Email ko ????? ch???ng");
            return kt = 1;
        }
        if (tfquequan.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Qu?? qu??n kh??ng ????? ch???ng ");
            return kt = 1;
        }
        if (tfluong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "L????ng kh??ng ????? ch???ng");
            return kt = 1;
        }
        if (tftaikhoan.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "T??i kho???n kh??ng ????? ch???ng");
            return kt = 1;
        }
        if (tfmatkhau.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "M???t kh???u kh??ng ????? ch???ng");
            return kt = 1;
        }
        if (null == dcNgaySinhNV.getDate()) {
            JOptionPane.showMessageDialog(this, "Ng??y sinh ph???i ????ng ?????nh d???ng :  dd-MM-yyyy");
            return kt = 1;
        } else {
            if (chuyenchucvuf() == 3) {
                JOptionPane.showMessageDialog(this, "Ch??a chon ch???c v???");
                return kt = 1;

            } else {

                if (checkemail() == 1) {
                    JOptionPane.showMessageDialog(this, "Email ph???i : ___________@gmail.com // __ @fpt.edu.cn");
                    return kt = 1;

                } else {

                    if (checktk()) {
                        return checkluong();
                    }
                    return kt = 1;

                }

            }
        }

    }

    public int checkchong1() {

        if (tfhoten.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "H??? t??n kh??ng ????? ch???ng");
            return kt = 1;
        }

        if (tfemail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Email kh??ng ????? ch???ng");
            return kt = 1;
        }
        if (tfquequan.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Qu?? qu??n  kh??ng ????? ch???ng ");
            return kt = 1;
        }
        if (tfluong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "L????ng kh??ng ????? ch???ng");
            return kt = 1;
        }
        if (tftaikhoan.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "T??i kho???n kh??ng ????? ch???ng");
            return kt = 1;
        }
        if (tfmatkhau.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "M???t kh???u kh??ng ????? ch???ng");
            return kt = 1;
        }
        if (null == dcNgaySinhNV.getDate()) {
            JOptionPane.showMessageDialog(this, "Ng??y sinh ph???i ????ng ?????nh d???ng :  dd-MM-yyyy");
            return kt = 1;
        } else {
            if (chuyenchucvuf() == 3) {
                JOptionPane.showMessageDialog(this, "Ch??a chon ch???c v???");
                return kt = 1;

            } else {

                if (checkemail() == 1) {
                    JOptionPane.showMessageDialog(this, "Email ph???i : ___________@gmail.com  // __ @fpt.edu.vn");
                    return kt = 1;

                } else {
                    return checkluong();
                }

            }
        }

    }

    public boolean checktk() {

        for (NhanVien nv : listNV) {
            if (nv.getTaikhoan().equals(tftaikhoan.getText())) {
                JOptionPane.showMessageDialog(this, "T??i kho???n ???? t???n t???i");
                return false;
            }
        }
        return true;

    }

    public int chuyengioitinhf() {
        if (rdnam.isSelected()) {
            return 0;

        } else {
            return 1;
        }
    }

    public int chuyenchucvuf() {
        String s = (String) cbchucvu.getSelectedItem();
        if (s.equals("Qu???n L??")) {
            return 0;

        } else if (s.equals("Nh??n Vi??n")) {
            return 1;
        } else {
            return 3;
        }
    }

    public String ngaysinhf() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = format.format(dcNgaySinhNV.getDate());
            return formattedDate;
        } catch (Exception e) {
            return "";
        }

    }

    NhanVien dulieuform() {
        return new NhanVien(tfhoten.getText(),
                chuyengioitinhf(), chuyenchucvuf(),
                tfemail.getText(), tfquequan.getText(),
                ngaysinhf(),
                Integer.parseInt(tfluong.getText()),
                tftaikhoan.getText(), tfmatkhau.getText());
    }

    NhanVien dulieuformU() {
        return new NhanVien(Integer.parseInt(tfmanv.getText()),
                tfhoten.getText(),
                chuyengioitinhf(), chuyenchucvuf(),
                tfemail.getText(), tfquequan.getText(),
                ngaysinhf(),
                Integer.parseInt(tfluong.getText()),
                tftaikhoan.getText(), tfmatkhau.getText());
    }

    public void click() {
        int rowIndex = tbbang.getSelectedRow();
        if (rowIndex == -1) {
            return;
        }

        tfmanv.setText(tbbang.getValueAt(rowIndex, 0).toString());
        tfhoten.setText(tbbang.getValueAt(rowIndex, 1).toString());
        if (tbbang.getValueAt(rowIndex, 2).toString().equals("NAM")) {
            rdnam.setSelected(true);
        } else {
            rdnu.setSelected(true);
        }
        if (tbbang.getValueAt(rowIndex, 3).toString().equals("Qu???n L??")) {
            cbchucvu.setSelectedIndex(1);
        } else {
            cbchucvu.setSelectedIndex(2);
        }
        tfemail.setText(tbbang.getValueAt(rowIndex, 4).toString());
        tfquequan.setText(tbbang.getValueAt(rowIndex, 5).toString());

        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(tbbang.getValueAt(rowIndex, 6).toString());
            dcNgaySinhNV.setDate(date);
        } catch (Exception e) {
        }
        tfluong.setText(tbbang.getValueAt(rowIndex, 7).toString());
        tftaikhoan.setText(tbbang.getValueAt(rowIndex, 8).toString());
        tfmatkhau.setText(tbbang.getValueAt(rowIndex, 9).toString());

    }

    public void timKiemNV() {
        int a = 0;
        if (tftimkiem.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p m?? nh??n vi??n c???n t??m ki???m");
            fillToTableNV();
            clearFormNhanVien();
            return;
        }

        try {
            int MNV = Integer.parseInt(tftimkiem.getText());
            for (NhanVien nv : listNV) {
                if (nv.getManv() == MNV) {
                    String v = String.valueOf(nv.getManv());
                    tfmanv.setText(v);
                    tfhoten.setText(nv.getTennv());
                    if (nv.getGioitinh() == 0) {
                        rdnam.setSelected(true);
                    } else {
                        rdnu.setSelected(true);
                    }
                    if (nv.getChucvu() == 0) {
                        cbchucvu.setSelectedIndex(1);
                    } else {
                        cbchucvu.setSelectedIndex(2);
                        tfemail.setText(nv.getEmail());
                        tfquequan.setText(nv.getQuequan());
                    }
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(nv.getNgaysinh());
                        dcNgaySinhNV.setDate(date);
                    } catch (Exception e) {
                    }
                    tfluong.setText(String.valueOf(nv.getLuong()));
                    tftaikhoan.setText(nv.getTaikhoan());
                    tfmatkhau.setText(nv.getMatkhau());
                    for (int i = 0; i < listNV.size(); i = i + 1) {
                        if (listNV.get(i).getManv() == MNV) {
                            tbbang.setRowSelectionInterval(i, i);
                        }
                    }

                    a = 1;
                }
            }
            if (a == 0) {

                JOptionPane.showMessageDialog(this, "Kh??ng c?? nh??n vi??n ????");
                clearFormNhanVien();;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? nh??n vi??n ????");
            clearFormNhanVien();
        }

    }

    public boolean taikhoannhap() {
        for (int q = 0; q < listNV.size(); q = q + 1) {
            if (q != tbbang.getSelectedRow()) {
                if (listNV.get(q).getTaikhoan().equals(tftaikhoan.getText())) {
                    JOptionPane.showMessageDialog(this, "T??i kho???n ???? t???n t???i");
                    return false;
                }
            }
        }
        return true;
    }
//H???t : PH???M ?????c Th???ng

    private void fillToHoaDon() {
        List<HoaDon> listHD = this.hoaDonService.layTatCaDanhSachHoaDon();
        tblModelHoaDon.setRowCount(0);
        for (HoaDon hd : listHD) {
            fillDataHoaHon(hd);
        }
    }

    private void fillDataHoaHon(HoaDon hd) {
        tblModelHoaDon.addRow(new Object[]{hd.getMaHD(),
            hd.getMaNV(),
            epKieuDate_ddMMyyy(hd.getNgayTao()),
            hd.getMaKH() == 0 ? "Kh??ch v??ng lai" : hd.getMaKH(),
            hd.getMaKM(),
            hd.getTongTien(), hd.getSoTienGiam(), hd.getTongTien() - hd.getSoTienGiam()});
    }

    public boolean getInt(JTextField txt, String mess) {
        if (txt.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p m?? kh??ch h??ng c???n t??m ki???m");
            return false;
        }
        int x = 0;
        try {
            x = Integer.parseInt(txt.getText());
            if (x < 0) {
                JOptionPane.showMessageDialog(this, "Vui l??ng nh???p " + mess + " l?? s??? nguy??n d????ng (>0)");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p " + mess + " l?? s???");
            return false;
        }
        return true;
    }

    private void bieuDoDanhThu() {
        List<BieuDoDanhThu> listDoanhThuThang = this.thongKeService.layDoanhThuThang();
        DefaultCategoryDataset dateset = new DefaultCategoryDataset();
        if (listDoanhThuThang != null) {
            for (BieuDoDanhThu bddt : listDoanhThuThang) {
                dateset.addValue(bddt.getTongDoanhThu(), "T???ng doanh thu", "Th??ng " + bddt.getSoThang());
            }
            JFreeChart chart = ChartFactory.createBarChart("TH???NG K?? DOANH THU", "Th??ng", "T???ng doanh thu", dateset);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(pnlBieuDo.getWidth(), pnlBieuDo.getHeight()));
            pnlBieuDo.removeAll();
            pnlBieuDo.setLayout(new CardLayout());
            pnlBieuDo.add(chartPanel);
            pnlBieuDo.validate();
            pnlBieuDo.repaint();
        }
    }

    public void thongTinThongKeThangHienTai() {
        // TODO add your handling code here:
        setDateChooser();
        Date date1 = dcNgayBatDauTK.getDate();
        String date1String = epKieuDateString(date1);
        Date date2 = dcNgayKetThucTK.getDate();
        String date2String = epKieuDateString(date2);
        List<ThongKeDoanhThu> list = layDanhSachHDTheoNgay(date1String, date2String);
        List<ThongKeSanPham> listSP = layDanhSachSPTheoNgay(date1String, date2String);
        fillDataThongKeSP(listSP);
        fillDaTaThongKe(list);
        dienThongTinTongHoaDonVaDoanhThu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrtrangthai = new javax.swing.ButtonGroup();
        btgGioiTinhNV = new javax.swing.ButtonGroup();
        btgtrangthaiKM = new javax.swing.ButtonGroup();
        pnlTong = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        pnlAnhDaiDien = new javax.swing.JPanel();
        lblAnhDaiDien = new javax.swing.JLabel();
        btnBanHang = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnKhuyenMai = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnDoiMatKhau = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        pnlCacGiaoDien = new javax.swing.JPanel();
        pnlBanHang = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblgiohang = new javax.swing.JTable();
        btnLamMoiBH = new javax.swing.JButton();
        btnXoaBH = new javax.swing.JButton();
        btnTaoHDBH = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        btnnext = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDSSanpham = new javax.swing.JTable();
        txtTimKiemBH = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        btnThemBH = new javax.swing.JButton();
        btnTimKiemBH = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        txtTenKHBH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnLayThongTin = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        txtMaGiamGia = new javax.swing.JTextField();
        btnSuDung = new javax.swing.JButton();
        cboLoaiKH = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        txtDiemTV = new javax.swing.JTextField();
        lblNgayTaoBH = new javax.swing.JLabel();
        btnGiamGiaBH = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        lbltongtienVND = new javax.swing.JLabel();
        txtPhiKhac = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lblgiamgiaVND = new javax.swing.JLabel();
        txtKhachTraBH = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        giamgia = new javax.swing.JLabel();
        txtGhiChuBH = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblkhachcantraVND = new javax.swing.JLabel();
        lbltientralaiVND = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        lblTienTraLaiBH = new javax.swing.JLabel();
        lblGiamGiaBH = new javax.swing.JLabel();
        lblTongTienBH = new javax.swing.JLabel();
        lblKhachCanTraBH = new javax.swing.JLabel();
        pnlSanPham = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel26 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        btnTimkiem = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblQLSanPham = new javax.swing.JTable();
        txtTimKiemSP = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        lblDonGia2 = new javax.swing.JLabel();
        lblTrangThai2 = new javax.swing.JLabel();
        lblDanhMuc2 = new javax.swing.JLabel();
        cbbDM = new javax.swing.JComboBox<>();
        cbbdongia = new javax.swing.JComboBox<>();
        cbbtrangthai = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        lblAnhSp = new javax.swing.JLabel();
        lblMaSp = new javax.swing.JLabel();
        lblTenSp = new javax.swing.JLabel();
        lblDonGia = new javax.swing.JLabel();
        lblimg = new javax.swing.JPanel();
        lblAnhSPSP = new javax.swing.JLabel();
        txtTensp = new javax.swing.JTextField();
        txtDongia = new javax.swing.JTextField();
        lblDanhMuc = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        cbbDanhmuc = new javax.swing.JComboBox<>();
        rdoConhang = new javax.swing.JRadioButton();
        rdoHethang = new javax.swing.JRadioButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtMota = new javax.swing.JTextArea();
        txtMasp = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        txtTimKiemDM = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblQLDanhMuc = new javax.swing.JTable();
        jPanel35 = new javax.swing.JPanel();
        txtMaDMSP = new javax.swing.JTextField();
        lblMota2 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        txaMotaDMSP = new javax.swing.JTextArea();
        lblMaDanhMuc2 = new javax.swing.JLabel();
        lblTenDanhMuc2 = new javax.swing.JLabel();
        txtTenDMSP = new javax.swing.JTextField();
        jPanel46 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnADD = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        pnlHoaDon = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDSHoaDon = new javax.swing.JTable();
        txtTimKiemHD = new javax.swing.JTextField();
        btnTimMaKhachHang = new javax.swing.JButton();
        btnTimMaHoaDon = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        pnlKhuyenMai = new javax.swing.JPanel();
        panel1 = new java.awt.Panel();
        jPanel39 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        txtMucGiamGiaPT = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtTenKM = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dcNgayKetThucKM = new com.toedter.calendar.JDateChooser();
        dcNgayBatDauKM = new com.toedter.calendar.JDateChooser();
        jPanel40 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        txtTimKiemKM = new javax.swing.JTextField();
        btnTimKiemKM = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        btnThemKM = new javax.swing.JButton();
        btnSuaKM = new javax.swing.JButton();
        btnXoaKM = new javax.swing.JButton();
        pnlNhanVien = new javax.swing.JPanel();
        panel3 = new java.awt.Panel();
        jPanel34 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        cbchucvu = new javax.swing.JComboBox<>();
        tfluong = new javax.swing.JTextField();
        tfemail = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        tfquequan = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        tfmanv = new javax.swing.JTextField();
        tfmatkhau = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        tftaikhoan = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        tfhoten = new javax.swing.JTextField();
        rdnam = new javax.swing.JRadioButton();
        jLabel49 = new javax.swing.JLabel();
        rdnu = new javax.swing.JRadioButton();
        dcNgaySinhNV = new com.toedter.calendar.JDateChooser();
        jPanel36 = new javax.swing.JPanel();
        btnThemNV = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        btnXoaNhanVien = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbbang = new javax.swing.JTable();
        tftimkiem = new javax.swing.JTextField();
        btnTimKiemNV = new javax.swing.JButton();
        pnlKhachHang = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        rdoNamKH = new javax.swing.JRadioButton();
        rdoNuKH = new javax.swing.JRadioButton();
        txtSoDienThoaiKH = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtDiaChiKH = new javax.swing.JTextArea();
        jPanel24 = new javax.swing.JPanel();
        btnThemKH = new javax.swing.JButton();
        btnXoaKH = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDSKhachHang = new javax.swing.JTable();
        txtTimKiemKH = new javax.swing.JTextField();
        btnTimKiemKH = new javax.swing.JButton();
        pnlThongKe = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        lblTongSoHoaDonTK = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblTongDoanhThuTK = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tblLoaiThoiGian = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnTimKiemTK = new javax.swing.JButton();
        btnLamMoiTK = new javax.swing.JButton();
        btnxuatBC = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        dcNgayBatDauTK = new com.toedter.calendar.JDateChooser();
        dcNgayKetThucTK = new com.toedter.calendar.JDateChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cbbSapXepTK = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        pnlBieuDo = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongKeSP = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        cbbLocTK = new javax.swing.JComboBox<>();
        pnlDoiMatKhau = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtMatKhauMoi = new javax.swing.JTextField();
        txtMatKhauCu = new javax.swing.JTextField();
        txtNhapLaiMatKhauMoi = new javax.swing.JTextField();
        btnDoiMatKhauTK = new javax.swing.JButton();
        pnlLayThongTin = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtTimKiemLTT = new javax.swing.JTextField();
        btnTimKiemLTT = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblLayThongTin = new javax.swing.JTable();
        btnXacNhanThongTin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        pnlTong.setPreferredSize(new java.awt.Dimension(1270, 760));
        pnlTong.setLayout(new java.awt.BorderLayout());

        pnlMenu.setBackground(new java.awt.Color(204, 255, 255));
        pnlMenu.setMaximumSize(new java.awt.Dimension(200, 610));
        pnlMenu.setMinimumSize(new java.awt.Dimension(200, 610));
        pnlMenu.setPreferredSize(new java.awt.Dimension(200, 760));

        pnlAnhDaiDien.setPreferredSize(new java.awt.Dimension(140, 130));

        javax.swing.GroupLayout pnlAnhDaiDienLayout = new javax.swing.GroupLayout(pnlAnhDaiDien);
        pnlAnhDaiDien.setLayout(pnlAnhDaiDienLayout);
        pnlAnhDaiDienLayout.setHorizontalGroup(
            pnlAnhDaiDienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhDaiDien, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        pnlAnhDaiDienLayout.setVerticalGroup(
            pnlAnhDaiDienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhDaiDien, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );

        pnlMenu.add(pnlAnhDaiDien);

        btnBanHang.setBackground(new java.awt.Color(204, 255, 255));
        btnBanHang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/cart-9-48.png"))); // NOI18N
        btnBanHang.setText("B??n H??ng");
        btnBanHang.setPreferredSize(new java.awt.Dimension(170, 50));
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });
        pnlMenu.add(btnBanHang);

        btnHoaDon.setBackground(new java.awt.Color(204, 255, 255));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/bill.png"))); // NOI18N
        btnHoaDon.setText("H??a ????n");
        btnHoaDon.setPreferredSize(new java.awt.Dimension(170, 50));
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });
        pnlMenu.add(btnHoaDon);

        btnSanPham.setBackground(new java.awt.Color(204, 255, 255));
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/article-marketing-48.png"))); // NOI18N
        btnSanPham.setText("S???n ph???m");
        btnSanPham.setPreferredSize(new java.awt.Dimension(170, 50));
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });
        pnlMenu.add(btnSanPham);

        btnKhuyenMai.setBackground(new java.awt.Color(204, 255, 255));
        btnKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/megaphone.png"))); // NOI18N
        btnKhuyenMai.setText("Khuy???n m??i");
        btnKhuyenMai.setPreferredSize(new java.awt.Dimension(170, 50));
        btnKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuyenMaiActionPerformed(evt);
            }
        });
        pnlMenu.add(btnKhuyenMai);

        btnNhanVien.setBackground(new java.awt.Color(204, 255, 255));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/customer.png"))); // NOI18N
        btnNhanVien.setText("Nh??n vi??n");
        btnNhanVien.setPreferredSize(new java.awt.Dimension(170, 50));
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        pnlMenu.add(btnNhanVien);

        btnKhachHang.setBackground(new java.awt.Color(204, 255, 255));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/add_employee.png"))); // NOI18N
        btnKhachHang.setText("Kh??ch h??ng");
        btnKhachHang.setPreferredSize(new java.awt.Dimension(170, 50));
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });
        pnlMenu.add(btnKhachHang);

        btnThongKe.setBackground(new java.awt.Color(204, 255, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/seo-performance-48.png"))); // NOI18N
        btnThongKe.setText("Th???ng k??");
        btnThongKe.setPreferredSize(new java.awt.Dimension(170, 50));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        pnlMenu.add(btnThongKe);

        btnDoiMatKhau.setBackground(new java.awt.Color(204, 255, 255));
        btnDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/key-3-48.png"))); // NOI18N
        btnDoiMatKhau.setText("?????i m???t kh???u");
        btnDoiMatKhau.setPreferredSize(new java.awt.Dimension(170, 50));
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });
        pnlMenu.add(btnDoiMatKhau);

        btnDangXuat.setBackground(new java.awt.Color(204, 255, 255));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/logout.png"))); // NOI18N
        btnDangXuat.setText("????ng xu???t");
        btnDangXuat.setPreferredSize(new java.awt.Dimension(170, 50));
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        pnlMenu.add(btnDangXuat);

        btnThoat.setBackground(new java.awt.Color(204, 255, 255));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/exit.png"))); // NOI18N
        btnThoat.setText("Tho??t");
        btnThoat.setPreferredSize(new java.awt.Dimension(170, 50));
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        pnlMenu.add(btnThoat);

        pnlTong.add(pnlMenu, java.awt.BorderLayout.LINE_START);

        pnlCacGiaoDien.setPreferredSize(new java.awt.Dimension(1070, 760));
        pnlCacGiaoDien.setLayout(new java.awt.CardLayout());

        pnlBanHang.setBackground(new java.awt.Color(204, 255, 204));
        pnlBanHang.setPreferredSize(new java.awt.Dimension(1070, 760));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(1070, 760));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Gi??? h??ng"));

        tblgiohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? s???n ph???m", "T??n s???n ph???m", "S??? l?????ng", "????n gi??", "Th??nh ti???n"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgiohangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblgiohang);

        btnLamMoiBH.setBackground(new java.awt.Color(204, 204, 204));
        btnLamMoiBH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoiBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-refresh-20.png"))); // NOI18N
        btnLamMoiBH.setText("L??m m???i");
        btnLamMoiBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiBHActionPerformed(evt);
            }
        });

        btnXoaBH.setBackground(new java.awt.Color(204, 204, 204));
        btnXoaBH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-delete-20.png"))); // NOI18N
        btnXoaBH.setText("X??a kh???i Gi??? h??ng");
        btnXoaBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaBHActionPerformed(evt);
            }
        });

        btnTaoHDBH.setBackground(new java.awt.Color(204, 204, 204));
        btnTaoHDBH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaoHDBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/add.png"))); // NOI18N
        btnTaoHDBH.setText("T???o h??a ????n");
        btnTaoHDBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDBHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLamMoiBH, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaBH, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoHDBH, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTaoHDBH, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnXoaBH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoiBH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh s??ch s???n ph???m"));

        btnnext.setBackground(new java.awt.Color(204, 204, 204));
        btnnext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/end.png"))); // NOI18N

        tblDSSanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "M?? s???n ph???m", "T??n S???n ph???m", "????n gi??", "Tr???ng th??i"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblDSSanpham);

        jLabel36.setText("1/3");

        btnThemBH.setBackground(new java.awt.Color(204, 204, 204));
        btnThemBH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/add.png"))); // NOI18N
        btnThemBH.setText("Th??m v??o gi??? H??ng");
        btnThemBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemBHActionPerformed(evt);
            }
        });

        btnTimKiemBH.setBackground(new java.awt.Color(204, 204, 204));
        btnTimKiemBH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-search-20.png"))); // NOI18N
        btnTimKiemBH.setText("T??m ki???m");
        btnTimKiemBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemBHActionPerformed(evt);
            }
        });

        btnback.setBackground(new java.awt.Color(204, 204, 204));
        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/start.png"))); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("T??n SP");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(btnThemBH)
                .addGap(17, 17, 17))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiemBH, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnTimKiemBH))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtTimKiemBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemBH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnback)
                    .addComponent(jLabel36)
                    .addComponent(btnnext)
                    .addComponent(btnThemBH))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Th??ng tin chung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtTenKHBH.setEditable(false);
        txtTenKHBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHBHActionPerformed(evt);
            }
        });

        jLabel7.setText("Lo???i Kh??ch H??ng");

        jLabel8.setText("H??? v?? T??n KH");

        jLabel10.setText("Ng??y T???o");

        jLabel12.setText("M?? gi???m gi??");

        btnLayThongTin.setBackground(new java.awt.Color(204, 204, 204));
        btnLayThongTin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLayThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-done-20.png"))); // NOI18N
        btnLayThongTin.setText("L???y th??ng tin");
        btnLayThongTin.setFocusable(false);
        btnLayThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayThongTinActionPerformed(evt);
            }
        });

        jLabel20.setText("M?? Nh??n Vi??n");

        lblMaNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaNV.setForeground(new java.awt.Color(51, 51, 255));
        lblMaNV.setText("1000");

        txtMaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaGiamGiaActionPerformed(evt);
            }
        });

        btnSuDung.setBackground(new java.awt.Color(204, 204, 204));
        btnSuDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-star-20.png"))); // NOI18N
        btnSuDung.setText("S??? d???ng");
        btnSuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuDungActionPerformed(evt);
            }
        });

        cboLoaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kh??ch H??ng", "Kh??ch V??ng Lai" }));
        cboLoaiKH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiKHItemStateChanged(evt);
            }
        });
        cboLoaiKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLoaiKHMouseClicked(evt);
            }
        });
        cboLoaiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiKHActionPerformed(evt);
            }
        });

        jLabel33.setText("??i???m Th??nh vi??n");

        txtDiemTV.setEditable(false);
        txtDiemTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiemTVActionPerformed(evt);
            }
        });

        btnGiamGiaBH.setBackground(new java.awt.Color(204, 204, 204));
        btnGiamGiaBH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGiamGiaBH.setText("S??? d???ng");
        btnGiamGiaBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiamGiaBHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaGiamGia))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenKHBH, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLayThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(btnGiamGiaBH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2))))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiemTV, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cboLoaiKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNgayTaoBH, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(2, 2, 2))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lblMaNV))
                .addGap(14, 14, 14)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addComponent(lblNgayTaoBH, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(btnLayThongTin))
                    .addComponent(txtTenKHBH, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGiamGiaBH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiemTV, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(btnSuDung)))
                .addGap(5, 5, 5))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi ti???t", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lbltongtienVND.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbltongtienVND.setForeground(new java.awt.Color(204, 0, 51));
        lbltongtienVND.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltongtienVND.setText("VND");

        txtPhiKhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhiKhacActionPerformed(evt);
            }
        });
        txtPhiKhac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhiKhacKeyPressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Ghi ch??");

        lblgiamgiaVND.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblgiamgiaVND.setForeground(new java.awt.Color(0, 51, 204));
        lblgiamgiaVND.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblgiamgiaVND.setText("VND");

        txtKhachTraBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhachTraBHActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Kh??ch tr???");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("T???ng Ti???n H??ng");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Kh??ch c???n tr???");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Ti???n Tr??? l???i");

        giamgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        giamgia.setText("Gi???m gi??");

        txtGhiChuBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGhiChuBHActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Ph?? Kh??c");

        lblkhachcantraVND.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblkhachcantraVND.setForeground(new java.awt.Color(204, 0, 51));
        lblkhachcantraVND.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblkhachcantraVND.setText("VND");

        lbltientralaiVND.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbltientralaiVND.setForeground(new java.awt.Color(0, 0, 153));
        lbltientralaiVND.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltientralaiVND.setText("VND");

        btnThanhToan.setBackground(new java.awt.Color(204, 204, 204));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-dolar-20.png"))); // NOI18N
        btnThanhToan.setText("Thanh To??n");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lblTienTraLaiBH.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblTienTraLaiBH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblGiamGiaBH.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblGiamGiaBH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblTongTienBH.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblTongTienBH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblKhachCanTraBH.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblKhachCanTraBH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(giamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTongTienBH, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltongtienVND, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtGhiChuBH, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(lblTienTraLaiBH, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbltientralaiVND, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPhiKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                            .addComponent(lblKhachCanTraBH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblkhachcantraVND))
                                        .addComponent(txtKhachTraBH, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addComponent(lblGiamGiaBH, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblgiamgiaVND, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 39, Short.MAX_VALUE))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltongtienVND)
                    .addComponent(jLabel34)
                    .addComponent(lblTongTienBH, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblgiamgiaVND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(giamgia)
                    .addComponent(lblGiamGiaBH, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPhiKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblkhachcantraVND, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(lblKhachCanTraBH, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtKhachTraBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltientralaiVND, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(lblTienTraLaiBH, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtGhiChuBH, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(pnlBanHang, "cardBanHang");

        pnlSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnlSanPham.setPreferredSize(new java.awt.Dimension(1070, 760));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "B???ng S???n Ph???m", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnTimkiem.setBackground(new java.awt.Color(204, 204, 204));
        btnTimkiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/research.png"))); // NOI18N
        btnTimkiem.setText("T??m Ki???m");
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        tblQLSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? S???n Ph???m", "T??n S???n Ph???m", "????n gi??", "Danh m???c", "Tr???ng th??i", "M?? t???", "???nh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLSanPham.setGridColor(new java.awt.Color(204, 255, 255));
        tblQLSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSanPhamMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblQLSanPham);

        txtTimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSPActionPerformed(evt);
            }
        });

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "L???c", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblDonGia2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonGia2.setText("????n gi??");

        lblTrangThai2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTrangThai2.setText("Tr???ng th??i");

        lblDanhMuc2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc2.setText("Danh m???c");

        cbbDM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "T???t c???", "Tr??", "Cafe" }));
        cbbDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDMActionPerformed(evt);
            }
        });

        cbbdongia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "T???t c???", "Tr??", "Cafe" }));

        cbbtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "T???t c???", "Tr??", "Cafe" }));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblDanhMuc2)
                        .addGap(18, 18, 18)
                        .addComponent(cbbDM, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(lblDonGia2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                .addGap(0, 2, Short.MAX_VALUE)
                                .addComponent(lblTrangThai2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDonGia2)
                    .addComponent(cbbdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrangThai2)
                    .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiemSP)
                    .addComponent(btnTimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addGap(19, 19, 19))
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Th??ng tin s???n ph???m", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel31.setForeground(new java.awt.Color(255, 255, 255));
        jPanel31.setName(""); // NOI18N

        lblAnhSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAnhSp.setText("???nh S???n ph???m");

        lblMaSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp.setText("M?? S???n Ph???m");

        lblTenSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTenSp.setText("T??n S???n Ph???m");

        lblDonGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonGia.setText("????n gi??");

        lblimg.setBackground(new java.awt.Color(204, 255, 255));

        lblAnhSPSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhSPSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhSPSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout lblimgLayout = new javax.swing.GroupLayout(lblimg);
        lblimg.setLayout(lblimgLayout);
        lblimgLayout.setHorizontalGroup(
            lblimgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhSPSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lblimgLayout.setVerticalGroup(
            lblimgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhSPSP, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );

        txtTensp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenspActionPerformed(evt);
            }
        });

        txtDongia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDongiaActionPerformed(evt);
            }
        });

        lblDanhMuc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc.setText("Danh m???c");

        lblMoTa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMoTa.setText("M?? t???");

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTrangThai.setText("Tr???ng th??i");

        btngrtrangthai.add(rdoConhang);
        rdoConhang.setSelected(true);
        rdoConhang.setText("C??n H??ng");
        rdoConhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoConhangActionPerformed(evt);
            }
        });

        btngrtrangthai.add(rdoHethang);
        rdoHethang.setText("H???t H??ng");

        txtMota.setColumns(20);
        txtMota.setRows(5);
        jScrollPane7.setViewportView(txtMota);

        txtMasp.setEditable(false);
        txtMasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaspActionPerformed(evt);
            }
        });

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/trash.png"))); // NOI18N
        btnXoa.setText("X??a th??ng tin");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(204, 204, 204));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/add (1).png"))); // NOI18N
        btnThem.setText("Th??m th??ng tin");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 204, 204));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/refresh (1).png"))); // NOI18N
        btnSua.setText("S???a th??ng tin");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(lblTenSp)
                        .addGap(18, 18, 18)
                        .addComponent(txtTensp))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSp)
                            .addComponent(lblAnhSp)
                            .addComponent(lblDonGia))
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtMasp))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(79, 79, 79)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTrangThai)
                    .addComponent(lblMoTa)
                    .addComponent(lblDanhMuc))
                .addGap(36, 36, 36)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(rdoConhang)
                        .addGap(72, 72, 72)
                        .addComponent(rdoHethang))
                    .addComponent(cbbDanhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAnhSp)
                            .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp)
                            .addComponent(txtMasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSp)
                            .addComponent(txtTensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDonGia)
                            .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDanhMuc)
                            .addComponent(cbbDanhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoConhang)
                            .addComponent(rdoHethang)
                            .addComponent(lblTrangThai))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMoTa)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("S???n ph???m", jPanel26);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder("B???ng danh m???c"));

        txtTimKiemDM.setToolTipText("");
        txtTimKiemDM.setVerifyInputWhenFocusTarget(false);
        txtTimKiemDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemDMActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(204, 204, 204));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/research.png"))); // NOI18N
        btnTimKiem.setText("T??m ki???m");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        tblQLDanhMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "M?? Danh M???c", "T??n Danh M???c", "M?? t???"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLDanhMucMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblQLDanhMuc);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(txtTimKiemDM, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnTimKiem)
                .addContainerGap(456, Short.MAX_VALUE))
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 1031, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiemDM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtTimKiemDM.getAccessibleContext().setAccessibleName("");

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder("Th??ng tin danh m???c"));

        txtMaDMSP.setEditable(false);

        lblMota2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMota2.setText("M?? t???");

        txaMotaDMSP.setColumns(20);
        txaMotaDMSP.setRows(5);
        jScrollPane13.setViewportView(txaMotaDMSP);

        lblMaDanhMuc2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaDanhMuc2.setText("M?? Danh M???c");

        lblTenDanhMuc2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenDanhMuc2.setText("T??n Danh M???c");

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnDelete.setBackground(new java.awt.Color(204, 204, 204));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/trash.png"))); // NOI18N
        btnDelete.setText("X??a");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnADD.setBackground(new java.awt.Color(204, 204, 204));
        btnADD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/add (1).png"))); // NOI18N
        btnADD.setText("Th??m");
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });

        btnupdate.setBackground(new java.awt.Color(204, 204, 204));
        btnupdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-refresh-20.png"))); // NOI18N
        btnupdate.setText("S???a");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(lblMaDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaDMSP, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(lblTenDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtTenDMSP, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMota2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel35Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaDMSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMota2))
                            .addGap(62, 62, 62)
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTenDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTenDMSP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 405, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Danh m???c", jPanel33);

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1498, Short.MAX_VALUE)
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        pnlCacGiaoDien.add(pnlSanPham, "cardSanPham");

        pnlHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        pnlHoaDon.setPreferredSize(new java.awt.Dimension(1070, 760));
        pnlHoaDon.setLayout(new java.awt.BorderLayout());

        jPanel20.setPreferredSize(new java.awt.Dimension(1271, 380));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh s??ch h??a ????n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        tblDSHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDSHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? h??a ????n", "M?? nh??n vi??n", "Ng??y t???o", "M?? kh??ch h??ng", "M?? khuy???n m??i", "T???ng ti???n", "S??? ti???n gi???m", "Thanh to??n"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSHoaDonMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblDSHoaDon);

        btnTimMaKhachHang.setBackground(new java.awt.Color(204, 204, 204));
        btnTimMaKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTimMaKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-search-20.png"))); // NOI18N
        btnTimMaKhachHang.setText("T??m m?? kh??ch h??ng");
        btnTimMaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMaKhachHangActionPerformed(evt);
            }
        });

        btnTimMaHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        btnTimMaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTimMaHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-search-20.png"))); // NOI18N
        btnTimMaHoaDon.setText("T??m m?? h??a ????n");
        btnTimMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMaHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(btnTimMaKhachHang)
                        .addGap(145, 145, 145)
                        .addComponent(btnTimMaHoaDon)
                        .addGap(223, 223, 223))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(390, 390, 390))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1043, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimMaKhachHang)
                    .addComponent(btnTimMaHoaDon))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1091, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlHoaDon.add(jPanel20, java.awt.BorderLayout.PAGE_START);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Th??ng tin h??a ????n chi ti???t", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        tblHoaDonChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "M?? s???n ph???m", "T??n s???n ph???m", "S??? l?????ng", "????n gi??/1 s???n ph???m", "Th??nh ti???n"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 1038, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 34, Short.MAX_VALUE))
        );

        pnlHoaDon.add(jPanel22, java.awt.BorderLayout.CENTER);

        pnlCacGiaoDien.add(pnlHoaDon, "cardHoaDon");

        pnlKhuyenMai.setBackground(new java.awt.Color(204, 204, 204));
        pnlKhuyenMai.setPreferredSize(new java.awt.Dimension(1070, 760));

        panel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder("TH??NG TIN KHUY???N M??I"));

        jLabel50.setText("TH???I GIAN K???T TH??C");

        txtMucGiamGiaPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMucGiamGiaPTActionPerformed(evt);
            }
        });

        jLabel52.setText("M???C GI???M GI??");

        txtTenKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKMActionPerformed(evt);
            }
        });

        jLabel53.setText("TH???I GIAN B???T ?????U");

        jLabel54.setText("T??N KHUY???N M??I");

        txtMaKM.setEditable(false);

        jLabel6.setText("M?? Khuy???n M??i\n");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel39Layout.createSequentialGroup()
                            .addComponent(jLabel54)
                            .addGap(40, 40, 40))
                        .addGroup(jPanel39Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(53, 53, 53)))
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKM, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(jLabel53))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(txtMucGiamGiaPT, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel50)))
                .addGap(18, 18, 18)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcNgayKetThucKM, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcNgayBatDauKM, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dcNgayBatDauKM, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(txtMucGiamGiaPT, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dcNgayKetThucKM, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
        );

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder("DANH S??CH KHUY???N M??I"));

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "M?? KHUY???N M??I", "T??N KHUY???N M??I", "NG??Y B???T ?????U ", "NG??Y K???T TH??C", "GI???M GI??"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tblKhuyenMai);

        txtTimKiemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemKMjTextField8ActionPerformed(evt);
            }
        });

        btnTimKiemKM.setBackground(new java.awt.Color(204, 204, 204));
        btnTimKiemKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/research.png"))); // NOI18N
        btnTimKiemKM.setText("T??M KI???M");
        btnTimKiemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(txtTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnTimKiemKM)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKiemKM, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txtTimKiemKM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder("CH???C N??NG"));

        btnThemKM.setBackground(new java.awt.Color(204, 204, 204));
        btnThemKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/add (1).png"))); // NOI18N
        btnThemKM.setText("TH??M");
        btnThemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKMActionPerformed(evt);
            }
        });

        btnSuaKM.setBackground(new java.awt.Color(204, 204, 204));
        btnSuaKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/refresh (1).png"))); // NOI18N
        btnSuaKM.setText("S???A");
        btnSuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKMActionPerformed(evt);
            }
        });

        btnXoaKM.setBackground(new java.awt.Color(204, 204, 204));
        btnXoaKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/trash.png"))); // NOI18N
        btnXoaKM.setText("X??A");
        btnXoaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemKM, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(btnSuaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlKhuyenMaiLayout = new javax.swing.GroupLayout(pnlKhuyenMai);
        pnlKhuyenMai.setLayout(pnlKhuyenMaiLayout);
        pnlKhuyenMaiLayout.setHorizontalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlKhuyenMaiLayout.setVerticalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlCacGiaoDien.add(pnlKhuyenMai, "cardKhuyenMai");

        pnlNhanVien.setBackground(new java.awt.Color(204, 255, 255));
        pnlNhanVien.setPreferredSize(new java.awt.Dimension(1070, 760));

        panel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TH??NG TIN NH??N VI??N", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 0, 0))); // NOI18N
        jPanel34.setForeground(new java.awt.Color(153, 153, 153));

        jLabel26.setText("CH???C V???");

        cbchucvu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ch???c V???", "Qu???n L??", "Nh??n Vi??n" }));

        tfluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfluongActionPerformed(evt);
            }
        });

        tfemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfemailActionPerformed(evt);
            }
        });

        jLabel27.setText("L????NG");

        jLabel42.setText("EMAIL");

        tfquequan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfquequanActionPerformed(evt);
            }
        });

        jLabel43.setText("GI???I T??NH");

        jLabel44.setText("QU?? QU??N");

        tfmanv.setEditable(false);
        tfmanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfmanvActionPerformed(evt);
            }
        });

        tfmatkhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfmatkhauActionPerformed(evt);
            }
        });

        jLabel45.setText("M???T KH???U");

        tftaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tftaikhoanActionPerformed(evt);
            }
        });

        jLabel46.setText("T??I KHO???N");

        jLabel47.setText("NG??Y SINH");

        jLabel48.setText("M?? NH??N VI??N");

        tfhoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfhotenActionPerformed(evt);
            }
        });

        btgGioiTinhNV.add(rdnam);
        rdnam.setSelected(true);
        rdnam.setText("NAM");
        rdnam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdnamActionPerformed(evt);
            }
        });

        jLabel49.setText("H??? V?? T??N");

        btgGioiTinhNV.add(rdnu);
        rdnu.setText("N???");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(tfmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(tfhoten, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel34Layout.createSequentialGroup()
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfemail)
                                .addGroup(jPanel34Layout.createSequentialGroup()
                                    .addComponent(cbchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel34Layout.createSequentialGroup()
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(rdnam, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(57, 57, 57)
                            .addComponent(rdnu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tftaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                    .addComponent(jLabel44)
                                    .addGap(29, 29, 29))
                                .addGroup(jPanel34Layout.createSequentialGroup()
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(21, 21, 21)))
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfluong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                    .addComponent(tfquequan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                                .addComponent(dcNgaySinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfquequan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(dcNgaySinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(tfluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(tftaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfhoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(rdnam)
                            .addComponent(rdnu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(cbchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("CH???C N??NG"));

        btnThemNV.setBackground(new java.awt.Color(204, 204, 204));
        btnThemNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/add (1).png"))); // NOI18N
        btnThemNV.setText("TH??M");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnSuaNV.setBackground(new java.awt.Color(204, 204, 204));
        btnSuaNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-refresh-20.png"))); // NOI18N
        btnSuaNV.setText("S???A");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        btnXoaNhanVien.setBackground(new java.awt.Color(204, 204, 204));
        btnXoaNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/trash.png"))); // NOI18N
        btnXoaNhanVien.setText("X??A");
        btnXoaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemNV, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder("DANH S??CH NH??N VI??N"));
        jPanel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tbbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? NH??N VI??N", "H??? V?? T??N", "GI???I T??NH", "CH???C V???", "EMAIL", "QU?? QU??N", "NG??Y SINH", "L????NG", "T??I KHO???N", "M???T KH???U"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbbang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbangMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tbbang);

        tftimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tftimkiemjTextField8ActionPerformed(evt);
            }
        });

        btnTimKiemNV.setBackground(new java.awt.Color(204, 204, 204));
        btnTimKiemNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/research.png"))); // NOI18N
        btnTimKiemNV.setText("T??M KI???M");
        btnTimKiemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tftimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnTimKiemNV)
                .addGap(217, 217, 217))
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiemNV)
                    .addComponent(tftimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlCacGiaoDien.add(pnlNhanVien, "cardNhanVien");

        pnlKhachHang.setBackground(new java.awt.Color(153, 255, 51));
        pnlKhachHang.setPreferredSize(new java.awt.Dimension(1070, 760));
        pnlKhachHang.setLayout(new java.awt.BorderLayout());

        jPanel12.setPreferredSize(new java.awt.Dimension(1271, 300));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thi???t l???p th??ng tin kh??ch h??ng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel23.setPreferredSize(new java.awt.Dimension(1271, 380));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("M?? kh??ch h??ng");

        txtMaKH.setFocusable(false);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("T??n kh??ch h??ng");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setText("Gi???i t??nh");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("S??? ??i???n tho???i");

        btgGioiTinhNV.add(rdoNamKH);
        rdoNamKH.setText("Nam");
        rdoNamKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamKHActionPerformed(evt);
            }
        });

        btgGioiTinhNV.add(rdoNuKH);
        rdoNuKH.setText("N???");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText("?????a ch???");

        txtDiaChiKH.setColumns(20);
        txtDiaChiKH.setRows(5);
        jScrollPane9.setViewportView(txtDiaChiKH);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel24.setForeground(new java.awt.Color(102, 255, 102));

        btnThemKH.setBackground(new java.awt.Color(204, 204, 204));
        btnThemKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/edit_kh.png"))); // NOI18N
        btnThemKH.setText("Th??m th??ng tin");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnXoaKH.setBackground(new java.awt.Color(204, 204, 204));
        btnXoaKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-delete-20.png"))); // NOI18N
        btnXoaKH.setText("X??a th??ng tin");
        btnXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKHActionPerformed(evt);
            }
        });

        btnSuaKH.setBackground(new java.awt.Color(204, 204, 204));
        btnSuaKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/sua.png"))); // NOI18N
        btnSuaKH.setText("S???a th??ng tin");
        btnSuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSuaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(txtMaKH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel40))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(56, 56, 56)
                        .addComponent(rdoNamKH)
                        .addGap(68, 68, 68)
                        .addComponent(rdoNuKH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(txtSoDienThoaiKH))
                .addGap(36, 36, 36)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)
                            .addComponent(txtSoDienThoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoNuKH)
                                    .addComponent(rdoNamKH)
                                    .addComponent(jLabel39)))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 1498, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pnlKhachHang.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh s??ch kh??ch h??ng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblDSKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "M?? kh??ch h??ng", "T??n kh??ch h??ng", "Gi???i t??nh", "S??? ??i???n tho???i", "?????a ch???"
            }
        ));
        tblDSKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSKhachHangMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblDSKhachHang);

        btnTimKiemKH.setBackground(new java.awt.Color(204, 204, 204));
        btnTimKiemKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-search-20.png"))); // NOI18N
        btnTimKiemKH.setText("T??m ki???m");
        btnTimKiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiemKH)
                .addContainerGap(516, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlKhachHang.add(jPanel3, java.awt.BorderLayout.CENTER);

        pnlCacGiaoDien.add(pnlKhachHang, "cardKhachHang");

        pnlThongKe.setBackground(new java.awt.Color(255, 255, 51));
        pnlThongKe.setPreferredSize(new java.awt.Dimension(1070, 760));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel27.setBackground(new java.awt.Color(204, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblTongSoHoaDonTK.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTongSoHoaDonTK.setText("200 h??a ????n");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel25.setText("T???ng s??? h??a ????n");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/bill.png"))); // NOI18N
        jLabel19.setText("jLabel15");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongSoHoaDonTK))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(lblTongSoHoaDonTK))
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jPanel6.setBackground(new java.awt.Color(204, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel9.setText("T???ng doanh thu");

        lblTongDoanhThuTK.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTongDoanhThuTK.setText("100.000.000 VND");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/seo-performance-48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongDoanhThuTK))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(lblTongDoanhThuTK, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Lo???i th???i gian:");

        tblLoaiThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ch???n kho???ng th???i gian" }));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Ng??y b???t ?????u:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ng??y k???t th??c:");

        btnTimKiemTK.setBackground(new java.awt.Color(204, 204, 204));
        btnTimKiemTK.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/search-3-24.png"))); // NOI18N
        btnTimKiemTK.setText("T??m ki???m");
        btnTimKiemTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTKActionPerformed(evt);
            }
        });

        btnLamMoiTK.setBackground(new java.awt.Color(204, 204, 204));
        btnLamMoiTK.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoiTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/sinchronize-24.png"))); // NOI18N
        btnLamMoiTK.setText("L??m m???i");
        btnLamMoiTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiTKActionPerformed(evt);
            }
        });

        btnxuatBC.setBackground(new java.awt.Color(204, 204, 204));
        btnxuatBC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnxuatBC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/info-24.png"))); // NOI18N
        btnxuatBC.setText("Xu???t b??o c??o");
        btnxuatBC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuatBCActionPerformed(evt);
            }
        });

        dcNgayBatDauTK.setDateFormatString("dd-MM-yyyy");

        dcNgayKetThucTK.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tblLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(44, 44, 44)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcNgayBatDauTK, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(dcNgayKetThucTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addComponent(btnTimKiemTK)
                .addGap(38, 38, 38)
                .addComponent(btnLamMoiTK)
                .addGap(26, 26, 26)
                .addComponent(btnxuatBC)
                .addContainerGap(22, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2))
                    .addComponent(dcNgayBatDauTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcNgayKetThucTK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tblLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnxuatBC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiemTK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Ng??y", "T???ng s??? h??a ????n trong ng??y", "T???ng doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDoanhThu);

        jLabel4.setText("S???p x???p:");

        cbbSapXepTK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbSapXepTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ng??y", "S??? h??a ????n", "T???ng doanh thu" }));
        cbbSapXepTK.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSapXepTKItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbSapXepTK, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSapXepTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("B???ng doanh thu", jPanel9);

        javax.swing.GroupLayout pnlBieuDoLayout = new javax.swing.GroupLayout(pnlBieuDo);
        pnlBieuDo.setLayout(pnlBieuDoLayout);
        pnlBieuDoLayout.setHorizontalGroup(
            pnlBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1082, Short.MAX_VALUE)
        );
        pnlBieuDoLayout.setVerticalGroup(
            pnlBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Bi???u ?????", jPanel8);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        tblThongKeSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "T??n ????? u???ng", "S??? l?????ng ???? b??n"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblThongKeSP);

        jLabel5.setText("L???c:");

        cbbLocTK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbLocTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "T???t c???", "B??n ch???y", "Kh??ng b??n ???????c" }));
        cbbLocTK.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLocTKItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(806, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbLocTK, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbLocTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("S???n ph???m", jPanel10);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 260, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1352, Short.MAX_VALUE)
            .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlThongKeLayout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 777, Short.MAX_VALUE)
            .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(pnlThongKe, "cardThongKe");

        pnlDoiMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        pnlDoiMatKhau.setPreferredSize(new java.awt.Dimension(1070, 760));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "?????i m???t kh???u", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("M???t kh???u c??");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Nh???p l???i m???t kh???u m???i");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("M???t kh???u m???i");

        txtMatKhauMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauMoiActionPerformed(evt);
            }
        });

        btnDoiMatKhauTK.setBackground(new java.awt.Color(204, 204, 204));
        btnDoiMatKhauTK.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDoiMatKhauTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-done-20.png"))); // NOI18N
        btnDoiMatKhauTK.setText("?????i m???t kh???u");
        btnDoiMatKhauTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauTKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22))
                .addGap(49, 49, 49)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMatKhauCu)
                    .addComponent(txtMatKhauMoi)
                    .addComponent(txtNhapLaiMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(176, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDoiMatKhauTK)
                .addGap(319, 319, 319))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtNhapLaiMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(btnDoiMatKhauTK, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        jPanel25Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMatKhauCu, txtMatKhauMoi, txtNhapLaiMatKhauMoi});

        javax.swing.GroupLayout pnlDoiMatKhauLayout = new javax.swing.GroupLayout(pnlDoiMatKhau);
        pnlDoiMatKhau.setLayout(pnlDoiMatKhauLayout);
        pnlDoiMatKhauLayout.setHorizontalGroup(
            pnlDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoiMatKhauLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(647, Short.MAX_VALUE))
        );
        pnlDoiMatKhauLayout.setVerticalGroup(
            pnlDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoiMatKhauLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
        );

        pnlCacGiaoDien.add(pnlDoiMatKhau, "cardDoiMatKhau");

        pnlLayThongTin.setBackground(new java.awt.Color(255, 255, 255));
        pnlLayThongTin.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1498, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel14, java.awt.BorderLayout.LINE_END);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1498, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel42, java.awt.BorderLayout.PAGE_END);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setText("TH??NG TIN KH??CH H??NG");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("T??n Kh??ch H??ng");

        txtTimKiemLTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemLTTActionPerformed(evt);
            }
        });

        btnTimKiemLTT.setBackground(new java.awt.Color(204, 204, 204));
        btnTimKiemLTT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemLTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-search-20.png"))); // NOI18N
        btnTimKiemLTT.setText("T??m Ki???m");

        tblLayThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tblLayThongTin);

        btnXacNhanThongTin.setBackground(new java.awt.Color(204, 204, 204));
        btnXacNhanThongTin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXacNhanThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlduanAgile/icon/icons8-done-20.png"))); // NOI18N
        btnXacNhanThongTin.setText("X??c Nh???n");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnXacNhanThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(38, 38, 38))
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(31, 31, 31)
                                .addComponent(txtTimKiemLTT, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addGap(13, 13, 13)
                        .addComponent(btnTimKiemLTT))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(467, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemLTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimKiemLTT)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnXacNhanThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel43, java.awt.BorderLayout.CENTER);

        pnlCacGiaoDien.add(pnlLayThongTin, "cardLayThongTin");

        pnlTong.add(pnlCacGiaoDien, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlTong, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardBanHang");
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardSanPham");
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardHoaDon");
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuyenMaiActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardKhuyenMai");
    }//GEN-LAST:event_btnKhuyenMaiActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        if (!chucVu) {
            JOptionPane.showMessageDialog(this, "B???n kh??ng c?? quy???n truy c???p");
            return;
        }
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardNhanVien");
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardKhachHang");
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        if (!chucVu) {
            JOptionPane.showMessageDialog(this, "B???n kh??ng c?? quy???n n??y");
        }
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardThongKe");
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardDoiMatKhau");
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n ????ng xu???t kh??ng", "????ng xu???t", JOptionPane.YES_NO_OPTION);
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        JOptionPane.showMessageDialog(this, "B???n ???? ????ng xu???t");
        new DangNhap().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnTimKiemTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTKActionPerformed
        Date date = new Date();
        Date dateBegin = getDateChooser(dcNgayBatDauTK, "ng??y b???t ?????u");
        if (dateBegin == null) {
            return;
        }
        Date dateEnd = getDateChooser(dcNgayKetThucTK, "ng??y k???t th??c");
        if (dateEnd == null) {
            return;
        }
        if (dateBegin.compareTo(dateEnd) > 0) {
            JOptionPane.showMessageDialog(this, "Ng??y b???t ?????u ph???i nh??? h??n ng??y k???t th??c",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (dateEnd.compareTo(date) > 0) {
            JOptionPane.showMessageDialog(this, "Ng??y k???t th??c kh??ng h???p l???", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String dateBeginString = epKieuDateString(dateBegin);
        String dateEndString = epKieuDateString(dateEnd);
        System.out.println(dateBeginString);
        System.out.println(dateEndString);
        List<ThongKeDoanhThu> listHoaDonTheoNgay = layDanhSachHDTheoNgay(dateBeginString, dateEndString);
        List<ThongKeSanPham> listSanPhamTheoNgay = layDanhSachSPTheoNgay(dateBeginString, dateEndString);

        fillDataThongKeSP(listSanPhamTheoNgay);

        fillDaTaThongKe(listHoaDonTheoNgay);
        dienThongTinTongHoaDonVaDoanhThu();
        cbbSapXepTK.setSelectedIndex(0);
        cbbLocTK.setSelectedIndex(0);
    }//GEN-LAST:event_btnTimKiemTKActionPerformed

    private void btnLamMoiTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiTKActionPerformed
        List<ThongKeDoanhThu> listDT = this.thongKeService.getAllDoanhThu();
        fillDaTaThongKe(listDT);
        List<ThongKeSanPham> listTKSP = this.thongKeService.getAllSanPham();
        fillDataThongKeSP(listTKSP);
        dcNgayBatDauTK.setDate(null);
        dcNgayKetThucTK.setDate(null);
    }//GEN-LAST:event_btnLamMoiTKActionPerformed

    private void tblgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblgiohangMouseClicked

    private void btnLamMoiBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiBHActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(this, "B???n mu???n xo?? t???t c??? s???n ph???m kh??ng ", "Xo?? t???t c??? s???n ph???m", JOptionPane.YES_NO_OPTION);
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        listGioHang.removeAll(listGioHang);
        modelGioHang.setRowCount(0);
        JOptionPane.showMessageDialog(this, "???? clear gi??? h??ng");
    }//GEN-LAST:event_btnLamMoiBHActionPerformed

    private void btnThemBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemBHActionPerformed
        // TODO add your handling code here:
        int index = tblDSSanpham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "B???n ch??a ch???n s???n ph???m c???n b??n");
            return;
        }
        if (tblDSSanpham.getValueAt(index, 3).toString().equalsIgnoreCase("H???t h??ng")) {
            JOptionPane.showMessageDialog(this, "S???n ph???m n??y ???? h???t h??ng");
            return;
        }
        String reSoLuong = "\\d+";
        String getSoLuong = JOptionPane.showInputDialog(this, "M???i b???n nh???p s??? l?????ng");
        while (true) {
            if (!getSoLuong.matches(reSoLuong)) {
                JOptionPane.showMessageDialog(this, "S??? l?????ng ph???i l?? s??? nguy??n , M???i nh???p l???i !");
                getSoLuong = JOptionPane.showInputDialog(this, "M???i b???n nh???p s??? l?????ng");
            } else {
                break;
            }
        }
        int soLuong = 0;
        soLuong = Integer.parseInt(getSoLuong);
        if (soLuong < 1) {
            JOptionPane.showMessageDialog(this, "S??? l?????ng ph???i l???n h??n 0");
            return;
        }
        //if
        String maSP = tblDSSanpham.getValueAt(index, 0).toString();
        for (int i = 0; i < listGioHang.size(); i++) {
            if (maSP.equalsIgnoreCase(listGioHang.get(i).get(0).toString())) {
                soLuong += Integer.parseInt(listGioHang.get(i).get(2).toString());
                String ten = tblDSSanpham.getValueAt(index, 1).toString();
                String donGia = tblDSSanpham.getValueAt(index, 2).toString();
                Vector v = new Vector();
                v.add(maSP);
                v.add(ten);
                v.add(soLuong);
                v.add(donGia);
                v.add(soLuong * Double.parseDouble(donGia));
                listGioHang.set(i, v);
                modelGioHang.setValueAt(v.get(0), i, 0);
                modelGioHang.setValueAt(v.get(1), i, 1);
                modelGioHang.setValueAt(v.get(2), i, 2);
                modelGioHang.setValueAt(v.get(3), i, 3);
                modelGioHang.setValueAt(String.format("%.2f", v.get(4)), i, 4);
                JOptionPane.showMessageDialog(this, "V?? s???n ph???m n??y ???? c?? , s??? s???a s??? l?????ng gi??? h??ng");
                return;
            }
        }
        //if
        String ten = tblDSSanpham.getValueAt(index, 1).toString();
        String donGia = tblDSSanpham.getValueAt(index, 2).toString();
        Vector v = new Vector();
        v.add(maSP);
        v.add(ten);
        v.add(soLuong);
        v.add(donGia);
        v.add(soLuong * Double.parseDouble(donGia));
        listGioHang.add(v);
        modelGioHang.addRow(new Object[]{v.get(0), v.get(1), v.get(2), v.get(3), String.format("%.2f", v.get(4))});


    }//GEN-LAST:event_btnThemBHActionPerformed

    private void btnTimMaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaKhachHangActionPerformed
        if (txtTimKiemHD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p m?? kh??ch h??ng c???n t??m ki???m");
            txtTimKiemHD.requestFocus();
            tblModelHoaDonCT.setRowCount(0);
            fillToHoaDon();
            return;
        }
        boolean isCheck = getInt(txtTimKiemHD, "M?? kh??ch h??ng");
        if (isCheck) {
            int maKhachHang = Integer.parseInt(txtTimKiemHD.getText());
            List<HoaDon> listHD = this.hoaDonService.timKiemHoaDonTheoMaKH(maKhachHang);
            tblModelHoaDonCT.setRowCount(0);
            if (!listHD.isEmpty()) {
                tblModelHoaDon.setRowCount(0);
                for (HoaDon hd : listHD) {
                    fillDataHoaHon(hd);
                }
                JOptionPane.showMessageDialog(this, "T??m ki???m th??nh c??ng");
            } else {
                JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y kh??ch h??ng. Vui l??ng nh???p l???i m?? kh??ch h??ng");
                txtTimKiemHD.setText("");
                txtTimKiemHD.requestFocus();
                tblModelHoaDonCT.setRowCount(0);
                List<HoaDon> listAll = this.hoaDonService.layTatCaDanhSachHoaDon();
                tblModelHoaDon.setRowCount(0);
                for (HoaDon hd : listAll) {
                    fillDataHoaHon(hd);
                }
            }

        }
    }//GEN-LAST:event_btnTimMaKhachHangActionPerformed

    private void btnTimMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaHoaDonActionPerformed
        if (txtTimKiemHD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "vui l??ng nh???p h??a ????n c???n t??m ki???m");
            txtTimKiemHD.requestFocus();
            tblModelHoaDonCT.setRowCount(0);
            fillToHoaDon();
            return;
        }
        boolean isCheck = getInt(txtTimKiemHD, "M?? h??a ????n");
        if (isCheck) {
            int maHoaDon = Integer.parseInt(txtTimKiemHD.getText());
            List<HoaDon> listHD = this.hoaDonService.timKiemHoaDonTheoMaHD(maHoaDon);
            tblModelHoaDonCT.setRowCount(0);
            if (!listHD.isEmpty()) {
                tblModelHoaDon.setRowCount(0);
                for (HoaDon hd : listHD) {
                    fillDataHoaHon(hd);
                }
                JOptionPane.showMessageDialog(this, "T??m ki???m th??nh c??ng");
            } else {
                JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y h??a ????n. Vui l??ng nh???p l???i m?? h??a ????n");
                txtTimKiemHD.setText("");
                txtTimKiemHD.requestFocus();
                tblModelHoaDonCT.setRowCount(0);
                List<HoaDon> listAll = this.hoaDonService.layTatCaDanhSachHoaDon();
                tblModelHoaDon.setRowCount(0);
                for (HoaDon hd : listAll) {
                    fillDataHoaHon(hd);
                }
            }

        }
    }//GEN-LAST:event_btnTimMaHoaDonActionPerformed

    private void rdoNamKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamKHActionPerformed

    private void btnXoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKHActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblDSKhachHang.getSelectedRow();
        modelKH = ((DefaultTableModel) tblDSKhachHang.getModel());
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                    "Vui l??ng ch???n 1 kh??ch h??ng ????? x??a",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int xacNhanXoa = JOptionPane.showConfirmDialog(this, "X??c nh???n x??a?", "X??a", JOptionPane.YES_NO_OPTION);
        if (xacNhanXoa == JOptionPane.YES_OPTION) {
            String idString = modelKH.getValueAt(selectedRow, 0).toString();

            int id = Integer.parseInt(idString);

            // luu thong tin xuong db
            khachHangService.deleteKH(id);

            // sua thong tin tren giao dien
            modelKH.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "X??a th??nh c??ng");
            reSetFromKhachHang();
        }
    }//GEN-LAST:event_btnXoaKHActionPerformed

    private void txtMatKhauMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauMoiActionPerformed

    private void txtTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemSPActionPerformed

    private void txtTenspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenspActionPerformed

    private void txtDongiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDongiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDongiaActionPerformed

    private void txtMaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaspActionPerformed

    private void rdoConhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoConhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoConhangActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        SanPham sp = getValuesSP();
        if (sp == null) {
            return;
        }
        sanPhamService.insertSP(sp);
        fillToTableSP();
        fillToTableBHSP();
        JOptionPane.showMessageDialog(this, "B???n ???? th??m th??nh c??ng");
        clearFormSanPham();
    }//GEN-LAST:event_btnThemActionPerformed
    private void clearFormSanPham() {
        lblAnhSPSP.setText("");
        lblAnhSPSP.setIcon(null);
        txtMasp.setText("");
        txtTensp.setText("");
        txtDongia.setText("");
        cbbDanhmuc.setSelectedIndex(0);
        rdoConhang.setSelected(true);
        txtMota.setText("");
    }
    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        modelDM = (DefaultTableModel) tblQLDanhMuc.getModel();
        int selectedRow = tblQLDanhMuc.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n 1 danh m???c ????? s???a", "L???i", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DanhMuc dm = getValuesDM();
        if (dm == null) {
            return;
        }
        Integer maDanhMuc = getDanhMucFromSelectedRow();
        int xacNhanSua = JOptionPane.showConfirmDialog(this, "X??c nh???n s???a?", "S???a", JOptionPane.YES_NO_OPTION);
        if (xacNhanSua == JOptionPane.YES_OPTION) {
            dm.setMaDanhMuc(maDanhMuc);
            danhMucService.upDateDM(dm);
            reSetFromDanhMuc();
            fillToTableDM();
            fillComBoBoxSP();
            JOptionPane.showMessageDialog(this, "S???a th??nh c??ng");
        }
    }//GEN-LAST:event_btnupdateActionPerformed
    public Integer getDanhMucFromSelectedRow() {
        Integer row = tblQLDanhMuc.getSelectedRow();
        Integer maDanhMuc = (Integer) tblQLDanhMuc.getValueAt(row, 0);
        return maDanhMuc;
    }
    private void txtTimKiemDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemDMActionPerformed

    private void tfluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfluongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfluongActionPerformed

    private void tfemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfemailActionPerformed

    private void tfquequanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfquequanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfquequanActionPerformed

    private void tfmanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfmanvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfmanvActionPerformed

    private void tfmatkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfmatkhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfmatkhauActionPerformed

    private void tftaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftaikhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tftaikhoanActionPerformed

    private void tfhotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfhotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfhotenActionPerformed

    private void rdnamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdnamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdnamActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        if (tfmanv.getText().equals("")) {
            JOptionPane.showMessageDialog(this, " Ch???n nh??n vi??n ho???c t??m m?? nh??n  vi??n r???i c???p nh???t   ");

            return;
        }
        checkchong1();

        if (kt == 0) {
            JOptionPane.showMessageDialog(this, nvservice.updateNV(dulieuformU()));
            fillToTableNV();
            clearFormNhanVien();
        }

    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void btnXoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienActionPerformed
        if (tfmanv.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, " Ch???n nh??n vi??n ho???c t??m m?? nh??n vi??n r???i x??a   ");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c xo?? nh??n vi??n n??y kh??ng ? ", "Xo?? nh??n vi??n", JOptionPane.YES_NO_OPTION);
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        Integer mnv = Integer.parseInt(tfmanv.getText());
        JOptionPane.showMessageDialog(this, nvservice.deleteNV(mnv));
        fillToTableNV();
        clearFormNhanVien();
    }//GEN-LAST:event_btnXoaNhanVienActionPerformed

    private void tftimkiemjTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftimkiemjTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tftimkiemjTextField8ActionPerformed

    private void txtMucGiamGiaPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMucGiamGiaPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucGiamGiaPTActionPerformed

    private void txtTenKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKMActionPerformed

    private void txtTimKiemKMjTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemKMjTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKMjTextField8ActionPerformed

    private void btnSuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKMActionPerformed
        if (txtMaKM.getText().equals("")) {
            JOptionPane.showMessageDialog(this, " Ch???n khuy???n m???i ho???c t??m m?? khuy???n m???i   r???i c???p nh???t   ");
            return;
        }
        try {
            checkchongKM();

        } catch (ParseException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            kt = 1;
        }
        if (kt == 0) {
            JOptionPane.showMessageDialog(this, khuyenMaiService.updateKM(dulieuformKM1()));
            fillToTableKM();
        }
    }//GEN-LAST:event_btnSuaKMActionPerformed

    private void btnXoaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKMActionPerformed
        if (txtMaKM.getText().equals("")) {
            JOptionPane.showMessageDialog(this, " Ch???n Khuy???n m???i ho???c t??m m?? khuy???n m???i r???i x??a   ");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c xo?? khuy???n m??i n??y kh??ng ? ", "Xo?? khuy???n m??i", JOptionPane.YES_NO_OPTION);
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        Integer mKM = Integer.parseInt(txtMaKM.getText());
        JOptionPane.showMessageDialog(this, khuyenMaiService.deleteKM(mKM));
        fillToTableKM();
    }//GEN-LAST:event_btnXoaKMActionPerformed

    private void btnThemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKMActionPerformed
        try {
            checkchongKM();
            if (km == 0) {
                JOptionPane.showMessageDialog(this, khuyenMaiService.insertKM(dulieuformKM()));
                fillToTableKM();
            }
        } catch (ParseException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemKMActionPerformed

    private void txtDiemTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemTVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiemTVActionPerformed

    private void btnLayThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayThongTinActionPerformed
        // TODO add your handling code here:
//        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
//        layout.show(pnlCacGiaoDien, "cardLayThongTin");
        try {
            System.out.println(thongTinKH.get(0).toString());
        } catch (Exception e) {
            System.out.println("Rong");
        }
        khView.setVisible(true);
    }//GEN-LAST:event_btnLayThongTinActionPerformed

    private void txtTimKiemLTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemLTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemLTTActionPerformed

    private void btnSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuDungActionPerformed
        // TODO add your handling code here:
        String diemThanhVien = txtDiemTV.getText().trim();
        if (diemThanhVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ch??? c?? kh??ch h??ng t???i c???a h??ng m???i c?? ??i???m th??nh vi??n");
            return;
        }
        double diem = Double.parseDouble(diemThanhVien);
        if (diem == 0) {
            JOptionPane.showMessageDialog(this, "Kh??ch h??ng n??y ch??a c?? ??i???m th??nh vi??n n??o ");
            return;
        }
        btnSuDung.setEnabled(false);
        JOptionPane.showMessageDialog(this, "S??? d???ng th??nh c??ng");
    }//GEN-LAST:event_btnSuDungActionPerformed

    private void txtMaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaGiamGiaActionPerformed

    private void txtTenKHBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHBHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHBHActionPerformed

    private void cboLoaiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiKHActionPerformed

    private void txtKhachTraBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachTraBHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhachTraBHActionPerformed

    private void btnTimKiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemKHActionPerformed
        // TODO add your handling code here:
        if (txtTimKiemKH.getText().trim().isEmpty()) {
            fillToTableKH();
        }
        int maKH;
        try {
            maKH = Integer.parseInt(txtTimKiemKH.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "L???i ?? t??m ki???m ph???i l?? s???");
            return;
        }
        if (maKH <= 0) {
            JOptionPane.showMessageDialog(this, "?? t??m ki???m ph???i l?? s??? l???n h??n 0!");
            return;
        }

        khachHangService.searchKH(maKH);
        modelKH = (DefaultTableModel) tblDSKhachHang.getModel();
        modelKH.setRowCount(0);
        List<KhachHang> khachHangs = khachHangService.getAllKH();
        int check = 0;
        for (KhachHang kh : khachHangs) {
            if (kh.getMaKH() == maKH) {
                check = 1;
                modelKH.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getGioiTinh() == 1 ? "Nam" : "N???", kh.getSdt(), kh.getDiaChi()});
            }
        }
        if (check == 1) {
            JOptionPane.showMessageDialog(this, "???? t??m th???y");
        } else {
            JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y");
            fillToTableKH();
        }
        reSetFromKhachHang();
    }//GEN-LAST:event_btnTimKiemKHActionPerformed

    private void cbbDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDMActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblQLDanhMuc.getSelectedRow();
        modelDM = ((DefaultTableModel) tblQLDanhMuc.getModel());
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                    "Vui l??ng ch???n 1 danh m???c ????? x??a",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int xacNhanXoa = JOptionPane.showConfirmDialog(this, "X??c nh???n x??a?", "X??a", JOptionPane.YES_NO_OPTION);
        if (xacNhanXoa == JOptionPane.YES_OPTION) {
            String idString = modelDM.getValueAt(selectedRow, 0).toString();

            int maDanhMuc = Integer.parseInt(idString);

            // luu thong tin xuong db
            danhMucService.deleteDM(maDanhMuc);
            // sua thong tin tren giao dien
            modelDM.removeRow(selectedRow);
            fillComBoBoxSP();
            JOptionPane.showMessageDialog(this, "X??a th??nh c??ng");
            reSetFromDanhMuc();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (btnTaoHDBH.isEnabled()) {
            JOptionPane.showMessageDialog(this, "B???n ch??a t???o ho?? ????n b??n h??ng");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "X??c nh???n thanh to??n ? ", "Thanh to??n", JOptionPane.YES_NO_OPTION);
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        int tongTien = Integer.parseInt(lblTongTienBH.getText().replace(".0", ""));
        Integer maKh = null;
        if (thongTinKH != null) {
            maKh = Integer.parseInt(thongTinKH.get(0).toString());
        }
        Integer khuyenMai = null;
        if (!btnGiamGiaBH.isEnabled()) {
            khuyenMai = Integer.parseInt(txtMaGiamGia.getText());
        }
        int maNv = Integer.parseInt(lblMaNV.getText());
        Integer soTienGiam = null;
        if (!lblGiamGiaBH.getText().trim().isEmpty()) {
            soTienGiam = Integer.parseInt(lblGiamGiaBH.getText().replace(".0", ""));
        }
        String ghiChu = txtGhiChuBH.getText().trim();
        if (txtTenKHBH.getText().trim().isEmpty()) {
            hoaDonChiTietService.insertBH(listGioHang, tongTien, null, khuyenMai, maNv, soTienGiam, ghiChu);
            clearFormBanHang();
            JOptionPane.showMessageDialog(this, "Thanh to??n th??nh c??ng");
            fillToHoaDon();
            thongTinThongKeThangHienTai();
            return;
        }
        hoaDonChiTietService.updateDiemTV(maKh);
        hoaDonChiTietService.insertBH(listGioHang, tongTien, maKh, khuyenMai, maNv, soTienGiam, ghiChu);
        clearFormBanHang();
        JOptionPane.showMessageDialog(this, "Thanh to??n th??nh c??ng");
        fillToHoaDon();
        thongTinThongKeThangHienTai();
    }//GEN-LAST:event_btnThanhToanActionPerformed
    private void clearFormBanHang() {
        btnLayThongTin.setEnabled(true);
        btnGiamGiaBH.setEnabled(true);
        btnSuDung.setEnabled(true);
        btnTaoHDBH.setEnabled(true);
        btnXoaBH.setEnabled(true);
        btnTaoHDBH.setEnabled(true);
        btnLamMoiBH.setEnabled(true);
        btnThemBH.setEnabled(true);
        cboLoaiKH.setEnabled(true);
        modelGioHang.setRowCount(0);
        thongTinKH = null;
        txtMaGiamGia.setText("");
        txtTenKHBH.setText("");
        cboLoaiKH.setSelectedIndex(0);
        txtDiemTV.setText("");
        lblGiamGiaBH.setText("");
        lblKhachCanTraBH.setText("");
        txtGhiChuBH.setText("");
    }

    private void btnXoaBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaBHActionPerformed
        // TODO add your handling code here:
        int index = tblgiohang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "B???n ch??a ch???n s???n ph???m c???n xo??");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "B???n mu???n xo?? s???n ph???m n??y kh???i gi??? h??ng ? ", "Xo?? s???n ph???m", JOptionPane.YES_NO_OPTION);
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        listGioHang.remove(index);
        modelGioHang.removeRow(index);
        JOptionPane.showMessageDialog(this, "???? xo?? kh???i gi??? h??ng");
    }//GEN-LAST:event_btnXoaBHActionPerformed

    private void btnTimKiemBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemBHActionPerformed
        // TODO add your handling code here:
        String values = txtTimKiemBH.getText().trim();
        if (values.isEmpty()) {
            fillToTableBHSP();
            return;
        }
        timKiemBHSP(values);
    }//GEN-LAST:event_btnTimKiemBHActionPerformed

    private void btnTaoHDBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDBHActionPerformed
        // TODO add your handling code here:
        if (listGioHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? g?? ????? t???o ho?? ????n");
            return;
        }
        String loaiKhachHang;
        loaiKhachHang = cboLoaiKH.getSelectedItem().toString();
        if (loaiKhachHang.equalsIgnoreCase("Kh??ch h??ng") && txtTenKHBH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "B???n ch??a ch???n t??n kh??ch h??ng");
            return;
        }
        double tong = 0;
        for (Vector x : listGioHang) {
            tong += Double.parseDouble(x.get(4).toString());
        }
        lblTongTienBH.setText(String.valueOf(tong));
        double giamGia = 0;
        double diemThanhVien = 0;
        if (!lblGiamGiaBH.getText().isBlank()) {
            giamGia = Double.parseDouble(lblGiamGiaBH.getText());
        }
        if (!btnSuDung.isEnabled()) {
            diemThanhVien = Double.parseDouble(txtDiemTV.getText());
        }
        lblGiamGiaBH.setText(String.valueOf(giamGia + diemThanhVien));
        lblKhachCanTraBH.setText(String.valueOf(tong - (giamGia + diemThanhVien)));
        btnTaoHDBH.setEnabled(false);
        btnThemBH.setEnabled(false);
        btnXoaBH.setEnabled(false);
        btnLamMoiBH.setEnabled(false);
        btnLayThongTin.setEnabled(false);
        cboLoaiKH.setEnabled(false);
        JOptionPane.showMessageDialog(this, "T???o ho?? ????n th??nh c??ng, Y??u c???u thanh to??n ????? t???o ho?? ????n m???i");
    }//GEN-LAST:event_btnTaoHDBHActionPerformed

    private void txtGhiChuBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGhiChuBHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuBHActionPerformed

    private void cboLoaiKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiKHMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cboLoaiKHMouseClicked

    private void txtPhiKhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhiKhacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhiKhacActionPerformed

    private void tbbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbangMouseClicked
        click();
    }//GEN-LAST:event_tbbangMouseClicked

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        checkchong();
        if (kt == 0) {
            JOptionPane.showMessageDialog(this, nvservice.insertNV(dulieuform()));
            fillToTableNV();
            clearFormNhanVien();
        }

    }//GEN-LAST:event_btnThemNVActionPerformed
    private void clearFormNhanVien() {
        tfmanv.setText("");
        tfhoten.setText("");
        tfluong.setText("");
        tfemail.setText("");
        tfmatkhau.setText("");
        tfquequan.setText("");
        tftaikhoan.setText("");
        dcNgaySinhNV.setDate(new Date());
        cbchucvu.setSelectedIndex(0);
        rdnam.setSelected(true);
    }
    private void btnTimKiemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNVActionPerformed
        // TODO add your handling code here:
        timKiemNV();
    }//GEN-LAST:event_btnTimKiemNVActionPerformed

    private void tblQLSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSanPhamMouseClicked
        // TODO add your handling code here:
        int index = tblQLSanPham.getSelectedRow();
        showDetailSP(index);
    }//GEN-LAST:event_tblQLSanPhamMouseClicked

    private void lblAnhSPSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhSPSPMouseClicked
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser("D:\\Java 3\\baitap\\DuAnAgile\\DuAnAgile\\src\\image");
        jfc.showOpenDialog(this);
        File f = jfc.getSelectedFile();
        if (f == null) {
            return;
        }
        anhSP = f.getAbsolutePath();
        if (anhSP == null) {
            return;
        }
        Image img;
        try {
            img = ImageIO.read(f);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Ph???i l?? ???nh");
            return;
        }
        try {
            lblAnhSPSP.setIcon(new ImageIcon(img.getScaledInstance(lblAnhSPSP.getWidth(), lblAnhSPSP.getHeight(), 0)));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "???? kh??ng ph???i ???nh");
        }
    }//GEN-LAST:event_lblAnhSPSPMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int index = tblQLSanPham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n s???n ph???m c???n x??a");
            return;
        }
        SanPham sp = getValuesSP();
        if (sp == null) {
            return;
        }
        sanPhamService.updateSP(sp);
        fillToTableSP();
        fillToTableBHSP();
        JOptionPane.showMessageDialog(this, "B???n ???? s???a th??nh c??ng");
        clearFormSanPham();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int index = tblQLSanPham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "B???n ch??a ch???n s???n ph???m c???n xo??");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c s??? xo?? s???n ph???m n??y kh??ng ?", "Xo?? s???n ph???m", JOptionPane.YES_NO_OPTION);
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        int maSp = Integer.parseInt(tblQLSanPham.getValueAt(index, 0).toString());
        try {
            sanPhamService.deleteSP(maSp);
        } catch (Exception e) {
            sanPhamService.deleteSPER(maSp);
        }
        fillToTableSP();
        fillToTableBHSP();
        JOptionPane.showMessageDialog(this, "B???n ???? xo?? th??nh c??ng");
        clearFormSanPham();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemKMActionPerformed
        timKM();
    }//GEN-LAST:event_btnTimKiemKMActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        clickKM();
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        // TODO add your handling code here:
        KhachHang kh = getValuesKH();
        if (kh == null) {
            return;
        }
        khachHangService.insertKH(kh);
        reSetFromKhachHang();
        fillToTableKH();
        JOptionPane.showMessageDialog(this, "B???n ???? th??m th??nh c??ng");
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void tblDSKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSKhachHangMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblDSKhachHang.getSelectedRow();
        modelKH = (DefaultTableModel) tblDSKhachHang.getModel();
        if (selectedRow < 0) {
            return;
        }
        String maKH = modelKH.getValueAt(selectedRow, 0).toString();
        String tenKH = modelKH.getValueAt(selectedRow, 1).toString();
        String gioiTinh = modelKH.getValueAt(selectedRow, 2).toString();
        String sdt = modelKH.getValueAt(selectedRow, 3).toString();
        String diaChi = modelKH.getValueAt(selectedRow, 4).toString();
        txtMaKH.setText(maKH);
        txtTenKH.setText(tenKH);
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rdoNamKH.setSelected(true);
        } else {
            rdoNuKH.setSelected(true);
        }
        txtSoDienThoaiKH.setText(sdt);
        txtDiaChiKH.setText(diaChi);
    }//GEN-LAST:event_tblDSKhachHangMouseClicked

    private void btnSuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKHActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblDSKhachHang.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n 1 kh??ch h??ng ????? s???a", "L???i", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KhachHang kh = getValuesKH();
        if (kh == null) {
            return;
        }
        Integer maKH = getKhachHangFromSelectedRow();
        int xacNhanSua = JOptionPane.showConfirmDialog(this, "X??c nh???n s???a?", "S???a", JOptionPane.YES_NO_OPTION);
        if (xacNhanSua == JOptionPane.YES_OPTION) {
            kh.setMaKH(maKH);
            khachHangService.upDateKH(kh);
            reSetFromKhachHang();
            fillToTableKH();
        }
        JOptionPane.showMessageDialog(this, "B???n ???? s???a th??nh c??ng");
    }//GEN-LAST:event_btnSuaKHActionPerformed

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        // TODO add your handling code here:
        DanhMuc dm = getValuesDM();
        if (dm == null) {
            return;
        }
        danhMucService.insertDM(dm);
        reSetFromDanhMuc();
        fillToTableDM();
        fillComBoBoxSP();
        JOptionPane.showMessageDialog(this, "B???n ???? th??m th??nh c??ng");
    }//GEN-LAST:event_btnADDActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        if (txtTimKiemDM.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p m?? danh m???c c???n t??m ki???m");
            fillToTableDM();
            reSetFromDanhMuc();
            return;
        }
        int maDanhMuc;
        try {
            maDanhMuc = Integer.parseInt(txtTimKiemDM.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "M?? danh m???c ph???i l?? s??? !");
            reSetFromDanhMuc();
            return;
        }
        DanhMuc dm = danhMucService.searchDM(maDanhMuc);
        if (dm != null && dm.getDaXoa() == 0) {

            modelDM.setRowCount(0);
            modelDM.addRow(new Object[]{
                dm.getMaDanhMuc(), dm.getTenDanhMuc(), dm.getMoTa()
            });

            txtMaDMSP.setText(String.valueOf(dm.getMaDanhMuc()));
            txtTenDMSP.setText(dm.getTenDanhMuc());
            txaMotaDMSP.setText(dm.getMoTa());
            JOptionPane.showMessageDialog(this, "???? t??m th???y");
        } else {
            JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y");
            reSetFromDanhMuc();
            fillToTableDM();
        }

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblQLDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLDanhMucMouseClicked
        // TODO add your handling code here:
        modelDM = (DefaultTableModel) tblQLDanhMuc.getModel();
        int selectedRow = tblQLDanhMuc.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        String maDanhMuc = modelDM.getValueAt(selectedRow, 0).toString();
        String tenDanhMuc = modelDM.getValueAt(selectedRow, 1).toString();
        String moTa = modelDM.getValueAt(selectedRow, 2).toString();

        txtMaDMSP.setText(maDanhMuc);
        txtTenDMSP.setText(tenDanhMuc);
        txaMotaDMSP.setText(moTa);
    }//GEN-LAST:event_tblQLDanhMucMouseClicked

    private void tblDSHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSHoaDonMouseClicked

        int index = tblDSHoaDon.getSelectedRow();
        int maHD = Integer.parseInt(tblModelHoaDon.getValueAt(index, 0).toString());

        List<HoaDonChiTiet> listHDCT = this.hoaDonChiTietService.timHDCTTheoMaHD(maHD);
        tblModelHoaDonCT.setRowCount(0);
        for (int i = 0; i < listHDCT.size(); i++) {
            HoaDonChiTiet hdct = listHDCT.get(i);
            int maSP = hdct.getMaSP();
            SanPham sp = this.hoaDonChiTietService.timSanPhamTheoMa(maSP);
            tblModelHoaDonCT.addRow(new Object[]{i + 1,
                maSP,
                sp.getTenSanPham(),
                hdct.getSoLuongMua(),
                hdct.getDonGia(),
                hdct.getTongTien()});
        }

    }//GEN-LAST:event_tblDSHoaDonMouseClicked

    private void btnxuatBCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuatBCActionPerformed
        try {
            XSSFWorkbook wordkbook = new XSSFWorkbook();
            XSSFSheet sheet = wordkbook.createSheet("danhsach");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(2);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("TH???NG K?? DOANH THU");
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("T???ng Doanh thu: " + tinhTongDoanhThu());
            row = sheet.createRow(4);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("T???ng H??a ????n: " + tinhTongSoHoaDon());
            row = sheet.createRow(5);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Ng??y t???o");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("S??? h??a ????n");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("T???ng ti???n");
            List<ThongKeDoanhThu> listDT = layDanhSachTKTrenForm();
            for (int i = 0; i < listDT.size(); i++) {
                row = sheet.createRow(5 + i);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(epKieuDate_ddMMyyy(listDT.get(i).getNgayTao()));
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(listDT.get(i).getSoHoaDon());
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(listDT.get(i).getTongTien());
            }
            JFileChooser fchoChooser = new JFileChooser();
            int result = fchoChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String duongDanhAnh = fchoChooser.getSelectedFile().getPath();
                File f = new File(duongDanhAnh);
                FileOutputStream fis = new FileOutputStream(f + ".xlsx");
                wordkbook.write(fis);
                fis.close();
            }
            JOptionPane.showMessageDialog(this, "Xu???t b??o c??o th??nh c??ng");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "C?? l???i x???y ra");
        }
    }//GEN-LAST:event_btnxuatBCActionPerformed

    private void cbbSapXepTKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSapXepTKItemStateChanged
        List<ThongKeDoanhThu> list = layDanhSachTKTrenForm();
        if (cbbSapXepTK.getSelectedItem().toString().equalsIgnoreCase("Ng??y")) {
            list.sort((ThongKeDoanhThu o1, ThongKeDoanhThu o2) -> o1.getNgayTao().compareTo(o2.getNgayTao()));
            fillDaTaThongKe(list);
        }
        if (cbbSapXepTK.getSelectedItem().toString().equalsIgnoreCase("S??? h??a ????n")) {
            list.sort((ThongKeDoanhThu o1, ThongKeDoanhThu o2) -> o2.getSoHoaDon() - o1.getSoHoaDon());
            fillDaTaThongKe(list);
        }
        if (cbbSapXepTK.getSelectedItem().toString().equalsIgnoreCase("T???ng doanh thu")) {
            list.sort((ThongKeDoanhThu o1, ThongKeDoanhThu o2) -> (int) o2.getTongTien() - (int) o1.getTongTien());
            fillDaTaThongKe(list);
        }
    }//GEN-LAST:event_cbbSapXepTKItemStateChanged

    private void cbbLocTKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLocTKItemStateChanged
        List<ThongKeSanPham> listTKSP = null;
        List<ThongKeSanPham> listKhongBanDuoc = null;
        if (dcNgayBatDauTK.getDate() == null && dcNgayKetThucTK.getDate() == null) {
            listTKSP = this.thongKeService.getAllSanPham();
            listKhongBanDuoc = this.thongKeService.getAllSanPhamKhongBanDuoc();
        } else {
            Date dateBegin = getDateChooser(dcNgayBatDauTK, "ng??y b???t ?????u");
            Date dateEnd = getDateChooser(dcNgayKetThucTK, "ng??y k???t th??c");
            String dateBeginString = epKieuDateString(dateBegin);
            String dateEndString = epKieuDateString(dateEnd);
            listTKSP = layDanhSachSPTheoNgay(dateBeginString, dateEndString);
            listKhongBanDuoc = this.thongKeService.laySanPhamKhongBanDuoc(dateBeginString, dateEndString);
        }
        if (cbbLocTK.getSelectedItem().toString().equalsIgnoreCase("T???t c???")) {
            listTKSP.sort((ThongKeSanPham o1, ThongKeSanPham o2) -> o1.getStt() - o2.getStt());
            fillDataThongKeSP(listTKSP);
        }
        if (cbbLocTK.getSelectedItem().toString().equalsIgnoreCase("B??n ch???y")) {
            listTKSP.sort((ThongKeSanPham o1, ThongKeSanPham o2) -> o2.getSoLuongBan() - o1.getSoLuongBan());
            fillDataThongKeSP(listTKSP);
        }
        if (cbbLocTK.getSelectedItem().toString().equalsIgnoreCase("Kh??ng b??n ???????c")) {
            listKhongBanDuoc.sort((ThongKeSanPham o1, ThongKeSanPham o2) -> o2.getSoLuongBan() - o1.getSoLuongBan());
            fillDataThongKeSP(listKhongBanDuoc);
        }
    }//GEN-LAST:event_cbbLocTKItemStateChanged

    private void btnDoiMatKhauTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauTKActionPerformed
        int maNV = Integer.parseInt(lblMaNV.getText());
        String matKhauCu = txtMatKhauCu.getText();
        if (matKhauCu.isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p m???t kh???u c??");
            return;
        }
        String matKhauMoi = txtMatKhauMoi.getText();
        if (matKhauMoi.isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p m???t kh???u m???i");
            return;
        }
        String nhapLaiMK = txtNhapLaiMatKhauMoi.getText();
        if (nhapLaiMK.isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p l???i m???t kh???u m???i");
            return;
        }
        NhanVien nv = nhanVienService.timNVTheoMa(maNV);
        if (!nv.getMatkhau().equals(matKhauCu)) {
            JOptionPane.showMessageDialog(this, "M???t kh???u c?? kh??ng ????ng. Vui l??ng nh???p l???i");
            txtMatKhauCu.requestFocus();
            return;
        }
        if (!matKhauMoi.equals(nhapLaiMK)) {
            JOptionPane.showMessageDialog(this, "M???t kh???u m???i kh??ng tr??ng kh???p. Vui l??ng nh???p l???i");
            return;
        }
        boolean isCheck = nhanVienService.doiMatKhau(maNV, matKhauMoi);
        if (isCheck) {
            JOptionPane.showMessageDialog(this, "?????i m???t kh???u th??nh c??ng");
            clearFormMatKhau();
        }
    }//GEN-LAST:event_btnDoiMatKhauTKActionPerformed
    private void clearFormMatKhau() {
        txtMatKhauCu.setText("");
        txtMatKhauMoi.setText("");
        txtNhapLaiMatKhauMoi.setText("");
    }
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        try {
            txtTenKHBH.setText(thongTinKH.get(1).toString());
            txtDiemTV.setText(thongTinKH.get(2).toString());
        } catch (Exception e) {
            System.out.println("Kh??ng c?? g??");
        }
    }//GEN-LAST:event_formWindowActivated

    private void cboLoaiKHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiKHItemStateChanged
        // TODO add your handling code here:
        int loaiKhachHang = cboLoaiKH.getSelectedIndex();
        if (loaiKhachHang == 0) {
            btnLayThongTin.setEnabled(true);
            btnSuDung.setEnabled(true);
        } else {
            btnLayThongTin.setEnabled(false);
            txtTenKHBH.setText("");
            txtDiemTV.setText("");
            try {
                thongTinKH = null;
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_cboLoaiKHItemStateChanged

    private void txtPhiKhacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhiKhacKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtPhiKhacKeyPressed

    private void btnGiamGiaBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiamGiaBHActionPerformed
        // TODO add your handling code here:
        String maGiamGia = txtMaGiamGia.getText().trim();
        if (maGiamGia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "B???n ch??a nh???p m?? gi???m gi??");
            return;
        }
        int ma = 0;
        try {
            ma = Integer.parseInt(maGiamGia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sai ?????nh d???ng");
            return;
        }
        Vector v = khuyenMaiService.getGiamGia(ma);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "Kh??ng c?? m?? gi???m gi?? n??y");
            return;
        }
        if (v.get(3).toString().equals(1)) {
            JOptionPane.showMessageDialog(this, "M?? gi???m gi?? n??y ???? h???t h???n");
            return;
        }
        lblGiamGiaBH.setText(v.get(2).toString());
        if (!lblTongTienBH.getText().trim().isEmpty()) {
            lblKhachCanTraBH.setText(String.valueOf(Integer.parseInt(lblTongTienBH.getText().replace(".0", "")) - Integer.parseInt(v.get(2).toString())));
        }
        btnGiamGiaBH.setEnabled(false);
        JOptionPane.showMessageDialog(this, "B???n ???? ??p d???ng th??nh c??ng");

    }//GEN-LAST:event_btnGiamGiaBHActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        String values = txtTimKiemSP.getText().trim();
        if (values.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p m?? ho???c t??n s???n ph???m c???n t??m ki???m");
            fillToTableSP();
            clearFormSanPham();
            return;
        }
        modelSP.setRowCount(0);
        List<SanPham> list = sanPhamService.timKiemSP(values);
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y s???n ph???m. Vui l??ng nh???p l???i!");
            txtTimKiemSP.setText("");
            txtTimKiemSP.requestFocus();
            fillToTableSP();
            clearFormSanPham();
        } else {
            for (SanPham x : list) {
                modelSP.addRow(new Object[]{x.getMaSanPham(), x.getTenSanPham(), x.getDonGia(),
                    sanPhamService.getTenDM(x.getMaDanhMuc()),
                    x.isTrangThai() ? "C??n h??ng" : "H???t h??ng", x.getMota(), x.getAnh()
                });
            }
        }

    }//GEN-LAST:event_btnTimkiemActionPerformed
    private DanhMuc getValuesDM() {
        DanhMuc dm = new DanhMuc();
        String tenDM = txtTenDMSP.getText();
        if (tenDM.isBlank()) {
            JOptionPane.showMessageDialog(this, "T??n danh m???c kh??ng ???????c r???ng!");
            return null;
        }
        if (tenDM.length() > 50) {
            JOptionPane.showMessageDialog(this, "T??n danh m???c kh??ng ???????c v?????t qu?? 50 k?? t???!");
            return null;
        }
        String moTa = txaMotaDMSP.getText();
        if (moTa.isBlank()) {
            JOptionPane.showMessageDialog(this, "M?? t??? kh??ng ???????c r???ng!");
            return null;
        }
        //T???m th???i ko ?????ng v??o
        dm.setTenDanhMuc(tenDM);
        dm.setMoTa(moTa);
        return dm;
    }

    public void reSetFromDanhMuc() {
        txtMaDMSP.setText("");
        txtTenDMSP.setText("");
        txaMotaDMSP.setText("");
        txtTimKiemDM.setText("");
    }

    public Integer getKhachHangFromSelectedRow() {
        Integer row = tblDSKhachHang.getSelectedRow();
        Integer maKH = (Integer) tblDSKhachHang.getValueAt(row, 0);
        return maKH;
    }

    private void reSetFromKhachHang() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        rdoNamKH.setSelected(true);
        txtSoDienThoaiKH.setText("");
        txtDiaChiKH.setText("");
        txtTimKiemKH.setText("");
    }

    private KhachHang getValuesKH() {
        KhachHang kh = new KhachHang();

        String tenKH = txtTenKH.getText();
        if (tenKH.isBlank()) {
            JOptionPane.showMessageDialog(this, "T??n kh??ch h??ng kh??ng ???????c r???ng!");
            return null;
        }
        if (tenKH.length() > 50) {
            JOptionPane.showMessageDialog(this, "T??n kh??ch h??ng kh??ng ???????c v?????t qu?? 50 k?? t???!");
            return null;
        }
        int gioiTinh = rdoNamKH.isSelected() == true ? 1 : 0;
        String sdt = txtSoDienThoaiKH.getText();
        if (sdt.isBlank()) {
            JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng ???????c r???ng!");
            return null;
        }
        Pattern pSDT = Pattern.compile("^[0-9]+$");
        if (!pSDT.matcher(sdt).find()) {
            JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i ph???i l?? s??? v?? kh??ng c?? kho???ng tr???ng!");
            return null;
        }
        if (sdt.length() < 10 || sdt.length() > 15) {
            JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i ph???i d??i t??? 10 - 15 k?? t???!");
            return null;
        }
        String diaChi = txtDiaChiKH.getText();
        if (diaChi.isBlank()) {
            JOptionPane.showMessageDialog(this, "?????a ch??? kh??ng ???????c r???ng!");
            return null;
        }
        //T???m th???i ko ?????ng v??o
        kh.setTenKH(tenKH);
        kh.setGioiTinh(gioiTinh);
        kh.setSdt(sdt);
        kh.setDiaChi(diaChi);
        return kh;
    }

    //th???ng k??
    // validate th??ng tin ng??y nh???p
    public Date getDateChooser(JDateChooser dateChooser, String errMesg) {
        String s = ((JTextField) dateChooser.getDateEditor()).getText();
        Date date = dateChooser.getDate();
        if (s.isBlank()) {
            dateChooser.setCalendar(null);
            JOptionPane.showMessageDialog(this, "Xin m???i nh???p ng??y ho???c ch???n ng??y " + errMesg, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (date == null) {
            dateChooser.setCalendar(null);
            dateChooser.setFocusable(true);
            JOptionPane.showMessageDialog(this, "L???i " + errMesg + ". M???i nh???p l???iho???c ch???n l???i " + errMesg, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return date;
    }

    private void fillDaTaThongKe(List<ThongKeDoanhThu> listThongKe) {
        tblModelThongKe.setRowCount(0);
        for (ThongKeDoanhThu tk : listThongKe) {
            Date ngayTao = tk.getNgayTao();
            String ngayTaoString = epKieuDate_ddMMyyy(ngayTao);
            int tongHoaDon = tk.getSoHoaDon();
            double tongTien = tk.getTongTien();
            tblModelThongKe.addRow(new Object[]{ngayTaoString, tongHoaDon, tongTien});
        }
    }

    private void fillDataThongKeSP(List<ThongKeSanPham> listSP) {
        tblModelThongKeSP.setRowCount(0);
        for (ThongKeSanPham tksp : listSP) {
            int stt = tksp.getStt();
            String tenSP = tksp.getTenSP();
            int soLuongBan = tksp.getSoLuongBan();
            tblModelThongKeSP.addRow(new Object[]{stt, tenSP, soLuongBan});
        }
    }

    private List<ThongKeDoanhThu> layDanhSachTKTrenForm() {
        List<ThongKeDoanhThu> list = new ArrayList<>();
        for (int i = 0; i < tblModelThongKe.getRowCount(); i++) {
            String ngayTaoString = tblModelThongKe.getValueAt(i, 0).toString();
            Date ngayTao = null;
            try {
                ngayTao = new SimpleDateFormat("dd-MM-yyyy").parse(ngayTaoString);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            int soHD = Integer.parseInt(tblModelThongKe.getValueAt(i, 1).toString());
            double soDT = Double.parseDouble(tblModelThongKe.getValueAt(i, 2).toString());
            ThongKeDoanhThu tkdt = new ThongKeDoanhThu(ngayTao, soHD, soDT);
            list.add(tkdt);
        }
        return list;
    }

    private List<ThongKeSanPham> layDanhSachSPTrenForm() {
        List<ThongKeSanPham> list = new ArrayList<>();
        for (int i = 0; i < tblModelThongKeSP.getRowCount(); i++) {
            int stt = Integer.parseInt(tblModelThongKeSP.getValueAt(i, 0).toString());
            String tenSP = tblModelThongKeSP.getValueAt(i, 1).toString();
            int soLuongBan = Integer.parseInt(tblModelThongKeSP.getValueAt(i, 2).toString());
            ThongKeSanPham tksp = new ThongKeSanPham(stt, tenSP, soLuongBan);
            list.add(tksp);
        }
        return list;
    }

    private List<ThongKeDoanhThu> layDanhSachHDTheoNgay(String dateBeginString, String dateEndString) {
        List<ThongKeDoanhThu> listHoaDonNgay = this.thongKeService.layDanhSachHoaDonTheoNgay(dateBeginString, dateEndString);
        return listHoaDonNgay;
    }

    private List<ThongKeSanPham> layDanhSachSPTheoNgay(String date1String, String date2String) {
        List<ThongKeSanPham> listSP = thongKeService.layDanhSachSPTheoNgay(date1String, date2String);
        return listSP;
    }

    private String epKieuDateString(Date date) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    private String epKieuDate_ddMMyyy(Date date) {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    private void dienThongTinTongHoaDonVaDoanhThu() {
        int tongSoHoaDon = tinhTongSoHoaDon();
        lblTongSoHoaDonTK.setText(tongSoHoaDon + " h??a ????n");
        double tongDoanhThu = tinhTongDoanhThu();
        lblTongDoanhThuTK.setText(tongDoanhThu + " VND");
    }

    private int tinhTongSoHoaDon() {
        int tongHD = 0;
        int soHD = 0;
        for (int i = 0; i < tblModelThongKe.getRowCount(); i++) {
            soHD = Integer.parseInt(tblModelThongKe.getValueAt(i, 1).toString());
            tongHD += soHD;
        }
        return tongHD;
    }

    private double tinhTongDoanhThu() {
        double tongDT = 0;
        double doanhThu = 0;
        for (int i = 0; i < tblModelThongKe.getRowCount(); i++) {
            doanhThu = Double.parseDouble(tblModelThongKe.getValueAt(i, 2).toString());
            tongDT += doanhThu;
        }
        return tongDT;
    }

    private void setDateChooser() {
        //l???y ng??y hi???n t??i
        Date nowDate = new Date();
        Date beginDate = getFirstDateOfMonth(nowDate);
        dcNgayBatDauTK.setDate(beginDate);
        dcNgayKetThucTK.setDate(nowDate);
    }

    //l???y ng??y ?????u ti??n trong th??ng
    public static Date getFirstDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHang().setVisible(true);
            }
        });
    }

    private void hoverMouse() {
        File file = new File("logots.jpg");
        File fileBieuDo = new File("bieudo.png");
        try {
            Image img = ImageIO.read(file);
            lblAnhDaiDien.setIcon(new ImageIcon(img.getScaledInstance(lblAnhDaiDien.getWidth(), lblAnhDaiDien.getHeight(), 0)));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JButton btn[] = {btnBanHang, btnDangXuat, btnHoaDon, btnKhachHang, btnKhuyenMai, btnNhanVien, btnThongKe, btnSanPham, btnDoiMatKhau, btnThoat};
        for (JButton x : btn) {
            x.setFocusPainted(false);
            x.setUI(new BasicButtonUI());
            x.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    x.setBackground(Color.white);
                    x.setForeground(Color.red);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    x.setBackground(new java.awt.Color(204, 255, 255));
                    x.setForeground(Color.black);
                }
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGioiTinhNV;
    private javax.swing.ButtonGroup btgtrangthaiKM;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnDoiMatKhauTK;
    private javax.swing.JButton btnGiamGiaBH;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnKhuyenMai;
    private javax.swing.JButton btnLamMoiBH;
    private javax.swing.JButton btnLamMoiTK;
    private javax.swing.JButton btnLayThongTin;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSuDung;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnSuaKM;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnTaoHDBH;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemBH;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnThemKM;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemBH;
    private javax.swing.JButton btnTimKiemKH;
    private javax.swing.JButton btnTimKiemKM;
    private javax.swing.JButton btnTimKiemLTT;
    private javax.swing.JButton btnTimKiemNV;
    private javax.swing.JButton btnTimKiemTK;
    private javax.swing.JButton btnTimMaHoaDon;
    private javax.swing.JButton btnTimMaKhachHang;
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnXacNhanThongTin;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaBH;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JButton btnXoaKM;
    private javax.swing.JButton btnXoaNhanVien;
    private javax.swing.JButton btnback;
    private javax.swing.ButtonGroup btngrtrangthai;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton btnxuatBC;
    private javax.swing.JComboBox<String> cbbDM;
    private javax.swing.JComboBox<String> cbbDanhmuc;
    private javax.swing.JComboBox<String> cbbLocTK;
    private javax.swing.JComboBox<String> cbbSapXepTK;
    private javax.swing.JComboBox<String> cbbdongia;
    private javax.swing.JComboBox<String> cbbtrangthai;
    private javax.swing.JComboBox<String> cbchucvu;
    private javax.swing.JComboBox<String> cboLoaiKH;
    private com.toedter.calendar.JDateChooser dcNgayBatDauKM;
    private com.toedter.calendar.JDateChooser dcNgayBatDauTK;
    private com.toedter.calendar.JDateChooser dcNgayKetThucKM;
    private com.toedter.calendar.JDateChooser dcNgayKetThucTK;
    private com.toedter.calendar.JDateChooser dcNgaySinhNV;
    private javax.swing.JLabel giamgia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblAnhDaiDien;
    private javax.swing.JLabel lblAnhSPSP;
    private javax.swing.JLabel lblAnhSp;
    private javax.swing.JLabel lblDanhMuc;
    private javax.swing.JLabel lblDanhMuc2;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblDonGia2;
    private javax.swing.JLabel lblGiamGiaBH;
    private javax.swing.JLabel lblKhachCanTraBH;
    private javax.swing.JLabel lblMaDanhMuc2;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMaSp;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblMota2;
    private javax.swing.JLabel lblNgayTaoBH;
    private javax.swing.JLabel lblTenDanhMuc2;
    private javax.swing.JLabel lblTenSp;
    private javax.swing.JLabel lblTienTraLaiBH;
    private javax.swing.JLabel lblTongDoanhThuTK;
    private javax.swing.JLabel lblTongSoHoaDonTK;
    private javax.swing.JLabel lblTongTienBH;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblTrangThai2;
    private javax.swing.JLabel lblgiamgiaVND;
    private javax.swing.JPanel lblimg;
    private javax.swing.JLabel lblkhachcantraVND;
    private javax.swing.JLabel lbltientralaiVND;
    private javax.swing.JLabel lbltongtienVND;
    private java.awt.Panel panel1;
    private java.awt.Panel panel3;
    private javax.swing.JPanel pnlAnhDaiDien;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlBieuDo;
    private javax.swing.JPanel pnlCacGiaoDien;
    private javax.swing.JPanel pnlDoiMatKhau;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlKhuyenMai;
    private javax.swing.JPanel pnlLayThongTin;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JRadioButton rdnam;
    private javax.swing.JRadioButton rdnu;
    private javax.swing.JRadioButton rdoConhang;
    private javax.swing.JRadioButton rdoHethang;
    private javax.swing.JRadioButton rdoNamKH;
    private javax.swing.JRadioButton rdoNuKH;
    private javax.swing.JTable tbbang;
    private javax.swing.JTable tblDSHoaDon;
    private javax.swing.JTable tblDSKhachHang;
    private javax.swing.JTable tblDSSanpham;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTable tblLayThongTin;
    private javax.swing.JComboBox<String> tblLoaiThoiGian;
    private javax.swing.JTable tblQLDanhMuc;
    private javax.swing.JTable tblQLSanPham;
    private javax.swing.JTable tblThongKeSP;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTextField tfemail;
    private javax.swing.JTextField tfhoten;
    private javax.swing.JTextField tfluong;
    private javax.swing.JTextField tfmanv;
    private javax.swing.JTextField tfmatkhau;
    private javax.swing.JTextField tfquequan;
    private javax.swing.JTextField tftaikhoan;
    private javax.swing.JTextField tftimkiem;
    private javax.swing.JTextArea txaMotaDMSP;
    private javax.swing.JTextArea txtDiaChiKH;
    private javax.swing.JTextField txtDiemTV;
    private javax.swing.JTextField txtDongia;
    private javax.swing.JTextField txtGhiChuBH;
    private javax.swing.JTextField txtKhachTraBH;
    private javax.swing.JTextField txtMaDMSP;
    private javax.swing.JTextField txtMaGiamGia;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtMasp;
    private javax.swing.JTextField txtMatKhauCu;
    private javax.swing.JTextField txtMatKhauMoi;
    private javax.swing.JTextArea txtMota;
    private javax.swing.JTextField txtMucGiamGiaPT;
    private javax.swing.JTextField txtNhapLaiMatKhauMoi;
    private javax.swing.JTextField txtPhiKhac;
    private javax.swing.JTextField txtSoDienThoaiKH;
    private javax.swing.JTextField txtTenDMSP;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenKHBH;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTensp;
    private javax.swing.JTextField txtTimKiemBH;
    private javax.swing.JTextField txtTimKiemDM;
    private javax.swing.JTextField txtTimKiemHD;
    private javax.swing.JTextField txtTimKiemKH;
    private javax.swing.JTextField txtTimKiemKM;
    private javax.swing.JTextField txtTimKiemLTT;
    private javax.swing.JTextField txtTimKiemSP;
    // End of variables declaration//GEN-END:variables

}
