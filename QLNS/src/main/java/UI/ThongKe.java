/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Connect.HoaDon_Connect;
import Connect.Sach_Connect;
import Connect.VanPhongPham_Connect;
import Model.HoaDon;
import Model.Sach;
import Model.TonKho;
import Model.VPP;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author dat
 */
public class ThongKe extends javax.swing.JFrame {
private DefaultTableModel dtmSach =null, dtmHoaDon =null, dtmBanChay = null, dtmTonKho = null, dtmNhapXuat=null;
private DefaultTableModel dtmSP =null, dtmHoaDonSP =null, dtmBanChaySP = null, dtmTonKhoSP = null, dtmNhapXuatSP=null;
private ArrayList<HoaDon> dshd_thongke =null;

private ArrayList<Sach> dss ;
private ArrayList<VPP> dssp;
private ArrayList<TonKho> dstk, dstksp, dsnx, dsnxsp ;
private double tongTien =0;
private DecimalFormat df = new DecimalFormat("###,###,###");
//sửa lại đường dẫn này cho phù hợp với đường dẫn trong máy
String filePath = "C:\\Users\\dat\\Downloads\\";
    /**
     * Creates new form ThongKe
     */
    public ThongKe(String title) {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/books_30px.png"));
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        showThongKe();
        hienThiCanNhap();
        hienThiSPCanNhap();
        hienThiSachBanChay();
        hienThiSPBanChay();
        hienThiTonKho();
        hienThiNhapXuat();
    }
    
    //chart cho doanh thu
    private JFreeChart chart;
    private DefaultCategoryDataset dataset;
    private CategoryPlot categoryPlot;
    private ChartPanel chartPanel;
    //pipe chart cho sách
    private JFreeChart pipeChartSach;
    private DefaultPieDataset pieDatasetSach;
    private PiePlot piePlotSach;
    private ChartPanel pieChartPanelSach;
    //pipe chart cho văn phòng phẩm
    private JFreeChart pipeChartSP;
    private DefaultPieDataset pieDatasetSP;
    private PiePlot piePlotSP;
    private ChartPanel pieChartPanelSP;
    public void showThongKe(){
        //dữ liệu đổ vào bảng thông kê
        dataset = new DefaultCategoryDataset();
        HoaDon_Connect hd_conn = new HoaDon_Connect();
        dataset = hd_conn.DoanhThuCacThang();
        
        //tạo bảng thống kê
        chart = ChartFactory.createBarChart("Doanh thu năm nay","Tháng","VNĐ",dataset, PlotOrientation.VERTICAL, true, true, false);
        
        categoryPlot = chart.getCategoryPlot();
        //thay đổi màu nền
        //categoryPlot.setBackgroundPaint(new Color(255,255,255));
        chartPanel = new ChartPanel(chart);
        
        //tạo biểu đồ tròn thể hiện số lượng các thể loại sách 
        pieDatasetSach = new DefaultPieDataset();
        Sach_Connect s_conn = new Sach_Connect();
        pieDatasetSach = s_conn.laySachTheoTheLoai();
        //tạo bảng pipe chart
        pipeChartSach = ChartFactory.createPieChart3D("Các loại sách", pieDatasetSach, true, true, false);
        piePlotSach =(PiePlot) pipeChartSach.getPlot();
        piePlotSach.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({1})"));
        pieChartPanelSach = new ChartPanel(pipeChartSach);
        
        //tạo biểu đồ tròn thể hiện số lượng các danh mục sản phẩm
        pieDatasetSP = new DefaultPieDataset();
        VanPhongPham_Connect vpp_conn =  new VanPhongPham_Connect();
        pieDatasetSP= vpp_conn.laySPTheoDanhMuc();
        //tạo bảng pipe chart
        pipeChartSP = ChartFactory.createPieChart3D("Các Danh mục của sản phẩm", pieDatasetSP, true, true, false);
        piePlotSP =(PiePlot) pipeChartSP.getPlot();
        // Thiết lập sectionLabelGenerator để hiển thị số lượng sản phẩm
        piePlotSP.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({1})"));
        pieChartPanelSP = new ChartPanel(pipeChartSP);
        
        DoanhThuPanel.removeAll();
        DoanhThuPanel.add(chartPanel,BorderLayout.CENTER);
        DoanhThuPanel.setPreferredSize(new Dimension(300, 300)); // Đặt kích thước tùy chỉnh cho DoanhThuPanel
        DoanhThuPanel.validate();
        
        TheLoaiPanel.removeAll();
        TheLoaiPanel.add(pieChartPanelSach,BorderLayout.CENTER);
        TheLoaiPanel.validate();
        
        DanhMucPanel.removeAll();
        DanhMucPanel.add(pieChartPanelSP,BorderLayout.CENTER);
        DanhMucPanel.validate();
    }
    
    private void hienThiCanNhap() {
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
    
    private void hienThiSPCanNhap() {
        VanPhongPham_Connect vpp_conn = new VanPhongPham_Connect();
        dssp = vpp_conn.laySPConDuoiTon((int)SLSPBaoDongInput.getValue());
        dtmSP = new DefaultTableModel();
        dtmSP.addColumn("Mã sản phẩm");
        dtmSP.addColumn("Tên sản phẩm");
        dtmSP.addColumn("Danh mục");
        dtmSP.addColumn("Số lượng tồn");
        dtmSP.setRowCount(0);
        for(VPP vpp : dssp){
            Vector<Object> vec = new Vector<Object>();
            vec.add(vpp.getMaVPP());
            vec.add(vpp.getTenVPP());
            vec.add(vpp.getDanhMuc());
            vec.add(vpp.getSoLuong());

            dtmSP.addRow(vec);
        }
        NeedSPTable.setModel(dtmSP);
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
    
    private void hienThiSPBanChay() {
        Calendar cal = Calendar.getInstance();
        SPBanChayMonthInput.setSelectedIndex(cal.get(Calendar.MONTH) + 1); // Tháng bắt đầu từ 0 nên cần +1 để lấy tháng thực tế
        SPBanChayYearInput.setText(Integer.toString(cal.get(Calendar.YEAR)) );
        VanPhongPham_Connect vpp_conn = new VanPhongPham_Connect();
        dssp = vpp_conn.laySPBanChay(TopSPBanChayInput.getValue().toString(), Integer.toString(SPBanChayMonthInput.getSelectedIndex()), SPBanChayYearInput.getText());
        dtmBanChaySP = new DefaultTableModel();
        dtmBanChaySP.addColumn("Mã sản phẩm");
        dtmBanChaySP.addColumn("Tên sản phẩm");
        dtmBanChaySP.addColumn("Số lượng đã bán");
        dtmBanChaySP.setRowCount(0);
        for(VPP vpp : dssp){
            Vector<Object> vec = new Vector<Object>();
            vec.add(vpp.getMaVPP());
            vec.add(vpp.getTenVPP());
            vec.add(vpp.getSoLuong());

            dtmBanChaySP.addRow(vec);
        }
        SPBanChayTable.setModel(dtmBanChaySP);
    }
    
    private void hienThiTonKho(){
        Calendar cal = Calendar.getInstance();
        Sach_Connect sach_conn = new Sach_Connect();
        dstk = sach_conn.laySachTonKho(cal.get(Calendar.MONTH) + 1,cal.get(Calendar.YEAR));
        dtmTonKho = new DefaultTableModel();
        dtmTonKho.addColumn("Mã sản phẩm");
        dtmTonKho.addColumn("Tên sản phẩm");
        dtmTonKho.addColumn("Tồn đầu");
        dtmTonKho.addColumn("Nhập");
        dtmTonKho.addColumn("Xuất");
        dtmTonKho.addColumn("Tồn Cuối");
        dtmTonKho.setRowCount(0);
        for(TonKho tk : dstk){
            Vector<Object> vec = new Vector<Object>();
            vec.add(tk.getMaSP());
            vec.add(tk.getTenSP());
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
        NhapXuatMonthInput.setSelectedIndex(cal.get(Calendar.MONTH)-1);
        NhapXuatYearInput.setText(Integer.toString(cal.get(Calendar.YEAR)) );
        Sach_Connect sach_conn = new Sach_Connect();
        dsnx = sach_conn.layThongTinNhapXuat(cal.get(Calendar.MONTH),cal.get(Calendar.YEAR));
        dtmNhapXuat = new DefaultTableModel();
        dtmNhapXuat.addColumn("Mã sản phẩm");
        dtmNhapXuat.addColumn("Tên sản phẩm");
        dtmNhapXuat.addColumn("Tồn đầu");
        dtmNhapXuat.addColumn("Nhập vào");
        dtmNhapXuat.addColumn("Bán ra");
        dtmNhapXuat.addColumn("Tồn cuối");
        dtmNhapXuat.setRowCount(0);
        for(TonKho tk : dsnx){
            Vector<Object> vec = new Vector<Object>();
            vec.add(tk.getMaSP());
            vec.add(tk.getTenSP());
            vec.add(tk.getTonDau());
            vec.add(tk.getNhap());
            vec.add(tk.getXuat());
            vec.add(tk.getTonCuoi());

            dtmNhapXuat.addRow(vec);
        }
        NhapXuatTable.setModel(dtmNhapXuat);
    }
    
    private void XuatFileExcel(DefaultTableModel dtm, String sheetName, String excelFilePath){
        try{
            TableModel model = dtm;
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(sheetName);

            // Ghi tiêu đề cột
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(model.getColumnName(col));
            }

            // Ghi dữ liệu từ JTable vào Sheet
            for (int row = 0; row < model.getRowCount(); row++) {
                Row sheetRow = sheet.createRow(row + 1);
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object value = model.getValueAt(row, col);
                    Cell cell = sheetRow.createCell(col);

                    // Xác định kiểu dữ liệu của ô dữ liệu
                    if (value instanceof Number) cell.setCellValue(((Number) value).doubleValue());
                    else cell.setCellValue(value.toString());
                }
            }

            // Tự động điều chỉnh kích thước các cột trong Excel
            for (int col = 0; col < model.getColumnCount(); col++) {
                sheet.autoSizeColumn(col);
            }

            //tạo file .xls
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            //mở file pdf đó ra
            File pdfFile = new File(excelFilePath);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    JOptionPane.showMessageDialog(null, "Máy tính không hỗ trợ!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "File không tồn tại!");
            }
        }
        catch (Exception e2) {
                e2.printStackTrace();
        }
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
        ThongKePane = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        DoanhThuPanel = new javax.swing.JPanel();
        TheLoaiPanel = new javax.swing.JPanel();
        DanhMucPanel = new javax.swing.JPanel();
        TonKhoSachPane = new javax.swing.JPanel();
        PrintTonKhoBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TonKhoTable = new javax.swing.JTable();
        TonKhoLabel = new javax.swing.JLabel();
        SaveBtn = new javax.swing.JButton();
        NhapXuatSachPane = new javax.swing.JPanel();
        NhapXuatMonthLabel = new javax.swing.JLabel();
        NhapXuatMonthInput = new javax.swing.JComboBox<>();
        NhapXuatYearLabel = new javax.swing.JLabel();
        NhapXuatYearInput = new javax.swing.JTextField();
        NhapXuatBtn = new javax.swing.JButton();
        PrintNhapXuatBtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        NhapXuatTable = new javax.swing.JTable();
        NeedBookPane = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        NeedBookTable = new javax.swing.JTable(dtmSach);
        SLBaoDongLabel = new javax.swing.JLabel();
        SLBaoDongInput = new javax.swing.JSpinner(new SpinnerNumberModel(10, 10, 100, 10));
        TKNeedBookBtn = new javax.swing.JButton();
        PrintNeedBookBtn = new javax.swing.JButton();
        NeedSPPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        NeedSPTable = new javax.swing.JTable(dtmSach);
        SLSPBaoDongLabel = new javax.swing.JLabel();
        SLSPBaoDongInput = new javax.swing.JSpinner(new SpinnerNumberModel(10, 10, 100, 10));
        TKNeedSPBtn = new javax.swing.JButton();
        PrintNeedSPBtn = new javax.swing.JButton();
        SachBanChayPane = new javax.swing.JPanel();
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
        SPBanChayPanel = new javax.swing.JPanel();
        SPBanChayMonthInput = new javax.swing.JComboBox<>();
        SPBanChayYearInput = new javax.swing.JTextField();
        SPBanChayYearLabel = new javax.swing.JLabel();
        SPBanChayMonthLabel = new javax.swing.JLabel();
        SPBanChayBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        SPBanChayTable = new javax.swing.JTable();
        SPPrintBanChayBtn = new javax.swing.JButton();
        TopSPBanChayInput = new javax.swing.JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
        SPBanChayTopLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        TKLable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        DoanhThuPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane2.addTab("Doanh thu", DoanhThuPanel);

        TheLoaiPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane2.addTab("Sách", TheLoaiPanel);

        DanhMucPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane2.addTab("Văn phòng phẩm", DanhMucPanel);

        javax.swing.GroupLayout ThongKePaneLayout = new javax.swing.GroupLayout(ThongKePane);
        ThongKePane.setLayout(ThongKePaneLayout);
        ThongKePaneLayout.setHorizontalGroup(
            ThongKePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1243, Short.MAX_VALUE)
        );
        ThongKePaneLayout.setVerticalGroup(
            ThongKePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKePaneLayout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thống kê", ThongKePane);

        PrintTonKhoBtn.setText("Xuất bảng báo cáo");
        PrintTonKhoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintTonKhoBtnActionPerformed(evt);
            }
        });

        TonKhoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(TonKhoTable);

        TonKhoLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TonKhoLabel.setForeground(new java.awt.Color(0, 0, 255));
        TonKhoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TonKhoLabel.setText("SỐ LƯỢNG SÁCH TỒN TRONG THÁNG NÀY");

        SaveBtn.setText("Lưu");
        SaveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout TonKhoSachPaneLayout = new javax.swing.GroupLayout(TonKhoSachPane);
        TonKhoSachPane.setLayout(TonKhoSachPaneLayout);
        TonKhoSachPaneLayout.setHorizontalGroup(
            TonKhoSachPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1243, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TonKhoSachPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(PrintTonKhoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TonKhoSachPaneLayout.createSequentialGroup()
                .addComponent(TonKhoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        TonKhoSachPaneLayout.setVerticalGroup(
            TonKhoSachPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TonKhoSachPaneLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(TonKhoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TonKhoSachPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PrintTonKhoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(SaveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tồn kho", TonKhoSachPane);

        NhapXuatMonthLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NhapXuatMonthLabel.setText("Tháng");

        NhapXuatMonthInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        NhapXuatYearLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NhapXuatYearLabel.setText("Năm");

        NhapXuatYearInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NhapXuatYearInputKeyTyped(evt);
            }
        });

        NhapXuatBtn.setBackground(new java.awt.Color(51, 153, 255));
        NhapXuatBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NhapXuatBtn.setForeground(new java.awt.Color(255, 255, 255));
        NhapXuatBtn.setText("Thống kê");
        NhapXuatBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NhapXuatBtnMouseClicked(evt);
            }
        });

        PrintNhapXuatBtn.setText("Xuất bảng báo cáo");
        PrintNhapXuatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintNhapXuatBtnActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(NhapXuatTable);

        javax.swing.GroupLayout NhapXuatSachPaneLayout = new javax.swing.GroupLayout(NhapXuatSachPane);
        NhapXuatSachPane.setLayout(NhapXuatSachPaneLayout);
        NhapXuatSachPaneLayout.setHorizontalGroup(
            NhapXuatSachPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1243, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhapXuatSachPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(NhapXuatSachPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhapXuatSachPaneLayout.createSequentialGroup()
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhapXuatSachPaneLayout.createSequentialGroup()
                        .addComponent(PrintNhapXuatBtn)
                        .addGap(14, 14, 14))))
        );
        NhapXuatSachPaneLayout.setVerticalGroup(
            NhapXuatSachPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapXuatSachPaneLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(NhapXuatSachPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NhapXuatMonthLabel)
                    .addComponent(NhapXuatMonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NhapXuatYearLabel)
                    .addComponent(NhapXuatYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NhapXuatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PrintNhapXuatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhập xuất", NhapXuatSachPane);

        jScrollPane3.setViewportView(NeedBookTable);

        SLBaoDongLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SLBaoDongLabel.setText("Số lượng của sách dưới :");

        JFormattedTextField text0Field = ((JSpinner.DefaultEditor) SLBaoDongInput.getEditor()).getTextField();
        ((NumberFormatter) text0Field.getFormatter()).setAllowsInvalid(false); // Chỉ cho phép giá trị hợp lệ là số

        TKNeedBookBtn.setBackground(new java.awt.Color(51, 153, 255));
        TKNeedBookBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TKNeedBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        TKNeedBookBtn.setText("Thống kê");
        TKNeedBookBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKNeedBookBtnMouseClicked(evt);
            }
        });

        PrintNeedBookBtn.setText("Xuất bảng báo cáo");
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
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1231, Short.MAX_VALUE)
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
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sách cần nhập", NeedBookPane);

        jScrollPane6.setViewportView(NeedSPTable);

        SLSPBaoDongLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SLSPBaoDongLabel.setText("Số lượng của sản phẩm dưới :");

        JFormattedTextField text1Field = ((JSpinner.DefaultEditor) SLSPBaoDongInput.getEditor()).getTextField();
        ((NumberFormatter) text1Field.getFormatter()).setAllowsInvalid(false); // Chỉ cho phép giá trị hợp lệ là số

        TKNeedSPBtn.setBackground(new java.awt.Color(51, 153, 255));
        TKNeedSPBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TKNeedSPBtn.setForeground(new java.awt.Color(255, 255, 255));
        TKNeedSPBtn.setText("Thống kê");
        TKNeedSPBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKNeedSPBtnMouseClicked(evt);
            }
        });

        PrintNeedSPBtn.setText("Xuất bảng báo cáo");
        PrintNeedSPBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintNeedSPBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NeedSPPanelLayout = new javax.swing.GroupLayout(NeedSPPanel);
        NeedSPPanel.setLayout(NeedSPPanelLayout);
        NeedSPPanelLayout.setHorizontalGroup(
            NeedSPPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NeedSPPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NeedSPPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeedSPPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1231, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeedSPPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SLSPBaoDongLabel)
                        .addGap(18, 18, 18)
                        .addComponent(SLSPBaoDongInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(TKNeedSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeedSPPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PrintNeedSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        NeedSPPanelLayout.setVerticalGroup(
            NeedSPPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NeedSPPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(NeedSPPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NeedSPPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(SLSPBaoDongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NeedSPPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TKNeedSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SLSPBaoDongInput, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PrintNeedSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm cần nhập", NeedSPPanel);

        BanChayMonthInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        BanChayYearInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BanChayYearInputKeyTyped(evt);
            }
        });

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

        PrintBanChayBtn.setText("Xuất bảng báo cáo");
        PrintBanChayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintBanChayBtnActionPerformed(evt);
            }
        });

        JFormattedTextField textField = ((JSpinner.DefaultEditor) TopBanChayInput.getEditor()).getTextField();
        ((NumberFormatter) textField.getFormatter()).setAllowsInvalid(false); // Chỉ cho phép giá trị hợp lệ là số

        BanChayTopLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BanChayTopLabel.setText("TOP");

        javax.swing.GroupLayout SachBanChayPaneLayout = new javax.swing.GroupLayout(SachBanChayPane);
        SachBanChayPane.setLayout(SachBanChayPaneLayout);
        SachBanChayPaneLayout.setHorizontalGroup(
            SachBanChayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SachBanChayPaneLayout.createSequentialGroup()
                .addContainerGap(660, Short.MAX_VALUE)
                .addGroup(SachBanChayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SachBanChayPaneLayout.createSequentialGroup()
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SachBanChayPaneLayout.createSequentialGroup()
                        .addComponent(PrintBanChayBtn)
                        .addGap(17, 17, 17))))
        );
        SachBanChayPaneLayout.setVerticalGroup(
            SachBanChayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SachBanChayPaneLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(SachBanChayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TopBanChayInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SachBanChayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sách bán chạy", SachBanChayPane);

        SPBanChayMonthInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        SPBanChayYearInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SPBanChayYearInputKeyTyped(evt);
            }
        });

        SPBanChayYearLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SPBanChayYearLabel.setText("Năm");

        SPBanChayMonthLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SPBanChayMonthLabel.setText("Tháng");

        SPBanChayBtn.setBackground(new java.awt.Color(51, 153, 255));
        SPBanChayBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SPBanChayBtn.setForeground(new java.awt.Color(255, 255, 255));
        SPBanChayBtn.setText("Thống kê");
        SPBanChayBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SPBanChayBtnMouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(SPBanChayTable);

        SPPrintBanChayBtn.setText("Xuất bảng báo cáo");
        SPPrintBanChayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPPrintBanChayBtnActionPerformed(evt);
            }
        });

        JFormattedTextField txtField = ((JSpinner.DefaultEditor) TopSPBanChayInput.getEditor()).getTextField();
        ((NumberFormatter) txtField.getFormatter()).setAllowsInvalid(false); // Chỉ cho phép giá trị hợp lệ là số

        SPBanChayTopLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SPBanChayTopLabel.setText("TOP");

        javax.swing.GroupLayout SPBanChayPanelLayout = new javax.swing.GroupLayout(SPBanChayPanel);
        SPBanChayPanel.setLayout(SPBanChayPanelLayout);
        SPBanChayPanelLayout.setHorizontalGroup(
            SPBanChayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SPBanChayPanelLayout.createSequentialGroup()
                .addContainerGap(660, Short.MAX_VALUE)
                .addGroup(SPBanChayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SPBanChayPanelLayout.createSequentialGroup()
                        .addComponent(SPBanChayTopLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TopSPBanChayInput, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SPBanChayMonthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SPBanChayMonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(SPBanChayYearLabel)
                        .addGap(18, 18, 18)
                        .addComponent(SPBanChayYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(SPBanChayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SPBanChayPanelLayout.createSequentialGroup()
                        .addComponent(SPPrintBanChayBtn)
                        .addGap(17, 17, 17))))
        );
        SPBanChayPanelLayout.setVerticalGroup(
            SPBanChayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SPBanChayPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(SPBanChayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TopSPBanChayInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SPBanChayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SPBanChayMonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SPBanChayYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SPBanChayYearLabel)
                        .addComponent(SPBanChayMonthLabel)
                        .addComponent(SPBanChayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SPBanChayTopLabel)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SPPrintBanChayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm bán chạy", SPBanChayPanel);

        TKLable.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TKLable.setForeground(new java.awt.Color(0, 0, 255));
        TKLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TKLable.setText("BÁO CÁO");
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
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        int dialogResult = JOptionPane.showConfirmDialog (null, "Xuất file excel?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION)
            XuatFileExcel(dtmSach, "Sách cần nhập", filePath+"SachCanNhap.xls" );
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
        int dialogResult = JOptionPane.showConfirmDialog (null, "Xuất file excel?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION)
            XuatFileExcel(dtmBanChay, "Sách bán chạy", filePath+"BanChay.xls" );
    }//GEN-LAST:event_PrintBanChayBtnActionPerformed

    private void PrintTonKhoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintTonKhoBtnActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Xuất file excel?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION)
            XuatFileExcel(dtmTonKho, "Tồn kho tháng này", filePath+"TonKho.xls" );
    }//GEN-LAST:event_PrintTonKhoBtnActionPerformed

    private void NhapXuatBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhapXuatBtnMouseClicked
        Calendar cal = Calendar.getInstance();
        Sach_Connect sach_conn = new Sach_Connect();
        dsnx = sach_conn.layThongTinNhapXuat(NhapXuatMonthInput.getSelectedIndex()+1, Integer.parseInt(NhapXuatYearInput.getText()));
        dtmNhapXuat.setRowCount(0);
        for(TonKho tk : dsnx){
            Vector<Object> vec = new Vector<Object>();
            vec.add(tk.getMaSP());
            vec.add(tk.getTenSP());
            vec.add(tk.getTonDau());
            vec.add(tk.getNhap());
            vec.add(tk.getXuat());
            vec.add(tk.getTonCuoi());

            dtmNhapXuat.addRow(vec);
        }
        NhapXuatTable.setModel(dtmNhapXuat);
    }//GEN-LAST:event_NhapXuatBtnMouseClicked

    private void PrintNhapXuatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintNhapXuatBtnActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Xuất file excel?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION)
            XuatFileExcel(dtmNhapXuat, "Nhập xuất tháng "+(NhapXuatMonthInput.getSelectedIndex()+1)+" năm "+NhapXuatYearInput.getText(), filePath+"NhapXuat.xls" );
    }//GEN-LAST:event_PrintNhapXuatBtnActionPerformed

    private void SaveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveBtnMouseClicked
        int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có chắc chắn muốn lưu vào Cơ sở dữ liệu?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            if(TonKhoTable.getModel().getRowCount() != 0){
                Sach_Connect sach_conn = new Sach_Connect();
                Calendar cal = Calendar.getInstance();
                int result = sach_conn.luuDuLieuVaoTonKho(TonKhoTable, cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
                if (result == 1) JOptionPane.showMessageDialog(null, "Lưu thành công!");
                else JOptionPane.showMessageDialog(null, "Lưu thất bại!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Bảng tồn kho rỗng!");
            }
        }
    }//GEN-LAST:event_SaveBtnMouseClicked

    private void TKNeedSPBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKNeedSPBtnMouseClicked
       VanPhongPham_Connect vpp_conn = new VanPhongPham_Connect();
        dssp = vpp_conn.laySPConDuoiTon((int)SLBaoDongInput.getValue());
        dtmSP.setRowCount(0);
        for(VPP s : dssp){
            Vector<Object> vec = new Vector<Object>();
            vec.add(s.getMaVPP());
            vec.add(s.getTenVPP());
            vec.add(s.getDanhMuc());
            vec.add(s.getSoLuong());

            dtmSP.addRow(vec);
        }
        NeedSPTable.setModel(dtmSP);

    }//GEN-LAST:event_TKNeedSPBtnMouseClicked

    private void PrintNeedSPBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintNeedSPBtnActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Xuất file excel?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION)
            XuatFileExcel(dtmSP, "Sản phẩm cần nhập", filePath+"SanPhamCanNhap.xls" );
    }//GEN-LAST:event_PrintNeedSPBtnActionPerformed

    private void SPBanChayBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SPBanChayBtnMouseClicked
        VanPhongPham_Connect vpp_conn = new VanPhongPham_Connect();
        dssp = vpp_conn.laySPBanChay(TopSPBanChayInput.getValue().toString(), Integer.toString(SPBanChayMonthInput.getSelectedIndex()), SPBanChayYearInput.getText());
        dtmBanChaySP.setRowCount(0);
        for(VPP s : dssp){
            Vector<Object> vec = new Vector<Object>();
            vec.add(s.getMaVPP());
            vec.add(s.getTenVPP());
            vec.add(s.getSoLuong());

            dtmBanChaySP.addRow(vec);
        }
        SPBanChayTable.setModel(dtmBanChaySP);
    }//GEN-LAST:event_SPBanChayBtnMouseClicked

    private void SPPrintBanChayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPPrintBanChayBtnActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Xuất file excel?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION)
            XuatFileExcel(dtmBanChaySP, "Sản phẩm bán chạy", filePath+"SanPhamBanChay.xls" );
    }//GEN-LAST:event_SPPrintBanChayBtnActionPerformed

    private void NhapXuatYearInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NhapXuatYearInputKeyTyped
       char c = evt.getKeyChar();
        if (!Character.isDigit(c) || NhapXuatYearInput.getText().length() >= 4) {
            evt.consume(); // Ngăn chặn ký tự không hợp lệ và ngăn chặn nhập quá 4 ký tự
        }
    }//GEN-LAST:event_NhapXuatYearInputKeyTyped

    private void BanChayYearInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BanChayYearInputKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || BanChayYearInput.getText().length() >= 4) {
            evt.consume(); // Ngăn chặn ký tự không hợp lệ và ngăn chặn nhập quá 4 ký tự
        }
    }//GEN-LAST:event_BanChayYearInputKeyTyped

    private void SPBanChayYearInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SPBanChayYearInputKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || SPBanChayYearInput.getText().length() >= 4) {
            evt.consume(); // Ngăn chặn ký tự không hợp lệ và ngăn chặn nhập quá 4 ký tự
        }
    }//GEN-LAST:event_SPBanChayYearInputKeyTyped

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
    private javax.swing.JTable BanChayTable;
    private javax.swing.JLabel BanChayTopLabel;
    private javax.swing.JTextField BanChayYearInput;
    private javax.swing.JLabel BanChayYearLabel;
    private javax.swing.JPanel DanhMucPanel;
    private javax.swing.JPanel DanhMucPanel1;
    private javax.swing.JPanel DoanhThuPanel;
    private javax.swing.JPanel DoanhThuPanel1;
    private javax.swing.JPanel NeedBookPane;
    private javax.swing.JTable NeedBookTable;
    private javax.swing.JPanel NeedSPPanel;
    private javax.swing.JTable NeedSPTable;
    private javax.swing.JButton NhapXuatBtn;
    private javax.swing.JComboBox<String> NhapXuatMonthInput;
    private javax.swing.JLabel NhapXuatMonthLabel;
    private javax.swing.JPanel NhapXuatSachPane;
    private javax.swing.JTable NhapXuatTable;
    private javax.swing.JTextField NhapXuatYearInput;
    private javax.swing.JLabel NhapXuatYearLabel;
    private javax.swing.JButton PrintBanChayBtn;
    private javax.swing.JButton PrintNeedBookBtn;
    private javax.swing.JButton PrintNeedSPBtn;
    private javax.swing.JButton PrintNhapXuatBtn;
    private javax.swing.JButton PrintTonKhoBtn;
    private javax.swing.JSpinner SLBaoDongInput;
    private javax.swing.JLabel SLBaoDongLabel;
    private javax.swing.JSpinner SLSPBaoDongInput;
    private javax.swing.JLabel SLSPBaoDongLabel;
    private javax.swing.JButton SPBanChayBtn;
    private javax.swing.JComboBox<String> SPBanChayMonthInput;
    private javax.swing.JLabel SPBanChayMonthLabel;
    private javax.swing.JPanel SPBanChayPanel;
    private javax.swing.JTable SPBanChayTable;
    private javax.swing.JLabel SPBanChayTopLabel;
    private javax.swing.JTextField SPBanChayYearInput;
    private javax.swing.JLabel SPBanChayYearLabel;
    private javax.swing.JButton SPPrintBanChayBtn;
    private javax.swing.JPanel SachBanChayPane;
    private javax.swing.JButton SaveBtn;
    private javax.swing.JLabel TKLable;
    private javax.swing.JButton TKNeedBookBtn;
    private javax.swing.JButton TKNeedSPBtn;
    private javax.swing.JPanel TheLoaiPanel;
    private javax.swing.JPanel TheLoaiPanel1;
    private javax.swing.JPanel ThongKePane;
    private javax.swing.JPanel ThongKePane1;
    private javax.swing.JLabel TonKhoLabel;
    private javax.swing.JPanel TonKhoSachPane;
    private javax.swing.JTable TonKhoTable;
    private javax.swing.JSpinner TopBanChayInput;
    private javax.swing.JSpinner TopSPBanChayInput;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    // End of variables declaration//GEN-END:variables
}
