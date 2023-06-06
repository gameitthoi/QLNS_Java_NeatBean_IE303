/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connect;

import Model.NhaCungCap_VPP;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class NhaCungCapVPP_Connect extends Connect_sqlServer {
    public ArrayList<NhaCungCap_VPP> layToanBoNhaCungCap_VPP()
    {
        ArrayList<NhaCungCap_VPP> dsNCC = new ArrayList<NhaCungCap_VPP>() ;
        try {
            String sql ="select * from NCCVPP" ;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                NhaCungCap_VPP ncc = new NhaCungCap_VPP();
                ncc.setMaNCCVPP(result.getString(1));
                ncc.setTenNCCVPP(result.getString(2));
                ncc.setSDT(result.getString(3));
                ncc.setDiaChi(result.getString(4));   
                ncc.setEmail(result.getString(5));
                dsNCC.add(ncc);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return dsNCC;
    }
    
    
    //lấy NCCVPP theo tên NCC
    public ArrayList<NhaCungCap_VPP> laySachTheoTenNhaCungCap (String tenNCC)
    {
         ArrayList<NhaCungCap_VPP> dsNCC = new  ArrayList<NhaCungCap_VPP>();

        try {
            String sql = "select * from NCCVPP where TenNCCVPP like ? " ;
            PreparedStatement pre1 = conn.prepareStatement(sql);
            pre1.setString(1, "%"+tenNCC+"%");
            ResultSet result = pre1.executeQuery();
            while (result.next()){
                NhaCungCap_VPP ncc = new NhaCungCap_VPP();
                ncc.setMaNCCVPP(result.getString(1));
                ncc.setTenNCCVPP(result.getString(2));
                ncc.setSDT(result.getString(3));
                ncc.setDiaChi(result.getString(4));   
                ncc.setEmail(result.getString(5));
                dsNCC.add(ncc);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dsNCC;
    }
    
    //for VPP page
    public NhaCungCap_VPP TimTenNCC(String tenNCC){
        NhaCungCap_VPP ncc = new NhaCungCap_VPP();
        try{
            String sql ="select top 1 MaNCCVPP, TenNCCVPP from NCCVPP where TenNCCVPP=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, tenNCC);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                ncc.setMaNCCVPP(result.getString(1));
                ncc.setTenNCCVPP(result.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ncc;
    }
    
    //add NCCVPP
     public int  themMoi(NhaCungCap_VPP ncc)
    {
        try {
            String sql = "insert into NCCVPP values (?,?,?,?,?)"  ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ncc.getMaNCCVPP());
            pre.setString(2, ncc.getTenNCCVPP());
            pre.setString(3, ncc.getSDT());
            pre.setString(4, ncc.getDiaChi());
            pre.setString(5, ncc.getEmail());
      
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1 ;
    }
     
     //sửa xoá NCCVPP
         public int Xoa(String MaNCC)
    {
        try {
            String sql ="delete NCCVPP where MaNCCVPP=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,MaNCC);

            return pre.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    // hàm cập nhật chỉnh sửa sách 
    public int update (NhaCungCap_VPP ncc)
    {
            try {
                String sql = "update NCCVPP set TenNCCVPP=? ,SDT=? ,DiaChi=? ,Email=? where MaNCCVPP=? " ;
                PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, ncc.getTenNCCVPP());
            pre.setString(2, ncc.getSDT());
            pre.setString(3, ncc.getDiaChi());
            pre.setString(4, ncc.getEmail());
            pre.setString(5, ncc.getMaNCCVPP());
                return pre.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return -1 ;
    }
}
