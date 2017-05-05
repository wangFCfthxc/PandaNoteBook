package demo;

import java.util.Scanner;

public class triangle {
	
	private static Scanner input;

	public static void main(String[] args) {	
		input = new Scanner(System.in);
		int a, b, c;
		System.out.print("請輸入三角形邊長a:");
		a = input.nextInt();
		System.out.print("請輸入三角形邊長b:");
		b = input.nextInt();
		System.out.print("請輸入三角形邊長c:");
		c = input.nextInt();
		
		if(a>0 && b>0 && c>0 && (a+b)>c && (b+c)>a && (a+c)>b){ // 邊長是正數,任兩邊之和大於第三邊
			if(a==b && b==c){ 
				System.out.println("正三角形"); // 三邊相等的三角形
			}else if((a*a + b*b)==c*c || (a*a + c*c)== b*b || (b*b + c*c)==a*a){
				System.out.println("是直角三角形"); // 任兩邊的平方和等於第三邊平方
			}else if((a==b && a!=c) || (b==c && b!=a) || (a==c && a!=b)){
				System.out.println("是等腰三角形"); // 任兩邊相等
			}else{
				System.out.println("是其他三角形");
			}
		}else{
			System.out.println("不是三角形");
		}
	}
}
