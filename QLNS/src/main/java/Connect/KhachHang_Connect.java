/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connect;

import Model.KhachHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author dat
 */
public class KhachHang_Connect extends Connect_sqlServer{
    public ArrayList<KhachHang> LayTatCaKhachHang(){
        ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
        try {
            String sql = "select MaKH,TenKH, Sdt, Diem from KHACHHANG" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet result  = pre.executeQuery();
            while(result.next()){
                KhachHang kh = new KhachHang();
                kh.setMaKH(result.getString(1));
                kh.setTenKH(result.getString(2));
                kh.setSdt(result.getString(3));
                kh.setDiem(result.getDouble(4));
                dskh.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dskh;
    }
    
    public KhachHang layKhachHangBangSDT(String sdt){
        KhachHang kh = new KhachHang();
        try {
            String sql = "select top 1 MaKH, TenKH, Sdt, Diem from KHACHHANG where Sdt like ?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+sdt+"%");
            ResultSet result  = pre.executeQuery();
            while(result.next()){
                kh.setMaKH(result.getString(1));
                kh.setTenKH(result.getString(2));
                kh.setSdt(result.getString(3));
                kh.setDiem(result.getDouble(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kh;
    }
    public int themKhachHang(KhachHang kh){
        try {
            String sql ="INSERT INTO KhachHang (MaKH, TenKH, Sdt, Diem) values (?,?,?,?) ";
            PreparedStatement pre  =  conn.prepareStatement(sql);
            pre.setString(1, kh.getMaKH());
            pre.setString(2, kh.getTenKH());
            pre.setString(3,  kh.getSdt());
            pre.setDouble(4,  kh.getDiem());
            return pre.executeUpdate();			

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1 ;
    }
    
    public int updateKhachHang(KhachHang kh){
        try {
            String sql ="update KhachHang set TenKH=?, Sdt=?, Diem=? where MaKH=?" ;
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setString(1, kh.getTenKH());
            pre.setString(2,  kh.getSdt());
            pre.setDouble(3,  kh.getDiem());
            pre.setString(4, kh.getMaKH());
            return pre.executeUpdate();	
        } catch (Exception e) {
                e.printStackTrace();
        }
        return -1;
    }
    
    public int xoaKhachHang(String makh ){
        try {
            String sql ="DELETE FROM KhachHang where MaKH=? " ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, makh);
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public boolean kiemTraTonTai(String makh)
    {
        KhachHang kh = new KhachHang();
        try {
            String sql ="select * from KhachHang where MaKH=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, makh);
            ResultSet result = pre.executeQuery();
            while (result.next()) return true ;
        } catch (Exception e) {
                e.printStackTrace();
        }

        return false ;
    }
    
    public ArrayList<KhachHang> timKhachHang(String key){
        ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
        try {
            String sql ="select MaKH, TenKH, Sdt, Diem from KhachHang where MaKH like ? or TenKH like ?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+key+"%");
            pre.setString(2, "%"+key+"%");
            ResultSet result = pre.executeQuery();
            while(result.next()){
                KhachHang kh = new KhachHang();
                kh.setMaKH(result.getString(1));
                kh.setTenKH(result.getString(2));
                kh.setSdt(result.getString(3));
                kh.setDiem(result.getDouble(4));
                dskh.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dskh ;
    }
}
