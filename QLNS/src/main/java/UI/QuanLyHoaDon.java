/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Connect.CTHD_Connect;
import Connect.HoaDon_Connect;
import Model.HoaDon;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author dat
 */
public class QuanLyHoaDon extends javax.swing.JFrame {
private DefaultTableModel dtmHoaDon = null, dtmCTHD =null;
private ArrayList<HoaDon> dshd_thongke =null;
private double tongTien =0;
private DecimalFormat df = new DecimalFormat("###,###,###");
String filePath = "C:\\Users\\dat\\Downloads\\";
    /**
     * Creates new form QuanLyHoaDon
     */
    public QuanLyHoaDon(String title) {
        this.setTitle(title);
        initComponents();
        this.setLocationRelativeTo(null);
        hienThiHoaDon();
        hienThiCTHD("0");
    }
    
    private void hienThiHoaDon() {
        Calendar cal = Calendar.getInstance();
        MonthInput.setSelectedIndex(cal.get(Calendar.MONTH) + 1); // Tháng bắt đầu từ 0 nên cần +1 để lấy tháng thực tế
        YearInput.setText(Integer.toString(cal.get(Calendar.YEAR)) );

        HoaDon_Connect hd_connect = new HoaDon_Connect();

        HoaDonTable.setModel(hd_connect.layToanBoHoaDonTheoThangNam(Integer.toString(MonthInput.getSelectedIndex()), YearInput.getText()));
        tongTien = 0;
        for (int i = 0; i< HoaDonTable.getRowCount();i++){
            tongTien = tongTien + Double.parseDouble(HoaDonTable.getValueAt(i, 4).toString());
        }
        ToTalLabel.setText(df.format(tongTien) + " vnđ");
    }
    
    private void hienThiHoaDonHomNay(){
        HoaDon_Connect hd_connect = new HoaDon_Connect();

        HoaDonTable.setModel(hd_connect.layToanBoHoaDonHomNay());
        tongTien = 0;
        for (int i = 0; i< HoaDonTable.getRowCount();i++){
            tongTien = tongTien + Double.parseDouble(HoaDonTable.getValueAt(i, 4).toString());
        }

        ToTalLabel.setText(df.format(tongTien) + " vnđ");
    }
    
    private void hienThiCTHD(String mahd){
        CTHD_Connect cthd_conn = new CTHD_Connect();
        dtmCTHD = cthd_conn.layCTHDBangMaHD(mahd);
        CTHDTable.setModel(dtmCTHD);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        HoaDonTable = new javax.swing.JTable(dtmHoaDon);
        TitleLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        MonthLabel = new javax.swing.JLabel();
        MonthInput = new javax.swing.JComboBox<>();
        YearLabel = new javax.swing.JLabel();
        YearInput = new javax.swing.JTextField();
        SearchBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        PrintBtn = new javax.swing.JButton();
        ToTalLabel = new javax.swing.JLabel();
        TongTienLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        CTHDTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        HoaDonTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HoaDonTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(HoaDonTable);

        TitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TitleLabel.setForeground(new java.awt.Color(0, 102, 255));
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLabel.setText("QUẢN LÝ HÓA ĐƠN");

        MonthLabel.setText("Tháng");

        MonthInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        YearLabel.setText("Năm");

        YearInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                YearInputKeyTyped(evt);
            }
        });

        SearchBtn.setText("Tìm");
        SearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchBtnMouseClicked(evt);
            }
        });

        jButton1.setText("Hôm nay");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MonthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(YearLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(YearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MonthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(MonthInput, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(YearLabel)
                        .addComponent(YearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PrintBtn.setText("Xuất Excel");
        PrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintBtnActionPerformed(evt);
            }
        });

        ToTalLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ToTalLabel.setForeground(new java.awt.Color(0, 51, 255));
        ToTalLabel.setText("0 VNĐ");

        TongTienLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TongTienLabel.setText("Tổng tiền bán được tháng này: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(TongTienLabel)
                .addGap(18, 18, 18)
                .addComponent(ToTalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(PrintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ToTalLabel)
                    .addComponent(TongTienLabel)
                    .addComponent(PrintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(CTHDTable);

        jLabel2.setText("Chi tiết hóa đơn");

        jLabel3.setText("Danh sách hóa đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(TitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchBtnMouseClicked
        HoaDon_Connect hd_connect = new HoaDon_Connect();
        String thang  = MonthInput.getSelectedItem().toString();
        String nam = YearInput.getText();
        HoaDonTable.setModel(hd_connect.layToanBoHoaDonTheoThangNam(thang, nam));
        tongTien = 0;
        for (int i = 0; i< HoaDonTable.getRowCount();i++){
            tongTien = tongTien + Double.parseDouble(HoaDonTable.getValueAt(i, 4).toString());
        }
        TongTienLabel.setText("Tổng tiền bán được tháng này: ");
        ToTalLabel.setText(df.format(tongTien) + " vnđ");
        dtmCTHD.setColumnCount(0);
    }//GEN-LAST:event_SearchBtnMouseClicked

    private void PrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintBtnActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Xuất file excel?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION)
            XuatFileExcel((DefaultTableModel)HoaDonTable.getModel(), "Danh sách hóa đơn", filePath+"HoaDon.xls" );        
    }//GEN-LAST:event_PrintBtnActionPerformed

    private void YearInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_YearInputKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || YearInput.getText().length() >= 4) {
            evt.consume(); // Ngăn chặn ký tự không hợp lệ và ngăn chặn nhập quá 4 ký tự
        }
    }//GEN-LAST:event_YearInputKeyTyped

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        hienThiHoaDonHomNay();
        dtmCTHD.setColumnCount(0);
        TongTienLabel.setText("Tổng tiền bán được hôm nay: ");
    }//GEN-LAST:event_jButton1MouseClicked

    private void HoaDonTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonTableMouseClicked
        int select = HoaDonTable.getSelectedRow();
        hienThiCTHD(HoaDonTable.getValueAt(select, 0).toString());
    }//GEN-LAST:event_HoaDonTableMouseClicked

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
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyHoaDon("Quản Lý Hóa Đơn").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CTHDTable;
    private javax.swing.JTable HoaDonTable;
    private javax.swing.JComboBox<String> MonthInput;
    private javax.swing.JLabel MonthLabel;
    private javax.swing.JButton PrintBtn;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JLabel ToTalLabel;
    private javax.swing.JLabel TongTienLabel;
    private javax.swing.JTextField YearInput;
    private javax.swing.JLabel YearLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
