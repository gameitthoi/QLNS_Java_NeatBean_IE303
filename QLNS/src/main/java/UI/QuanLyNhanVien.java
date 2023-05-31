/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Connect.ChucVu_Connect;
import Connect.NhanVien_Connect;
import Model.ChucVu;
import Model.NhanVien;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author dat
 */
public class QuanLyNhanVien extends javax.swing.JFrame {
private DefaultTableModel dtmNhanVien;
private ArrayList<NhanVien> nhanViens =null;
private ArrayList<NhanVien> dsnv_tim = null;
private ArrayList<ChucVu> dscv = null;
    /**
     * Creates new form QuanLyNhanVien
     */
    public QuanLyNhanVien(String title) {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        hienThiChucVu();
        hienThiToanBoNhanVien();
    }
    
    private void hienThiChucVu () {
        ChucVu_Connect cvconn = new ChucVu_Connect();
        dscv=cvconn.layToanBoChucVu();
        CVInput.removeAllItems();
        for(ChucVu s : dscv) CVInput.addItem(s);
    }
    
    private void ResetForm(){
        MaNVInput.setText("");
        TenNVInput.setText("");
        NSInput.setText("");
        NVLInput.setText("");
        CCCDInput.setText("");
        CVInput.setSelectedIndex(1);
        SDTInput.setText("");
        EmailInput.setText("");
        LuongInput.setText("");
    }
    
    private void hienThiToanBoNhanVien() {
        NhanVien_Connect nv_conn = new NhanVien_Connect();
        nhanViens = nv_conn.layToanBoNhanVien();
        dtmNhanVien = new DefaultTableModel();
        dtmNhanVien.addColumn("Mã nhân viên");
        dtmNhanVien.addColumn("Tên nhân viên");
        dtmNhanVien.addColumn("Ngày Sinh");
        dtmNhanVien.addColumn("Ngày vào làm");
        dtmNhanVien.addColumn("Số chứng minh");
        dtmNhanVien.addColumn("Chức vụ");
        dtmNhanVien.addColumn("Số điện thoại");
        dtmNhanVien.addColumn("Email");
        dtmNhanVien.addColumn("Lương");
        dtmNhanVien.setRowCount(0);
        for(NhanVien nv : nhanViens){
            Vector<Object> vec = new Vector<Object>();
            vec.add(nv.getMaNV());
            vec.add(nv.getTenNV());
            vec.add(nv.getNgaySinh());
            vec.add(nv.getNgayVaolam());
            vec.add(nv.getSoChungMinh());
            vec.add(nv.getMaCV());
            vec.add(nv.getSDT());
            vec.add(nv.getEmail());
            vec.add(formatCurrency(nv.getLuong()));
            dtmNhanVien.addRow(vec);
        }
        NVTable.setModel(dtmNhanVien);
        AddFromExcelBtn.setEnabled(false);
        ResetForm();
    }
    
    private void xuLyThemMoi(String maNV) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        NhanVien nv = new NhanVien();
        nv.setMaNV(MaNVInput.getText());
        nv.setTenNV(TenNVInput.getText());
        try {
            nv.setNgaySinh(dateFormat.format(dateFormat.parse(NSInput.getText())));
            nv.setNgayVaolam(dateFormat.format(dateFormat.parse(NVLInput.getText())));
        } catch (ParseException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        nv.setSoChungMinh(CCCDInput.getText());
        if(CVInput.getSelectedIndex()<10)
            nv.setMaCV("CV0"+(CVInput.getSelectedIndex()+1));
        else
            nv.setMaCV("CV"+(CVInput.getSelectedIndex()+1));
        nv.setSDT(SDTInput.getText());
        nv.setEmail(EmailInput.getText());
        nv.setLuong(Double.parseDouble(LuongInput.getText()));
        NhanVien_Connect nv_conn = new NhanVien_Connect();
        if(nv_conn.kiemTraTonTai(maNV)==true) JOptionPane.showMessageDialog(null, "Mã nhân viên này đã tồn tại!");
        else{
            int activeLuu = nv_conn.themNhanVien(nv);
            if(activeLuu>0){
                JOptionPane.showMessageDialog(null, "Thêm mới thành công!");
                hienThiToanBoNhanVien();
            }
            else JOptionPane.showMessageDialog(null, "Thêm mới thất bại!");
        } 
    }  
    
    //hàm check ngày tháng năm
    private boolean CheckDate(String dateString){
        String dateFormatPattern = "dd/MM/yyyy"; // Định dạng ngày tháng dd/MM/yyyy
    
       try {
        LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormatPattern));
            return true; // Chuỗi hợp lệ với định dạng
        } catch (DateTimeParseException e) {
            return false; // Chuỗi không hợp lệ với định dạng
        }
    }
    
    //hàm check email
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    //hàm định dạng tiền tệ
    public String formatCurrency(double amount) {
        // Định dạng số có dấu phân cách và đơn vị tiền tệ
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedAmount = formatter.format(amount);

        // Thêm ký tự đơn vị tiền tệ
        formattedAmount += " đ";

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

        jPanel1 = new javax.swing.JPanel();
        TKNVBtn = new javax.swing.JButton();
        AllNVBtn = new javax.swing.JButton();
        TimKiemInput = new javax.swing.JTextField();
        NhanTenLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        MaNVLabel = new javax.swing.JLabel();
        MaNVInput = new javax.swing.JTextField();
        NVLLabel = new javax.swing.JLabel();
        NVLInput = new javax.swing.JTextField();
        SDTLabel = new javax.swing.JLabel();
        SDTInput = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        TenNVLabel = new javax.swing.JLabel();
        TenNVInput = new javax.swing.JTextField();
        CCCDLabel = new javax.swing.JLabel();
        CCCDInput = new javax.swing.JTextField();
        NSLabel = new javax.swing.JLabel();
        NSInput = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        EmailLabel = new javax.swing.JLabel();
        EmailInput = new javax.swing.JTextField();
        CVLabel = new javax.swing.JLabel();
        CVInput = new javax.swing.JComboBox<ChucVu>();
        LuongLabel = new javax.swing.JLabel();
        LuongInput = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        AddBtn = new javax.swing.JButton();
        UpdateBtn = new javax.swing.JButton();
        ResetBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        ImportExcelBtn = new javax.swing.JButton();
        AddFromExcelBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        NVTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        TKNVBtn.setText("Tìm kiếm");
        TKNVBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKNVBtnActionPerformed(evt);
            }
        });

        AllNVBtn.setText("Tất cả");
        AllNVBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllNVBtnActionPerformed(evt);
            }
        });

        TimKiemInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TimKiemInputKeyPressed(evt);
            }
        });

        NhanTenLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NhanTenLabel.setText("Nhập tên hoặc mã nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimKiemInput, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(NhanTenLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TKNVBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AllNVBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(NhanTenLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TimKiemInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TKNVBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AllNVBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        MaNVLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MaNVLabel.setText("Mã nhân viên");

        NVLLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NVLLabel.setText("Ngày vào làm");

        SDTLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SDTLabel.setText("Số điện thoại");

        SDTInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SDTInputKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(SDTLabel)
                        .addGap(18, 18, 18)
                        .addComponent(SDTInput, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(NVLLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NVLInput, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(MaNVLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MaNVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaNVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaNVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NVLInput)
                    .addComponent(NVLLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SDTLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SDTInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        TenNVLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TenNVLabel.setText("Tên nhân viên");

        CCCDLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CCCDLabel.setText("CCCD");

        CCCDInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CCCDInputKeyTyped(evt);
            }
        });

        NSLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NSLabel.setText("Ngày sinh");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(TenNVLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TenNVInput, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CCCDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NSLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CCCDInput)
                            .addComponent(NSInput))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenNVLabel)
                    .addComponent(TenNVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NSLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NSInput, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CCCDInput)
                    .addComponent(CCCDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        EmailLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        EmailLabel.setText("Email");

        CVLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CVLabel.setText("Chức vụ");

        LuongLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LuongLabel.setText("Lương");

        LuongInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LuongInputKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CVLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EmailInput)
                            .addComponent(CVInput, 0, 142, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(LuongLabel)
                        .addGap(50, 50, 50)
                        .addComponent(LuongInput)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmailLabel)
                    .addComponent(EmailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LuongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LuongInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddBtn.setText("Thêm");
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });

        UpdateBtn.setText("Cập nhật");
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });

        ResetBtn.setText("Nhập lại");
        ResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetBtnActionPerformed(evt);
            }
        });

        DeleteBtn.setText("Xóa");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });

        ImportExcelBtn.setText("Nhập từ excel");
        ImportExcelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportExcelBtnActionPerformed(evt);
            }
        });

        AddFromExcelBtn.setText("Thêm tất cả");
        AddFromExcelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddFromExcelBtnActionPerformed(evt);
            }
        });
        AddFromExcelBtn.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ResetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ImportExcelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddFromExcelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(UpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ResetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ImportExcelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddFromExcelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        NVTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NVTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(NVTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AllNVBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllNVBtnActionPerformed
        hienThiToanBoNhanVien();
        AddFromExcelBtn.setEnabled(false);
    }//GEN-LAST:event_AllNVBtnActionPerformed

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        if(!CheckDate(NVLInput.getText()) || !CheckDate(NSInput.getText())) {
            JOptionPane.showMessageDialog(null, "Ngày vào làm hoặc ngày sinh không hợp lệ");
            return;
        }
        if(!isValidEmail(EmailInput.getText())){
            JOptionPane.showMessageDialog(null, "Email không đúng định dạng!");
            return;
        }
        int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thêm nhân viên này?", "xác nhận xác nhận để thêm", JOptionPane.OK_CANCEL_OPTION);
        if(ret==JOptionPane.OK_OPTION){
            if(MaNVInput.getText().equals("") || TenNVInput.getText().equals("") || NSInput.getText().equals("") || NVLInput.getText().equals("") || CCCDInput.getText().equals("") || SDTInput.getText().equals("") || EmailInput.getText().equals("") || LuongInput.getText().equals("") )
                JOptionPane.showMessageDialog(null, "Dữ liệu còn thiếu");
            else {
                String maNV = MaNVInput.getText();				
                try {
                    xuLyThemMoi(maNV);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_AddBtnActionPerformed

    private void TKNVBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKNVBtnActionPerformed
        if(TimKiemInput.getText()==null ) return ;
        String key = TimKiemInput.getText();
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
            vec.add(String.format("%.0f", nv.getLuong()));
            dtmNhanVien.addRow(vec);
        }
	NVTable.setModel(dtmNhanVien);
        AddFromExcelBtn.setEnabled(false);
    }//GEN-LAST:event_TKNVBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn xóa nhân viên này?", "xác nhận xác nhận để xóa", JOptionPane.OK_CANCEL_OPTION);
        if(ret==JOptionPane.OK_OPTION){
            if(MaNVInput.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Dữ liệu còn thiếu");
            else {
                String manv = MaNVInput.getText();
                NhanVien_Connect nv_conn = new NhanVien_Connect();
                int active = nv_conn.xoaNhanVien(manv);
                if(active>0){
                        JOptionPane.showMessageDialog(null, "Xóa thành công!");
                        hienThiToanBoNhanVien();
                }
                else JOptionPane.showMessageDialog(null, "Xóa thất bại!");
            }
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void ResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetBtnActionPerformed
        ResetForm();
    }//GEN-LAST:event_ResetBtnActionPerformed

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        if(!CheckDate(NVLInput.getText()) || !CheckDate(NSInput.getText())) {
            JOptionPane.showMessageDialog(null, "Ngày vào làm hoặc ngày sinh không hợp lệ");
            return;
        }
        if(!isValidEmail(EmailInput.getText())){
            JOptionPane.showMessageDialog(null, "Email không đúng định dạng!");
            return;
        }
        int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn chỉnh sửa nhân viên này?", "xác nhận xác nhận để thêm", JOptionPane.OK_CANCEL_OPTION);
        if(ret==JOptionPane.OK_OPTION){
            if(MaNVInput.getText().equals("") || TenNVInput.getText().equals("") || NSInput.getText().equals("") || NVLInput.getText().equals("") || CCCDInput.getText().equals("") || SDTInput.getText().equals("") || EmailInput.getText().equals("") || LuongInput.getText().equals("") ){
                JOptionPane.showMessageDialog(null, "Dữ liệu còn thiếu");
            }
            else {
                NhanVien_Connect nv_conn = new NhanVien_Connect();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                NhanVien nv = new NhanVien();
                nv.setMaNV(MaNVInput.getText());
                nv.setTenNV(TenNVInput.getText());
                nv.setNgaySinh(NSInput.getText());
                try {
                    nv.setNgaySinh(dateFormat.format(dateFormat.parse(NSInput.getText())));
                    nv.setNgayVaolam(dateFormat.format(dateFormat.parse(NVLInput.getText())));
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                nv.setNgaySinh(NSInput.getText());
                nv.setNgayVaolam(NVLInput.getText());
                nv.setSoChungMinh(CCCDInput.getText());
                if(CVInput.getSelectedIndex()<10)
                    nv.setMaCV("CV0"+(CVInput.getSelectedIndex()+1));
                else
                    nv.setMaCV("CV"+(CVInput.getSelectedIndex()+1));
                nv.setSDT(SDTInput.getText());
                nv.setEmail(EmailInput.getText());
                nv.setLuong(Double.parseDouble(LuongInput.getText()));
                int activeUpdate = nv_conn.updateNhanVien(nv);
                if(activeUpdate>0){
                    JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công!");
                    hienThiToanBoNhanVien();
                }
                else JOptionPane.showMessageDialog(null, "Chỉnh sửa thất bại!"); 
            }
        }
    }//GEN-LAST:event_UpdateBtnActionPerformed

    private void NVTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NVTableMouseClicked
        int select = NVTable.getSelectedRow();
        ChucVu_Connect cvconn = new ChucVu_Connect();
        MaNVInput.setText(NVTable.getValueAt(select, 0)+"");
        TenNVInput.setText(NVTable.getValueAt(select, 1)+"");
        NSInput.setText(NVTable.getValueAt(select, 2)+"");
        NVLInput.setText(NVTable.getValueAt(select, 3)+"");
        CCCDInput.setText(NVTable.getValueAt(select, 4)+"");
        //CVInput.setSelectedItem(cvconn.TimChucVu(NVTable.getValueAt(select, 5).toString()));
        CVInput.setSelectedIndex(Integer.parseInt(cvconn.TimChucVu(NVTable.getValueAt(select, 5).toString()).getMaCV().substring(2))-1);
        SDTInput.setText(NVTable.getValueAt(select, 6)+"");
        EmailInput.setText(NVTable.getValueAt(select, 7)+"");
        LuongInput.setText(removeCurrencyFormatting(NVTable.getValueAt(select, 8)+""));
    }//GEN-LAST:event_NVTableMouseClicked

    private void ImportExcelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportExcelBtnActionPerformed
        // Tạo đối tượng JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Chỉ định bộ lọc để chỉ cho phép chọn các tệp Excel
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls");
        fileChooser.setFileFilter(filter);

        // Hiển thị cửa sổ Explorer và lấy tệp được chọn
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            //đọc file excel đó
            try {
                // Tạo đối tượng File từ đường dẫn
                File file = new File(filePath);

                // Đọc tệp Excel
                FileInputStream fis = new FileInputStream(file);
                Workbook workbook = new HSSFWorkbook(fis);

                // Lấy ra sheet đầu tiên từ workbook
                Sheet sheet = workbook.getSheetAt(0);
                
                //Xóa tất cả các dòng trong bảng nhân viên
                dtmNhanVien.setRowCount(0);

                // Duyệt qua các dòng trong sheet
                for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    if (rowIndex == 0) {
                        continue; // Bỏ qua dòng đầu tiên
                    }
                    
                    Row row = sheet.getRow(rowIndex);
                    
                    String[] rowData = new String[dtmNhanVien.getColumnCount()];

                    // Duyệt qua các ô trong dòng
                    for (int columnIndex = 0; columnIndex < dtmNhanVien.getColumnCount(); columnIndex++) {
                        Cell cell = row.getCell(columnIndex);
                        
                        // Chuyển đổi giá trị của ô thành kiểu String và lưu vào mảng rowData
                        String cellValue = "";
                        if (cell != null) {
                            if (cell.getCellType() == CellType.STRING) {
                                cellValue = cell.getStringCellValue();
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    // Kiểm tra nếu là giá trị ngày tháng
                                    Date dateValue = cell.getDateCellValue();
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    cellValue = dateFormat.format(dateValue);
                                } else {
                                    cellValue = String.format("%.0f",cell.getNumericCellValue()); 
                                }
                            } else if (cell.getCellType() == CellType.BLANK) {
                                cellValue = "";
                            }
                        }

                        rowData[columnIndex] = cellValue;
                    }

                    // Thêm dòng dữ liệu vào mô hình dtmNhanVien
                    dtmNhanVien.addRow(rowData);
                }

                // Đóng FileInputStream và workbook
                fis.close();
                workbook.close();

                // Cập nhật bảng NVTable với mô hình dtmNhanVien
                NVTable.setModel(dtmNhanVien);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AddFromExcelBtn.setEnabled(true);
    }//GEN-LAST:event_ImportExcelBtnActionPerformed

    private void AddFromExcelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddFromExcelBtnActionPerformed
        int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thêm tất cả nhân viên có trong bảng?", "xác nhận xác nhận để thêm", JOptionPane.OK_CANCEL_OPTION);
        if(ret==JOptionPane.OK_OPTION){
            int dem = 0;
            NhanVien_Connect nv_conn = new NhanVien_Connect();
            ChucVu_Connect cvconn = new ChucVu_Connect();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            //lặp qua tất cả các dòng của bảng
            for (int i=0; i< NVTable.getRowCount(); i++){
                NhanVien nv = new NhanVien();
                nv.setMaNV(NVTable.getValueAt(i, 0).toString());
                nv.setTenNV(NVTable.getValueAt(i, 1).toString());
                try {
                    nv.setNgaySinh(dateFormat.format(dateFormat.parse(NVTable.getValueAt(i, 2).toString())));
                    nv.setNgayVaolam(dateFormat.format(dateFormat.parse(NVTable.getValueAt(i, 3).toString())));
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                nv.setSoChungMinh(NVTable.getValueAt(i, 4).toString());
                ChucVu cv = cvconn.TimChucVu(NVTable.getValueAt(i, 5).toString());
                nv.setMaCV(cv.getMaCV());
                nv.setSDT(NVTable.getValueAt(i, 6).toString());
                nv.setEmail(NVTable.getValueAt(i, 7).toString());
                nv.setLuong(Double.parseDouble(NVTable.getValueAt(i, 8).toString()));
                if(nv_conn.kiemTraTonTai(nv.getMaNV())==true) JOptionPane.showMessageDialog(null, "Mã nhân viên "+nv.getMaNV()+" đã tồn tại!");
                else{
                    int activeLuu = nv_conn.themNhanVien(nv);
                    if(activeLuu<=0) JOptionPane.showMessageDialog(null, "Thêm mới "+nv.getMaNV()+" thất bại!");
                    else dem++;
                }
            }
            if(dem == NVTable.getRowCount()) hienThiToanBoNhanVien();
        }
    }//GEN-LAST:event_AddFromExcelBtnActionPerformed

    private void SDTInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SDTInputKeyTyped
        //kiểm tra xem người dùng có nhập số vào không, nếu không phải số thì không nhận
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') ||
            (c == KeyEvent.VK_BACK_SPACE) ||
            (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
    }//GEN-LAST:event_SDTInputKeyTyped

    private void CCCDInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CCCDInputKeyTyped
        //kiểm tra xem người dùng có nhập số vào không, nếu không phải số thì không nhận
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') ||
            (c == KeyEvent.VK_BACK_SPACE) ||
            (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
    }//GEN-LAST:event_CCCDInputKeyTyped

    private void LuongInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LuongInputKeyTyped
        //kiểm tra xem người dùng có nhập số vào không, nếu không phải số thì không nhận
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') ||
            (c == KeyEvent.VK_BACK_SPACE) ||
            (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
    }//GEN-LAST:event_LuongInputKeyTyped

    private void TimKiemInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemInputKeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            TKNVBtnActionPerformed(null);
        }
    }//GEN-LAST:event_TimKiemInputKeyPressed

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
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien("Quản lý nhân viên").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JButton AddFromExcelBtn;
    private javax.swing.JButton AllNVBtn;
    private javax.swing.JTextField CCCDInput;
    private javax.swing.JLabel CCCDLabel;
    private javax.swing.JComboBox<ChucVu> CVInput;
    private javax.swing.JLabel CVLabel;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JTextField EmailInput;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JButton ImportExcelBtn;
    private javax.swing.JTextField LuongInput;
    private javax.swing.JLabel LuongLabel;
    private javax.swing.JTextField MaNVInput;
    private javax.swing.JLabel MaNVLabel;
    private javax.swing.JTextField NSInput;
    private javax.swing.JLabel NSLabel;
    private javax.swing.JTextField NVLInput;
    private javax.swing.JLabel NVLLabel;
    private javax.swing.JTable NVTable;
    private javax.swing.JLabel NhanTenLabel;
    private javax.swing.JButton ResetBtn;
    private javax.swing.JTextField SDTInput;
    private javax.swing.JLabel SDTLabel;
    private javax.swing.JButton TKNVBtn;
    private javax.swing.JTextField TenNVInput;
    private javax.swing.JLabel TenNVLabel;
    private javax.swing.JTextField TimKiemInput;
    private javax.swing.JButton UpdateBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
