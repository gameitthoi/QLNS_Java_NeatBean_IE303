/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Connect.CTHD_Connect;
import Connect.HoaDon_Connect;
import Connect.NXB_Connect;
import Connect.Sach_Connect;
import Model.CTHD;
import Model.HoaDon;
import Model.NXB;
import Model.Sach;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dat
 */
public class BanHang extends javax.swing.JFrame {
    private String MaHD=null;
    private String MaNV= null;
    private ArrayList<NXB> dsnxb = null;
    private ArrayList<CTHD> cthd = new ArrayList<CTHD>(); 
    private ArrayList<Sach> dssMaTen = null;
    private ArrayList<Sach> dss_nxb = null;
    private ArrayList<Sach> dss_tensach = null;
    private ArrayList<Sach> dss = null;
    private DefaultTableModel dtmSach , dtmHoaDon;
    /**
     * Creates new form BanHang
     */
    public BanHang(String title,String maNV) {
        initComponents();
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        MaNV=maNV;
        hienThiToanBoSach();
        hienThiToanBoNhaXuatBan();
        TaoBangCTHD();
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
    
    private void TaoBangCTHD (){
        dtmHoaDon = new DefaultTableModel();
        dtmHoaDon.addColumn("Tên sách");
        dtmHoaDon.addColumn("Số lượng");
        dtmHoaDon.addColumn("Thành tiền");
        TableCTHD.setModel(dtmHoaDon);
    }
    
    private void TaoHD(){
        String HD = "HD";
        HoaDon_Connect tHD = new HoaDon_Connect();
        String lastmahd = tHD.LastMaHD();
        if (lastmahd==null) MaHD= "HD01";
        else {
            int sohd = Integer.parseInt(lastmahd.substring(2))+1;
            if (sohd<10) MaHD = HD+"0"+String.valueOf(sohd);
            else MaHD = HD+String.valueOf(sohd);
        }                                               
        HoaDon hd = new HoaDon();
        hd.setMaHD(MaHD);
        hd.setMaNV(MaNV);
        Date date = new Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(date);
        hd.setNgaylap(currentTime);
        hd.setIsDelete(0);
        tHD.TaoHD(hd); 
    }
    
    private void ThemCTHD() {
        Vector<Object> vec = new Vector<Object>();
        CTHD vec1 = new CTHD();
        vec1.setMaHD(MaHD);
        vec1.setMaSach(TableSach.getValueAt(TableSach.getSelectedRow(), 0).toString());
        vec1.setDonGia(Double.parseDouble(TableSach.getValueAt(TableSach.getSelectedRow(), 5).toString()));
        vec1.setIsDelete(0);
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
        addHDBtn = new javax.swing.JButton();
        SLLabel = new javax.swing.JLabel();
        SLInput = new javax.swing.JSpinner();
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
        NavBar = new javax.swing.JMenuBar();
        QLMenu = new javax.swing.JMenu();
        QLSach = new javax.swing.JMenuItem();
        QLNXB = new javax.swing.JMenuItem();
        QLNV = new javax.swing.JMenuItem();
        TKMenu = new javax.swing.JMenu();
        ThoatBtn = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SeparatorLine.setBackground(new java.awt.Color(0, 0, 255));
        SeparatorLine.setForeground(new java.awt.Color(51, 51, 255));
        SeparatorLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TKBtn.setText("Tìm kiếm");
        TKBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKBtnMouseClicked(evt);
            }
        });

        ScrollPaneTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ScrollPaneTable.setViewportView(TableSach);
        TableSach.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        addHDBtn.setBackground(new java.awt.Color(0, 102, 255));
        addHDBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addHDBtn.setForeground(new java.awt.Color(255, 255, 255));
        addHDBtn.setText("Thêm vào hóa đơn");
        addHDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHDBtnActionPerformed(evt);
            }
        });

        SLLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SLLabel.setForeground(new java.awt.Color(255, 0, 51));
        SLLabel.setText("Nhập số lượng mua:");

        SLInput.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        SLInput.setRequestFocusEnabled(false);

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

        ReceiveInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ReceiveInputKeyReleased(evt);
            }
        });

        ChangeInput.setEnabled(false);

        PaymentBtn.setBackground(new java.awt.Color(0, 153, 51));
        PaymentBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PaymentBtn.setForeground(new java.awt.Color(255, 255, 255));
        PaymentBtn.setText("Thanh toán");
        PaymentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentBtnActionPerformed(evt);
            }
        });

        CancleBtn.setBackground(new java.awt.Color(255, 0, 0));
        CancleBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CancleBtn.setForeground(new java.awt.Color(255, 255, 255));
        CancleBtn.setText("Hủy hóa đơn");
        CancleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancleBtnActionPerformed(evt);
            }
        });

        DeleteBtn.setBackground(new java.awt.Color(255, 255, 0));
        DeleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DeleteBtn.setText("Xóa sản phẩm");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });

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

        NavBar.add(QLMenu);

        TKMenu.setText("Thống kê");
        TKMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKMenuMouseClicked(evt);
            }
        });
        NavBar.add(TKMenu);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(24, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(SLLabel)
                                .addGap(38, 38, 38)
                                .addComponent(SLInput, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(addHDBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TKInput, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NXBInput, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TKBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ScrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(SeparatorLine, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(261, 261, 261))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(PaymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CancleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(170, 170, 170))))
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
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SeparatorLine, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TKInput, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TKBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(NXBInput))
                .addGap(18, 18, 18)
                .addComponent(ScrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SLInput, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SLLabel)
                    .addComponent(addHDBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ReceiveLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ReceiveInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TotalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TotalInput, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ChangeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChangeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PaymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CancleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QLNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLNXBActionPerformed
        QuanLyNXB_UI nxbUi = new QuanLyNXB_UI("Quản lý nhà xuất bản");
        nxbUi.showWindow();
    }//GEN-LAST:event_QLNXBActionPerformed

    private void QLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLNVActionPerformed
        QuanLyNhanVienUI nhanVienui = new QuanLyNhanVienUI("Quản lý nhân viên");
        nhanVienui.showWindow();
    }//GEN-LAST:event_QLNVActionPerformed

    private void ThoatBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThoatBtnMouseClicked
        int ret =JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muôn thoát chương trình?", "Xác Nhận Thoát", JOptionPane.OK_CANCEL_OPTION);
        if(ret==JOptionPane.OK_OPTION) System.exit(0);
    }//GEN-LAST:event_ThoatBtnMouseClicked

    private void TKMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKMenuMouseClicked
        ThongKe thongkeui = new ThongKe("Thống kê");
        thongkeui.showWindow();
    }//GEN-LAST:event_TKMenuMouseClicked

    private void QLSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLSachActionPerformed
        QuanLySachUI sachui = new QuanLySachUI("Quản lý sách");
        sachui.showWindow();
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
            ThemCTHD();
        }
    }//GEN-LAST:event_addHDBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        if (TableCTHD.getSelectedRow()<0) 
        JOptionPane.showMessageDialog(null, "Bạn chưa chọn sách muốn xóa!");
        else{
            int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có chắc chắn muôn xóa?","Warning",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    cthd.remove(TableCTHD.getSelectedRow());
                    double tien = Double.parseDouble(TableCTHD.getValueAt(TableCTHD.getSelectedRow(), 2).toString());
                    dtmHoaDon.removeRow(TableCTHD.getSelectedRow());                                                                      
                    tien = Double.parseDouble(TotalInput.getText())-tien;
                    if (tien==0) TotalInput.setText("");
                    else
                    TotalInput.setText(String.valueOf(tien)); 
            }
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void TKBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKBtnMouseClicked
        NXB nxb = (NXB)NXBInput.getSelectedItem();
        String ten = TKInput.getText();

        Sach_Connect sachnxb1 = new Sach_Connect();
        dss_tensach = sachnxb1.laySachTheoMaTen(ten);
        dss_nxb = sachnxb1.laySachTheoNXBTen(nxb.getMaNXB(), ten);
        dssMaTen = sachnxb1.laySachTheoNXB(nxb.getMaNXB());
        if (TKInput.getText().length()==0 && "0".equals(nxb.getMaNXB()))
            hienThiToanBoSach();
        if (TKInput.getText().length()!= 0 && "0".equals(nxb.getMaNXB())){
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
        else if (TKInput.getText().length()!= 0 && !"0".equals(nxb.getMaNXB())){
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
        else if (TKInput.getText().length()== 0 && !"0".equals(nxb.getMaNXB())){
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
        int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có chắc muốn thanh toán?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            for (CTHD ct : cthd ){
                CTHD_Connect taoCT = new CTHD_Connect();
                Sach_Connect sach = new Sach_Connect();
                taoCT.ThemCT(ct);
//                giảm số lượng sách sau khi bán cho khách
                for (Sach s : dss){
                    if (s.getMaSach()==ct.getMaSach())
                        sach.updateSL(s.getMaSach(), s.getSoLuong()-ct.getSoLuong());
                }
            }
            cthd.clear();
            while(dtmHoaDon.getRowCount() > 0) dtmHoaDon.removeRow(0);
            HoaDon_Connect HD = new HoaDon_Connect();
            HD.ThanhToan(MaHD, TotalInput.getText());
            TotalInput.setText("");
            MaHD = null;
            hienThiToanBoSach();
        }
    }//GEN-LAST:event_PaymentBtnActionPerformed

    private void CancleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancleBtnActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có chắc chắn muốn hủy hóa đơn này?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            cthd.clear();
            while(dtmHoaDon.getRowCount() > 0) dtmHoaDon.removeRow(0);   
            TotalInput.setText("");
            HoaDon_Connect HD = new HoaDon_Connect();
            HD.HuyHoaDon(MaHD);
            MaHD = null;
        }
    }//GEN-LAST:event_CancleBtnActionPerformed

    private void ReceiveInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReceiveInputKeyReleased
        if (!ReceiveInput.getText().isEmpty()){
            double tienthua = Double.parseDouble(ReceiveInput.getText())-Double.parseDouble(TotalInput.getText());
            ChangeInput.setText(String.valueOf(tienthua));
        }
        else ChangeInput.setText("");
    }//GEN-LAST:event_ReceiveInputKeyReleased

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
                new BanHang("Bán hàng",MaNV).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancleBtn;
    private javax.swing.JTextField ChangeInput;
    private javax.swing.JLabel ChangeLabel;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JComboBox<NXB> NXBInput;
    private javax.swing.JMenuBar NavBar;
    private javax.swing.JButton PaymentBtn;
    private javax.swing.JMenu QLMenu;
    private javax.swing.JMenuItem QLNV;
    private javax.swing.JMenuItem QLNXB;
    private javax.swing.JMenuItem QLSach;
    private javax.swing.JTextField ReceiveInput;
    private javax.swing.JLabel ReceiveLabel;
    private javax.swing.JSpinner SLInput;
    private javax.swing.JLabel SLLabel;
    private javax.swing.JScrollPane ScrollPaneTable;
    private javax.swing.JSeparator SeparatorLine;
    private javax.swing.JButton TKBtn;
    private javax.swing.JTextField TKInput;
    private javax.swing.JMenu TKMenu;
    private javax.swing.JTable TableCTHD;
    private javax.swing.JTable TableSach;
    private javax.swing.JMenu ThoatBtn;
    private javax.swing.JTextField TotalInput;
    private javax.swing.JLabel TotalLabel;
    private javax.swing.JButton addHDBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
