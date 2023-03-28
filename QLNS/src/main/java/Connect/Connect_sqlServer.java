package Connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect_sqlServer {
	protected Connection conn = null ;
	
	public Connect_sqlServer() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//String connectionUrl="jdbc:sqlserver://"+"LAPTOP-C560D797\\dat"+":1433;databaseName="+"dbQLNS"+";integratedSecurity=true;";
                        String connectionUrl="jdbc:sqlserver://"+"LAPTOP-C560D797\\dat"+":1433;databaseName="+"dbQLNS"+";user=sa;password=<your-pass>;";
			conn= DriverManager.getConnection(connectionUrl);
		            } 
		
		    catch (Exception e) {
		    	
		                        e.printStackTrace();
		            }	
	}
	
}
