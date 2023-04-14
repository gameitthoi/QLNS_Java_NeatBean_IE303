package Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.TaiKhoan;

public class login_sql extends Connect_sqlServer{
	
    public TaiKhoan login (String user , String pass){
        String sql = "select * from TAIKHOAN where username=? and password=?" ;
        TaiKhoan tk = null;
        try {
            PreparedStatement pre  = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet result = pre.executeQuery();
            if(result.next()){
            tk= new TaiKhoan();
            tk.setMaTK(result.getString(1));
            tk.setUserName(result.getString(2));
            tk.setPassWord(result.getString(3));
            tk.setMaNV(result.getString(4));
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk ;
    }

}
