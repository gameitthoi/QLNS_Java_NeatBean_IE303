/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connect;

import Model.NhaCungCap_VPP;
import Model.Sach;
import Model.TonKho;
import Model.VPP;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author nguye
 */
public class VanPhongPham_Connect extends Connect_sqlServer{
    public ArrayList<VPP> layToanBoVPP()
    {
        ArrayList<VPP> dsVPP = new ArrayList<VPP>() ;
        try {
            String sql ="select MaVPP, TenVPP, TenNCCVPP, GiaBanVPP, DanhMuc, SoLuong, Discount from VPP,NCCVPP where VPP.MaNCCVPP = NCCVPP.MaNCCVPP" ;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                VPP vpp = new VPP();
                vpp.setMaVPP(result.getString(1));
                vpp.setTenVPP(result.getString(2));
                vpp.setMaNCC(result.getString(3));
                vpp.setGiaBan(result.getDouble(4));
                vpp.setDanhMuc(result.getString(5));
                vpp.setSoLuong(result.getInt(6));
                vpp.setDiscount(result.getInt(7));
               
                dsVPP.add(vpp);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return dsVPP;
    }
    public ArrayList<VPP> layVPPTheoNCC(String ma)
    {
        ArrayList<VPP> dsVPP = new ArrayList<VPP>() ;
        try {
            String sql ="select MaVPP, TenVPP, TenNCCVPP, GiaBanVPP, DanhMuc, SoLuong, Discount from VPP,NCCVPP  where VPP.MaNCCVPP = NCCVPP.MaNCCVPP and VPP.MaNCCVPP like ?" ;
            PreparedStatement pre1 = conn.prepareStatement(sql);
            pre1.setString(1, "%"+ma+"%");
            ResultSet result = pre1.executeQuery();
            while(result.next()){
                VPP vpp = new VPP();
                vpp.setMaVPP(result.getString(1));
                vpp.setTenVPP(result.getString(2));
                vpp.setMaNCC(result.getString(3));
                vpp.setGiaBan(result.getDouble(4));

                vpp.setDanhMuc(result.getString(5));
                vpp.setSoLuong(result.getInt(6));
                vpp.setDiscount(result.getInt(7));
               
                dsVPP.add(vpp);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return dsVPP;
    }
    public ArrayList<String> layTatCaDanhMuc(){
            ArrayList<String> dsdm = new ArrayList<String>() ;
            try{
                String sql ="SELECT DISTINCT DanhMuc FROM VPP" ;
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while(result.next()){
                    dsdm.add(result.getString(1));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return dsdm;
    }
    
    public ArrayList<TonKho> laySPTonKho(int thang, int nam ){
        ArrayList<TonKho> dsTK = new ArrayList<TonKho>();
        String sql = "";
        Calendar cal = Calendar.getInstance();
        try {
            sql ="{call GetTonKhoThangHienTai("+nam+","+thang+")}";
            CallableStatement  stmt  = conn.prepareCall(sql);		
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                TonKho tk = new TonKho();
                tk.setMaSP(result.getString(1));
                tk.setTenSP(result.getString(2));
                tk.setTonDau(result.getInt(3));
                tk.setNhap(result.getInt(4));
                tk.setXuat(result.getInt(5));
                tk.setTonCuoi(result.getInt(6));

                dsTK.add(tk);  
            }	
        } catch (Exception e) {
                e.printStackTrace();
        }
        return dsTK;
    }
    
    public ArrayList<VPP> laySPBanChay(String top, String thang, String nam ){
        ArrayList<VPP> dsSBC = new ArrayList<VPP>();
        String sql = "";
        try {
            if ("0".equals(thang))  
                sql ="SELECT TOP " +top +" A.MaVPP,TenVPP, SUM(A.SoLuong) as SL FROM (SELECT VPP.MaVPP,TenVPP,CTHD.SoLuong FROM VPP,HOADON,CTHD WHERE VPP.MaVPP=CTHD.MaSP AND CTHD.MaHD=HOADON.MaHD AND YEAR(NgayLap)=? AND HOADON.ThanhCong=1 AND HOADON.NhapSach=0) AS A GROUP BY MaVPP, TenVPP ORDER BY SL DESC ";
            else 
                sql ="SELECT TOP " +top +" A.MaVPP,TenVPP, SUM(A.SoLuong) as SL FROM (SELECT VPP.MaVPP,TenVPP,CTHD.SoLuong FROM VPP,HOADON,CTHD WHERE VPP.MaVPP=CTHD.MaSP AND CTHD.MaHD=HOADON.MaHD AND MONTH(NgayLap)=? AND YEAR(NgayLap)=? AND HOADON.ThanhCong=1 AND HOADON.NhapSach=0 ) AS A GROUP BY MaVPP, TenVPP ORDER BY SL DESC";

            PreparedStatement pre = conn.prepareStatement(sql);

            if ("0".equals(thang)) {
                pre.setString(1, nam);
            }
            else {
                pre.setString(1, thang);
                pre.setString(2, nam);
            }		
            ResultSet result = pre.executeQuery();
            while(result.next()){
                VPP s = new VPP();
                s.setMaVPP(result.getString(1));
                s.setTenVPP(result.getString(2));
                s.setSoLuong(result.getInt(3));
                dsSBC.add(s);
            }


        } catch (Exception e) {
                e.printStackTrace();
        }
        return dsSBC;
    }
    
    public ArrayList<VPP> laySPConDuoiTon(int SL)
    {
        ArrayList<VPP> dssTon = new ArrayList<VPP>();
        try {
            String sql ="select VPP.MaVPP , VPP.TenVPP , VPP.SoLuong, VPP.DanhMuc from VPP where VPP.SoLuong<=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, SL);
            ResultSet result = pre.executeQuery();
            while (result.next()){	
                VPP s = new VPP();				
                s.setMaVPP(result.getString(1));
                s.setTenVPP(result.getString(2));
                s.setDanhMuc(result.getString(4));
                s.setSoLuong(result.getInt(3));				
                dssTon.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dssTon;
    }
    
    public int  themMoi(VPP vpp)
    {
        try {
            String sql = "insert into VPP values (?,?,?,?,?,?,?)"  ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, vpp.getMaVPP());
            pre.setString(2, vpp.getTenVPP());
            pre.setString(3, vpp.getMaNCC());
            pre.setDouble(4, vpp.getGiaBan());
            pre.setString(5, vpp.getDanhMuc());
            pre.setInt(6, vpp.getSoLuong());
            pre.setInt(7, vpp.getDiscount());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1 ;
    }
    
    public int Xoa(String maVPP)
    {
        try {
            String sql ="delete VPP where MaVPP=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,maVPP);

            return pre.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    
    //Hàm update số lượng sách sau khi bán
    public int updateSL(String MaSP, int SL){
        try {
            String sql ="update VPP set SoLuong=? where MaVPP=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, SL);
            pre.setString(2,MaSP);

            return pre.executeUpdate();

            } catch (Exception e) {
                    e.printStackTrace();
            }

        return -1;
    }
     public int Update(VPP vpp){
         try {
                String sql = "update VPP set TenVPP=? ,MaNCCVPP=? ,GiaBanVPP=? ,DanhMuc=?,SoLuong=?, Discount=? where MaVPP=? " ;
                PreparedStatement pre = conn.prepareStatement(sql);
                
            pre.setString(1, vpp.getTenVPP());
            pre.setString(2, vpp.getMaNCC());
            pre.setDouble(3, vpp.getGiaBan());
            pre.setString(4, vpp.getDanhMuc());
            pre.setInt(5, vpp.getSoLuong());
            pre.setInt(6, vpp.getDiscount());
              pre.setString(7, vpp.getMaVPP());  
                return pre.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return -1 ;
     }
        
     
     public int updatePrice(String MaVPP, double extra){
        try {
            String sql ="update VPP set GiaBanVPP=? where MaVPP=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDouble(1, extra);
            pre.setString(2,MaVPP);

            return pre.executeUpdate();

            } catch (Exception e) {
                    e.printStackTrace();
            }

        return -1;
    }
     
     public ArrayList<VPP> laySachTheoTenVPP(String maten)
    {
        ArrayList<VPP> dsvpp = new ArrayList<VPP>();

        try {
            String sql = "select MaVPP, TenVPP, TenNCCVPP, GiaBanVPP, DanhMuc, SoLuong, Discount from VPP, NCCVPP where VPP.MaNCCVPP = NCCVPP.MaNCCVPP and TenVPP like ? " ;
            PreparedStatement pre1 = conn.prepareStatement(sql);
            pre1.setString(1, "%"+maten+"%");
            ResultSet result = pre1.executeQuery();
            while (result.next()){
                VPP vpp = new VPP();
                vpp.setMaVPP(result.getString(1));
                vpp.setTenVPP(result.getString(2));
                vpp.setMaNCC(result.getString(3));
                vpp.setGiaBan(result.getDouble(4));

                vpp.setDanhMuc(result.getString(5));
                vpp.setSoLuong(result.getInt(6));
                vpp.setDiscount(result.getInt(7));
                dsvpp.add(vpp);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dsvpp;
    }
     
     public ArrayList<VPP> laySachTheoDanhMuc(String dm)
    {
        ArrayList<VPP> dsvpp = new ArrayList<VPP>();

        try {
            String sql = "select MaVPP, TenVPP, TenNCCVPP, GiaBanVPP, DanhMuc, SoLuong, Discount from VPP, NCCVPP where VPP.MaNCCVPP = NCCVPP.MaNCCVPP and DanhMuc like ? " ;
            PreparedStatement pre1 = conn.prepareStatement(sql);
            pre1.setString(1, "%"+dm+"%");
            ResultSet result = pre1.executeQuery();
            while (result.next()){
                VPP vpp = new VPP();
                vpp.setMaVPP(result.getString(1));
                vpp.setTenVPP(result.getString(2));
                vpp.setMaNCC(result.getString(3));
                vpp.setGiaBan(result.getDouble(4));

                vpp.setDanhMuc(result.getString(5));
                vpp.setSoLuong(result.getInt(6));
                vpp.setDiscount(result.getInt(7));
                dsvpp.add(vpp);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dsvpp;
    }
     
     public ArrayList<VPP> laySachTheoDanhMucVaTenVPP(String dm, String maten)
    {
        ArrayList<VPP> dsvpp = new ArrayList<VPP>();

        try {
            String sql = "select MaVPP, TenVPP, TenNCCVPP, GiaBanVPP, DanhMuc, SoLuong, Discount from VPP, NCCVPP where VPP.MaNCCVPP = NCCVPP.MaNCCVPP and DanhMuc like ? and TenVPP like ? " ;
            PreparedStatement pre1 = conn.prepareStatement(sql);
            pre1.setString(1, "%"+dm+"%");
            pre1.setString(2, "%"+maten+"%");
            ResultSet result = pre1.executeQuery();
            while (result.next()){
                VPP vpp = new VPP();
                vpp.setMaVPP(result.getString(1));
                vpp.setTenVPP(result.getString(2));
                vpp.setMaNCC(result.getString(3));
                vpp.setGiaBan(result.getDouble(4));

                vpp.setDanhMuc(result.getString(5));
                vpp.setSoLuong(result.getInt(6));
                vpp.setDiscount(result.getInt(7));
                dsvpp.add(vpp);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dsvpp;
    }
    
    public DefaultPieDataset laySPTheoDanhMuc(){
       DefaultPieDataset dataset = new DefaultPieDataset();
       try{
            String sql = "SELECT DanhMuc, COUNT(MaVPP) AS SoLuongSanPham FROM VPP GROUP BY DanhMuc";
            PreparedStatement pre = conn.prepareStatement(sql);		
            ResultSet result = pre.executeQuery();
            while(result.next()){
                dataset.setValue( result.getString(1), result.getInt(2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
   } 
    
    public boolean kiemTraTonTai(String maVPP)
    {
        
        try {
            String sql ="select * from VPP where MaVPP=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, maVPP);
            ResultSet result = pre.executeQuery();
            while (result.next()) return true ;
        } catch (Exception e) {
                e.printStackTrace();
        }

        return false ;
    }
}
