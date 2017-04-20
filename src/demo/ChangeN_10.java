package demo;

import java.util.Scanner;

public class ChangeN_10 {
//	N進位轉十進位
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("7進位轉10進位 ex:七進位13 → 十進位10");
		String a = scn.next(); 
		System.out.println("七進位" + a + " → 十進位"+ Integer.valueOf(a,7).toString());
	}

}
