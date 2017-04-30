package demo;

import java.util.Scanner;

public class var_change {
	
	private static Scanner input;

	public static void main(String[] args) {
		// 不使用暫存變數交換，a變數、b變數
		input = new Scanner(System.in);	
		int a, b;
		System.out.print("請輸入一個整數a:");
		a = input.nextInt();
		System.out.print("請輸入一個整數b:");
		b = input.nextInt();

		a = a + b - (b = a);
		System.out.println("現在a=" + a);
		System.out.println("現在b=" + b);
	}

}
