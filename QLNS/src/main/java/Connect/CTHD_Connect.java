
package Connect;

import Model.CTHD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HUYPM
 */
public class CTHD_Connect extends Connect_sqlServer{
    public int ThemCT(CTHD cthd) {
        try {	
            String sql="insert into CTHD values(?,?,?,?,?) " ;
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setString(1, cthd.getMaHD()+"");
            pre.setString(2, cthd.getMaSP()+"");
            pre.setString(3, cthd.getDonGia()+"");
            pre.setString(4, cthd.getSoLuong()+"");
            pre.setString(5, cthd.getThanhTien()+"");

            return pre.executeUpdate();	
            } 
        catch (Exception e) {
                    e.printStackTrace();
        }
        return -1 ;
    }
    
    public DefaultTableModel layCTHDBangMaHD(String mahd){
        DefaultTableModel dtm_cthd = new DefaultTableModel();
        dtm_cthd.addColumn("Tên sản phẩm");
        dtm_cthd.addColumn("Đơn giá");
        dtm_cthd.addColumn("Số lượng");
        dtm_cthd.addColumn("Thành tiền");
        dtm_cthd.setRowCount(0);
        try {	
            String sql = "SELECT S.TenSach, V.TenVPP, C.SoLuong, C.DonGia, C.ThanhTien  FROM HOADON AS H LEFT JOIN CTHD AS C ON H.MaHD= C.MaHD LEFT JOIN SACH AS S ON C.MaSP=S.MaSach LEFT JOIN VPP AS V ON C.MaSP=V.MaVPP WHERE H.NhapSach=0 AND H.ThanhCong=1 AND H.MaHD = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, mahd);
            ResultSet result  = pre.executeQuery();
            while(result.next())
            {
                Vector<Object> vec = new Vector<Object>();
                if(result.getString(1)!=null)
                    vec.add(result.getString(1));
                if(result.getString(2)!=null)
                    vec.add(result.getString(2));
                vec.add(result.getString(3));
                vec.add(result.getString(4));
                vec.add(result.getString(5));
                dtm_cthd.addRow(vec);
            }
        } 
        catch (Exception e) {
                    e.printStackTrace();
        }
        return dtm_cthd;
    }
}
