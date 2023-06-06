package Connect;

import Model.ChucVu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import Model.NXB;
import Model.Sach;
import Model.TonKho;
import java.sql.CallableStatement;
import java.util.Calendar;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Sach_Connect extends Connect_sqlServer{
	
    public ArrayList<Sach> layToanBoSach()
    {
        ArrayList<Sach> dss = new ArrayList<Sach>() ;
        try {
            String sql ="select MaSach, TenSach, TenNXB, TacGia, GiaBan, TheLoai, SoLuong, Discount  from SACH,NXB where SACH.MaNXB = NXB.MaNXB" ;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                Sach s = new Sach();
                s.setMaSach(result.getString(1));
                s.setTenSach(result.getString(2));
                s.setMaNXB(result.getString(3));
                s.setTacGia(result.getString(4));
                s.setGiaBan(result.getDouble(5));
                s.setTheLoai(result.getString(6));
                s.setSoLuong(result.getInt(7));
                s.setDiscount(result.getInt(8));
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
            String sql ="select MaSach, TenSach, TenNXB, TacGia, GiaBan, TheLoai, SoLuong, Discount from SACH,NXB where SACH.MaNXB = NXB.MaNXB and MaNXB=? and TenSach like ? " ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, manxb);
            pre.setString(2, "%"+ten+"%");

            ResultSet result = pre.executeQuery();
            while(result.next()){
                Sach s = new Sach();
                s.setMaSach(result.getString(1));
                s.setTenSach(result.getString(2));
                s.setMaNXB(result.getString(3));
                s.setTacGia(result.getString(4));
                s.setGiaBan(result.getDouble(5));
                s.setTheLoai(result.getString(6));
                s.setSoLuong(result.getInt(7));
                s.setDiscount(result.getInt(8));
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
            String sql = "select MaSach, TenSach, TenNXB, TacGia, GiaBan, TheLoai, SoLuong, Discount from SACH,NXB where SACH.MaNXB = NXB.MaNXB and TenSach like ? " ;
            PreparedStatement pre1 = conn.prepareStatement(sql);
            pre1.setString(1, "%"+maten+"%");
            ResultSet result = pre1.executeQuery();
            while (result.next()){
                Sach s = new Sach();
                s.setMaSach(result.getString(1));
                s.setTenSach(result.getString(2));
                s.setMaNXB(result.getString(3));
                s.setTacGia(result.getString(4));
                s.setGiaBan(result.getDouble(5));
                s.setTheLoai(result.getString(6));
                s.setSoLuong(result.getInt(7));
                s.setDiscount(result.getInt(8));
                dss3.add(s);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dss3;
    }
    
     public ArrayList<Sach> laySachTheoTenTacGia(String tenTacGia)
    {
        ArrayList<Sach> dss2 = new ArrayList<Sach>();

        try {
            String sql = "select MaSach, TenSach, TenNXB, TacGia, GiaBan, TheLoai, SoLuong, Discount from SACH,NXB where SACH.MaNXB = NXB.MaNXB and TacGia like ? " ;
            PreparedStatement pre1 = conn.prepareStatement(sql);
            pre1.setString(1, "%"+tenTacGia+"%");
            ResultSet result = pre1.executeQuery();
            while (result.next()){
                Sach s = new Sach();
                s.setMaSach(result.getString(1));
                s.setTenSach(result.getString(2));
                s.setMaNXB(result.getString(3));
                s.setTacGia(result.getString(4));
                s.setGiaBan(result.getDouble(5));
                s.setTheLoai(result.getString(6));
                s.setSoLuong(result.getInt(7));
                s.setDiscount(result.getInt(8));
                dss2.add(s);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dss2;
    }
     
     public ArrayList<Sach> laySachTheoMaTenVaTenTacGia(String maten, String tenTacGia)
    {
        ArrayList<Sach> dss4 = new ArrayList<Sach>();

        try {
            String sql = "select MaSach, TenSach, TenNXB, TacGia, GiaBan, TheLoai, SoLuong, Discount from SACH,NXB where SACH.MaNXB = NXB.MaNXB and TenSach like ? and TacGia like ?" ;
            PreparedStatement pre1 = conn.prepareStatement(sql);
            pre1.setString(1, "%"+maten+"%");
            pre1.setString(2, "%"+tenTacGia+"%");
            ResultSet result = pre1.executeQuery();
            while (result.next()){
                Sach s = new Sach();
                s.setMaSach(result.getString(1));
                s.setTenSach(result.getString(2));
                s.setMaNXB(result.getString(3));
                s.setTacGia(result.getString(4));
                s.setGiaBan(result.getDouble(5));
                s.setTheLoai(result.getString(6));
                s.setSoLuong(result.getInt(7));
                s.setDiscount(result.getInt(8));
                dss4.add(s);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dss4;
    }

    // lay doanh sach theo ma nha xuat ban
    public ArrayList<Sach> laySachTheoNXB(String keyWord )
    {
            ArrayList<Sach> dss2 = new ArrayList<Sach>();
            try {
                String sql =" select * from SACH where MaNXB=? ";
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, keyWord);			
                ResultSet result = pre.executeQuery();
                while(result.next()){
                    Sach s = new Sach();
                    s.setMaSach(result.getString(1));
                    s.setTenSach(result.getString(2));
                    s.setMaNXB(result.getString(3));
                    s.setTacGia(result.getString(4));
                    s.setGiaBan(result.getDouble(5));
                    s.setTheLoai(result.getString(6));
                    s.setSoLuong(result.getInt(7));
                    s.setDiscount(result.getInt(8));
                    dss2.add(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return dss2;
    }

    public ArrayList<Sach> laySachBanChay(String top, String thang, String nam ){
        ArrayList<Sach> dsSBC = new ArrayList<Sach>();
        String sql = "";
        try {
            if ("0".equals(thang))  
                sql ="SELECT TOP " +top +" A.MaSach,TenSach, SUM(A.SoLuong) as SL FROM (SELECT SACH.MaSach,TenSach,CTHD.SoLuong FROM SACH,HOADON,CTHD WHERE SACH.MaSach=CTHD.MaSP AND CTHD.MaHD=HOADON.MaHD AND YEAR(NgayLap)=? AND HOADON.ThanhCong=1 AND HOADON.NhapSach=0) AS A GROUP BY MaSach, TenSach ORDER BY SL DESC ";
            else 
                sql ="SELECT TOP " +top +" A.MaSach,TenSach, SUM(A.SoLuong) as SL FROM (SELECT SACH.MaSach,TenSach,CTHD.SoLuong FROM SACH,HOADON,CTHD WHERE SACH.MaSach=CTHD.MaSP AND CTHD.MaHD=HOADON.MaHD AND MONTH(NgayLap)=? AND YEAR(NgayLap)=? AND HOADON.ThanhCong=1 AND HOADON.NhapSach=0 ) AS A GROUP BY MaSach, TenSach ORDER BY SL DESC";

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
                Sach s = new Sach();
                s.setMaSach(result.getString(1));
                s.setTenSach(result.getString(2));
                s.setSoLuong(result.getInt(3));
                dsSBC.add(s);
            }


        } catch (Exception e) {
                e.printStackTrace();
        }
        return dsSBC;
    }
    
    

    public ArrayList<TonKho> laySachTonKho(int thang, int nam ){
        ArrayList<TonKho> dsTK = new ArrayList<TonKho>();
        String sql = "";
        Calendar cal = Calendar.getInstance();
        try {
            sql ="{call GetTonKhoThangHienTai("+nam+","+thang+")}";
            CallableStatement  stmt  = conn.prepareCall(sql);		
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                TonKho tk = new TonKho();
                //khi dòng hiện tại là sách
                if(result.getString(1)!=null){
                    tk.setMaSP(result.getString(1));
                    tk.setTenSP(result.getString(2));
                    tk.setTonDau(result.getInt(5));
                    tk.setNhap(result.getInt(7));
                    tk.setXuat(result.getInt(8));
                    tk.setTonCuoi(result.getInt(9));
                }
                //khi dòng hiện tại là văn phòng phẩm
                else{
                    tk.setMaSP(result.getString(3));
                    tk.setTenSP(result.getString(4));
                    tk.setTonDau(result.getInt(6));
                    tk.setNhap(result.getInt(7));
                    tk.setXuat(result.getInt(8));
                    tk.setTonCuoi(result.getInt(10));
                }
                dsTK.add(tk);  
            }	
        } catch (Exception e) {
                e.printStackTrace();
        }
        return dsTK;
    }

    public ArrayList<TonKho> layThongTinNhapXuat(int thang, int nam ){
        ArrayList<TonKho> dsTK = new ArrayList<TonKho>();
        String sql = "";
        try {
            sql ="select T.MaSP, S.TenSach, V.TenVPP, TonDau, Nhap, Xuat, TonCuoi from TONKHO as T left join SACH as S on T.MaSP = S.MaSach left join VPP as V on T.MaSP = V.MaVPP where Thang="+thang+" and Nam="+nam+"";
            PreparedStatement pre = conn.prepareStatement(sql);	
            ResultSet result = pre.executeQuery();
            while(result.next()){
                TonKho tk = new TonKho();
                
                tk.setMaSP(result.getString(1));
                if(result.getString(2)!=null)
                    tk.setTenSP(result.getString(2));
                if(result.getString(3)!=null)
                    tk.setTenSP(result.getString(3));
                tk.setTonDau(result.getInt(4));
                tk.setNhap(result.getInt(5));
                tk.setXuat(result.getInt(6));
                tk.setTonCuoi(result.getInt(7));
                dsTK.add(tk);  
            }	
        } catch (Exception e) {
                e.printStackTrace();
        }
        return dsTK;
    }
    
    public int luuDuLieuVaoTonKho(JTable jTable1, int thang, int nam){
        try{
            int matk = 0;
            String sql = "SELECT TOP 1 MaTK FROM TONKHO ORDER BY MaTK DESC";
            PreparedStatement pre = conn.prepareStatement(sql);	
            ResultSet result = pre.executeQuery();
            while(result.next()){
                matk = Integer.parseInt(result.getString(1).substring(2))+1;
            }
            sql = "INSERT INTO TONKHO(MaTK, MaSP, TonDau, Nhap, Xuat, TonCuoi, Thang, Nam) VALUES ( ?, ?, ?, ?, ?, ?, "+thang+", "+nam+")";
            for (int row = 0; row < jTable1.getRowCount(); row++) {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, "TK"+matk);
                statement.setString(2, jTable1.getValueAt(row, 0).toString());
                statement.setInt(3, Integer.parseInt(jTable1.getValueAt(row, 2).toString()));
                statement.setInt(4, Integer.parseInt(jTable1.getValueAt(row, 3).toString()));
                statement.setInt(5, Integer.parseInt(jTable1.getValueAt(row, 4).toString()));
                statement.setInt(6, Integer.parseInt(jTable1.getValueAt(row, 5).toString()));
                matk++;
                statement.executeUpdate();
            }
            return 1;
        }
        catch (Exception e) {
                e.printStackTrace();
        }
        return 0;
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
            pre.setInt(8, s.getDiscount());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1 ;
    }
    
//    public NXB TimTenNXB(String nxb){
//        NXB cv = new NXB();
//        try{
//            String sql ="select top 1 * from NXB where TenNXB=?" ;
//            PreparedStatement pre = conn.prepareStatement(sql);
//            pre.setString(1, nxb);
//            ResultSet result = pre.executeQuery();
//            while (result.next()) {
//                cv.setMaNXB(result.getString(1));
//                cv.setTenNXB(result.getString(2));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return cv;
//    }
    // ham xoa 
    //sửa SACH set -> SACH 
    public int XoaSach(String maSach)
    {
        try {
            String sql ="delete SACH where MaSach=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,maSach);

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
                String sql = "update SACH set TenSach=? ,MaNXB=? ,TacGia=? ,GiaBan=? ,TheLoai=?,SoLuong=?, Discount=? where MaSach=? " ;
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, s.getTenSach());
                pre.setString(2, s.getMaNXB());
                pre.setString(3, s.getTacGia());
                pre.setDouble(4, s.getGiaBan());
                pre.setString(5, s.getTheLoai());
                pre.setInt(6, s.getSoLuong());
                pre.setInt(7, s.getDiscount());
                pre.setString(8, s.getMaSach());
                
                return pre.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return -1 ;
    }
    //Hàm update số lượng sách sau khi bán
    public int updateSL(String MaSach, int SL){
        try {
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
    
    //update all price
       public int updateAllPrice(String MaSach, double ex){
        try {
            String sql ="update SACH set GiaBan=? where MaSach=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDouble(1, ex);
            pre.setString(2,MaSach);

            return pre.executeUpdate();

            } catch (Exception e) {
                    e.printStackTrace();
            }

        return -1;
    }
    //Hàm Lấy danh sách sách có số lượng còn dưới 5
    public ArrayList<Sach> laySachConDuoiTon(int SL)
    {
        ArrayList<Sach> dssTon = new ArrayList<Sach>();
        try {
            String sql ="select SACH.MaSach , SACH.TenSach , SACH.SoLuong , NXB.TenNXB from SACH,NXB where SACH.MaNXB = NXB.MaNXB and SACH.SoLuong<=?" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, SL);
            ResultSet result = pre.executeQuery();
            while (result.next()){	
                Sach s = new Sach();				
                s.setMaSach(result.getString(1));
                s.setTenSach(result.getString(2));
                s.setMaNXB(result.getString(4));
                s.setSoLuong(result.getInt(3));				
                dssTon.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dssTon;
    }
    
   public DefaultPieDataset laySachTheoTheLoai(){
       DefaultPieDataset dataset = new DefaultPieDataset();
       try{
            String sql = "SELECT TheLoai, COUNT(MaSach) AS SoLuongSach FROM SACH GROUP BY TheLoai";
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
}
