package Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.NXB;
import Model.Sach;

public class Sach_Connect extends Connect_sqlServer{
	
	public ArrayList<Sach> layToanBoSach()
	{
		ArrayList<Sach> dss = new ArrayList<Sach>() ;
		try {
			String sql ="select * from SACH where IsDelete =0" ;
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next())
			{
				Sach s = new Sach();
				s.setMaSach(result.getString(1));
				s.setTenSach(result.getString(2));
				s.setMaNXB(result.getString(3));
				s.setTacGia(result.getString(4));
				s.setGiaBan(result.getDouble(5));
				s.setTheLoai(result.getString(6));
				s.setSoLuong(result.getInt(7));
				s.setIsDelete(result.getInt(8));
				dss.add(s);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dss;
	}
	
	// lay doanh sach theo ma nxb va ten sach
	public ArrayList<Sach> laySachTheoNXBTen(String manxb , String ten)
	{
		ArrayList<Sach> dss2 = new ArrayList<Sach>();
		try {
			String sql ="select * from SACH where IsDelete=? and MaNXB=? and TenSach like ? " ;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, 0);
			pre.setString(2, manxb);
			pre.setString(3, "%"+ten+"%");
			
			ResultSet result = pre.executeQuery();
			while(result.next())
			{
//				JOptionPane.showMessageDialog(null, "ss");
				Sach s = new Sach();
				s.setMaSach(result.getString(1));
				s.setTenSach(result.getString(2));
				s.setMaNXB(result.getString(3));
				s.setTacGia(result.getString(4));
				s.setGiaBan(result.getDouble(5));
				s.setTheLoai(result.getString(6));
				s.setSoLuong(result.getInt(7));
				s.setIsDelete(result.getInt(8));
				
				dss2.add(s);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dss2;
	}
	// ham lay doanh sach theo ten sach
	public ArrayList<Sach> laySachTheoMaTen(String maten)
	{
		ArrayList<Sach> dss3 = new ArrayList<Sach>();
		
		try {
			String sql = "select * from SACH where IsDelete=?  and TenSach like ? " ;
			PreparedStatement pre1 = conn.prepareStatement(sql);
			pre1.setInt(1, 0);
			pre1.setString(2, "%"+maten+"%");
			ResultSet result = pre1.executeQuery();
			while (result.next())
			{
//				JOptionPane.showMessageDialog(null, "sssss");
				Sach s = new Sach();
				s.setMaSach(result.getString(1));
				s.setTenSach(result.getString(2));
				s.setMaNXB(result.getString(3));
				s.setTacGia(result.getString(4));
				s.setGiaBan(result.getDouble(5));
				s.setTheLoai(result.getString(6));
				s.setSoLuong(result.getInt(7));
				s.setIsDelete(result.getInt(8));
				
				dss3.add(s);
			}
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		
		return dss3;
	}
	
	// lay doanh sach theo ma nha xuat ban
	public ArrayList<Sach> laySachTheoNXB(String keyWord )
	{
		ArrayList<Sach> dss2 = new ArrayList<Sach>();
		try {
			String sql =" select * from SACH where IsDelete=? and MaNXB=? ";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, 0);
			pre.setString(2, keyWord);
//			Statement statement = conn.createStatement();			
			ResultSet result = pre.executeQuery();
			while(result.next())
			{
//				JOptionPane.showMessageDialog(null, "ss");
				Sach s = new Sach();
				s.setMaSach(result.getString(1));
				s.setTenSach(result.getString(2));
				s.setMaNXB(result.getString(3));
				s.setTacGia(result.getString(4));
				s.setGiaBan(result.getDouble(5));
				s.setTheLoai(result.getString(6));
				s.setSoLuong(result.getInt(7));
				s.setIsDelete(result.getInt(8));
				
				dss2.add(s);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dss2;
	}
	// ham them moi vao csdl
	public int  themSachMoi(Sach s)
	{
		
		try {
			String sql = "insert into SACH values (?,?,?,?,?,?,?,?)"  ;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, s.getMaSach());
			pre.setString(2, s.getTenSach());
			pre.setString(3, s.getMaNXB());
			pre.setString(4, s.getTacGia());
			pre.setDouble(5, s.getGiaBan());
			pre.setString(6, s.getTheLoai());
			pre.setInt(7, s.getSoLuong());
			pre.setInt(8, 0);
			
			 return pre.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1 ;
	}
	// ham xoa 
	public int XoaSach(String maSach)
	{
		try {
//			String sql ="delete  from SACH  where MaSach=? " ; //set IsDelete=?
			String sql ="update SACH set IsDelete=? where MaSach=?" ;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, 1);
			pre.setString(2,maSach);
			
			return pre.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	// hàm cập nhật chỉnh sửa sách 
	public int update (Sach s)
	{
		try {
			String sql = "update SACH set TenSach=? ,MaNXB=? ,TacGia=? ,GiaBan=? ,TheLoai=?,SoLuong=? where MaSach=? " ;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, s.getTenSach());
			pre.setString(2, s.getMaNXB());
			pre.setString(3, s.getTacGia());
			pre.setDouble(4, s.getGiaBan());
			pre.setString(5, s.getTheLoai());
			pre.setInt(6, s.getSoLuong());
			pre.setString(7, s.getMaSach());
			return pre.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1 ;
	}
	//Hàm update số lượng sách sau khi bán
	public int updateSL(String MaSach, int SL){
        try {
//		String sql ="delete  from SACH  where MaSach=? " ; //set IsDelete=?
		String sql ="update SACH set SoLuong=? where MaSach=?" ;
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setInt(1, SL);
		pre.setString(2,MaSach);
		
		return pre.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
	}
	
	return -1;
    }
	//Hàm Lấy danh sách sách có số lượng còn dưới 5
	public ArrayList<Sach> laySachConDuoiTon()
	{
		ArrayList<Sach> dssTon = new ArrayList<Sach>();
		try {
			String sql ="select SACH.MaSach , SACH.TenSach , SACH.SoLuong , NXB.TenNXB from SACH,NXB where SACH.MaNXB = NXB.MaNXB and SACH.IsDelete=? and SACH.SoLuong<?" ;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, 0);
			pre.setInt(2, 10);
			ResultSet result = pre.executeQuery();
			while (result.next())
			{
				JOptionPane.showMessageDialog(null	, "ss");	
				Sach s = new Sach();				
				s.setMaSach(result.getString(1));
				s.setTenSach(result.getString(2));
				s.setMaNXB(result.getString(4));
				s.setSoLuong(result.getInt(3));
				s.setIsDelete(0);
				
				dssTon.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dssTon;
	}

	
	

}
