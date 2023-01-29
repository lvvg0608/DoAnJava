package Controller;

import java.sql.*;

public class ConnectionDB {
	public static Connection conn;
	public static PreparedStatement pst;
	public ConnectionDB()
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL ="jdbc:sqlserver://LAPTOP-1CLBPMLG\\LVVG:1433;databaseName=LinhKienMayTinh;integratedSecurity=true";
			conn = DriverManager.getConnection(connectionURL, "sa", "123456");			
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Loi");
		}
		catch(SQLException ex)
		{
			System.out.println("Loi");
		}
		
		
	}
	
	public static ResultSet ThucThiCauLenh(String caulenhsql)
	{
		try {
			Statement stm = conn.createStatement();
			return stm.executeQuery(caulenhsql);
			
		}
		catch(SQLException ex)
		{
			System.out.println("Loi");
		}
		return null;
	}
	
	public static PreparedStatement ThucThi(String caulenh)
	{
		try {
			PreparedStatement pst = conn.prepareStatement(caulenh);
			return pst;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
