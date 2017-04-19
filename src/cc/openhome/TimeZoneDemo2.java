package cc.openhome;

import static java.lang.System.out;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeZoneDemo2 {

	public static void main(String[] args) {
		TimeZone taipeiTz = TimeZone.getTimeZone("Asia/Taipei"); // 取得台灣時區
		Calendar calendar1 = Calendar.getInstance(taipeiTz); // 台灣時區的時間
		showTime(calendar1); // 用showTime方法印出calendar1

		TimeZone copenhagenTz = TimeZone.getTimeZone("Europe/Copenhagen");// 取得中歐時區
		Calendar calendar2 = Calendar.getInstance(copenhagenTz);// 中歐時區的時間
		showTime(calendar2); // 用showTime方法印出calendar2

	}

	static void showTime(Calendar calendar) { // showTime方法
		out.println(calendar.getTimeZone().getDisplayName()); // 取得該時區名稱
		out.printf("%d:%d%n", calendar.get(calendar.HOUR), // 小時
				calendar.get(calendar.MINUTE)); // 分鐘
	}

}
