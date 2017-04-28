package cc.openhome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.lang.System.out;

public class ConnectionDemo {
	final static String className = "com.mysql.jdbc.Driver";
	final static String URL = "jdbc:mysql://localhost/";
	final static String DATABASE = "demo";
	final static String USER = "root";
	final static String PASS = "root";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(className);	// 載入驅動程式
		try(
		Connection conn = DriverManager.getConnection(URL+DATABASE, USER, PASS)){
			out.printf("已%s資料庫連線%n", conn.isClosed() ? "關閉" : "開啟");
		}
	}
}
