package cc.openhome;

import static java.lang.System.out;

import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.MinguoDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class HowOld2 {

	public static void main(String[] args) {
		out.print("輸入出生年月日(yyyy-mm-dd):");
		LocalDate birth = LocalDate.parse(new Scanner(System.in).nextLine()); // .parse，從文本字符串（例如2007-12-03）獲取LocalDate的實例。
		LocalDate now = LocalDate.now(); // 取得本機當前時區日期
		Period period = Period.between(birth, now);
		// Period，ISO-8601日曆系統中的日期時間，例如“2年3個月4天”。
		// .between，獲得由兩個日期之間的年數，月份和日期組成的期間。
//		out.printf("你活了%d年%d月%d日%n", period.getYears(), period.getMonths(), period.getDays());
		
//		out.printf("歲數:%d%n", ChronoUnit.YEARS.between(birth, now));
//		out.printf("天數:%d%n", ChronoUnit.DAYS.between(birth, now));
		
		// MinguoChronology就是中華民國年曆，台灣通行的年曆系統
		MinguoDate mingoBirth = MinguoDate.from(birth); // .from 從時間對象獲取MinguoDate。
		out.println(mingoBirth);
		
		
	}
}

// <Standard Calendar> https://docs.oracle.com/javase/tutorial/datetime/iso/
