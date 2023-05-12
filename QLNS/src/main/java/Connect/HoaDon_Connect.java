package Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.HoaDon;
import java.sql.CallableStatement;
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
                hd.setNgaylap(result.getDate(3)+"");
                hd.setTongTien(result.getDouble(4));
                dshd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dshd ;
    }

    public ArrayList<HoaDon> layToanBoHoaDonTheoThangNam(String month , String year)
    {
        ArrayList<HoaDon> dshd = new ArrayList<HoaDon>();
        String sql = "";
        try {
            if("0".equals(month))
                sql = "select * from HOADON where Year(NgayLap) = ? and ThanhCong = 1 and NhapSach=0" ;
            else
                sql = "select * from HOADON where MONTH(NgayLap) = ? and Year(NgayLap) = ? and ThanhCong = 1 and NhapSach=0" ;

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
                HoaDon hd = new HoaDon();
                hd.setMaHD(result.getString(1));
                hd.setMaNV(result.getString(2));
                hd.setNgaylap(result.getDate(3)+"");
                hd.setTongTien(result.getDouble(4));
                dshd.add(hd);
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
                dataset.addValue( Double.parseDouble(result.getString(2)),"Doanh thu bán sách",result.getString(1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public int TaoHD(HoaDon hd) {
        try {
            String sql="insert into hoadon values(?,?,?,?,?,?) " ;
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setString(1, hd.getMaHD()+"");
            pre.setString(2, hd.getMaNV()+"");
            pre.setString(3, hd.getNgaylap()+"");
            pre.setString(4, hd.getTongTien()+"");
            pre.setString(5, hd.getTrangThai()+"");
            pre.setString(6, hd.getNhapSach()+"");
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
