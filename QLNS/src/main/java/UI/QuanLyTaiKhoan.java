/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Connect.NhanVien_Connect;
import Connect.TaiKhoan_Connect;
import Model.NhanVien;
import Model.TaiKhoan;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dat
 */
public class QuanLyTaiKhoan extends javax.swing.JFrame {
private DefaultTableModel dtmNhanVien;
private ArrayList<NhanVien> nhanViens =null;
private DefaultTableModel dtmTaiKhoan;
private ArrayList<TaiKhoan> taiKhoans =null;
private ArrayList<NhanVien> dsnv_tim = null;
private ArrayList<TaiKhoan> dstk_tim=null;

    public QuanLyTaiKhoan(String title) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        hienThiToanBoTaiKhoan();
        hienThiToanBoNhanVien();
    }
    
    private void hienThiToanBoNhanVien() {
        NhanVien_Connect nv_conn = new NhanVien_Connect();
        nhanViens = nv_conn.layToanBoNhanVien();
        dtmNhanVien = new DefaultTableModel();
        dtmNhanVien.addColumn("Mã nhân viên");
        dtmNhanVien.addColumn("Tên nhân viên");
        dtmNhanVien.addColumn("Chức vụ");
        dtmNhanVien.setRowCount(0);
        for(NhanVien nv : nhanViens){
            Vector<Object> vec = new Vector<Object>();
            vec.add(nv.getMaNV());
            vec.add(nv.getTenNV());
            vec.add(nv.getMaCV());
            dtmNhanVien.addRow(vec);
        }
        NhanVienTable.setModel(dtmNhanVien);
    }   
    
    private void hienThiToanBoTaiKhoan(){
        TaiKhoan_Connect tk_conn = new TaiKhoan_Connect();
        taiKhoans = tk_conn.layToanBoTaiKhoan();
        dtmTaiKhoan = new DefaultTableModel();
        dtmTaiKhoan.addColumn("Mã tài khoản");
        dtmTaiKhoan.addColumn("Mã nhân viên");
        dtmTaiKhoan.addColumn("Username");
        dtmTaiKhoan.addColumn("Password");
        for(TaiKhoan tk : taiKhoans){
            Vector<Object> vec = new Vector<Object>();
            vec.add(tk.getMaTK());
            vec.add(tk.getMaNV());
            vec.add(tk.getUserName());
            vec.add(tk.getPassWord());
            dtmTaiKhoan.addRow(vec);
        }
        TaiKhoanTable.setModel(dtmTaiKhoan);
    }
    
    private void xuLyThemMoi(String maTK, String username, String manv) throws ParseException{
        TaiKhoan_Connect tk_conn = new TaiKhoan_Connect();
        if(tk_conn.kiemTraTonTai(maTK, username, manv)==true) JOptionPane.showMessageDialog(null, "Mã tài khoản này đã tồn tại!");
        else{
            int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thêm tài khoản mới?", "xác nhận xác nhận để thêm", JOptionPane.OK_CANCEL_OPTION);
            if(ret==JOptionPane.OK_OPTION){
                TaiKhoan tk= new TaiKhoan();
                tk.setMaTK(maTK);
                tk.setMaNV(MaNVInput.getText());
                tk.setUserName(UsernameInput.getText());
                tk.setPassWord(PasswordInput.getText());
                int activeLuu = tk_conn.themTaiKhoan(tk);
                if(activeLuu>0){
                    JOptionPane.showMessageDialog(null, "Thêm mới thành công!");
                    hienThiToanBoTaiKhoan();
                }
                else JOptionPane.showMessageDialog(null, "Thêm mới thất bại!");	
            } 
        } 
    }
    
    private boolean checkInputs(){
        if(MaTKInput.getText().isEmpty() || MaNVInput.getText().isEmpty() || UsernameInput.getText().isEmpty() || PasswordInput.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Thiếu dữ liệu!");
            return false;
        } 
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TaiKhoanTable = new javax.swing.JTable();
        MaTaiKhoanPanel = new javax.swing.JPanel();
        MaTKInput = new javax.swing.JTextField();
        MaTKLabel = new javax.swing.JLabel();
        MaNhanVienPanel = new javax.swing.JPanel();
        MaNVInput = new javax.swing.JTextField();
        MaNVLabel = new javax.swing.JLabel();
        UsernamePane = new javax.swing.JPanel();
        UsernameInput = new javax.swing.JTextField();
        UsernameLabel = new javax.swing.JLabel();
        PasswordPane = new javax.swing.JPanel();
        PasswordInput = new javax.swing.JTextField();
        PasswordLabel = new javax.swing.JLabel();
        ThemTKBtn = new javax.swing.JButton();
        SuaTKBtn = new javax.swing.JButton();
        XoaTKBTN = new javax.swing.JButton();
        TimKiemTaiKhoanPanel = new javax.swing.JPanel();
        TKTaiKhoanBtn = new javax.swing.JButton();
        TKMaNVInput = new javax.swing.JTextField();
        TKMaNVLabel = new javax.swing.JLabel();
        ResetTKBTN = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        NhanVienTable = new javax.swing.JTable();
        TimKiemNhanVienPanel = new javax.swing.JPanel();
        TKNhanVienBtn = new javax.swing.JButton();
        TenNVInput = new javax.swing.JTextField();
        TKNhanVienLabel = new javax.swing.JLabel();
        DSNVLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        PhanQuyenLabel = new javax.swing.JLabel();
        TaiKhoanCheckBox = new javax.swing.JCheckBox();
        BaoCaoCheckBox = new javax.swing.JCheckBox();
        NhanVienCheckBox = new javax.swing.JCheckBox();
        SachCheckBox = new javax.swing.JCheckBox();
        NXBCheckBox = new javax.swing.JCheckBox();
        HoaDonCheckBox = new javax.swing.JCheckBox();
        VPPCheckBox = new javax.swing.JCheckBox();
        KHCheckBox = new javax.swing.JCheckBox();
        MaVachCheckBox = new javax.swing.JCheckBox();
        NCCVPPCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tài khoản");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51)));

        TaiKhoanTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaiKhoanTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TaiKhoanTable);

        MaTKLabel.setText("Mã tài khoản");

        javax.swing.GroupLayout MaTaiKhoanPanelLayout = new javax.swing.GroupLayout(MaTaiKhoanPanel);
        MaTaiKhoanPanel.setLayout(MaTaiKhoanPanelLayout);
        MaTaiKhoanPanelLayout.setHorizontalGroup(
            MaTaiKhoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaTaiKhoanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MaTaiKhoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MaTaiKhoanPanelLayout.createSequentialGroup()
                        .addComponent(MaTKLabel)
                        .addGap(0, 108, Short.MAX_VALUE))
                    .addComponent(MaTKInput))
                .addContainerGap())
        );
        MaTaiKhoanPanelLayout.setVerticalGroup(
            MaTaiKhoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaTaiKhoanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MaTKLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MaTKInput)
                .addContainerGap())
        );

        MaNVLabel.setText("Mã nhân viên");

        javax.swing.GroupLayout MaNhanVienPanelLayout = new javax.swing.GroupLayout(MaNhanVienPanel);
        MaNhanVienPanel.setLayout(MaNhanVienPanelLayout);
        MaNhanVienPanelLayout.setHorizontalGroup(
            MaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaNhanVienPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MaNVLabel)
                    .addComponent(MaNVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MaNhanVienPanelLayout.setVerticalGroup(
            MaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaNhanVienPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MaNVLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MaNVInput, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        UsernameLabel.setText("Username");

        javax.swing.GroupLayout UsernamePaneLayout = new javax.swing.GroupLayout(UsernamePane);
        UsernamePane.setLayout(UsernamePaneLayout);
        UsernamePaneLayout.setHorizontalGroup(
            UsernamePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsernamePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UsernamePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UsernamePaneLayout.createSequentialGroup()
                        .addComponent(UsernameLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(UsernameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addContainerGap())
        );
        UsernamePaneLayout.setVerticalGroup(
            UsernamePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsernamePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UsernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsernameInput)
                .addContainerGap())
        );

        PasswordLabel.setText("Password");

        javax.swing.GroupLayout PasswordPaneLayout = new javax.swing.GroupLayout(PasswordPane);
        PasswordPane.setLayout(PasswordPaneLayout);
        PasswordPaneLayout.setHorizontalGroup(
            PasswordPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PasswordPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PasswordPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PasswordPaneLayout.createSequentialGroup()
                        .addComponent(PasswordLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PasswordInput, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addContainerGap())
        );
        PasswordPaneLayout.setVerticalGroup(
            PasswordPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PasswordPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordInput, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        ThemTKBtn.setText("Thêm");
        ThemTKBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThemTKBtnMouseClicked(evt);
            }
        });

        SuaTKBtn.setText("Sửa");
        SuaTKBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SuaTKBtnMouseClicked(evt);
            }
        });

        XoaTKBTN.setText("Xóa");
        XoaTKBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XoaTKBTNMouseClicked(evt);
            }
        });

        TimKiemTaiKhoanPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));

        TKTaiKhoanBtn.setBackground(new java.awt.Color(0, 102, 255));
        TKTaiKhoanBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TKTaiKhoanBtn.setForeground(new java.awt.Color(255, 255, 255));
        TKTaiKhoanBtn.setText("Tìm kiếm");
        TKTaiKhoanBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKTaiKhoanBtnMouseClicked(evt);
            }
        });

        TKMaNVLabel.setText("Nhập mã nhân viên ");

        javax.swing.GroupLayout TimKiemTaiKhoanPanelLayout = new javax.swing.GroupLayout(TimKiemTaiKhoanPanel);
        TimKiemTaiKhoanPanel.setLayout(TimKiemTaiKhoanPanelLayout);
        TimKiemTaiKhoanPanelLayout.setHorizontalGroup(
            TimKiemTaiKhoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimKiemTaiKhoanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TKMaNVLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TKMaNVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TKTaiKhoanBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );
        TimKiemTaiKhoanPanelLayout.setVerticalGroup(
            TimKiemTaiKhoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemTaiKhoanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimKiemTaiKhoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TKTaiKhoanBtn)
                    .addComponent(TKMaNVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TKMaNVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ResetTKBTN.setText("Reset");
        ResetTKBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetTKBTNMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(MaTaiKhoanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(MaNhanVienPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(UsernamePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PasswordPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(ThemTKBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SuaTKBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ResetTKBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(XoaTKBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TimKiemTaiKhoanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MaNhanVienPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MaTaiKhoanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UsernamePane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PasswordPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ThemTKBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SuaTKBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(XoaTKBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ResetTKBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TimKiemTaiKhoanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));

        jScrollPane2.setViewportView(NhanVienTable);

        TimKiemNhanVienPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));

        TKNhanVienBtn.setBackground(new java.awt.Color(0, 102, 255));
        TKNhanVienBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TKNhanVienBtn.setForeground(new java.awt.Color(255, 255, 255));
        TKNhanVienBtn.setText("Tìm kiếm");
        TKNhanVienBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKNhanVienBtnMouseClicked(evt);
            }
        });

        TKNhanVienLabel.setText("Nhập tên nhân viên ");

        javax.swing.GroupLayout TimKiemNhanVienPanelLayout = new javax.swing.GroupLayout(TimKiemNhanVienPanel);
        TimKiemNhanVienPanel.setLayout(TimKiemNhanVienPanelLayout);
        TimKiemNhanVienPanelLayout.setHorizontalGroup(
            TimKiemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimKiemNhanVienPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TKNhanVienLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TenNVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TKNhanVienBtn)
                .addContainerGap())
        );
        TimKiemNhanVienPanelLayout.setVerticalGroup(
            TimKiemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemNhanVienPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimKiemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TKNhanVienBtn)
                    .addComponent(TenNVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TKNhanVienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DSNVLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        DSNVLabel.setForeground(new java.awt.Color(0, 0, 255));
        DSNVLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DSNVLabel.setText("Danh sách nhân viên");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addComponent(DSNVLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TimKiemNhanVienPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(DSNVLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TimKiemNhanVienPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102)));

        PhanQuyenLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PhanQuyenLabel.setForeground(new java.awt.Color(0, 0, 255));
        PhanQuyenLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PhanQuyenLabel.setText("Phân quyền");

        TaiKhoanCheckBox.setText("Tài khoản");

        BaoCaoCheckBox.setText("Báo cáo");

        NhanVienCheckBox.setText("Nhân viên");

        SachCheckBox.setText("Sách");

        NXBCheckBox.setText("NXB");

        HoaDonCheckBox.setText("Hóa đơn");

        VPPCheckBox.setText("VPP");

        KHCheckBox.setText("Khách hàng");

        MaVachCheckBox.setText("Mã vạch");

        NCCVPPCheckBox.setText("NCCVPP");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(PhanQuyenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(NhanVienCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(HoaDonCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NCCVPPCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(VPPCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(KHCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(BaoCaoCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TaiKhoanCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(MaVachCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SachCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NXBCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(PhanQuyenLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TaiKhoanCheckBox)
                    .addComponent(BaoCaoCheckBox)
                    .addComponent(MaVachCheckBox)
                    .addComponent(SachCheckBox)
                    .addComponent(NXBCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VPPCheckBox)
                    .addComponent(KHCheckBox)
                    .addComponent(NCCVPPCheckBox)
                    .addComponent(NhanVienCheckBox)
                    .addComponent(HoaDonCheckBox))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(106, 106, 106))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TaiKhoanTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoanTableMouseClicked
        int select = TaiKhoanTable.getSelectedRow();
        MaTKInput.setText(TaiKhoanTable.getValueAt(select, 0)+"");
        MaNVInput.setText(TaiKhoanTable.getValueAt(select, 1)+"");
        UsernameInput.setText(TaiKhoanTable.getValueAt(select, 2)+"");
        PasswordInput.setText(TaiKhoanTable.getValueAt(select, 2)+"");
        TaiKhoan_Connect tk_conn = new TaiKhoan_Connect();
        TaiKhoan tk = tk_conn.TimTaiKhoanBangMaTK(MaTKInput.getText());
        if(tk.getBaoCao()==1) BaoCaoCheckBox.setSelected(true);
        else BaoCaoCheckBox.setSelected(false);
        if(tk.getTaiKhoan()==1) TaiKhoanCheckBox.setSelected(true);
        else TaiKhoanCheckBox.setSelected(false);
        if(tk.getMaVach()==1) MaVachCheckBox.setSelected(true);
        else MaVachCheckBox.setSelected(false);
        if(tk.getSach()==1) SachCheckBox.setSelected(true);
        else SachCheckBox.setSelected(false);
        if(tk.getNXB()==1) NXBCheckBox.setSelected(true);
        else NXBCheckBox.setSelected(false);
        if(tk.getNhanVien()==1) NhanVienCheckBox.setSelected(true);
        else NhanVienCheckBox.setSelected(false);
        if(tk.getHoaDon()==1) HoaDonCheckBox.setSelected(true);
        else HoaDonCheckBox.setSelected(false);
        if(tk.getNCCVPP()==1) NCCVPPCheckBox.setSelected(true);
        else NCCVPPCheckBox.setSelected(false);
        if(tk.getVPP()==1) VPPCheckBox.setSelected(true);
        else VPPCheckBox.setSelected(false);
        if(tk.getKhachHang()==1) KHCheckBox.setSelected(true);
        else KHCheckBox.setSelected(false);
    }//GEN-LAST:event_TaiKhoanTableMouseClicked

    private void ThemTKBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThemTKBtnMouseClicked
        if(!checkInputs()) return;
        String maTK = MaTKInput.getText();
        String username = UsernameInput.getText();
        String manv = MaNVInput.getText();
        try {
            xuLyThemMoi(maTK, username, manv);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_ThemTKBtnMouseClicked

    private void SuaTKBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SuaTKBtnMouseClicked
        if(!checkInputs()) return;
        int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn chỉnh sửa tài khoản?", "Xác nhận chỉnh sửa", JOptionPane.OK_CANCEL_OPTION);
        if(ret==JOptionPane.OK_OPTION){
            TaiKhoan_Connect tk_conn = new TaiKhoan_Connect();
            TaiKhoan tk = new TaiKhoan();
            tk.setMaTK(MaTKInput.getText());
            tk.setMaNV(MaNVInput.getText());
            tk.setUserName(UsernameInput.getText());
            tk.setPassWord(PasswordInput.getText());
            tk.setBaoCao(BaoCaoCheckBox.isSelected()? 1: 0);
            tk.setTaiKhoan(TaiKhoanCheckBox.isSelected()? 1: 0);
            tk.setMaVach(MaVachCheckBox.isSelected()? 1: 0);
            tk.setSach(SachCheckBox.isSelected()? 1: 0);
            tk.setNXB(NXBCheckBox.isSelected()? 1: 0);
            tk.setNhanVien(NhanVienCheckBox.isSelected()? 1: 0);
            tk.setHoaDon(HoaDonCheckBox.isSelected()? 1: 0);
            tk.setNCCVPP(NCCVPPCheckBox.isSelected()? 1: 0);
            tk.setVPP(VPPCheckBox.isSelected()? 1: 0);
            tk.setKhachHang(KHCheckBox.isSelected()? 1: 0);
            int activeUpdate = tk_conn.updateTaiKhoan(tk);
             if(activeUpdate>0){
                JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công!");
                hienThiToanBoTaiKhoan();
            }
            else JOptionPane.showMessageDialog(null, "Chỉnh sửa thất bại!");
        }
    }//GEN-LAST:event_SuaTKBtnMouseClicked

    private void XoaTKBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XoaTKBTNMouseClicked
        String matk = MaTKInput.getText();
        TaiKhoan_Connect tk_conn = new TaiKhoan_Connect();
        int ret = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.OK_CANCEL_OPTION);
        if(ret==JOptionPane.OK_OPTION){
            int active = tk_conn.xoaTaiKhoan(matk);
            if(active>0){
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    hienThiToanBoTaiKhoan();
            }
            else JOptionPane.showMessageDialog(null, "Xóa thất bại!");
        }
    }//GEN-LAST:event_XoaTKBTNMouseClicked

    private void TKTaiKhoanBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKTaiKhoanBtnMouseClicked
        //nếu ô mã nhân viên rỗng thì trả về tất cả nhân viên
        if(TKMaNVInput.getText()==null ) {
            hienThiToanBoTaiKhoan();
            return ;
        }
        //nếu không thì tìm theo mã nhân viên đó
        String key = TKMaNVInput.getText();
        TaiKhoan_Connect tk_conn = new TaiKhoan_Connect();
        dstk_tim = tk_conn.timTaiKhoan(key);
        dtmTaiKhoan.setRowCount(0);
        for(TaiKhoan tk : dstk_tim){
            Vector<Object> vec = new Vector<Object>();
            vec.add(tk.getMaTK());
            vec.add(tk.getMaNV());
            vec.add(tk.getUserName());
            vec.add(tk.getPassWord());
            dtmTaiKhoan.addRow(vec);
        }
        TaiKhoanTable.setModel(dtmTaiKhoan);
    }//GEN-LAST:event_TKTaiKhoanBtnMouseClicked

    private void TKNhanVienBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKNhanVienBtnMouseClicked
        //nếu ô tên nhân viên rỗng thì trả về tất cả nhân viên
        if(TenNVInput.getText()==null ) {
            hienThiToanBoNhanVien();
            return ;
        }
        //nếu không thì tìm theo tên nhân viên đó
        String key = TenNVInput.getText();
        NhanVien_Connect nv_conn = new NhanVien_Connect();
        dsnv_tim = nv_conn.timNhanVien(key);
        dtmNhanVien.setRowCount(0);
        for(NhanVien nv : dsnv_tim){
            Vector<Object> vec = new Vector<Object>();
            vec.add(nv.getMaNV());
            vec.add(nv.getTenNV());
            vec.add(nv.getNgaySinh());
            vec.add(nv.getNgayVaolam());
            vec.add(nv.getSoChungMinh());
            vec.add(nv.getMaCV());
            vec.add(nv.getSDT());
            vec.add(nv.getEmail());
            dtmNhanVien.addRow(vec);
        }
	NhanVienTable.setModel(dtmNhanVien);	
    }//GEN-LAST:event_TKNhanVienBtnMouseClicked

    private void ResetTKBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetTKBTNMouseClicked
        MaTKInput.setText("");
        MaNVInput.setText("");
        UsernameInput.setText("");
        PasswordInput.setText("");
        BaoCaoCheckBox.setSelected(false);
        TaiKhoanCheckBox.setSelected(false);
        MaVachCheckBox.setSelected(false);
        SachCheckBox.setSelected(false);
        NXBCheckBox.setSelected(false);
        NhanVienCheckBox.setSelected(false);
        HoaDonCheckBox.setSelected(false);
        NCCVPPCheckBox.setSelected(false);
        VPPCheckBox.setSelected(false);
        KHCheckBox.setSelected(false);
    }//GEN-LAST:event_ResetTKBTNMouseClicked

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
            java.util.logging.Logger.getLogger(TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyTaiKhoan("Tài khoản").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox BaoCaoCheckBox;
    private javax.swing.JLabel DSNVLabel;
    private javax.swing.JCheckBox HoaDonCheckBox;
    private javax.swing.JCheckBox KHCheckBox;
    private javax.swing.JTextField MaNVInput;
    private javax.swing.JLabel MaNVLabel;
    private javax.swing.JPanel MaNhanVienPanel;
    private javax.swing.JTextField MaTKInput;
    private javax.swing.JLabel MaTKLabel;
    private javax.swing.JPanel MaTaiKhoanPanel;
    private javax.swing.JCheckBox MaVachCheckBox;
    private javax.swing.JCheckBox NCCVPPCheckBox;
    private javax.swing.JCheckBox NXBCheckBox;
    private javax.swing.JCheckBox NhanVienCheckBox;
    private javax.swing.JTable NhanVienTable;
    private javax.swing.JTextField PasswordInput;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JPanel PasswordPane;
    private javax.swing.JLabel PhanQuyenLabel;
    private javax.swing.JButton ResetTKBTN;
    private javax.swing.JCheckBox SachCheckBox;
    private javax.swing.JButton SuaTKBtn;
    private javax.swing.JTextField TKMaNVInput;
    private javax.swing.JLabel TKMaNVLabel;
    private javax.swing.JButton TKNhanVienBtn;
    private javax.swing.JLabel TKNhanVienLabel;
    private javax.swing.JButton TKTaiKhoanBtn;
    private javax.swing.JCheckBox TaiKhoanCheckBox;
    private javax.swing.JTable TaiKhoanTable;
    private javax.swing.JTextField TenNVInput;
    private javax.swing.JButton ThemTKBtn;
    private javax.swing.JPanel TimKiemNhanVienPanel;
    private javax.swing.JPanel TimKiemTaiKhoanPanel;
    private javax.swing.JTextField UsernameInput;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.JPanel UsernamePane;
    private javax.swing.JCheckBox VPPCheckBox;
    private javax.swing.JButton XoaTKBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
