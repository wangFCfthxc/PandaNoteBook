package demo;

public class assertDemo {
	
	// http://jhengjyun.blogspot.tw/2010/06/java-asser.html
	// https://openhome.cc/Gossip/JavaGossip-V1/Assertion.htm
	// https://www.javaworld.com.tw/jute/post/view?bid=17&id=9019&sty=3&age=0&tpg=1&ppg=1
	// 編譯方式不同
	/*
	 * (1) 編譯指令 => javac -source 1.4 XXX.java
	 * (2) 執行指令 => java -ea XXX
	 * (3) 關閉指令 => java -da XXX
	 */

	public static void main(String[] args) {
		int score = -10;
		assert (score > 0) : "成績錯誤!score = " + score;
		if(score >= 60){
			System.out.println("及格");
		}else{
			System.out.println("不及格");
		}

	}

}
