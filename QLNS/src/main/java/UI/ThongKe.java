/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Connect.HoaDon_Connect;
import Connect.Sach_Connect;
import Model.HoaDon;
import Model.Sach;
import Model.TonKho;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dat
 */
public class ThongKe extends javax.swing.JFrame {
private DefaultTableModel dtmSach =null, dtmHoaDon =null, dtmBanChay = null, dtmTonKho = null, dtmNhapXuat=null;
private ArrayList<HoaDon> dshd_thongke =null;
private ArrayList<Sach> dss ;
private ArrayList<TonKho> dstk,dsnx ;
private double tongTien =0;
private DecimalFormat df = new DecimalFormat("###,###,###");
    /**
     * Creates new form ThongKe
     */
    public ThongKe(String title) {
        initComponents();
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        hienThiHoaDon();
        hienThiSachTonKho();
        hienThiSachBanChay();
        hienThiTonKho();
        hienThiNhapXuat();
    }
    
    private void hienThiSachTonKho() {
        Sach_Connect sach_conn = new Sach_Connect();
        dss = sach_conn.laySachConDuoiTon((int)SLBaoDongInput.getValue());
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã sách");
        dtmSach.addColumn("Tên sách");
        dtmSach.addColumn("Tên nhà xuất bản");
        dtmSach.addColumn("Số lượng tồn");
        dtmSach.setRowCount(0);
        for(Sach s : dss){
            Vector<Object> vec = new Vector<Object>();
            vec.add(s.getMaSach());
            vec.add(s.getTenSach());
            vec.add(s.getMaNXB());
            vec.add(s.getSoLuong());

            dtmSach.addRow(vec);
        }
        NeedBookTable.setModel(dtmSach);
    }
    
    private void hienThiHoaDon() {
        Calendar cal = Calendar.getInstance();
        MonthInput.setSelectedIndex(cal.get(Calendar.MONTH) + 1); // Tháng bắt đầu từ 0 nên cần +1 để lấy tháng thực tế
        YearInput.setText(Integer.toString(cal.get(Calendar.YEAR)) );
        
        dtmHoaDon = new DefaultTableModel();
        dtmHoaDon.addColumn("Mã hóa Đơn");
        dtmHoaDon.addColumn("Mã Nhân Viên");
        dtmHoaDon.addColumn("Ngày lập");
        dtmHoaDon.addColumn("Tổng tiền");
        dtmHoaDon.setRowCount(0);
        HoaDon_Connect hd_connect = new HoaDon_Connect();
        dshd_thongke = hd_connect.layToanBoHoaDonTheoThangNam(Integer.toString(MonthInput.getSelectedIndex()), YearInput.getText());
        tongTien = 0;
        for(HoaDon hd : dshd_thongke){
            tongTien = tongTien + hd.getTongTien();
            Vector<Object> vec = new Vector<Object>();
            vec.add(hd.getMaHD());
            vec.add(hd.getMaNV());
            vec.add(hd.getNgaylap());
            vec.add(df.format(hd.getTongTien()));
            dtmHoaDon.addRow(vec);
        }
        HDTable.setModel(dtmHoaDon);
        TotalOutput.setText(df.format(tongTien) + " vnđ");
    }
    
    private void hienThiSachBanChay() {
        Calendar cal = Calendar.getInstance();
        BanChayMonthInput.setSelectedIndex(cal.get(Calendar.MONTH) + 1); // Tháng bắt đầu từ 0 nên cần +1 để lấy tháng thực tế
        BanChayYearInput.setText(Integer.toString(cal.get(Calendar.YEAR)) );
        Sach_Connect sach_conn = new Sach_Connect();
        dss = sach_conn.laySachBanChay(TopBanChayInput.getValue().toString(), Integer.toString(BanChayMonthInput.getSelectedIndex()), BanChayYearInput.getText());
        dtmBanChay = new DefaultTableModel();
        dtmBanChay.addColumn("Mã sách");
        dtmBanChay.addColumn("Tên sách");
        dtmBanChay.addColumn("Số lượng đã bán");
        dtmBanChay.setRowCount(0);
        for(Sach s : dss){
            Vector<Object> vec = new Vector<Object>();
            vec.add(s.getMaSach());
            vec.add(s.getTenSach());
            vec.add(s.getSoLuong());

            dtmBanChay.addRow(vec);
        }
        BanChayTable.setModel(dtmBanChay);
    }
    
    private void hienThiTonKho(){
        Calendar cal = Calendar.getInstance();
        Sach_Connect sach_conn = new Sach_Connect();
        dstk = sach_conn.laySachTonKho(cal.get(Calendar.MONTH) + 1,cal.get(Calendar.YEAR));
        dtmTonKho = new DefaultTableModel();
        dtmTonKho.addColumn("Mã sách");
        dtmTonKho.addColumn("Tên sách");
        dtmTonKho.addColumn("Tồn đầu");
        dtmTonKho.addColumn("Nhập");
        dtmTonKho.addColumn("Xuất");
        dtmTonKho.addColumn("Tồn Cuối");
        dtmTonKho.setRowCount(0);
        for(TonKho tk : dstk){
            Vector<Object> vec = new Vector<Object>();
            vec.add(tk.getMaSach());
            vec.add(tk.getTenSach());
            vec.add(tk.getTonDau());
            vec.add(tk.getNhap());
            vec.add(tk.getXuat());
            vec.add(tk.getTonCuoi());

            dtmTonKho.addRow(vec);
        }
        TonKhoTable.setModel(dtmTonKho);
    }
    
    private void hienThiNhapXuat(){
        Calendar cal = Calendar.getInstance();
        NhapXuatMonthInput.setSelectedIndex(cal.get(Calendar.MONTH) + 1); // Tháng bắt đầu từ 0 nên cần +1 để lấy tháng thực tế
        NhapXuatYearInput.setText(Integer.toString(cal.get(Calendar.YEAR)) );
        Sach_Connect sach_conn = new Sach_Connect();
        dsnx = sach_conn.layThongTinNhapXuat(cal.get(Calendar.MONTH) + 1,cal.get(Calendar.YEAR));
        dtmNhapXuat = new DefaultTableModel();
        dtmNhapXuat.addColumn("Mã sách");
        dtmNhapXuat.addColumn("Tên sách");
        dtmNhapXuat.addColumn("Nhập vào");
        dtmNhapXuat.addColumn("Bán ra");
        dtmNhapXuat.setRowCount(0);
        for(TonKho tk : dsnx){
            Vector<Object> vec = new Vector<Object>();
            vec.add(tk.getMaSach());
            vec.add(tk.getTenSach());
            vec.add(tk.getNhap());
            vec.add(tk.getXuat());

            dtmNhapXuat.addRow(vec);
        }
        NhapXuatTable.setModel(dtmNhapXuat);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        HDPane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        HDTable = new javax.swing.JTable(dtmHoaDon);
        MonthInput = new javax.swing.JComboBox<>();
        YearInput = new javax.swing.JTextField();
        TKBtn = new javax.swing.JButton();
        MonthLabel = new javax.swing.JLabel();
        YearLabel = new javax.swing.JLabel();
        PrintHDBtn = new javax.swing.JButton();
        TotalLabel = new javax.swing.JLabel();
        TotalOutput = new javax.swing.JLabel();
        NeedBookPane = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        NeedBookTable = new javax.swing.JTable(dtmSach);
        SLBaoDongLabel = new javax.swing.JLabel();
        SLBaoDongInput = new javax.swing.JSpinner(new SpinnerNumberModel(10, 10, 100, 10));
        TKNeedBookBtn = new javax.swing.JButton();
        PrintNeedBookBtn = new javax.swing.JButton();
        BanChayPane = new javax.swing.JPanel();
        BanChayMonthInput = new javax.swing.JComboBox<>();
        BanChayYearInput = new javax.swing.JTextField();
        BanChayYearLabel = new javax.swing.JLabel();
        BanChayMonthLabel = new javax.swing.JLabel();
        BanChayBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        BanChayTable = new javax.swing.JTable();
        PrintBanChayBtn = new javax.swing.JButton();
        TopBanChayInput = new javax.swing.JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
        BanChayTopLabel = new javax.swing.JLabel();
        TonKhoPane = new javax.swing.JPanel();
        PrintTonKhoBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TonKhoTable = new javax.swing.JTable();
        TonKhoLabel = new javax.swing.JLabel();
        NhapXuatPane = new javax.swing.JPanel();
        NhapXuatMonthLabel = new javax.swing.JLabel();
        NhapXuatMonthInput = new javax.swing.JComboBox<>();
        NhapXuatYearLabel = new javax.swing.JLabel();
        NhapXuatYearInput = new javax.swing.JTextField();
        NhapXuatBtn = new javax.swing.JButton();
        PrintNhapXuatBtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        NhapXuatTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        TKLable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane2.setViewportView(HDTable);

        MonthInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        TKBtn.setBackground(new java.awt.Color(51, 153, 255));
        TKBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TKBtn.setForeground(new java.awt.Color(255, 255, 255));
        TKBtn.setText("Thống kê");
        TKBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKBtnMouseClicked(evt);
            }
        });

        MonthLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MonthLabel.setText("Tháng");

        YearLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        YearLabel.setText("Năm");

        PrintHDBtn.setText("In bảng thông kê");
        PrintHDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintHDBtnActionPerformed(evt);
            }
        });

        TotalLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TotalLabel.setText("Tổng tiền bán được của tháng :");

        TotalOutput.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TotalOutput.setText("0 vnđ");

        javax.swing.GroupLayout HDPaneLayout = new javax.swing.GroupLayout(HDPane);
        HDPane.setLayout(HDPaneLayout);
        HDPaneLayout.setHorizontalGroup(
            HDPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HDPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MonthLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(YearLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(YearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(TKBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HDPaneLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(TotalLabel)
                .addGap(18, 18, 18)
                .addComponent(TotalOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrintHDBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        HDPaneLayout.setVerticalGroup(
            HDPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HDPaneLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(HDPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TKBtn)
                    .addComponent(YearInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(YearLabel)
                    .addComponent(MonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MonthLabel))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(HDPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalLabel)
                    .addComponent(TotalOutput)
                    .addComponent(PrintHDBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa đơn", HDPane);

        jScrollPane3.setViewportView(NeedBookTable);

        SLBaoDongLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SLBaoDongLabel.setText("Số lượng của sách dưới :");

        TKNeedBookBtn.setBackground(new java.awt.Color(51, 153, 255));
        TKNeedBookBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TKNeedBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        TKNeedBookBtn.setText("Thống kê");
        TKNeedBookBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKNeedBookBtnMouseClicked(evt);
            }
        });

        PrintNeedBookBtn.setText("In bảng thống kê");
        PrintNeedBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintNeedBookBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NeedBookPaneLayout = new javax.swing.GroupLayout(NeedBookPane);
        NeedBookPane.setLayout(NeedBookPaneLayout);
        NeedBookPaneLayout.setHorizontalGroup(
            NeedBookPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NeedBookPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NeedBookPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeedBookPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeedBookPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SLBaoDongLabel)
                        .addGap(18, 18, 18)
                        .addComponent(SLBaoDongInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(TKNeedBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeedBookPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PrintNeedBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        NeedBookPaneLayout.setVerticalGroup(
            NeedBookPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NeedBookPaneLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(NeedBookPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeedBookPaneLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(SLBaoDongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NeedBookPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TKNeedBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SLBaoDongInput, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PrintNeedBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sách cần nhập", NeedBookPane);

        BanChayMonthInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        BanChayYearLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BanChayYearLabel.setText("Năm");

        BanChayMonthLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BanChayMonthLabel.setText("Tháng");

        BanChayBtn.setBackground(new java.awt.Color(51, 153, 255));
        BanChayBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BanChayBtn.setForeground(new java.awt.Color(255, 255, 255));
        BanChayBtn.setText("Thống kê");
        BanChayBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BanChayBtnMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(BanChayTable);

        PrintBanChayBtn.setText("In bảng thống kê");
        PrintBanChayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintBanChayBtnActionPerformed(evt);
            }
        });

        BanChayTopLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BanChayTopLabel.setText("TOP");

        javax.swing.GroupLayout BanChayPaneLayout = new javax.swing.GroupLayout(BanChayPane);
        BanChayPane.setLayout(BanChayPaneLayout);
        BanChayPaneLayout.setHorizontalGroup(
            BanChayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BanChayPaneLayout.createSequentialGroup()
                .addContainerGap(261, Short.MAX_VALUE)
                .addGroup(BanChayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BanChayPaneLayout.createSequentialGroup()
                        .addComponent(BanChayTopLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TopBanChayInput, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BanChayMonthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BanChayMonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(BanChayYearLabel)
                        .addGap(18, 18, 18)
                        .addComponent(BanChayYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(BanChayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BanChayPaneLayout.createSequentialGroup()
                        .addComponent(PrintBanChayBtn)
                        .addGap(17, 17, 17))))
        );
        BanChayPaneLayout.setVerticalGroup(
            BanChayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanChayPaneLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(BanChayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TopBanChayInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BanChayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BanChayMonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BanChayYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BanChayYearLabel)
                        .addComponent(BanChayMonthLabel)
                        .addComponent(BanChayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BanChayTopLabel)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PrintBanChayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bán chạy", BanChayPane);

        PrintTonKhoBtn.setText("In bảng thống kê");
        PrintTonKhoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintTonKhoBtnActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(TonKhoTable);

        TonKhoLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TonKhoLabel.setForeground(new java.awt.Color(0, 0, 255));
        TonKhoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TonKhoLabel.setText("SỐ LƯỢNG HÀNG TỒN TRONG THÁNG NÀY");

        javax.swing.GroupLayout TonKhoPaneLayout = new javax.swing.GroupLayout(TonKhoPane);
        TonKhoPane.setLayout(TonKhoPaneLayout);
        TonKhoPaneLayout.setHorizontalGroup(
            TonKhoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TonKhoPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PrintTonKhoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TonKhoPaneLayout.createSequentialGroup()
                .addComponent(TonKhoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        TonKhoPaneLayout.setVerticalGroup(
            TonKhoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TonKhoPaneLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(TonKhoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PrintTonKhoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tồn kho", TonKhoPane);

        NhapXuatMonthLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NhapXuatMonthLabel.setText("Tháng");

        NhapXuatMonthInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        NhapXuatYearLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NhapXuatYearLabel.setText("Năm");

        NhapXuatBtn.setBackground(new java.awt.Color(51, 153, 255));
        NhapXuatBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NhapXuatBtn.setForeground(new java.awt.Color(255, 255, 255));
        NhapXuatBtn.setText("Thống kê");
        NhapXuatBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NhapXuatBtnMouseClicked(evt);
            }
        });

        PrintNhapXuatBtn.setText("In bảng thống kê");
        PrintNhapXuatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintNhapXuatBtnActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(NhapXuatTable);

        javax.swing.GroupLayout NhapXuatPaneLayout = new javax.swing.GroupLayout(NhapXuatPane);
        NhapXuatPane.setLayout(NhapXuatPaneLayout);
        NhapXuatPaneLayout.setHorizontalGroup(
            NhapXuatPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhapXuatPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(NhapXuatPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhapXuatPaneLayout.createSequentialGroup()
                        .addComponent(NhapXuatMonthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NhapXuatMonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NhapXuatYearLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NhapXuatYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NhapXuatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhapXuatPaneLayout.createSequentialGroup()
                        .addComponent(PrintNhapXuatBtn)
                        .addGap(14, 14, 14))))
        );
        NhapXuatPaneLayout.setVerticalGroup(
            NhapXuatPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapXuatPaneLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(NhapXuatPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NhapXuatMonthLabel)
                    .addComponent(NhapXuatMonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NhapXuatYearLabel)
                    .addComponent(NhapXuatYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NhapXuatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PrintNhapXuatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhập xuất kho", NhapXuatPane);

        TKLable.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TKLable.setForeground(new java.awt.Color(0, 0, 255));
        TKLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TKLable.setText("THỐNG KÊ");
        TKLable.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(TKLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TKLable)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TKBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKBtnMouseClicked
        HoaDon_Connect hd_connect = new HoaDon_Connect();
        String thang  = MonthInput.getSelectedItem().toString();
        String nam = YearInput.getText();
        dshd_thongke = hd_connect.layToanBoHoaDonTheoThangNam(thang, nam);
        tongTien = 0;
        dtmHoaDon.setRowCount(0);
        for(HoaDon hd : dshd_thongke){
            tongTien = tongTien + hd.getTongTien();
            Vector<Object> vec = new Vector<Object>();
            vec.add(hd.getMaHD());
            vec.add(hd.getMaNV());
            vec.add(hd.getNgaylap());
            vec.add(df.format(hd.getTongTien()));
            dtmHoaDon.addRow(vec);
        }
        HDTable.setModel(dtmHoaDon);
        TotalOutput.setText(df.format(tongTien) + " vnđ");
    }//GEN-LAST:event_TKBtnMouseClicked

    private void PrintHDBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintHDBtnActionPerformed
        try {
            MessageFormat header = new MessageFormat("Danh sách hóa đơn tháng")  ;
            MessageFormat footer = new MessageFormat("Page{1,number,interger} ")  ;
            HDTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e2) {
                e2.printStackTrace();
        }
    }//GEN-LAST:event_PrintHDBtnActionPerformed

    private void TKNeedBookBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKNeedBookBtnMouseClicked
        Sach_Connect sach_conn = new Sach_Connect();
        dss = sach_conn.laySachConDuoiTon((int)SLBaoDongInput.getValue());
        dtmSach.setRowCount(0);
        for(Sach s : dss){
            Vector<Object> vec = new Vector<Object>();
            vec.add(s.getMaSach());
            vec.add(s.getTenSach());
            vec.add(s.getMaNXB());
            vec.add(s.getSoLuong());

            dtmSach.addRow(vec);
        }
        NeedBookTable.setModel(dtmSach);
    }//GEN-LAST:event_TKNeedBookBtnMouseClicked

    private void PrintNeedBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintNeedBookBtnActionPerformed
        try {
            MessageFormat header = new MessageFormat("Danh sách loại sách cần nhập");
            MessageFormat footer = new MessageFormat("Page{1,number,interger}");
            NeedBookTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e2) {
                e2.printStackTrace();
        }
    }//GEN-LAST:event_PrintNeedBookBtnActionPerformed

    private void BanChayBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BanChayBtnMouseClicked
        Sach_Connect sach_conn = new Sach_Connect();
        dss = sach_conn.laySachBanChay(TopBanChayInput.getValue().toString(), Integer.toString(BanChayMonthInput.getSelectedIndex()), BanChayYearInput.getText());
        dtmBanChay.setRowCount(0);
        for(Sach s : dss){
            Vector<Object> vec = new Vector<Object>();
            vec.add(s.getMaSach());
            vec.add(s.getTenSach());
            vec.add(s.getSoLuong());

            dtmBanChay.addRow(vec);
        }
        BanChayTable.setModel(dtmBanChay);
    }//GEN-LAST:event_BanChayBtnMouseClicked

    private void PrintBanChayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintBanChayBtnActionPerformed
        try {
            MessageFormat header = new MessageFormat("Danh sách loại sách bán chạy");
            MessageFormat footer = new MessageFormat("Page{1,number,interger}");
            BanChayTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e2) {
                e2.printStackTrace();
        }
    }//GEN-LAST:event_PrintBanChayBtnActionPerformed

    private void PrintTonKhoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintTonKhoBtnActionPerformed
        try {
            MessageFormat header = new MessageFormat("Danh sách tồn kho");
            MessageFormat footer = new MessageFormat("Page{1,number,interger}");
            TonKhoTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e2) {
                e2.printStackTrace();
        }
    }//GEN-LAST:event_PrintTonKhoBtnActionPerformed

    private void NhapXuatBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhapXuatBtnMouseClicked
        Calendar cal = Calendar.getInstance();
        Sach_Connect sach_conn = new Sach_Connect();
        dsnx = sach_conn.layThongTinNhapXuat(NhapXuatMonthInput.getSelectedIndex(), Integer.parseInt(NhapXuatYearInput.getText()));
        dtmNhapXuat.setRowCount(0);
        for(TonKho tk : dsnx){
            Vector<Object> vec = new Vector<Object>();
            vec.add(tk.getMaSach());
            vec.add(tk.getTenSach());
            vec.add(tk.getNhap());
            vec.add(tk.getXuat());

            dtmNhapXuat.addRow(vec);
        }
        NhapXuatTable.setModel(dtmNhapXuat);
    }//GEN-LAST:event_NhapXuatBtnMouseClicked

    private void PrintNhapXuatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintNhapXuatBtnActionPerformed
        try {
            MessageFormat header = new MessageFormat("Danh sách tồn kho");
            MessageFormat footer = new MessageFormat("Page{1,number,interger}");
            TonKhoTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e2) {
                e2.printStackTrace();
        }
    }//GEN-LAST:event_PrintNhapXuatBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKe("Thống kê").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BanChayBtn;
    private javax.swing.JComboBox<String> BanChayMonthInput;
    private javax.swing.JLabel BanChayMonthLabel;
    private javax.swing.JPanel BanChayPane;
    private javax.swing.JTable BanChayTable;
    private javax.swing.JLabel BanChayTopLabel;
    private javax.swing.JTextField BanChayYearInput;
    private javax.swing.JLabel BanChayYearLabel;
    private javax.swing.JPanel HDPane;
    private javax.swing.JTable HDTable;
    private javax.swing.JComboBox<String> MonthInput;
    private javax.swing.JLabel MonthLabel;
    private javax.swing.JPanel NeedBookPane;
    private javax.swing.JTable NeedBookTable;
    private javax.swing.JButton NhapXuatBtn;
    private javax.swing.JComboBox<String> NhapXuatMonthInput;
    private javax.swing.JLabel NhapXuatMonthLabel;
    private javax.swing.JPanel NhapXuatPane;
    private javax.swing.JTable NhapXuatTable;
    private javax.swing.JTextField NhapXuatYearInput;
    private javax.swing.JLabel NhapXuatYearLabel;
    private javax.swing.JButton PrintBanChayBtn;
    private javax.swing.JButton PrintHDBtn;
    private javax.swing.JButton PrintNeedBookBtn;
    private javax.swing.JButton PrintNhapXuatBtn;
    private javax.swing.JButton PrintTonKhoBtn;
    private javax.swing.JSpinner SLBaoDongInput;
    private javax.swing.JLabel SLBaoDongLabel;
    private javax.swing.JButton TKBtn;
    private javax.swing.JLabel TKLable;
    private javax.swing.JButton TKNeedBookBtn;
    private javax.swing.JLabel TonKhoLabel;
    private javax.swing.JPanel TonKhoPane;
    private javax.swing.JTable TonKhoTable;
    private javax.swing.JSpinner TopBanChayInput;
    private javax.swing.JLabel TotalLabel;
    private javax.swing.JLabel TotalOutput;
    private javax.swing.JTextField YearInput;
    private javax.swing.JLabel YearLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
