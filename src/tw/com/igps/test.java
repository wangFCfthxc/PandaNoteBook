package tw.com.igps;

import java.text.DecimalFormat;

public class test {

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("$#,##0.0000#");
		System.out.println(df.format(1234567.25645654));

	}
}
