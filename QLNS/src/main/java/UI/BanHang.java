/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Connect.CTHD_Connect;
import Connect.HoaDon_Connect;
import Connect.KhachHang_Connect;
import Connect.NXB_Connect;
import Connect.Sach_Connect;
import Connect.TaiKhoan_Connect;
import Connect.VanPhongPham_Connect;
import Model.CTHD;
import Model.HoaDon;
import Model.KhachHang;
import Model.NXB;
import Model.Sach;
import Model.TaiKhoan;
import Model.VPP;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author dat
 */
public class BanHang extends javax.swing.JFrame {
    private String MaHD=null;
    private String MaNV= null;
    private TaiKhoan tk = new TaiKhoan();
    private ArrayList<NXB> dsnxb = null;
    private ArrayList<CTHD> cthd = new ArrayList<CTHD>(); 
    private ArrayList<Sach> dssMaTen = null;
    private ArrayList<Sach> dss_nxb = null;
    private ArrayList<String> dsdm = null;
    private ArrayList<Sach> dss_tensach = null;
    private ArrayList<Sach> dss = null;
    private ArrayList<VPP> dsvpp=null;
    private DefaultTableModel dtmSach , dtmHoaDon, dtmVPP;
    private ArrayList<VPP> dssp_ten=null;
    private ArrayList<VPP> dssp_dm=null;
    private ArrayList<VPP> dssp_ten_dm=null;
    private String makh = "";
    /**
     * Creates new form BanHang
     */
    public BanHang(String title,String maNV) {
        initComponents();
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        MaNV=maNV;
        TaiKhoan_Connect tk_conn = new TaiKhoan_Connect();
        tk = tk_conn.timTaiKhoanBangMaNV(MaNV);
        hienThiToanBoSach();
        hienThiToanBoNhaXuatBan();
        TaoBangCTHD();
        hienThiTatCaVPP();
        hienThiTatCaDanhMuc();
    }
    
    private void hienThiToanBoNhaXuatBan() {
        NXB_Connect nxbconn = new NXB_Connect();
        dsnxb=nxbconn.layToanBoNhaXuatBan();
        NXBInput.removeAllItems();
        NXB nxb = new NXB();
        nxb.setMaNXB("0");
        nxb.setTenNXB("Tất cả");
        NXBInput.addItem(nxb);
        for(NXB s : dsnxb) NXBInput.addItem(s); 	
    }
    
    private void hienThiToanBoSach() {
        Sach_Connect sachConn = new Sach_Connect();
        dss = sachConn.layToanBoSach();
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã sách");
        dtmSach.addColumn("Tên Sách");
        dtmSach.addColumn("Thể loại");
        dtmSach.addColumn("Tác giả");
        dtmSach.addColumn("Số lượng");
        dtmSach.addColumn("Giá Bán");
        dtmSach.setRowCount(0);
        for (Sach s : dss){
            Vector<Object> vec = new Vector<Object>();
            vec.add(s.getMaSach());
            vec.add(s.getTenSach());
            vec.add(s.getTheLoai());
            vec.add(s.getTacGia());
            vec.add(s.getSoLuong());
            vec.add(s.getGiaBan());
            dtmSach.addRow(vec);	
        }
        TableSach.setModel(dtmSach);
    }
    
    private void hienThiTatCaVPP(){
        VanPhongPham_Connect vpp_conn = new VanPhongPham_Connect();
        dsvpp = vpp_conn.layToanBoVPP();
        dtmVPP = new DefaultTableModel();
        dtmVPP.addColumn("Mã sản phẩm");
        dtmVPP.addColumn("Tên sản phẩm");
        dtmVPP.addColumn("Danh mục");
        dtmVPP.addColumn("Số lượng");
        dtmVPP.addColumn("Giá Bán");
        dtmVPP.setRowCount(0);
        for(VPP vpp: dsvpp){
            Vector<Object> vec = new Vector<Object>();
            vec.add(vpp.getMaVPP());
            vec.add(vpp.getTenVPP());
            vec.add(vpp.getDanhMuc());
            vec.add(vpp.getSoLuong());
            vec.add(vpp.getGiaBan());
            dtmVPP.addRow(vec);
        }
        SPTable.setModel(dtmVPP);
    }
    
    private void hienThiTatCaDanhMuc() {
        VanPhongPham_Connect vpp_conn = new VanPhongPham_Connect();
        dsdm = vpp_conn.layTatCaDanhMuc();
        DanhMucInput.addItem("Tất cả");
        for(String dm: dsdm) DanhMucInput.addItem(dm);
    }
    
    private void TaoBangCTHD (){
        dtmHoaDon = new DefaultTableModel();
        dtmHoaDon.addColumn("Tên sách");
        dtmHoaDon.addColumn("Số lượng");
        dtmHoaDon.addColumn("Thành tiền");
        TableCTHD.setModel(dtmHoaDon);
    }
    
    private void TaoHD(){
        String HD = "HD";
        //tạo hóa đơn với mã hóa đơn mới tính từ hóa đơn cuối cùng
        HoaDon_Connect tHD = new HoaDon_Connect();
        String lastmahd = tHD.LastMaHD();
        if (lastmahd==null) MaHD= "HD01";
        else {
            int sohd = Integer.parseInt(lastmahd.substring(2))+1; //bỏ đi hai chữ "HD" và công thêm 1 vào hai số phía sau
            if (sohd<10) MaHD = HD+"0"+String.valueOf(sohd);
            else MaHD = HD+String.valueOf(sohd);
        }                                               
        HoaDon hd = new HoaDon();
        hd.setMaHD(MaHD);
        hd.setMaNV(MaNV);
        hd.setMaKH(makh);
        Date date = new Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(date);
        hd.setNgaylap(currentTime);
        hd.setTrangThai(0);
        hd.setNhapSach(0);
        tHD.TaoHD(hd); 
    }
    
    private void ThemCTHD(boolean sach) {
        Vector<Object> vec = new Vector<Object>();
        CTHD vec1 = new CTHD();
        vec1.setMaHD(MaHD);
        if(sach){ //nếu trả về true thì đang thêm sách còn là false thì là thêm sản phẩm mới
            vec1.setMaSP(TableSach.getValueAt(TableSach.getSelectedRow(), 0).toString());
            vec1.setDonGia(Double.parseDouble(TableSach.getValueAt(TableSach.getSelectedRow(), 5).toString()));
            int vitriSach=-1;
    //        tính giá tiền
            double tien = Long.parseLong(SLInput.getValue().toString())*Double.parseDouble(TableSach.getValueAt(TableSach.getSelectedRow(), 5).toString()); 
    //        kiểm tra xem sách đang được chọn có nằm trong hóa đơn chưa
            for (int i=0;i<dtmHoaDon.getRowCount();i++) {
                if (dtmHoaDon.getValueAt(i, 0).equals(TableSach.getValueAt(TableSach.getSelectedRow(), 1))) vitriSach=i;
            }
    //        nếu đã có trong hóa đơn thì cập nhật lại
            if (vitriSach!=-1){
                vec1.setSoLuong(Integer.parseInt(SLInput.getValue().toString())+Integer.parseInt(dtmHoaDon.getValueAt(vitriSach, 1).toString()));
                vec1.setThanhTien(tien+Double.parseDouble(dtmHoaDon.getValueAt(vitriSach, 2).toString()));
                dtmHoaDon.setValueAt((tien+Double.parseDouble(dtmHoaDon.getValueAt(vitriSach, 2).toString())), vitriSach, 2);
                dtmHoaDon.setValueAt(Integer.parseInt(SLInput.getValue().toString())+Integer.parseInt(dtmHoaDon.getValueAt(vitriSach, 1).toString()), vitriSach, 1);                               
                cthd.set(vitriSach, vec1);
            }
    //        nếu chưa có thì tạo dòng mới
            else{                        
                vec.add(TableSach.getValueAt(TableSach.getSelectedRow(), 1));
                vec.add(SLInput.getValue());                                                                     
                vec.add(tien);
                dtmHoaDon.addRow(vec);                       
                vec1.setSoLuong(Integer.parseInt(SLInput.getValue().toString()));                        
                vec1.setThanhTien(tien);                        
                cthd.add(vec1);
            }

            if (!TotalInput.getText().equals(""))
            tien = Double.parseDouble(TotalInput.getText())+tien;
            TotalInput.setText(String.valueOf(tien));                                              
            SLInput.setValue(1);
        }
        else{
            vec1.setMaSP(SPTable.getValueAt(SPTable.getSelectedRow(), 0).toString());
            vec1.setDonGia(Double.parseDouble(SPTable.getValueAt(SPTable.getSelectedRow(), 4).toString()));
            int vitriSP=-1;
    //        tính giá tiền
            double tien = Long.parseLong(SLSPInput.getValue().toString())*Double.parseDouble(SPTable.getValueAt(SPTable.getSelectedRow(), 4).toString()); 
    //        kiểm tra xem sản phẩm đang được chọn có nằm trong hóa đơn chưa
            for (int i=0;i<dtmHoaDon.getRowCount();i++) {
                if (dtmHoaDon.getValueAt(i, 0).equals(SPTable.getValueAt(SPTable.getSelectedRow(), 1))){
                    vitriSP=i;
                    break;
                }
            }
    //        nếu đã có trong hóa đơn thì cập nhật lại
            if (vitriSP!=-1){
                vec1.setSoLuong(Integer.parseInt(SLSPInput.getValue().toString())+Integer.parseInt(dtmHoaDon.getValueAt(vitriSP, 1).toString()));
                vec1.setThanhTien(tien+Double.parseDouble(dtmHoaDon.getValueAt(vitriSP, 2).toString()));
                dtmHoaDon.setValueAt((tien+Double.parseDouble(dtmHoaDon.getValueAt(vitriSP, 2).toString())), vitriSP, 2);
                dtmHoaDon.setValueAt(Integer.parseInt(SLSPInput.getValue().toString())+Integer.parseInt(dtmHoaDon.getValueAt(vitriSP, 1).toString()), vitriSP, 1);                               
                cthd.set(vitriSP, vec1);
            }
    //        nếu chưa có thì tạo dòng mới
            else{                        
                vec.add(SPTable.getValueAt(SPTable.getSelectedRow(), 1));
                vec.add(SLSPInput.getValue());                                                                     
                vec.add(tien);
                dtmHoaDon.addRow(vec);                       
                vec1.setSoLuong(Integer.parseInt(SLSPInput.getValue().toString()));                        
                vec1.setThanhTien(tien);                        
                cthd.add(vec1);
            }

            if (!TotalInput.getText().equals(""))
            tien = Double.parseDouble(TotalInput.getText())+tien;
            TotalInput.setText(String.valueOf(tien));                                              
            SLSPInput.setValue(1);
        }
    }
    
    //hàm định dạng tiền tệ
    public String formatCurrency(double amount) {
        // Định dạng số có dấu phân cách và đơn vị tiền tệ
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedAmount = formatter.format(amount);

        return formattedAmount;
    }
    
    //hàm loại bỏ dấu phẩy và chữ đ trong tiền tệ
    public String removeCurrencyFormatting(String formattedAmount) {
        // Loại bỏ các ký tự không phải số và dấu phân cách
        String amountWithoutFormatting = formattedAmount.replace(",", "").replace("đ", "");

        return amountWithoutFormatting;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SeparatorLine = new javax.swing.JSeparator();
        TKInput = new javax.swing.JTextField();
        NXBInput = new javax.swing.JComboBox<NXB>();
        TKBtn = new javax.swing.JButton();
        ScrollPaneTable = new javax.swing.JScrollPane();
        TableSach = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableCTHD = new javax.swing.JTable(dtmHoaDon);
        TotalLabel = new javax.swing.JLabel();
        ReceiveLabel = new javax.swing.JLabel();
        ChangeLabel = new javax.swing.JLabel();
        TotalInput = new javax.swing.JTextField();
        ReceiveInput = new javax.swing.JTextField();
        ChangeInput = new javax.swing.JTextField();
        PaymentBtn = new javax.swing.JButton();
        CancleBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        SLLabel = new javax.swing.JLabel();
        SLInput = new javax.swing.JSpinner();
        addHDBtn = new javax.swing.JButton();
        BookNameLabel = new javax.swing.JLabel();
        NXBNameLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        SPTable = new javax.swing.JTable();
        SPNameLabel = new javax.swing.JLabel();
        BookNameLabel2 = new javax.swing.JLabel();
        TKSPInput = new javax.swing.JTextField();
        DanhMucInput = new javax.swing.JComboBox<String>();
        TKSPBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        SLSPLabel = new javax.swing.JLabel();
        SLSPInput = new javax.swing.JSpinner();
        addSPHDBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        KHOutput = new javax.swing.JTextField();
        KHLabel = new javax.swing.JLabel();
        DiemLabel = new javax.swing.JLabel();
        DiemOutput = new javax.swing.JTextField();
        DiemCheckBox = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        TimSDTButton = new javax.swing.JButton();
        SDTInput = new javax.swing.JTextField();
        SDTLabel = new javax.swing.JLabel();
        NavBar = new javax.swing.JMenuBar();
        QLMenu = new javax.swing.JMenu();
        QLSach = new javax.swing.JMenuItem();
        QLNXB = new javax.swing.JMenuItem();
        QLNV = new javax.swing.JMenuItem();
        QLHD = new javax.swing.JMenuItem();
        QL_NCCVPP = new javax.swing.JMenuItem();
        QL_VPP = new javax.swing.JMenuItem();
        QLKH = new javax.swing.JMenuItem();
        TKMenu = new javax.swing.JMenu();
        TaiKhoanMenu = new javax.swing.JMenu();
        BarcodeMenu = new javax.swing.JMenu();
        ThoatBtn = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SeparatorLine.setBackground(new java.awt.Color(0, 0, 255));
        SeparatorLine.setForeground(new java.awt.Color(51, 51, 255));
        SeparatorLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TKInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKInputKeyPressed(evt);
            }
        });

        TKBtn.setText("Tìm kiếm");
        TKBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKBtnMouseClicked(evt);
            }
        });

        ScrollPaneTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ScrollPaneTable.setViewportView(TableSach);
        TableSach.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("CHI TIẾT HÓA ĐƠN");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setViewportView(TableCTHD);

        TotalLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TotalLabel.setText("Tổng tiền");

        ReceiveLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ReceiveLabel.setText("Khách đưa");

        ChangeLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ChangeLabel.setText("Tiền thừa");

        TotalInput.setEnabled(false);
        TotalInput.setText("0");

        ReceiveInput.setText("0");
        ReceiveInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ReceiveInputKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ReceiveInputKeyTyped(evt);
            }
        });

        ChangeInput.setEnabled(false);
        ChangeInput.setText("0");

        PaymentBtn.setText("Thanh toán");
        PaymentBtn.setEnabled(false);
        PaymentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentBtnActionPerformed(evt);
            }
        });

        CancleBtn.setText("Hủy hóa đơn");
        CancleBtn.setEnabled(false);
        CancleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancleBtnActionPerformed(evt);
            }
        });

        DeleteBtn.setText("Xóa sản phẩm");
        DeleteBtn.setEnabled(false);
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });

        SLLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SLLabel.setForeground(new java.awt.Color(255, 0, 51));
        SLLabel.setText("Số lượng");

        SLInput.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        SLInput.setRequestFocusEnabled(false);
        JFormattedTextField txtField = ((JSpinner.DefaultEditor) SLInput.getEditor()).getTextField();
        ((NumberFormatter) txtField.getFormatter()).setAllowsInvalid(false); // Chỉ cho phép giá trị hợp lệ là số

        addHDBtn.setText("Thêm vào hóa đơn");
        addHDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHDBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SLLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SLInput, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addHDBtn)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SLLabel)
                    .addComponent(SLInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addHDBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        BookNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BookNameLabel.setForeground(new java.awt.Color(0, 0, 255));
        BookNameLabel.setText("Tên sách:");

        NXBNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NXBNameLabel.setForeground(new java.awt.Color(0, 0, 255));
        NXBNameLabel.setText("NXB:");

        jScrollPane2.setViewportView(SPTable);

        SPNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SPNameLabel.setForeground(new java.awt.Color(0, 0, 255));
        SPNameLabel.setText("Tên sản phẩm:");

        BookNameLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BookNameLabel2.setForeground(new java.awt.Color(0, 0, 255));
        BookNameLabel2.setText("Danh mục:");

        TKSPInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKSPInputKeyPressed(evt);
            }
        });

        TKSPBtn.setText("Tìm kiếm");
        TKSPBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKSPBtnMouseClicked(evt);
            }
        });

        SLSPLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SLSPLabel.setForeground(new java.awt.Color(255, 0, 51));
        SLSPLabel.setText("Số lượng");

        SLSPInput.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        SLSPInput.setRequestFocusEnabled(false);
        JFormattedTextField txt1Field = ((JSpinner.DefaultEditor) SLSPInput.getEditor()).getTextField();
        ((NumberFormatter) txt1Field.getFormatter()).setAllowsInvalid(false); // Chỉ cho phép giá trị hợp lệ là số

        addSPHDBtn.setText("Thêm vào hóa đơn");
        addSPHDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSPHDBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SLSPLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SLSPInput, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addSPHDBtn)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SLSPLabel)
                    .addComponent(SLSPInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSPHDBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(TKSPInput, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DanhMucInput, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TKSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(SPNameLabel)
                                .addGap(235, 235, 235)
                                .addComponent(BookNameLabel2)))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SPNameLabel)
                    .addComponent(BookNameLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DanhMucInput, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TKSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TKSPInput, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        KHOutput.setEditable(false);
        KHOutput.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        KHLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        KHLabel.setForeground(new java.awt.Color(0, 0, 255));
        KHLabel.setText("Tên khách hàng");

        DiemLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DiemLabel.setForeground(new java.awt.Color(0, 0, 255));
        DiemLabel.setText("Điểm");

        DiemOutput.setEditable(false);
        DiemOutput.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DiemOutput.setText("0");

        DiemCheckBox.setText("Dùng điểm");
        DiemCheckBox.setEnabled(false);
        DiemCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiemCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(KHLabel)
                    .addComponent(DiemLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(DiemCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(KHOutput)
                    .addComponent(DiemOutput))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KHOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KHLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DiemLabel)
                    .addComponent(DiemOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DiemCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        TimSDTButton.setText("Tìm");
        TimSDTButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimSDTButtonActionPerformed(evt);
            }
        });

        SDTInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SDTInputKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SDTInputKeyTyped(evt);
            }
        });

        SDTLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SDTLabel.setForeground(new java.awt.Color(0, 0, 255));
        SDTLabel.setText("số điện thoại:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SDTLabel)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(SDTInput, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TimSDTButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SDTLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SDTInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimSDTButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        QLMenu.setText("Quản lý");

        QLSach.setText("Quản lý sách");
        QLSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLSachActionPerformed(evt);
            }
        });
        QLMenu.add(QLSach);

        QLNXB.setText("Quản lý NXB");
        QLNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLNXBActionPerformed(evt);
            }
        });
        QLMenu.add(QLNXB);

        QLNV.setText("Quản lý nhân viên");
        QLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLNVActionPerformed(evt);
            }
        });
        QLMenu.add(QLNV);

        QLHD.setText("Quản lý hóa đơn");
        QLHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLHDActionPerformed(evt);
            }
        });
        QLMenu.add(QLHD);

        QL_NCCVPP.setText("Quản lý nhà cung cấp VPP");
        QL_NCCVPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QL_NCCVPPActionPerformed(evt);
            }
        });
        QLMenu.add(QL_NCCVPP);

        QL_VPP.setText("Quản lý VPP");
        QL_VPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QL_VPPActionPerformed(evt);
            }
        });
        QLMenu.add(QL_VPP);

        QLKH.setText("Quản lý khách hàng");
        QLKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLKHActionPerformed(evt);
            }
        });
        QLMenu.add(QLKH);

        NavBar.add(QLMenu);

        TKMenu.setText("Báo cáo");
        TKMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKMenuMouseClicked(evt);
            }
        });
        NavBar.add(TKMenu);

        TaiKhoanMenu.setText("Tài khoản");
        TaiKhoanMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaiKhoanMenuMouseClicked(evt);
            }
        });
        NavBar.add(TaiKhoanMenu);

        BarcodeMenu.setText("In mã vạch");
        BarcodeMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BarcodeMenuMouseClicked(evt);
            }
        });
        NavBar.add(BarcodeMenu);

        ThoatBtn.setForeground(new java.awt.Color(255, 0, 51));
        ThoatBtn.setText("Thoát");
        ThoatBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThoatBtnMouseClicked(evt);
            }
        });
        NavBar.add(ThoatBtn);

        setJMenuBar(NavBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPaneTable)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TKInput, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BookNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(NXBInput, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TKBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(NXBNameLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SeparatorLine, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TotalLabel)
                            .addComponent(TotalInput, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ReceiveLabel)
                            .addComponent(ReceiveInput, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ChangeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ChangeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(261, 261, 261))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(PaymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(CancleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(158, 158, 158))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap()))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SeparatorLine, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BookNameLabel)
                            .addComponent(NXBNameLabel))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TKInput, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(TKBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(NXBInput))
                        .addGap(18, 18, 18)
                        .addComponent(ScrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TotalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalInput, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ChangeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ChangeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ReceiveLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ReceiveInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PaymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CancleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QLNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLNXBActionPerformed
        if(tk.getNXB()==1){
            QuanLyNXB_UI nxbUi = new QuanLyNXB_UI("Quản lý nhà xuất bản");
            nxbUi.showWindow();
        }
        else JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào trang này!");   
    }//GEN-LAST:event_QLNXBActionPerformed

    private void QLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLNVActionPerformed
        if(tk.getNhanVien()==1){
            QuanLyNhanVien nhanVienui = new QuanLyNhanVien("Quản lý nhân viên");
            nhanVienui.showWindow();
        }
        else JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào trang này!");
    }//GEN-LAST:event_QLNVActionPerformed

    private void ThoatBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThoatBtnMouseClicked
        int ret =JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muôn thoát chương trình?", "Xác Nhận Thoát", JOptionPane.OK_CANCEL_OPTION);
        if(ret==JOptionPane.OK_OPTION) System.exit(0);
    }//GEN-LAST:event_ThoatBtnMouseClicked

    private void TKMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKMenuMouseClicked
        if(tk.getBaoCao()==1) {
            ThongKe thongkeui = new ThongKe("Thống kê");
            thongkeui.showWindow();
        }
        else JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào trang này!");
    }//GEN-LAST:event_TKMenuMouseClicked

    private void QLSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLSachActionPerformed
        if(tk.getSach()==1){
            QuanLySach sachui = new QuanLySach("Quản lý sách");
            sachui.showWindow();
        }
        else JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào trang này!");
    }//GEN-LAST:event_QLSachActionPerformed

    private void addHDBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHDBtnActionPerformed
        if ((TableSach.getSelectedRow()<0))
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn loại sách muốn thêm vào hóa đơn");
//        nếu sách cần lấy có giá trị là 0 thì không cho lấy
        else if("0".equals(TableSach.getValueAt(TableSach.getSelectedRow(), TableSach.getSelectedColumn()).toString()) ){
            JOptionPane.showMessageDialog(null, "Sách bạn muốn chọn đã hết");
        }
        else {
            if (MaHD==null) TaoHD();
            ThemCTHD(true);
        }
        ReceiveInputKeyReleased(null);
        DeleteBtn.setEnabled(true);
        PaymentBtn.setEnabled(true);
        CancleBtn.setEnabled(true);
    }//GEN-LAST:event_addHDBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        if (TableCTHD.getSelectedRow()<0) 
        JOptionPane.showMessageDialog(null, "Bạn chưa chọn sách muốn xóa!");
        else{
            int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có chắc chắn muốn xóa?","Warning",JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                cthd.remove(TableCTHD.getSelectedRow());
                double tien = Double.parseDouble(TableCTHD.getValueAt(TableCTHD.getSelectedRow(), 2).toString());
                dtmHoaDon.removeRow(TableCTHD.getSelectedRow());                                                                      
                tien = Double.parseDouble(TotalInput.getText())-tien;
                if (tien==0) TotalInput.setText("0");
                else
                    TotalInput.setText(String.valueOf(tien)); 
            }
            //nếu không còn sản phẩm nào trong bảng thì hủy hóa đơn này
            ReceiveInputKeyReleased(null);
            if(TableCTHD.getRowCount()==0){
                DeleteBtn.setEnabled(false);
                PaymentBtn.setEnabled(false);
                CancleBtn.setEnabled(false);
                HoaDon_Connect HD = new HoaDon_Connect();
                HD.HuyHoaDon(MaHD);
                MaHD = null;
            }
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void TKBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKBtnMouseClicked
        NXB nxb = (NXB)NXBInput.getSelectedItem(); //lấy nhà xuất bản
        String ten = TKInput.getText(); //lấy tên sách

        Sach_Connect sachnxb1 = new Sach_Connect();
        //khi tên sách và nxb rỗng
        if (ten.equals("") && "0".equals(nxb.getMaNXB()))
            hienThiToanBoSach();
        // khi chỉ có tên sách
        else if (!ten.equals("") && "0".equals(nxb.getMaNXB())){
            dss_tensach = sachnxb1.laySachTheoMaTen(ten);
            dtmSach.setRowCount(0);
            for(Sach s : dss_tensach){
                Vector<Object> vec = new Vector<Object>();
                vec.add(s.getMaSach());
                vec.add(s.getTenSach());
                vec.add(s.getTheLoai());
                vec.add(s.getTacGia());
                vec.add(s.getSoLuong());
                vec.add(s.getGiaBan());
                dtmSach.addRow(vec);
            }
        }
        //khi có cả hai
        else if (!ten.equals("") && !"0".equals(nxb.getMaNXB())){
            dss_nxb = sachnxb1.laySachTheoNXBTen(nxb.getMaNXB(), ten);
            dtmSach.setRowCount(0);
            for(Sach s : dss_nxb){
                Vector<Object> vec = new Vector<Object>();
                vec.add(s.getMaSach());
                vec.add(s.getTenSach());
                vec.add(s.getTheLoai());
                vec.add(s.getTacGia());
                vec.add(s.getSoLuong());
                vec.add(s.getGiaBan());				
                dtmSach.addRow(vec);
            }
        }
        //khi chỉ có nxb
        else {
            dssMaTen = sachnxb1.laySachTheoNXB(nxb.getMaNXB());
            dtmSach.setRowCount(0);
            for(Sach s : dssMaTen){
            Vector<Object> vec = new Vector<Object>();			
            vec.add(s.getMaSach());
            vec.add(s.getTenSach());
            vec.add(s.getTheLoai());
            vec.add(s.getTacGia());
            vec.add(s.getSoLuong());
            vec.add(s.getGiaBan());	
            dtmSach.addRow(vec);
            }
	}
    }//GEN-LAST:event_TKBtnMouseClicked

    private void PaymentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentBtnActionPerformed
        if(Double.parseDouble(ChangeInput.getText())<0) {
            JOptionPane.showMessageDialog(null, "Không đủ tiền để thanh toán");
            return;
        }
        int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có chắc muốn thanh toán?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            //duyệt qua tất cả các dòng của bảng hóa đơn
            Sach_Connect sach = new Sach_Connect();
            VanPhongPham_Connect vpp_conn = new VanPhongPham_Connect();
            for (CTHD ct : cthd ){
                CTHD_Connect taoCT = new CTHD_Connect();
                taoCT.ThemCT(ct);
                //kiểm tra xem mã sản phẩm phải sách hay không
                if(ct.getMaSP().substring(0, 1).equals("s")){ //nếu chữ đầu của masp la "s" thì là sách
                    //giảm số lượng sách sau khi bán cho khách
                    for (Sach s : dss){
                        if (s.getMaSach()==ct.getMaSP())
                            sach.updateSL(s.getMaSach(), s.getSoLuong()-ct.getSoLuong());
                    }
                }
                else{
                    //giảm số lượng sản phẩm sau khi bán cho khách
                    for (VPP vpp : dsvpp){
                        if (vpp.getMaVPP()==ct.getMaSP())
                            vpp_conn.updateSL(vpp.getMaVPP(), vpp.getSoLuong()-ct.getSoLuong());
                    }
                }
            }
            cthd.clear();
            while(dtmHoaDon.getRowCount() > 0) dtmHoaDon.removeRow(0);
            HoaDon_Connect HD = new HoaDon_Connect();
            int state = HD.ThanhToan(MaHD, TotalInput.getText());
                if(state != -1)
                    JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
                else JOptionPane.showMessageDialog(null, "Thanh toán thất bại!");
            //điểm của khách sẽ bằng điểm hiện tại cộng thêm tổng hóa đơn  x 0.02
            KhachHang kh = new KhachHang();
            KhachHang_Connect kh_conn = new KhachHang_Connect();
            kh= kh_conn.layKhachHangBangSDT(SDTInput.getText());
            kh.setDiem(kh.getDiem()+Double.parseDouble(TotalInput.getText())*0.02);
            kh_conn.updateKhachHang(kh);
            DiemOutput.setText(Double.toString(kh.getDiem()));
            TotalInput.setText("0");
            ReceiveInput.setText("0");
            ChangeInput.setText("0");
            MaHD = null;
            hienThiToanBoSach();
            DeleteBtn.setEnabled(false);
            PaymentBtn.setEnabled(false);
            CancleBtn.setEnabled(false);
        }
    }//GEN-LAST:event_PaymentBtnActionPerformed

    private void CancleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancleBtnActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có chắc chắn muốn hủy hóa đơn này?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            cthd.clear();
            while(dtmHoaDon.getRowCount() > 0) dtmHoaDon.removeRow(0);   
            TotalInput.setText("0");
            ReceiveInput.setText("0");
            ChangeInput.setText("0");
            HoaDon_Connect HD = new HoaDon_Connect();
            HD.HuyHoaDon(MaHD);
            MaHD = null;
            JOptionPane.showMessageDialog(null, "Hủy hóa đơn thành công!");
            DeleteBtn.setEnabled(false);
            PaymentBtn.setEnabled(false);
            CancleBtn.setEnabled(false);
        }
    }//GEN-LAST:event_CancleBtnActionPerformed

    private void ReceiveInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReceiveInputKeyReleased
        String text = ReceiveInput.getText();
        String diem = removeCurrencyFormatting(DiemOutput.getText());
        if(ReceiveInput.getText().equals(""))
            text = "0";
        String total = TotalInput.getText();
        //tính tiền thối lại cho khách
        if(DiemCheckBox.isSelected()){
            double tienthua = Double.parseDouble(diem) + Double.parseDouble(text) - Double.parseDouble(total);
            ChangeInput.setText(Double.toString(tienthua));
        }
        else{ //ko được chọn
            double tienthua = Double.parseDouble(text) - Double.parseDouble(total);
            ChangeInput.setText(Double.toString(tienthua));
        }  
        
        //tính tiền thối lại cho khách
        if (text.isEmpty() && total.isEmpty()){
            ChangeInput.setText("0");
        }
    }//GEN-LAST:event_ReceiveInputKeyReleased

    private void QLHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLHDActionPerformed
        if(tk.getHoaDon()==1){
            QuanLyHoaDon hdUI = new QuanLyHoaDon("Quản Lý Hóa Đơn");
            hdUI.showWindow();
        }
        else JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào trang này!");
    }//GEN-LAST:event_QLHDActionPerformed

    private void TaiKhoanMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoanMenuMouseClicked
        if(tk.getTaiKhoan()==1){
            QuanLyTaiKhoan tkUI = new QuanLyTaiKhoan("Tài khoản");
            tkUI.showWindow();
        }
        else JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào trang này!");
    }//GEN-LAST:event_TaiKhoanMenuMouseClicked

    private void BarcodeMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarcodeMenuMouseClicked
        if(tk.getMaVach()==1){
            InMaVach mvUI = new InMaVach("In mã vạch");
            mvUI.showWindow();
        }
        else JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào trang này!");
    }//GEN-LAST:event_BarcodeMenuMouseClicked

    private void QL_NCCVPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QL_NCCVPPActionPerformed
        if(tk.getNCCVPP()==1){
            QuanLyNCCVPP np = new QuanLyNCCVPP("Quản lý nhà cung cấp văn phòng mẫu");
            np.showWindow();
        }
        else JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào trang này!");
    }//GEN-LAST:event_QL_NCCVPPActionPerformed

    private void QL_VPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QL_VPPActionPerformed
        if(tk.getVPP()==1){
            QuanLyVPP vpp = new QuanLyVPP("Quản lý văn phòng phẩm");
            vpp.showWindow();
        }
        else JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào trang này!");
    }//GEN-LAST:event_QL_VPPActionPerformed

    private void ReceiveInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReceiveInputKeyTyped
        //kiểm tra xem người dùng có nhập số vào không, nếu không phải số thì không nhận
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') ||
            (c == KeyEvent.VK_BACK_SPACE) ||
            (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
    }//GEN-LAST:event_ReceiveInputKeyTyped

    private void QLKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLKHActionPerformed
        if(tk.getKhachHang()==1){
            QuanLyKhachHang qlkh = new QuanLyKhachHang();
            qlkh.showWindow();
        }
        else JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào trang này!");
    }//GEN-LAST:event_QLKHActionPerformed

    private void TKSPBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKSPBtnMouseClicked
        //lấy danh mục
        String dm ="";
        if(!DanhMucInput.getSelectedItem().toString().equals("Tất cả"))
            dm = DanhMucInput.getSelectedItem().toString();
        //lấy tên sản phẩm
        String ten = TKSPInput.getText();

        VanPhongPham_Connect vpp_conn = new VanPhongPham_Connect();
        //hiển thị tất cả sản phẩm
        if (ten.equals("") && dm.equals(""))
            hienThiTatCaVPP();
        //lấy sản phẩm theo tên
        else if (!ten.equals("") && dm.equals("")){
            dssp_ten = vpp_conn.laySachTheoTenVPP(ten);
            dtmVPP.setRowCount(0);
            for(VPP vpp : dssp_ten){
                Vector<Object> vec = new Vector<Object>();
                vec.add(vpp.getMaVPP());
                vec.add(vpp.getTenVPP());
                vec.add(vpp.getDanhMuc());
                vec.add(vpp.getSoLuong());
                vec.add(vpp.getGiaBan());
                dtmVPP.addRow(vec);
            }
        }
        //lấy sản phẩm theo tên và danh mục
        else if (!ten.equals("") && !dm.equals("")){
            dssp_ten_dm = vpp_conn.laySachTheoDanhMucVaTenVPP(dm, ten);
            dtmVPP.setRowCount(0);
            for(VPP vpp : dssp_ten_dm){
                Vector<Object> vec = new Vector<Object>();
                vec.add(vpp.getMaVPP());
                vec.add(vpp.getTenVPP());
                vec.add(vpp.getDanhMuc());
                vec.add(vpp.getSoLuong());
                vec.add(vpp.getGiaBan());			
                dtmVPP.addRow(vec);
            }
        }
        //lấy sản phẩm theo danh mục
        else {
            dssp_dm = vpp_conn.laySachTheoDanhMuc(dm);
            dtmVPP.setRowCount(0);
            for(VPP vpp : dssp_dm){
                Vector<Object> vec = new Vector<Object>();
                vec.add(vpp.getMaVPP());
                vec.add(vpp.getTenVPP());
                vec.add(vpp.getDanhMuc());
                vec.add(vpp.getSoLuong());
                vec.add(vpp.getGiaBan());			
                dtmVPP.addRow(vec);
            }
	}
    }//GEN-LAST:event_TKSPBtnMouseClicked

    private void addSPHDBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSPHDBtnActionPerformed
        if ((SPTable.getSelectedRow()<0))
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn loại sản phẩm muốn thêm vào hóa đơn");
//        nếu sách cần lấy có giá trị là 0 thì không cho lấy
        else if("0".equals(SPTable.getValueAt(SPTable.getSelectedRow(), SPTable.getSelectedColumn()).toString()) ){
            JOptionPane.showMessageDialog(null, "Sản phẩm bạn muốn chọn đã hết");
        }
        else {
            if (MaHD==null) TaoHD();
            ThemCTHD(false);
        }
        ReceiveInputKeyReleased(null);
        DeleteBtn.setEnabled(true);
        PaymentBtn.setEnabled(true);
        CancleBtn.setEnabled(true);
    }//GEN-LAST:event_addSPHDBtnActionPerformed

    private void TimSDTButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimSDTButtonActionPerformed
        KhachHang_Connect kh_conn = new KhachHang_Connect();
        KhachHang kh = kh_conn.layKhachHangBangSDT(SDTInput.getText());
        KHOutput.setText(kh.getTenKH());
        makh = kh.getMaKH();
        DiemOutput.setText(formatCurrency(kh.getDiem()));
        //lưu lại điểm của khách hàng này bằng biến toàn cục
        DiemOutput.setText(formatCurrency(kh.getDiem()));
        DiemCheckBox.setEnabled(true);
    }//GEN-LAST:event_TimSDTButtonActionPerformed

    private void SDTInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SDTInputKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || SDTInput.getText().length() >= 10) {
            evt.consume(); // Ngăn chặn ký tự không hợp lệ và ngăn chặn nhập quá 4 ký tự
        }
    }//GEN-LAST:event_SDTInputKeyTyped

    private void DiemCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiemCheckBoxActionPerformed
        ReceiveInputKeyReleased(null);
    }//GEN-LAST:event_DiemCheckBoxActionPerformed

    private void SDTInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SDTInputKeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) TimSDTButtonActionPerformed(null);
    }//GEN-LAST:event_SDTInputKeyPressed

    private void TKInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKInputKeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) TKBtnMouseClicked(null);
    }//GEN-LAST:event_TKInputKeyPressed

    private void TKSPInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKSPInputKeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) TKSPBtnMouseClicked(null);
    }//GEN-LAST:event_TKSPInputKeyPressed

    /**
     * @param args the command line arguments
     */
    public void showWindow() {
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
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHang("Bán hàng - Tạo hóa đơn",MaNV).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu BarcodeMenu;
    private javax.swing.JLabel BookNameLabel;
    private javax.swing.JLabel BookNameLabel2;
    private javax.swing.JButton CancleBtn;
    private javax.swing.JTextField ChangeInput;
    private javax.swing.JLabel ChangeLabel;
    private javax.swing.JComboBox<String> DanhMucInput;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JCheckBox DiemCheckBox;
    private javax.swing.JLabel DiemLabel;
    private javax.swing.JTextField DiemOutput;
    private javax.swing.JLabel KHLabel;
    private javax.swing.JTextField KHOutput;
    private javax.swing.JComboBox<NXB> NXBInput;
    private javax.swing.JLabel NXBNameLabel;
    private javax.swing.JMenuBar NavBar;
    private javax.swing.JButton PaymentBtn;
    private javax.swing.JMenuItem QLHD;
    private javax.swing.JMenuItem QLKH;
    private javax.swing.JMenu QLMenu;
    private javax.swing.JMenuItem QLNV;
    private javax.swing.JMenuItem QLNXB;
    private javax.swing.JMenuItem QLSach;
    private javax.swing.JMenuItem QL_NCCVPP;
    private javax.swing.JMenuItem QL_VPP;
    private javax.swing.JTextField ReceiveInput;
    private javax.swing.JLabel ReceiveLabel;
    private javax.swing.JTextField SDTInput;
    private javax.swing.JLabel SDTLabel;
    private javax.swing.JSpinner SLInput;
    private javax.swing.JLabel SLLabel;
    private javax.swing.JSpinner SLSPInput;
    private javax.swing.JLabel SLSPLabel;
    private javax.swing.JLabel SPNameLabel;
    private javax.swing.JTable SPTable;
    private javax.swing.JScrollPane ScrollPaneTable;
    private javax.swing.JSeparator SeparatorLine;
    private javax.swing.JButton TKBtn;
    private javax.swing.JTextField TKInput;
    private javax.swing.JMenu TKMenu;
    private javax.swing.JButton TKSPBtn;
    private javax.swing.JTextField TKSPInput;
    private javax.swing.JTable TableCTHD;
    private javax.swing.JTable TableSach;
    private javax.swing.JMenu TaiKhoanMenu;
    private javax.swing.JMenu ThoatBtn;
    private javax.swing.JButton TimSDTButton;
    private javax.swing.JTextField TotalInput;
    private javax.swing.JLabel TotalLabel;
    private javax.swing.JButton addHDBtn;
    private javax.swing.JButton addSPHDBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
