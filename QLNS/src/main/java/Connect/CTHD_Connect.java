
package Connect;

import Model.CTHD;
import java.sql.PreparedStatement;

/**
 *
 * @author HUYPM
 */
public class CTHD_Connect extends Connect_sqlServer{
    public int ThemCT(CTHD cthd) {
        try {	
            String sql="insert into CTHD values(?,?,?,?,?,?) " ;
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setString(1, cthd.getMaHD()+"");
            pre.setString(2, cthd.getMaSach()+"");
            pre.setString(3, cthd.getDonGia()+"");
            pre.setString(4, cthd.getSoLuong()+"");
            pre.setString(5, cthd.getThanhTien()+"");
            pre.setString(6, cthd.getIsDelete()+"");

            return pre.executeUpdate();	
            } 
        catch (Exception e) {
                    e.printStackTrace();
        }
        return -1 ;
    }
}
