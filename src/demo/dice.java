package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class dice {
	static String choseGame; // 選擇遊戲
	static ArrayList<Integer> compareSize = new ArrayList<Integer>();

	public static void main(String[] args) {
		do { // 重複啟動遊戲
			doGame();
		} while (choseGame.equals("Y")); // 輸入"Y"時重複啟動
	}

	// 啟動遊戲
	private static void doGame() {
		compareSize.clear();	// 比較用的list每次開始清空
		System.out.println("是否開始遊戲Y/N?");
		Scanner in = new Scanner(System.in);
		choseGame = in.next();
		if (choseGame.equals("Y")) {
			System.out.println("開始");	
			doDice(1);
			doDice(2);
			doDice(3);
			doDice(4);
			// 比大小
			Collections.sort(compareSize);
			System.out.println(compareSize);
			if(compareSize.get(2) == compareSize.get(3)){
				System.out.println("點數" + compareSize.get(3) + "平手");
			}else{
				System.out.println("點數" + compareSize.get(3) + "最大");
			}
		} else if (choseGame.equals("N")) {
			System.out.println("結束");
		} else {
			System.out.println("請輸入Y或N");
			doGame();
		}
	}

	// 擲骰子
	private static void doDice(int player) {
		int num;
		int addPoint = 0;
		
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		
		
//		int a=5;					// 一色測試	
//		for(int i=0; i < 4; i++){
//			arrayList.add(a);
//		}

		Random point = new Random();
		for (int i = 0; i < 4; i++) {
			num = point.nextInt(6) + 1;
			arrayList.add(num);
		}
		// System.out.println(arrayList.get(0) + "," + arrayList.get(1) + "," +
		// arrayList.get(2) + "," + arrayList.get(3));

		// 是否計點
		if (arrayList.get(0) != arrayList.get(1) && arrayList.get(0) != arrayList.get(2)
				&& arrayList.get(0) != arrayList.get(3) && arrayList.get(1) != arrayList.get(2)
				&& arrayList.get(1) != arrayList.get(3) && arrayList.get(2) != arrayList.get(3)) { // 4個數字不一樣就重骰
			doDice(player); // 重骰
		} else if (arrayList.get(0) == arrayList.get(1) && arrayList.get(1) == arrayList.get(2)
				&& arrayList.get(2) == arrayList.get(3)) { // 一色
			addPoint = arrayList.get(0) + 50;
			compareSize.add(addPoint);
			System.out.println(arrayList.get(0) + "," + arrayList.get(1) + "," + arrayList.get(2) + ","
					+ arrayList.get(3) + "  點數:" + addPoint + "一色");
		} else if (arrayList.get(0) == arrayList.get(1) || arrayList.get(0) == arrayList.get(2)
				|| arrayList.get(0) == arrayList.get(3) || arrayList.get(1) == arrayList.get(2)
				|| arrayList.get(1) == arrayList.get(3) || arrayList.get(2) == arrayList.get(3)) { // 數字兩兩相等時,另外2個相加

			if (arrayList.get(0) == arrayList.get(1)) {
				if (arrayList.get(2) == arrayList.get(3)) {
					if (arrayList.get(0) > arrayList.get(2)) {
						addPoint = arrayList.get(0) + arrayList.get(1);
					} else {
						addPoint = arrayList.get(2) + arrayList.get(3);
					}
				} else {
					addPoint = arrayList.get(2) + arrayList.get(3);
				}
			}

			if (arrayList.get(0) == arrayList.get(2)) {
				if (arrayList.get(1) == arrayList.get(3)) {
					if (arrayList.get(0) > arrayList.get(1)) {
						addPoint = arrayList.get(0) + arrayList.get(2);
					} else {
						addPoint = arrayList.get(1) + arrayList.get(3);
					}
				} else {
					addPoint = arrayList.get(1) + arrayList.get(3);
				}
			}

			if (arrayList.get(0) == arrayList.get(3)) {
				if (arrayList.get(1) == arrayList.get(2)) {
					if (arrayList.get(0) > arrayList.get(1)) {
						addPoint = arrayList.get(0) + arrayList.get(3);
					} else {
						addPoint = arrayList.get(1) + arrayList.get(2);
					}
				} else {
					addPoint = arrayList.get(1) + arrayList.get(2);
				}
			}

			if (arrayList.get(1) == arrayList.get(2)) {
				if (arrayList.get(0) == arrayList.get(3)) {
					if (arrayList.get(1) > arrayList.get(0)) {
						addPoint = arrayList.get(1) + arrayList.get(2);
					} else {
						addPoint = arrayList.get(0) + arrayList.get(3);
					}
				} else {
					addPoint = arrayList.get(0) + arrayList.get(3);
				}
			}

			if (arrayList.get(1) == arrayList.get(3)) {
				if (arrayList.get(0) == arrayList.get(2)) {
					if (arrayList.get(1) > arrayList.get(0)) {
						addPoint = arrayList.get(1) + arrayList.get(3);
					} else {
						addPoint = arrayList.get(0) + arrayList.get(2);
					}
				} else {
					addPoint = arrayList.get(0) + arrayList.get(2);
				}
			}

			if (arrayList.get(2) == arrayList.get(3)) {
				if (arrayList.get(0) == arrayList.get(1)) {
					if (arrayList.get(2) > arrayList.get(1)) {
						addPoint = arrayList.get(2) + arrayList.get(3);
					} else {
						addPoint = arrayList.get(0) + arrayList.get(1);
					}
				} else {
					addPoint = arrayList.get(0) + arrayList.get(1);
				}
			}
			compareSize.add(addPoint);
			System.out.println("玩家"+ player + ":" + arrayList.get(0) + "," + arrayList.get(1) + "," + arrayList.get(2) + ","
					+ arrayList.get(3) + "  點數:" + addPoint);
		}
		// System.out.println(arrayList.get(0) + "," + arrayList.get(1) + "," +
		// arrayList.get(2) + "," + arrayList.get(3));

	}

}
