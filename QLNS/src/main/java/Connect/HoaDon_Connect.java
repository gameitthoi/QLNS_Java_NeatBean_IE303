package Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.HoaDon;
import java.sql.CallableStatement;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.jfree.data.category.DefaultCategoryDataset;

public class HoaDon_Connect extends Connect_sqlServer{
    
    public ArrayList<HoaDon> LayTatCaHoaDon(){
        ArrayList<HoaDon> dshd = new ArrayList<HoaDon>();
        try {
            String sql = "select * from HOADON where ThanhCong=1 and NhapSach=0" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet result  = pre.executeQuery();
            while(result.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(result.getString(1));
                hd.setMaNV(result.getString(2));
                hd.setMaKH(result.getString(3));
                hd.setNgaylap(result.getDate(4)+"");
                hd.setTongTien(result.getDouble(5));
                dshd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dshd ;
    }

    public DefaultTableModel layToanBoHoaDonTheoThangNam(String month , String year)
    {
        DefaultTableModel dshd = new DefaultTableModel();
        dshd.addColumn("Mã hóa Đơn");
        dshd.addColumn("Nhân viên");
        dshd.addColumn("Khách hàng");
        dshd.addColumn("Ngày lập");
        dshd.addColumn("Tổng tiền");
        dshd.setRowCount(0);
        String sql = "";
        try {
            if("0".equals(month))
                sql = "select H.MaHD, N.TenNV, K.TenKH, NgayLap, TongTien from HOADON AS H LEFT JOIN NHANVIEN AS N ON H.MaNV=N.MaNV LEFT JOIN KHACHHANG AS K ON H.MaKH=K.MaKH where Year(NgayLap) = ? and ThanhCong = 1 and NhapSach=0" ;
            else
                sql = "select H.MaHD, N.TenNV, K.TenKH, NgayLap, TongTien from HOADON AS H LEFT JOIN NHANVIEN AS N ON H.MaNV=N.MaNV LEFT JOIN KHACHHANG AS K ON H.MaKH=K.MaKH where MONTH(NgayLap) = ? and Year(NgayLap) = ? and ThanhCong = 1 and NhapSach=0" ;

            PreparedStatement pre = conn.prepareStatement(sql);

            if ("0".equals(month))
                pre.setString(1, year);
            else {
                pre.setString(1,month);
                pre.setString(2, year);
            }
            ResultSet result  = pre.executeQuery();
            while(result.next())
            {
                Vector<Object> vec = new Vector<Object>();
                vec.add(result.getString(1));
                vec.add(result.getString(2));
                vec.add(result.getString(3));
                vec.add(result.getString(4));
                vec.add(result.getString(5));
                dshd.addRow(vec);
            }

        } catch (Exception e) {
                e.printStackTrace();
        }
        return dshd ;
    }
    
    public DefaultTableModel layToanBoHoaDonHomNay()
    {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0 nên cần +1 để lấy tháng thực tế
        int year =cal.get(Calendar.YEAR);
        DefaultTableModel dshd = new DefaultTableModel();
        dshd.addColumn("Mã hóa Đơn");
        dshd.addColumn("Nhân viên");
        dshd.addColumn("Khách hàng");
        dshd.addColumn("Ngày lập");
        dshd.addColumn("Tổng tiền");
        dshd.setRowCount(0);
        try {
            String sql = "select H.MaHD, N.TenNV, K.TenKH, NgayLap, TongTien from HOADON AS H LEFT JOIN NHANVIEN AS N ON H.MaNV=N.MaNV LEFT JOIN KHACHHANG AS K ON H.MaKH=K.MaKH where Day(NgayLap)=? and MONTH(NgayLap)=? and Year(NgayLap) = ? and ThanhCong = 1 and NhapSach=0";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, day);
            pre.setInt(2, month);
            pre.setInt(3, year);
            ResultSet result  = pre.executeQuery();
            while(result.next())
            {
                Vector<Object> vec = new Vector<Object>();
                vec.add(result.getString(1));
                vec.add(result.getString(2));
                vec.add(result.getString(3));
                vec.add(result.getString(4));
                vec.add(result.getString(5));
                dshd.addRow(vec);
            }

        } catch (Exception e) {
                e.printStackTrace();
        }
        return dshd ;
    }
	
    public String LastMaHD(){
        try{
            //String sql = "select * from hoadon ORDER BY mahd DESC LIMIT 1" ;
            String sql = "SELECT TOP 1 * FROM hoadon ORDER BY mahd DESC";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) return result.getString(1); 
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public DefaultCategoryDataset DoanhThuCacThang(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try{
            String sql = "{call DoanhThuCacThang()}";
             CallableStatement  stmt  = conn.prepareCall(sql);		
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                dataset.addValue( Double.parseDouble(result.getString(2)),"Doanh thu bán hàng",result.getString(1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public int TaoHD(HoaDon hd) {
        try {
            String sql="insert into hoadon values(?,?,?,?,?,?,?) " ;
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setString(1, hd.getMaHD()+"");
            pre.setString(2, hd.getMaNV()+"");
            pre.setString(3, hd.getMaKH()+"");
            pre.setString(4, hd.getNgaylap()+"");
            pre.setString(5, hd.getTongTien()+"");
            pre.setString(6, hd.getTrangThai()+"");
            pre.setString(7, hd.getNhapSach()+"");
            return pre.executeUpdate();
        } catch (Exception e) {
                e.printStackTrace();
        }
        return -1 ;
    }
    
    public int ThanhToan(String MaHD, String total){
        try{
            String sql="update hoadon set ThanhCong = 1, TongTien = ? where MaHD=? and NhapSach=0" ;
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setString(1,total);
            pre.setString(2,MaHD+"");
            return pre.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    
    public int HuyHoaDon(String MaHD){
        try{
            String sql="DELETE FROM HOADON WHERE MaHD=?" ;
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setString(1,MaHD);
            return pre.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
