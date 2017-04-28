package cc.openhome;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

public class MessageDAO3 {
	private DataSource datasouce;

	public MessageDAO3(DataSource datasouce) {
		this.datasouce = datasouce;
	}

	public void add(Message message) { // 新增留言方法
		try (Connection conn = datasouce.getConnection(); // 取得Connection物件
				PreparedStatement statement = conn
						.prepareStatement("INSERT INTO t_message(name, email, msg) VALUES (?,?,?)")) {
			statement.setString(1, message.getName());
			statement.setString(2, message.getEmail());
			statement.setString(3, message.getMsg());
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public List<Message> get() { // 從資料庫查詢所有留言的方法
		List<Message> messages = new ArrayList<>();
		try (Connection conn = datasouce.getConnection();
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

	private Message toMessage(ResultSet result) throws SQLException {
		Message message = new Message();
		message.setId(result.getLong(1));
		message.setName(result.getString(2));
		message.setEmail(result.getString(3));
		message.setMsg(result.getString(4));
		return message;
	}

}
