package advance;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

abstract public class JDBCTemplate<T> {
	private static String driverClass="com.mysql.jdbc.Driver";
	private static String jdbcURL="jdbc:mysql://localhost:3306/FindJob";	
	private static String user="root";
	private static String pwd="zer0";

	abstract public T execute() throws Exception;
	public static Connection getConnection()throws Exception {
			Class.forName(driverClass);
			Connection conn=(Connection) DriverManager.getConnection(jdbcURL, user, pwd);
		    return conn;
		
	}

}