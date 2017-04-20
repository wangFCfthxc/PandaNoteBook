package cc.openhome;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import static java.lang.System.out;

public class ZoneDateTimeDemo {

	public static void main(String[] args) {
		LocalTime localTime = LocalTime.of(0, 0, 0);
		LocalDate localDate = LocalDate.of(1975, 4, 1);
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, ZoneId.of("Asia/Taipei"));
		
		out.println(zonedDateTime);
		out.println(zonedDateTime.toEpochSecond()); // 從1970從1970-01-01T00：00：00Z的時代開始的秒數
		out.println(zonedDateTime.toInstant().toEpochMilli()); // 自1970-01-01T00：00：00Z以來的毫秒數

	}

}
