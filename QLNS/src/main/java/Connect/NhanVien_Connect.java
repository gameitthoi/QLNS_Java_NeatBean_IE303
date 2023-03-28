package Connect;

import Model.NhanVien;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

public class NhanVien_Connect extends Connect_sqlServer{
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public ArrayList<NhanVien> layToanBoNhanVien()
	{
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		try {
			String sql ="select * from NHANVIEN where Isdelete =?" ;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, 0);
			ResultSet result = pre.executeQuery();
			while(result.next())
			{
//				JOptionPane.showMessageDialog(null, "ss");
				NhanVien nv = new NhanVien();
				nv.setMaNV(result.getString(1));
				nv.setTenNV(result.getString(2));
				nv.setNgaySinh(df.format(result.getDate(3)));
				nv.setNgayVaolam(df.format(result.getDate(4)));
				nv.setSoChungMinh(result.getString(5));
				nv.setMaCV(result.getString(6));
				nv.setSDT(result.getString(7));
				nv.setTrangThai(result.getString(8));
				nv.setEmail(result.getString(9));
				
				dsnv.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsnv;
	}
	// theem moi nv 
	public int themNhanVien(NhanVien nv)
	{
		try {
			String sql ="insert into NHANVIEN values (?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement pre  =  conn.prepareStatement(sql);
			pre.setString(1, nv.getMaNV());
			pre.setString(2, nv.getTenNV());
			pre.setString(3,  nv.getNgaySinh()+"");
			pre.setString(4,  nv.getNgayVaolam()+"");
			pre.setString(5, nv.getSoChungMinh());
			pre.setString(6, nv.getMaCV());
			pre.setString(7, nv.getSDT());
			pre.setString(8, nv.getTrangThai());
			pre.setString(9, nv.getEmail());
			pre.setInt(10, 0);
			return pre.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return -1 ;
	}
	//update nhân viên
	public int updateNhanVien(NhanVien nv)
	{
		try {
			
			String sql ="update NHANVIEN set TenNV=?, NgaySinh=?, NgayVaoLam=?, SoChungMinh=?, MaCV=?, SDT=?,TrangThai=? ,Email=? where MaNV=?" ;
			PreparedStatement pre =conn.prepareStatement(sql);
			pre.setString(1, nv.getTenNV());
			pre.setString(2,  nv.getNgaySinh()+"");
			pre.setString(3, nv.getNgayVaolam()+"");
			pre.setString(4, nv.getSoChungMinh());
			pre.setString(5, nv.getMaCV());
			pre.setString(6, nv.getSDT());
			pre.setString(7, nv.getTrangThai());
			pre.setString(8, nv.getEmail());
			pre.setString(9, nv.getMaNV());
			return pre.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	//Xóa nhân viên
	public int xoaNhanVien(String manv )
	{
		try {
			String sql ="update NHANVIEN set Isdelete =? where MaNV=? " ;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, 1);
			pre.setString(2, manv);
			return pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return -1;
	}
	// lấy ra nhân viên có mã cho trước (kiểm tra đã tồn tại hay chưa)
	public boolean kiemTraTonTai(String manv)
	{
		NhanVien nv = new NhanVien();
		try {
			
			String sql ="select * from NHANVIEN where MaNV=? and Isdelete=?" ;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, manv);
			pre.setInt(2, 0);
			ResultSet result = pre.executeQuery();
			while (result.next())
			{
				
				return true ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false ;
	}
	//Tìm nhân viên theo keyword
	public ArrayList<NhanVien> timNhanVien(String key)
	{
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		try {
			String sql ="select * from NHANVIEN where MaNV like ? or TenNV like ? and Isdelete=?" ;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, "%"+key+"%");
			pre.setString(2, "%"+key+"%");
			pre.setInt(3, 0);
			ResultSet result = pre.executeQuery();
			while(result.next())
			{
				NhanVien nv = new NhanVien();
				nv.setMaNV(result.getString(1));
				nv.setTenNV(result.getString(2));
				nv.setNgaySinh(df.format(result.getDate(3)));
				nv.setNgayVaolam(df.format(result.getDate(4)));
				nv.setSoChungMinh(result.getString(5));
				nv.setMaCV(result.getString(6));
				nv.setSDT(result.getString(7));
				nv.setTrangThai(result.getString(8));
				nv.setEmail(result.getString(9));
				dsnv.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsnv ;
	}
	

}
