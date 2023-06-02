package Connect;

import Model.NXB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class NXB_Connect extends Connect_sqlServer
{
	//lấy toàn bộ nhà xuất bản 
	public ArrayList<NXB> layToanBoNhaXuatBan()
	{
            ArrayList<NXB> dsnxb = new ArrayList<NXB>() ;
            try {
                String sql = "select * from NXB" ;
                PreparedStatement pre = conn.prepareStatement(sql);
                ResultSet result = pre.executeQuery();
                while(result.next()){
                    NXB nxb = new NXB();
                    nxb.setMaNXB(result.getString(1));
                    nxb.setTenNXB(result.getString(2));
                    nxb.setSDT(result.getString(3));
                    nxb.setDiaChi(result.getString(4));
                    nxb.setEmail(result.getString(5));
                    dsnxb.add(nxb);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return dsnxb;
	}
	// lấy toàn bộ nhà xuất bản theo tên nhà xuất bản
	public ArrayList<NXB> layNhaXuatBanTheoTen(String tennxb)
	{
            ArrayList<NXB> dsnxb = new ArrayList<NXB>() ;		
            try {
                String sql ="select * from NXB where TenNXB like ?" ;
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, "%"+tennxb+"%");
                ResultSet result  = pre.executeQuery();
                while(result.next()){
                    NXB nxb = new NXB();
                    nxb.setMaNXB(result.getString(1));
                    nxb.setTenNXB(result.getString(2));
                    nxb.setSDT(result.getString(3));
                    nxb.setDiaChi(result.getString(4));
                    nxb.setEmail(result.getString(5));
                    dsnxb.add(nxb);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return dsnxb;
	}
        
        
	// Hàm thêm mới nxb
	public int ThemMoiNXB(NXB nxb)
	{
            try {
                String sql="insert into NXB values(?,?,?,?,?) " ;
                PreparedStatement pre =conn.prepareStatement(sql);
                pre.setString(1, nxb.getMaNXB()+"");
                pre.setString(2, nxb.getTenNXB()+"");
                pre.setString(3, nxb.getSDT()+"");
                pre.setString(4, nxb.getDiaChi()+"");
                pre.setString(5, nxb.getEmail()+"");

                return pre.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1 ;
	}
        public boolean kiemTraTonTai(String manv)
        {
        NXB nxb = new NXB();
        try {
            String sql ="select * from NXB where MaNXB=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, manv);
            ResultSet result = pre.executeQuery();
            while (result.next()) return true ;
        } catch (Exception e) {
                e.printStackTrace();
        }

        return false ;
        }
	//hàm xóa 1 NXB
	public int XoaNXB(String manxb)
	{
            try {
                String sql ="delete from NXB where MaNXB=?";
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, manxb);
                return pre.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return -1;
	}
	//cap nhật chỉnh sửa nhà xuất bản
	public int updateNXB (NXB nxb)
	{
            try {
                String sql = "update NXB set TenNXB=? ,SDT=? ,DiaChi=? ,Email=? where MaNXB=?" ;
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, nxb.getTenNXB());
                pre.setString(2, nxb.getSDT());
                pre.setString(3, nxb.getDiaChi());
                pre.setString(4, nxb.getEmail());
                pre.setString(5, nxb.getMaNXB());
                return pre.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1 ;
	}
        
        public ArrayList<NXB> TimTenNXB(String key){
            ArrayList<NXB> dsnxb = new ArrayList<NXB>();
            try{
                String sql ="select * from NXB where MaNXB like ? or TenNXB like ?" ;
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, "%"+key+"%");                
                pre.setString(2, "%"+key+"%");
                ResultSet result = pre.executeQuery();
                while (result.next()) {
                    NXB nxb = new NXB();
                    nxb.setMaNXB(result.getString(1));
                    nxb.setTenNXB(result.getString(2));                    
                    nxb.setSDT(result.getString(3));
                    nxb.setDiaChi(result.getString(4));
                    nxb.setEmail(result.getString(5));
                    dsnxb.add(nxb);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return dsnxb;
    }
        public NXB TimTenNXBCheckBox(String tenNXB){
        NXB nxb = new NXB();
        try{
            String sql ="select top 1 * from NXB where MaNXB=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, tenNXB);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                nxb.setMaNXB(result.getString(1));
                nxb.setTenNXB(result.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return nxb;
    }
}
	

