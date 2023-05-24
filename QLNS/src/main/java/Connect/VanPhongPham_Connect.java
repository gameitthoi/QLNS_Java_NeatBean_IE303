/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connect;

import Model.NhaCungCap_VPP;
import Model.VPP;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class VanPhongPham_Connect extends Connect_sqlServer{
    public ArrayList<VPP> layToanBoVPP()
    {
        ArrayList<VPP> dsVPP = new ArrayList<VPP>() ;
        try {
            String sql ="select * from VPP" ;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                VPP vpp = new VPP();
                vpp.setMaVPP(result.getString(1));
                vpp.setTenVPP(result.getString(2));
                vpp.setMaNCC(result.getString(3));
                vpp.setGiaBan(result.getDouble(4));

                vpp.setDanhMuc(result.getString(5));
                vpp.setSoLuong(result.getInt(6));
                vpp.setDiscount(result.getInt(7));
               
                dsVPP.add(vpp);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return dsVPP;
    }
}
