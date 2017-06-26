package demo;

import java.util.Arrays;
import java.util.Scanner;


public class poker {
	static int[] poker = new int[52]; // poker[0] = 0 ~ poker[51] = 0
	static int[][] players = new int[4][13]; // 4家,每家13張
	static String choseGame; // 選擇遊戲

	public static void main(String[] args) {
		do {
			doGame(); // 重複啟動遊戲
		} while (choseGame.equals("Y")); // 輸入"Y"時重複啟動
	}
	
	private static void doGame(){
		System.out.println("是否開始遊戲Y/N?");
		Scanner in = new Scanner(System.in);
		choseGame = in.next();
		if (choseGame.equals("Y")) {
			System.out.println("開始");
			doShuffle(); // 洗牌
			doSendCard(); // 發牌
			showCard(); // 看牌+理牌
		}else if(choseGame.equals("N")){
			System.out.println("結束");
		}else{
			System.out.println("請輸入Y或N");
			doGame();
		}
	}
	
	// 看牌+理牌
	private static void showCard() {
		String[] suits = { "黑桃", "紅心", "方塊", "梅花" };
		String[] values = { "2 ", "A ", "K ", "Q ", "J ", "10", "9 ", "8 ", "7 ", "6 ", "5 ", "4 ", "3 " };
		for (int[] player : players) {
			Arrays.sort(player);	// 理牌排序
			for (int card : player) {
				System.out.print(suits[card / 13] + values[card % 13] + "  ");
			}
			System.out.println();	// 每家牌換行
		}
	}

	// 發牌
	private static void doSendCard() {
		// 按照[0][0].[1][0].[2][0].[3][0].[0][1]...下去分牌
		for (int i = 0; i < poker.length; i++) {
			players[i % 4][i / 4] = poker[i];
		}
		// for(int[] row :players){
		// for(int element : row){
		// System.out.println(element);
		// }
		// }
	}

	// 洗牌
	private static void doShuffle() {
		int temp;
		boolean isRepeat; // 是否重複
		for (int i = 0; i < poker.length; i++) { // 撲克牌迴圈
			do {
				temp = (int) (Math.random() * poker.length);
				// 檢查機制
				isRepeat = false;
				for (int j = 0; j < i; j++) {
					if (poker[j] == temp) {
						// 此時重複了
						isRepeat = true;
						break;
					}
				}
			} while (isRepeat);
			poker[i] = temp; // 不重複才存進poker[i]
			// System.out.println(poker[i]);
		}
	}

}
