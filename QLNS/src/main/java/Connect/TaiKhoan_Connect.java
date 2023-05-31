/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connect;
import Model.NhanVien;
import Model.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author dat
 */
public class TaiKhoan_Connect extends Connect_sqlServer{
    public ArrayList<TaiKhoan> layToanBoTaiKhoan(){
        ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();
        try{
            String sql ="select * from TAIKHOAN" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while(result.next())
            {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTK(result.getString(1));
                tk.setMaNV(result.getString(2));
                tk.setUserName(result.getString(3));
                tk.setPassWord(result.getString(4));
                tk.setBaoCao(result.getInt(5));
                tk.setTaiKhoan(result.getInt(6));
                tk.setMaVach(result.getInt(7));
                tk.setSach(result.getInt(8));
                tk.setNXB(result.getInt(9));
                tk.setNhanVien(result.getInt(10));
                tk.setHoaDon(result.getInt(11));
                tk.setNCCVPP(result.getInt(12));
                tk.setVPP(result.getInt(13));
                tk.setKhachHang(result.getInt(14));
                dstk.add(tk);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return dstk;
    }
    
    //Tìm tài khoản theo keyword
    public ArrayList<TaiKhoan> timTaiKhoan(String key){
        ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();
        try {
            String sql ="select * from TAIKHOAN where MaNV like ? " ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+key+"%");
            ResultSet result = pre.executeQuery();
            while(result.next())
            {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTK(result.getString(1));
                tk.setMaNV(result.getString(2));
                tk.setUserName(result.getString(3));
                tk.setPassWord(result.getString(4));
                tk.setBaoCao(result.getInt(5));
                tk.setTaiKhoan(result.getInt(6));
                tk.setMaVach(result.getInt(7));
                tk.setSach(result.getInt(8));
                tk.setNXB(result.getInt(9));
                tk.setNhanVien(result.getInt(10));
                tk.setHoaDon(result.getInt(11));
                tk.setNCCVPP(result.getInt(12));
                tk.setVPP(result.getInt(13));
                tk.setKhachHang(result.getInt(14));
                dstk.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dstk ;
    }
    
    public TaiKhoan TimTaiKhoanBangMaTK(String matk){
        TaiKhoan tk = new TaiKhoan();
        try {
            String sql ="select top 1 * from TAIKHOAN where MaTK = ? " ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, matk);
            ResultSet result = pre.executeQuery();
            while(result.next())
            {
                tk.setMaTK(result.getString(1));
                tk.setMaNV(result.getString(2));
                tk.setUserName(result.getString(3));
                tk.setPassWord(result.getString(4));
                tk.setBaoCao(result.getInt(5));
                tk.setTaiKhoan(result.getInt(6));
                tk.setMaVach(result.getInt(7));
                tk.setSach(result.getInt(8));
                tk.setNXB(result.getInt(9));
                tk.setNhanVien(result.getInt(10));
                tk.setHoaDon(result.getInt(11));
                tk.setNCCVPP(result.getInt(12));
                tk.setVPP(result.getInt(13));
                tk.setKhachHang(result.getInt(14));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
    
     public TaiKhoan timTaiKhoanBangMaNV(String manv){
        TaiKhoan tk = new TaiKhoan();
        try {
            String sql ="select top 1* from TAIKHOAN where MaNV = ?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, manv);
            ResultSet result = pre.executeQuery();
            while(result.next()){
                tk.setMaTK(result.getString(1));
                tk.setMaNV(result.getString(2));
                tk.setUserName(result.getString(3));
                tk.setPassWord(result.getString(4));
                tk.setBaoCao(result.getInt(5));
                tk.setTaiKhoan(result.getInt(6));
                tk.setMaVach(result.getInt(7));
                tk.setSach(result.getInt(8));
                tk.setNXB(result.getInt(9));
                tk.setNhanVien(result.getInt(10));
                tk.setHoaDon(result.getInt(11));
                tk.setNCCVPP(result.getInt(12));
                tk.setVPP(result.getInt(13));
                tk.setKhachHang(result.getInt(14));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
    
    public boolean kiemTraTonTai(String matk, String username, String manv)
    {
        try {
            String sql ="select * from TAIKHOAN where MaTK=? or username=? or MaNV = ?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, matk);
            pre.setString(2, username);
            pre.setString(2, manv);
            ResultSet result = pre.executeQuery();
            while (result.next()) return true ;
        } catch (Exception e) {
                e.printStackTrace();
        }

        return false ;
    }
    
    public int themTaiKhoan( TaiKhoan tk){
        try{
            String sql ="INSERT INTO TAIKHOAN (MaTK, MaNV, username, password, BaoCao, TaiKhoan, MaVach, Sach, NXB, NhanVien, HoaDon, NCCVPP, VPP, KhachHang) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement pre  =  conn.prepareStatement(sql);
            pre.setString(1, tk.getMaTK());
            pre.setString(2, tk.getMaNV());
            pre.setString(3,  tk.getUserName());
            pre.setString(4,  tk.getPassWord());
            pre.setInt(5,  tk.getBaoCao());
            pre.setInt(6,  tk.getTaiKhoan());
            pre.setInt(7,  tk.getMaVach());
            pre.setInt(8,  tk.getSach());
            pre.setInt(9,  tk.getNXB());
            pre.setInt(10,  tk.getNhanVien());
            pre.setInt(11,  tk.getHoaDon());
            pre.setInt(12,  tk.getNCCVPP());
            pre.setInt(13,  tk.getVPP());
            pre.setInt(14,  tk.getKhachHang());
            return pre.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int updateTaiKhoan(TaiKhoan tk){
        try {
            String sql ="update TAIKHOAN set MaNV=?, username=?, password=? , BaoCao=?, TaiKhoan=?, MaVach=?, Sach=?, NXB=?, NhanVien=?, HoaDon=?, NCCVPP=?, VPP=?, KhachHang=? where MaTK=?" ;
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setString(1, tk.getMaNV());
            pre.setString(2,  tk.getUserName());
            pre.setString(3,  tk.getPassWord());
            pre.setInt(4,  tk.getBaoCao());
            pre.setInt(5,  tk.getTaiKhoan());
            pre.setInt(6,  tk.getMaVach());
            pre.setInt(7,  tk.getSach());
            pre.setInt(8,  tk.getNXB());
            pre.setInt(9,  tk.getNhanVien());
            pre.setInt(10,  tk.getHoaDon());
            pre.setInt(11,  tk.getNCCVPP());
            pre.setInt(12,  tk.getVPP());
            pre.setInt(13,  tk.getKhachHang());
            pre.setString(14, tk.getMaTK());
            return pre.executeUpdate();	
        } catch (Exception e) {
                e.printStackTrace();
        }
        return -1;
    }
    
    public int xoaTaiKhoan(String matk ){
        try {
            String sql ="DELETE FROM TAIKHOAN where MaTK=? " ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, matk);
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
