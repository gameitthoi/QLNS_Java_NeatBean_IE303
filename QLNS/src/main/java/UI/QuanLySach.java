/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Connect.NXB_Connect;
import Connect.Sach_Connect;
import Model.NXB;
import Model.Sach;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class QuanLySach extends javax.swing.JFrame {

    /**
     * Creates new form QuanLySach
     */
        private DefaultTableModel dtmSach ;
	private ArrayList<NXB> dsnxb = null;
        private ArrayList<Sach> dsChung = null;
	private ArrayList<Sach> dss = null;
	private ArrayList<Sach> dssTacGia = null;
	private ArrayList<Sach> dss_tensach = null;
	
    public QuanLySach(String title) {
        initComponents();
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        hienThiToanBoSach();
        hienThiToanBoNhaXuatBan();
 
    }
    
    private void hienThiToanBoSach() {
        Sach_Connect sachConn = new Sach_Connect();
        dss = sachConn.layToanBoSach();
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã sách");
        dtmSach.addColumn("Mã NXB");
        dtmSach.addColumn("Tên Sách");
        dtmSach.addColumn("Thể loại");
        dtmSach.addColumn("Tác giả");
        dtmSach.addColumn("Số lượng");
        dtmSach.addColumn("Giá Bán (VNĐ)");
        dtmSach.addColumn("Giảm Giá (%)");
        dtmSach.setRowCount(0);
        for (Sach s : dss){
            Vector<Object> vec = new Vector<Object>();
            vec.add(s.getMaSach());
            vec.add(s.getMaNXB());
            vec.add(s.getTenSach());
            vec.add(s.getTheLoai());
            vec.add(s.getTacGia());
            vec.add(s.getSoLuong());
            vec.add(s.getGiaBan());
            vec.add(s.getDiscount());
            dtmSach.addRow(vec);	
        }
        jTable_Books.setModel(dtmSach);
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

      

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Top = new javax.swing.JPanel();
        jPanel_TopFunction = new javax.swing.JPanel();
        jButton_ChinhSua = new javax.swing.JButton();
        jButton_XoaSach = new javax.swing.JButton();
        jButton_ThemSach = new javax.swing.JButton();
        jLabel_Top = new javax.swing.JLabel();
        jPanel_Data = new javax.swing.JPanel();
        jLabel_MaSach = new javax.swing.JLabel();
        jLabel_TenSach = new javax.swing.JLabel();
        jLabel_MaNXB = new javax.swing.JLabel();
        jLabel_TacGia = new javax.swing.JLabel();
        jLabel_GiaBan = new javax.swing.JLabel();
        jLabel_TheLoai = new javax.swing.JLabel();
        jLabel_SoLuong = new javax.swing.JLabel();
        NXBInput = new javax.swing.JComboBox<NXB>();
        TKInput_MaSach = new javax.swing.JTextField();
        TKInput_TenSach = new javax.swing.JTextField();
        TKInput_TacGia = new javax.swing.JTextField();
        TKInput_TheLoai = new javax.swing.JTextField();
        TKInput_SoLuong = new javax.swing.JTextField();
        TKInput_GiaBan = new javax.swing.JTextField();
        jLabel_Discount = new javax.swing.JLabel();
        TKInput_Discount = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jButton_NhapLai = new javax.swing.JButton();
        jPanel_Center = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Books = new javax.swing.JTable();
        jLabel_Loc = new javax.swing.JLabel();
        TKInput = new javax.swing.JTextField();
        jButton_Search = new javax.swing.JButton();
        jLabel_Loc1 = new javax.swing.JLabel();
        TKInput1 = new javax.swing.JTextField();
        NhanTenLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_Top.setLayout(new javax.swing.BoxLayout(jPanel_Top, javax.swing.BoxLayout.LINE_AXIS));

        jButton_ChinhSua.setBackground(new java.awt.Color(255, 255, 102));
        jButton_ChinhSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_ChinhSua.setText("Cập nhật");
        jButton_ChinhSua.setMaximumSize(new java.awt.Dimension(150, 40));
        jButton_ChinhSua.setMinimumSize(new java.awt.Dimension(150, 40));
        jButton_ChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ChinhSuaActionPerformed(evt);
            }
        });

        jButton_XoaSach.setBackground(new java.awt.Color(255, 102, 102));
        jButton_XoaSach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_XoaSach.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XoaSach.setText("Xoá Sách");
        jButton_XoaSach.setMaximumSize(new java.awt.Dimension(150, 40));
        jButton_XoaSach.setMinimumSize(new java.awt.Dimension(150, 40));
        jButton_XoaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XoaSachActionPerformed(evt);
            }
        });

        jButton_ThemSach.setBackground(new java.awt.Color(51, 255, 153));
        jButton_ThemSach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_ThemSach.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ThemSach.setText("Thêm Sách");
        jButton_ThemSach.setMaximumSize(new java.awt.Dimension(150, 40));
        jButton_ThemSach.setMinimumSize(new java.awt.Dimension(150, 40));
        jButton_ThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemSachActionPerformed(evt);
            }
        });

        jLabel_Top.setFont(new java.awt.Font("Segoe UI", 3, 28)); // NOI18N
        jLabel_Top.setForeground(new java.awt.Color(0, 153, 204));
        jLabel_Top.setText("Quản Lý Sách");

        jLabel_MaSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_MaSach.setText("Mã Sách");

        jLabel_TenSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_TenSach.setText("Tên Sách");

        jLabel_MaNXB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_MaNXB.setText("Mã NXB");

        jLabel_TacGia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_TacGia.setText("Tác Giả");

        jLabel_GiaBan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_GiaBan.setText("Giá Bán");

        jLabel_TheLoai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_TheLoai.setText("Thể Loại");

        jLabel_SoLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_SoLuong.setText("Số Lượng");

        NXBInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        TKInput_MaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKInput_MaSachActionPerformed(evt);
            }
        });

        jLabel_Discount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_Discount.setText("Giảm Giá");

        javax.swing.GroupLayout jPanel_DataLayout = new javax.swing.GroupLayout(jPanel_Data);
        jPanel_Data.setLayout(jPanel_DataLayout);
        jPanel_DataLayout.setHorizontalGroup(
            jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DataLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_MaSach)
                    .addComponent(jLabel_MaNXB)
                    .addComponent(jLabel_TenSach))
                .addGap(36, 36, 36)
                .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DataLayout.createSequentialGroup()
                        .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_DataLayout.createSequentialGroup()
                                .addComponent(TKInput_MaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel_TacGia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                            .addGroup(jPanel_DataLayout.createSequentialGroup()
                                .addComponent(TKInput_TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_SoLuong)))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel_DataLayout.createSequentialGroup()
                        .addComponent(NXBInput, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_TheLoai)
                        .addGap(40, 40, 40)))
                .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TKInput_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TKInput_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TKInput_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_GiaBan)
                    .addComponent(jLabel_Discount))
                .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_DataLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(TKInput_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_DataLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(TKInput_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55))
        );
        jPanel_DataLayout.setVerticalGroup(
            jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DataLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TKInput_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TKInput_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_MaSach)
                    .addComponent(TKInput_MaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_TacGia)
                    .addComponent(jLabel_GiaBan))
                .addGap(25, 25, 25)
                .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_MaNXB)
                    .addComponent(NXBInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_TheLoai)
                    .addComponent(TKInput_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Discount)
                    .addComponent(TKInput_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel_TenSach, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(TKInput_TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TKInput_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_SoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );

        jLabel_MaSach.setIcon(new ImageIcon("images/repository_28px.png"));
        jLabel_TenSach.setIcon(new ImageIcon("images/cbz_28px.png"));
        jLabel_MaNXB.setIcon(new ImageIcon("images/link_28px.png"));
        jLabel_TacGia.setIcon(new ImageIcon("images/customer_28px.png"));
        jLabel_GiaBan.setIcon(new ImageIcon("images/Banknotes_28px.png"));
        jLabel_TheLoai.setIcon(new ImageIcon("images/category_28px.png"));
        jLabel_SoLuong.setIcon(new ImageIcon("images/how_many_quest_28px.png"));
        jLabel_Discount.setIcon(new ImageIcon("images/discount_28px.png"));

        jSeparator2.setBackground(new java.awt.Color(102, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(0, 102, 204));

        jButton_NhapLai.setBackground(new java.awt.Color(255, 204, 204));
        jButton_NhapLai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_NhapLai.setText("Nhập lại");
        jButton_NhapLai.setMaximumSize(new java.awt.Dimension(150, 40));
        jButton_NhapLai.setMinimumSize(new java.awt.Dimension(150, 40));
        jButton_NhapLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NhapLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_TopFunctionLayout = new javax.swing.GroupLayout(jPanel_TopFunction);
        jPanel_TopFunction.setLayout(jPanel_TopFunctionLayout);
        jPanel_TopFunctionLayout.setHorizontalGroup(
            jPanel_TopFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TopFunctionLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel_Top)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_ThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jButton_ChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jButton_NhapLai, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jButton_XoaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addComponent(jPanel_Data, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_TopFunctionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
        );
        jPanel_TopFunctionLayout.setVerticalGroup(
            jPanel_TopFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TopFunctionLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel_TopFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_ThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_ChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_XoaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Top, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_NhapLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton_ChinhSua.setIcon(new ImageIcon("images/book_and_pencil_25px.png"));
        jButton_XoaSach.setIcon(new ImageIcon("images/remove_book_25px.png"));
        jButton_ThemSach.setIcon(new ImageIcon("images/add_book_25px.png"));
        jLabel_Top.setIcon(new ImageIcon("images/add_book_40px.png"));
        jButton_ChinhSua.setIcon(new ImageIcon("images/hand_with_pen_25px.png"));

        jPanel_Top.add(jPanel_TopFunction);

        getContentPane().add(jPanel_Top, java.awt.BorderLayout.PAGE_START);

        jSeparator1.setBackground(new java.awt.Color(102, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(0, 102, 204));

        jTable_Books.setBackground(new java.awt.Color(244, 243, 243));
        jTable_Books.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Books.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_BooksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Books);

        jLabel_Loc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Loc.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_Loc.setText("Tên Sách:");
        jLabel_Loc.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        TKInput.setToolTipText("Tên Sách");

        jButton_Search.setBackground(new java.awt.Color(51, 153, 255));
        jButton_Search.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_Search.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Search.setText("Tìm Kiếm");
        jButton_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_SearchMouseClicked(evt);
            }
        });

        jLabel_Loc1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Loc1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_Loc1.setText("Tên Tác Giả:");
        jLabel_Loc1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        TKInput1.setToolTipText("Tên Sách");

        NhanTenLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NhanTenLabel.setForeground(new java.awt.Color(0, 153, 153));
        NhanTenLabel.setText("Nhập tên sách và tên tác giả");

        javax.swing.GroupLayout jPanel_CenterLayout = new javax.swing.GroupLayout(jPanel_Center);
        jPanel_Center.setLayout(jPanel_CenterLayout);
        jPanel_CenterLayout.setHorizontalGroup(
            jPanel_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel_CenterLayout.createSequentialGroup()
                .addGroup(jPanel_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_CenterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel_CenterLayout.createSequentialGroup()
                        .addGroup(jPanel_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_CenterLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel_Loc)
                                .addGap(18, 18, 18)
                                .addComponent(TKInput, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(jLabel_Loc1)
                                .addGap(18, 18, 18)
                                .addComponent(TKInput1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_CenterLayout.createSequentialGroup()
                                .addGap(514, 514, 514)
                                .addComponent(NhanTenLabel)))
                        .addGap(0, 130, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_CenterLayout.setVerticalGroup(
            jPanel_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(NhanTenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TKInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Loc1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TKInput1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        jLabel_Loc.setIcon(new ImageIcon("images/filter_48px.png"));
        jButton_Search.setIcon(new ImageIcon("images/search_25px.png"));
        jLabel_Loc.setIcon(new ImageIcon("images/filter_48px.png"));

        getContentPane().add(jPanel_Center, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //Chỉnh sửa, cập nhật sách
    private void jButton_ChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ChinhSuaActionPerformed
        // TODO add your handling code here:

NXB manxb = (NXB) NXBInput.getSelectedItem();
				Sach s = new Sach();
				s.setMaSach(TKInput_MaSach.getText());
				s.setTenSach(TKInput_TenSach.getText());
				s.setMaNXB(manxb.getMaNXB());
                                
				s.setTacGia(TKInput_TacGia.getText());
				s.setGiaBan(Double.parseDouble(TKInput_GiaBan.getText()));
				s.setTheLoai(TKInput_TheLoai.getText());
				s.setSoLuong(Integer.parseInt(TKInput_SoLuong.getText()));
                                s.setDiscount(Integer.parseInt(TKInput_Discount.getText()));
				Sach_Connect sachconnect = new Sach_Connect();
				int active = sachconnect.update(s);
				if (active>0)
				{
					JOptionPane.showMessageDialog(null, "Chỉnh sửa thông tin sách thành công");
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Chỉnh sửa sách thất bại");
				}
                                hienThiToanBoSach();
    }//GEN-LAST:event_jButton_ChinhSuaActionPerformed

    
    //Thêm sách
    private void jButton_ThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemSachActionPerformed
        // TODO add your handling code here:
//        ThemMoiSach themmoi = new ThemMoiSach("Thêm mới sản phẩm");
//				themmoi.showWindow();
//				hienThiToanBoSach();
//                                

               
				if(TKInput_MaSach.getText().length()==0 ||  
						TKInput_TenSach.getText().length()==0 || TKInput_TacGia.getText().length()==0 || TKInput_GiaBan.getText().length()==0 ||
						TKInput_TheLoai.getText().length()==0 || TKInput_SoLuong.getText().length()==0 || TKInput_Discount.getText().length()==0) return ;
				
				NXB nxb = (NXB) NXBInput.getSelectedItem();
				Sach s = new Sach();
				s.setMaSach(TKInput_MaSach.getText());
				s.setTenSach(TKInput_TenSach.getText());
				s.setMaNXB(nxb.getMaNXB());
				s.setTacGia(TKInput_TacGia.getText());
				s.setGiaBan(Double.parseDouble(TKInput_GiaBan.getText()));
				s.setTheLoai(TKInput_TheLoai.getText());
				s.setSoLuong(Integer.parseInt(TKInput_SoLuong.getText()));
				s.setDiscount(Integer.parseInt(TKInput_Discount.getText()));
                                
				int x =JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muôn thêm sách", "Xác nhận thêm",JOptionPane.OK_CANCEL_OPTION);
				if(x==JOptionPane.OK_OPTION)
				{
					xuLyThemMoi(s);	
				}
				else return;
    }//GEN-LAST:event_jButton_ThemSachActionPerformed

    protected void xuLyThemMoi(Sach s) {
		Sach_Connect themsach = new Sach_Connect();
		int active = themsach.themSachMoi(s);
		if(active > 0 )
		{
			JOptionPane.showMessageDialog(null, "Thêm mới thành công");
                                TKInput_MaSach.setText("");
				TKInput_TenSach.setText("");	
				TKInput_TacGia.setText("");
				TKInput_GiaBan.setText("");
				TKInput_TheLoai.setText("");
				TKInput_SoLuong.setText("");
                                TKInput_Discount.setText("");
				TKInput_MaSach.requestFocus();
                                hienThiToanBoSach();
//                      
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Thêm mới thất bại");
		}
		
	}
    
    
    //Tìm kiếm sách
    private void jButton_SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_SearchMouseClicked
        // TODO add your handling code here:
        String ten = TKInput.getText();
        String tenTacGia = TKInput1.getText();
        Sach_Connect sachnxb1 = new Sach_Connect();
        dss_tensach = sachnxb1.laySachTheoMaTen(ten);
        dssTacGia = sachnxb1.laySachTheoTenTacGia(tenTacGia);
        dsChung = sachnxb1.laySachTheoMaTenVaTenTacGia(ten, tenTacGia);
        //show all data
        if (TKInput.getText().length()==0 && TKInput1.getText().length()==0)
            hienThiToanBoSach();
        //tìm kiếm theo tên sách
        if (TKInput.getText().length()!= 0 && TKInput1.getText().length()==0){
            dtmSach.setRowCount(0);
            for(Sach s : dss_tensach){
                Vector<Object> vec = new Vector<Object>();
                vec.add(s.getMaSach());
                vec.add(s.getMaNXB());
                vec.add(s.getTenSach());
                vec.add(s.getTheLoai());
                vec.add(s.getTacGia());
                vec.add(s.getSoLuong());
                vec.add(s.getGiaBan());
                vec.add(s.getDiscount());
                dtmSach.addRow(vec);
            }
        }
        //Tìm kiếm theo tên sách và tên tác giả
        else if (TKInput.getText().length()!= 0 && TKInput1.getText().length()!=0){
            
            dtmSach.setRowCount(0);
            for(Sach s : dsChung){
                Vector<Object> vec = new Vector<Object>();
                vec.add(s.getMaSach());
                vec.add(s.getMaNXB());
                vec.add(s.getTenSach());
                vec.add(s.getTheLoai());
                vec.add(s.getTacGia());
                vec.add(s.getSoLuong());
                vec.add(s.getGiaBan());	
                vec.add(s.getDiscount());
                dtmSach.addRow(vec);
            }

            
        }
        //tìm kiếm theo tên tác giả
        else if (TKInput.getText().length()== 0 && TKInput1.getText().length()!=0){
            dtmSach.setRowCount(0);
            for(Sach s : dssTacGia){
            Vector<Object> vec = new Vector<Object>();			
            vec.add(s.getMaSach());
            vec.add(s.getMaNXB());
            vec.add(s.getTenSach());
            vec.add(s.getTheLoai());
            vec.add(s.getTacGia());
            vec.add(s.getSoLuong());
            vec.add(s.getGiaBan());	
            vec.add(s.getDiscount());
            dtmSach.addRow(vec);
            }
	}
    }//GEN-LAST:event_jButton_SearchMouseClicked

    
    //Xoá sách
    private void jButton_XoaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaSachActionPerformed
        // TODO add your handling code here:
        int select = jTable_Books.getSelectedRow();
				if(select==-1)  return ;
				String maSach = (String) jTable_Books.getValueAt(select, 0);
				//JOptionPane.showMessageDialog(null,jTable_Books.getValueAt(select, 0) );
				int active = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa", "Xác Nhận Xóa", JOptionPane.OK_CANCEL_OPTION);
				if(active==JOptionPane.OK_OPTION)
				{
					xuLyXoa(maSach);
					//JOptionPane.showMessageDialog(null,maSach );
				}
    }//GEN-LAST:event_jButton_XoaSachActionPerformed

    
    //Nhập lại
    private void jButton_NhapLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NhapLaiActionPerformed
        // TODO add your handling code here:
        TKInput_MaSach.setText("");
				TKInput_TenSach.setText("");
				NXBInput.setSelectedIndex(0);
				TKInput_TacGia.setText("");
				TKInput_GiaBan.setText("");
				TKInput_TheLoai.setText("");
				TKInput_SoLuong.setText("");
				TKInput_Discount.setText("");
				TKInput_MaSach.requestFocus();
    }//GEN-LAST:event_jButton_NhapLaiActionPerformed

    
    
    //Get selected row
    private void jTable_BooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_BooksMouseClicked
        // TODO add your handling code here:

            int select = jTable_Books.getSelectedRow();
            NXB_Connect nxb_con = new NXB_Connect();
				if(select==-1) return ;
				
                                //lấy thông tin trong table
				Sach s = new Sach();
				s.setMaSach((String) jTable_Books.getValueAt(select, 0));
                                //s.setMaNXB((String) jTable_Books.getValueAt(select, 1));
                                //nxb.setMaNXB((String) jTable_Books.getValueAt(select, 1));
                                
				s.setTenSach((String) jTable_Books.getValueAt(select, 2));
				s.setTheLoai((String) jTable_Books.getValueAt(select, 3));
				s.setTacGia((String) jTable_Books.getValueAt(select, 4));
                                s.setSoLuong( (int) jTable_Books.getValueAt(select, 5));
				s.setGiaBan( Double.parseDouble(jTable_Books.getValueAt(select, 6)+"") );
				s.setDiscount((int) jTable_Books.getValueAt(select, 7));
				
				
				//đưa thông tin lên panelTop
				TKInput_MaSach.setText(s.getMaSach());
                             //   NXBInput.setSelectedItem(nxb.getTenNXB());
                               // System.out.println(nxb.getTenNXB());
                                
                                TKInput_TenSach.setText(s.getTenSach());
                                TKInput_TacGia.setText(s.getTacGia());
                                TKInput_GiaBan.setText(s.getGiaBan()+"");
                                TKInput_TheLoai.setText(s.getTheLoai());
                                TKInput_SoLuong.setText(s.getSoLuong()+"");
                                TKInput_Discount.setText(s.getDiscount()+"");
                                NXBInput.setSelectedIndex(Integer.parseInt(nxb_con.TimTenNXB(jTable_Books.getValueAt(select, 1).toString()).getMaNXB().substring(3)) );
    }//GEN-LAST:event_jTable_BooksMouseClicked

    private void TKInput_MaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKInput_MaSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKInput_MaSachActionPerformed
protected void xuLyXoa(String maSach) {
		Sach_Connect sachXoa = new Sach_Connect();
		int active= sachXoa.XoaSach(maSach);
		if(active > 0)
		{
			JOptionPane.showMessageDialog(null, "Xóa thành công sách!");
			hienThiToanBoSach();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Xóa thất bại");	
		}
		
	}


//Chỉnh Sửa

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<NXB> NXBInput;
    private javax.swing.JLabel NhanTenLabel;
    private javax.swing.JTextField TKInput;
    private javax.swing.JTextField TKInput1;
    private javax.swing.JTextField TKInput_Discount;
    private javax.swing.JTextField TKInput_GiaBan;
    private javax.swing.JTextField TKInput_MaSach;
    private javax.swing.JTextField TKInput_SoLuong;
    private javax.swing.JTextField TKInput_TacGia;
    private javax.swing.JTextField TKInput_TenSach;
    private javax.swing.JTextField TKInput_TheLoai;
    private javax.swing.JButton jButton_ChinhSua;
    private javax.swing.JButton jButton_NhapLai;
    private javax.swing.JButton jButton_Search;
    private javax.swing.JButton jButton_ThemSach;
    private javax.swing.JButton jButton_XoaSach;
    private javax.swing.JLabel jLabel_Discount;
    private javax.swing.JLabel jLabel_GiaBan;
    private javax.swing.JLabel jLabel_Loc;
    private javax.swing.JLabel jLabel_Loc1;
    private javax.swing.JLabel jLabel_MaNXB;
    private javax.swing.JLabel jLabel_MaSach;
    private javax.swing.JLabel jLabel_SoLuong;
    private javax.swing.JLabel jLabel_TacGia;
    private javax.swing.JLabel jLabel_TenSach;
    private javax.swing.JLabel jLabel_TheLoai;
    private javax.swing.JLabel jLabel_Top;
    private javax.swing.JPanel jPanel_Center;
    private javax.swing.JPanel jPanel_Data;
    private javax.swing.JPanel jPanel_Top;
    private javax.swing.JPanel jPanel_TopFunction;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable_Books;
    // End of variables declaration//GEN-END:variables

    void showWindow() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuanLySach("Qu\u1ea3n l\u00fd s\u00e1ch").setVisible(true);
            }
        });
    }

    
}
