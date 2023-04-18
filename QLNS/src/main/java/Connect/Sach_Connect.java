package Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.NXB;
import Model.Sach;
import Model.TonKho;
import java.sql.CallableStatement;
import java.util.Calendar;

public class Sach_Connect extends Connect_sqlServer{
	
    public ArrayList<Sach> layToanBoSach()
    {
        ArrayList<Sach> dss = new ArrayList<Sach>() ;
        try {
            String sql ="select * from SACH" ;
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
            String sql ="select * from SACH where MaNXB=? and TenSach like ? " ;
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
            String sql = "select * from SACH where TenSach like ? " ;
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

                dss3.add(s);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dss3;
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
                sql ="SELECT TOP " +top +" A.MaSach,TenSach, SUM(A.SoLuong) as SL FROM (SELECT SACH.MaSach,TenSach,CTHD.SoLuong FROM SACH,HOADON,CTHD WHERE SACH.MaSach=CTHD.MaSach AND CTHD.MaHD=HOADON.MaHD AND YEAR(NgayLap)=? AND HOADON.ThanhCong=1 AND HOADON.NhapSach=0) AS A GROUP BY MaSach, TenSach ORDER BY SL DESC ";
            else 
                sql ="SELECT TOP " +top +" A.MaSach,TenSach, SUM(A.SoLuong) as SL FROM (SELECT SACH.MaSach,TenSach,CTHD.SoLuong FROM SACH,HOADON,CTHD WHERE SACH.MaSach=CTHD.MaSach AND CTHD.MaHD=HOADON.MaHD AND MONTH(NgayLap)=? AND YEAR(NgayLap)=? AND HOADON.ThanhCong=1 AND HOADON.NhapSach=0 ) AS A GROUP BY MaSach, TenSach ORDER BY SL DESC";

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
                tk.setMaSach(result.getString(1));
                tk.setTenSach(result.getString(2));
                tk.setTonDau(result.getInt(3));
                tk.setNhap(result.getInt(4));
                tk.setXuat(result.getInt(5));
                tk.setTonCuoi(result.getInt(6));

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
            if(thang==0)
                sql ="SELECT S.MaSach, S.TenSach, SUM(CASE WHEN H.NhapSach=1 THEN C.SoLuong ELSE 0 END) AS NHAP, SUM(CASE WHEN H.NhapSach=0 THEN C.SoLuong ELSE 0 END) AS XUAT FROM SACH AS S, CTHD AS C, HOADON AS H WHERE S.MaSach=C.MaSach AND C.MaHD=H.MaHD AND YEAR(H.NgayLap)="+nam+" AND H.ThanhCong=1 GROUP BY S.MaSach, S.TenSach";
            else
                sql ="SELECT S.MaSach, S.TenSach, SUM(CASE WHEN H.NhapSach=1 THEN C.SoLuong ELSE 0 END) AS NHAP, SUM(CASE WHEN H.NhapSach=0 THEN C.SoLuong ELSE 0 END) AS XUAT FROM SACH AS S, CTHD AS C, HOADON AS H WHERE S.MaSach=C.MaSach AND C.MaHD=H.MaHD AND YEAR(H.NgayLap)="+nam+" AND MONTH(NgayLap)="+thang+" AND H.ThanhCong=1 GROUP BY S.MaSach, S.TenSach";
            PreparedStatement pre = conn.prepareStatement(sql);	
            ResultSet result = pre.executeQuery();
            while(result.next()){
                TonKho tk = new TonKho();
                tk.setMaSach(result.getString(1));
                tk.setTenSach(result.getString(2));
                tk.setNhap(result.getInt(3));
                tk.setXuat(result.getInt(4));

                dsTK.add(tk);  
            }	
        } catch (Exception e) {
                e.printStackTrace();
        }
        return dsTK;
    }
    // ham them moi vao csdl
    public int  themSachMoi(Sach s)
    {
        try {
            String sql = "insert into SACH values (?,?,?,?,?,?,?)"  ;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, s.getMaSach());
            pre.setString(2, s.getTenSach());
            pre.setString(3, s.getMaNXB());
            pre.setString(4, s.getTacGia());
            pre.setDouble(5, s.getGiaBan());
            pre.setString(6, s.getTheLoai());
            pre.setInt(7, s.getSoLuong());

             return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1 ;
    }
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
    public ArrayList<Sach> laySachConDuoiTon(int SL)
    {
        ArrayList<Sach> dssTon = new ArrayList<Sach>();
        try {
            String sql ="select SACH.MaSach , SACH.TenSach , SACH.SoLuong , NXB.TenNXB from SACH,NXB where SACH.MaNXB = NXB.MaNXB and SACH.SoLuong<?" ;
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
}
