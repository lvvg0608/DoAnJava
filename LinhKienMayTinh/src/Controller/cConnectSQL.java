package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cConnectSQL {

	private static cConnectSQL instance = new cConnectSQL();

    public static Connection connection = null;

    public static cConnectSQL getInstance() {
        return instance;
    }
	
	
	public static void main(String[] args) throws SQLException {
		try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL ="jdbc:sqlserver://LAPTOP-1CLBPMLG\\LVVG:1433;databaseName=LinhKienMayTinh;integratedSecurity=true";
            connection = DriverManager.getConnection(connectionURL, "sa", "123456");
            System.out.print("Connection Successful!!!");
        } catch (ClassNotFoundException e) {
            System.out.print("Connection Failed!!");
            System.err.println(e.getMessage() + "/n" + e.getClass() + "/n" + e.getCause());
        }
	}
	
	public static void disconnect() {
        if(connection != null) {
            try {
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static Connection getConnection() {
        return connection;
    }
	

}
