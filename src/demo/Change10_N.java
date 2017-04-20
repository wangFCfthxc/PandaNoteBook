package demo;

import java.util.Scanner;

public class Change10_N {

	public static void main(String[] args) {
		System.out.println("10進位→任何進位\n//-----------------------");
		Scanner input = new Scanner(System.in);

		System.out.print("輸入進位值：");
		int x = input.nextInt(); // 除數

		System.out.print("輸入轉換的數字：");// 被除數（欲轉換的數字）
		int y = input.nextInt();

		int yu = 0;// 餘數
		String shang = "商";// 商
		String sum = "";// 最後顯示的結果
		String in = "";// 儲存用所有計算結果的字串
		int xx = String.valueOf(y).length();
		in = "10進位：" + y + "    →    " + x + "進位" + "\n\n";

		// 被除數 不等於0
		while (y != 0) {
			yu = y / x;// 餘數=被除數 / 除數
			shang = String.valueOf(yu - (yu - (y % x)));// 餘數
			in += String.format("%1$" + "" + xx + "d", y) + " ÷  " + x + "    =  "
					+ String.format("%1$" + "" + xx + "d", yu) + "  ----  " + shang + "\n";
			y = yu;// 讓下次迴圈的 被除數= 目前的 餘數
			sum = shu_zhuan_ying(shang) + sum;// 答案（注意【商】在前面）
		}

		in += "\n" + "答案：  " + sum;
		System.out.println(in);// 顯示計算結果
		System.gc();// 釋放記憶體

	}

	// 數字轉英文
	public static String shu_zhuan_ying(String h) {

		String[] s = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		String[] z = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34",
				"35" };

		for (int i = 0; i <= 35; i++) {
			if (h.equals(z[i])) {
				return s[i];
			}
		}
		return "(" + h + ")";// 如果都不是，則印出原來的數字
	}
}
