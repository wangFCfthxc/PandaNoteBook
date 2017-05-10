package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sort {

	private static Scanner input;

	public static void main(String[] args) {
		input = new Scanner(System.in);
		int a, b, c, d, e, f, g;
		System.out.print("請輸入整數a:");
		a = input.nextInt();
		System.out.print("請輸入整數b:");
		b = input.nextInt();
		System.out.print("請輸入整數c:");
		c = input.nextInt();
		System.out.print("請輸入整數d:");
		d = input.nextInt();
		System.out.print("請輸入整數e:");
		e = input.nextInt();
		System.out.print("請輸入整數f:");
		f = input.nextInt();
		System.out.print("請輸入整數g:");
		g = input.nextInt();

		int score[] = { a, b, c, d, e, f, g };
		for (int i = 0; i < score.length; i++) {
			if (score[i] > 60) {
				System.out.println("大於60的整數:" + score[i]);
			} else if (score[i] < 50) {
				System.out.println("小於50的整數:" + score[i]);
			}
		}

		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(a);
		arrayList.add(b);
		arrayList.add(c);
		arrayList.add(d);
		arrayList.add(e);
		arrayList.add(f);
		arrayList.add(g);
		Collections.sort(arrayList);
		System.out.println("結果:" + arrayList);
		
		for(int num : arrayList){
			if(num > 60){
				System.out.println("大於60的整數:" + num);
			}else if(num<50){
				System.out.println("小於50的整數:" + num);
			}
		}
	}
}
