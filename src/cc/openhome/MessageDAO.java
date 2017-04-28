package cc.openhome;

import java.sql.*;
import java.util.*;

public class MessageDAO {
	private String url;
	private String user;
	private String passwd;

	public MessageDAO(String url, String user, String passwd) {
		this.url = url;
		this.user = user;
		this.passwd = passwd;
	}

	public void add(Message message) { // 新增留言方法
		try (Connection conn = DriverManager.getConnection(url, user, passwd); // 取得Connection物件
				Statement statement = conn.createStatement()) { // 建立Statement物件
			String sql = String.format("INSERT INTO t_message(name, email, msg) VALUES ('%s', '%s', '%s')",
					message.getName(), message.getEmail(), message.getMsg()); // SQL
			statement.executeUpdate(sql); // 執行SQL語句
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public List<Message> get() { // 從資料庫查詢所有留言的方法
		List<Message> messages = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, user, passwd);
				Statement statement = conn.createStatement()) {
			ResultSet result = statement.executeQuery("SELECT * FROM t_message");
			while (result.next()) {
				Message message = toMessage(result);
				messages.add(message);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return messages;

	}

	private Message toMessage(ResultSet result) throws SQLException{
		Message message = new Message();
        message.setId(result.getLong(1));
        message.setName(result.getString(2));
        message.setEmail(result.getString(3));
        message.setMsg(result.getString(4));
		return message;
	}

}
