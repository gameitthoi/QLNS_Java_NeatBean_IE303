package Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.HoaDon;

public class HoaDon_Connect extends Connect_sqlServer{
	
	public ArrayList<HoaDon> layToanBoHoaDonTheoThangNam(String dauThang , String CuoiThang)
	{
		ArrayList<HoaDon> dshd = new ArrayList<HoaDon>();
		try {
			String sql = "select * from HOADON where NgayLap between CAST(? as date) and CAST(? as date) and IsDelete=?" ;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1,dauThang);
			pre.setString(2, CuoiThang);
			pre.setInt(3, 0);
			ResultSet result  = pre.executeQuery();
			while(result.next())
			{
//				JOptionPane.showMessageDialog(null, result.getString(3));
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
            String sql = "select * from hoadon ORDER BY mahd DESC LIMIT 1" ;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next())
			{
				return result.getString(1);
			}
            
            
        }
        catch (Exception e) {
			e.printStackTrace();
	}
                        
        return null;
    }

    public int TaoHD(HoaDon hd) {
        try {
			
			String sql="insert into hoadon values(?,?,?,?,?) " ;
			PreparedStatement pre =conn.prepareStatement(sql);
			pre.setString(1, hd.getMaHD()+"");
			pre.setString(2, hd.getMaNV()+"");
			pre.setString(3, hd.getNgaylap()+"");
			pre.setString(4, hd.getTongTien()+"");
			pre.setString(5, hd.getIsDelete()+"");
			
			return pre.executeUpdate();
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1 ;
    }
    
    public int ThanhToan(String MaHD){
        try{
            String sql="update hoadon set IsDelete = 1 where MaHD=? " ;
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setString(1,MaHD+"");
            return pre.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
	
}
