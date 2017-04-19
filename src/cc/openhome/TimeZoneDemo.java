package cc.openhome;

import java.util.TimeZone;
import static java.lang.System.out;

public class TimeZoneDemo {

	public static void main(String[] args) {
		// 取得此主機默認時區
		TimeZone timeZone = TimeZone.getDefault();
		out.println(timeZone.getDisplayName()); // 此時區名稱
		out.println("時區ID:" + timeZone.getID()); // 此時區ID
		out.println("日光節約時數:" + timeZone.getDSTSavings()); // 節省時間（毫秒）
		out.println("UTC偏移毫秒數:" + timeZone.getRawOffset());
	}
}
