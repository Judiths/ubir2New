package test;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

public class JDBCTest {
    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String password = "zer0";
        String userName = "root";
        String url = "jdbc:mysql://localhost:3306/ubir2" ;
       // String sql = "select * from work";
 
        try {
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(url, userName, password);		
            System.out.println("hahah");	//连接数据库成功
            // 数据库访问
           // work test = new work();
            
            	conn.close();
            System.out.print("closed");
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
    }
 
}
