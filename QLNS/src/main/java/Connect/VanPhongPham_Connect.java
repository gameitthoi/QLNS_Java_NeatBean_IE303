/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connect;

import Model.NhaCungCap_VPP;
import Model.Sach;
import Model.VPP;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class VanPhongPham_Connect extends Connect_sqlServer{
    public ArrayList<VPP> layToanBoVPP()
    {
        ArrayList<VPP> dsVPP = new ArrayList<VPP>() ;
        try {
            String sql ="select * from VPP" ;
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
            String sql = "select * from VPP where TenVPP like ? " ;
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
            String sql = "select * from VPP where DanhMuc like ? " ;
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
            String sql = "select * from VPP where DanhMuc like ? and TenVPP like ? " ;
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
    
}
