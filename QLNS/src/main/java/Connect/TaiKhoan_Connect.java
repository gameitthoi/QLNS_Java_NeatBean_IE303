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
            String sql ="select MaTk, MaNV, username, password from TAIKHOAN" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while(result.next())
            {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTK(result.getString(1));
                tk.setMaNV(result.getString(2));
                tk.setUserName(result.getString(3));
                tk.setPassWord(result.getString(4));
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
            String sql ="select MaTk, MaNV, username, password from TAIKHOAN where MaNV like ? " ;
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
                dstk.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dstk ;
    }
    
    public boolean kiemTraTonTai(String matk, String username)
    {
        try {
            String sql ="select * from TAIKHOAN where MaTK=? and username=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, matk);
            pre.setString(2, username);
            ResultSet result = pre.executeQuery();
            while (result.next()) return true ;
        } catch (Exception e) {
                e.printStackTrace();
        }

        return false ;
    }
    
    public int themTaiKhoan( TaiKhoan tk){
        try{
            String sql ="INSERT INTO TAIKHOAN (MaTK, MaNV, username, password) values (?,?,?,?) ";
            PreparedStatement pre  =  conn.prepareStatement(sql);
            pre.setString(1, tk.getMaTK());
            pre.setString(2, tk.getMaNV());
            pre.setString(3,  tk.getUserName());
            pre.setString(4,  tk.getPassWord());
            return pre.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int updateTaiKhoan(TaiKhoan tk){
        try {
            String sql ="update TAIKHOAN set MaNV=?, username=?, password=? where MaTK=?" ;
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setString(1, tk.getMaNV());
            pre.setString(2,  tk.getUserName());
            pre.setString(3,  tk.getPassWord());
            pre.setString(4, tk.getMaTK());
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
