package myservlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

	static String url = "jdbc:mysql://localhost:3306/book?user=root&password=1234"
			+"&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";

	static Connection getConn(){

		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
}
