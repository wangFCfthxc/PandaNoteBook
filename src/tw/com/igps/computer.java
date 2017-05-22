package tw.com.igps;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class computer extends JFrame {
	private JPanel contentPane;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnBackspace;
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnMultiplied;
	private JButton btnDivided;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton btnClear;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btnDot;
	private JButton btn0;
	private JButton btnEqual;
	private JTextField txtAnswer;
	int count2 = 0; // 計數器數字鍵用

	public static String str = "";
	public static double strToNum = 0;
	public static double No_result1 = 0;// 存第一次數值
	public static double No_result2 = 0;// 存第二次數值
	public static double temp = 0;// 暫存數
	public static double maxNum = 999999999999.0;// 最大數值
	public static double minNum = -999999999999.0;// 最小數值
	public static byte op;// 代表運算子
	public static boolean opFlag = false;// 代表運算子

	/**
	 * Create the frame.
	 */
	public computer() {
		try {
			setResizable(true); // 調適窗大小
			setTitle("JAVA計算機"); // 視窗計算機
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默認的關閉
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);

			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.NORTH);
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setLayout(new BorderLayout(0, 0));
			// 顯示區
			txtAnswer = new JTextField();
			txtAnswer.setHorizontalAlignment(SwingConstants.RIGHT); // 文字靠右
			txtAnswer.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel.add(txtAnswer, BorderLayout.CENTER);
			txtAnswer.setEditable(false); // 不能編輯打字
			txtAnswer.setText("0"); // 初始值0

			panel_1 = new JPanel();
			contentPane.add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new BorderLayout(0, 0));

			panel_2 = new JPanel();
			panel_1.add(panel_2, BorderLayout.EAST);
			panel_2.setLayout(new GridLayout(0, 1, 5, 5));
			// backspace鈕
			btnBackspace = new JButton("Backspace");
			btnBackspace.addActionListener(new Bk_Cr_List());
			btnBackspace.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_2.add(btnBackspace);
			// 加鈕
			btnPlus = new JButton("+");
			btnPlus.addActionListener(new OperList());
			btnPlus.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_2.add(btnPlus);
			// 減鈕
			btnMinus = new JButton("-");
			btnMinus.addActionListener(new OperList());
			btnMinus.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_2.add(btnMinus);
			// 乘鈕
			btnMultiplied = new JButton("X");
			btnMultiplied.addActionListener(new OperList());
			btnMultiplied.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_2.add(btnMultiplied);
			// 除鈕
			btnDivided = new JButton("÷");
			btnDivided.addActionListener(new OperList());
			btnDivided.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_2.add(btnDivided);

			panel_3 = new JPanel();
			panel_1.add(panel_3, BorderLayout.CENTER);
			panel_3.setLayout(new BorderLayout(0, 0));

			panel_4 = new JPanel();
			panel_3.add(panel_4, BorderLayout.NORTH);
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.setBorder(new EmptyBorder(5, 5, 5, 5));
			// 清除鈕
			btnClear = new JButton("Clear");
			btnClear.addActionListener(new Bk_Cr_List());
			btnClear.setFont(new Font("Verdana", Font.PLAIN, 40));
			btnClear.setPreferredSize(new Dimension(57, 77));
			panel_4.add(btnClear);

			panel_5 = new JPanel();
			panel_3.add(panel_5, BorderLayout.CENTER);
			panel_5.setLayout(new GridLayout(0, 3, 5, 5));
			panel_5.setBorder(new EmptyBorder(5, 5, 5, 5));
			// 7鈕
			btn7 = new JButton("7");
			btn7.addActionListener(new ActList());
			btn7.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btn7);
			// 8鈕
			btn8 = new JButton("8");
			btn8.addActionListener(new ActList());
			btn8.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btn8);
			// 9鈕
			btn9 = new JButton("9");
			btn9.addActionListener(new ActList());
			btn9.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btn9);
			// 4鈕
			btn4 = new JButton("4");
			btn4.addActionListener(new ActList());
			btn4.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btn4);
			// 5鈕
			btn5 = new JButton("5");
			btn5.addActionListener(new ActList());
			btn5.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btn5);
			// 6鈕
			btn6 = new JButton("6");
			btn6.addActionListener(new ActList());
			btn6.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btn6);
			// 1鈕
			btn1 = new JButton("1");
			btn1.addActionListener(new ActList());
			btn1.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btn1);
			// 2鈕
			btn2 = new JButton("2");
			btn2.addActionListener(new ActList());
			btn2.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btn2);
			// 3鈕
			btn3 = new JButton("3");
			btn3.addActionListener(new ActList());
			btn3.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btn3);
			// 小數點鈕
			btnDot = new JButton(".");
			btnDot.addActionListener(new DotList());
			btnDot.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btnDot);
			// 0鈕
			btn0 = new JButton("0");
			btn0.addActionListener(new ActList());
			btn0.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btn0);
			// 等於鈕
			btnEqual = new JButton("=");
			btnEqual.addActionListener(new EqualList());
			btnEqual.setFont(new Font("Verdana", Font.PLAIN, 40));
			panel_5.add(btnEqual);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 數字傾聽器
	class ActList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) { // 動作時執行
			JButton btn = (JButton) e.getSource();
			try {
				if ((btn == btn0) || (btn == btn1) || (btn == btn2) || (btn == btn3) || (btn == btn4) || (btn == btn5)
						|| (btn == btn6) || (btn == btn7) || (btn == btn8) || (btn == btn9)) {
					if (opFlag == false) {
						txtAnswer.setText("");
						opFlag = true;
					}
					display_Num(btn);
				}
			} catch (Exception ex) {
				System.out.println("數字傾聽:" + ex);
			}
		}

		private void display_Num(JButton btn) {
			if (txtAnswer.getText().indexOf(".", 0) == -1) { // 無小數點
				if (txtAnswer.getText().length() < 12) { // 沒超過12位能輸入
					txtAnswer.setText((txtAnswer.getText() + btn.getText()));
				}
			} else { // 有小數點
				if (txtAnswer.getText().length() < 13) { // 沒超過13未能輸入
					txtAnswer.setText((txtAnswer.getText() + btn.getText()));
				}
			}
		}
	}

	// 小數點傾聽器
	class DotList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			try {
				if ((btn == btnDot)) {
					if (opFlag == false) {
						txtAnswer.setText("");
						opFlag = true;
					}
					display_Dot(btn);
				}
			} catch (Exception ex) {
				System.out.println("小數點傾聽" + ex);
			}
		}

		private void display_Dot(JButton btn) {
			if (txtAnswer.getText().indexOf(".", 0) == -1) { // 小數點只能點1次
				txtAnswer.setText((txtAnswer.getText() + btn.getText()));
			}
		}
	}

	// 清除.返回傾聽器
	class Bk_Cr_List implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			try {
				if (btn == btnClear) { // 清除
					No_result1 = 0D;
					No_result2 = 0L;
					op = 0;
					txtAnswer.setText("0");
					opFlag = false;
				} else if (btn == btnBackspace) {
					if (txtAnswer.getText().length() != 0) { // 長度不等於0才可以Back
						txtAnswer.setText(txtAnswer.getText().substring(0, txtAnswer.getText().length() - 1));
						if (txtAnswer.getText().length() == 0) { // 長度等於0時就顯示"0"
							No_result1 = 0D;
							No_result2 = 0L;
							op = 0;
							txtAnswer.setText("0");
							opFlag = false;
						}
					}
				}
			} catch (Exception ex) {
				System.out.println("清除.返回傾聽器:" + ex);
			}
		}
	}

	// 運算子+-*/傾聽器
	class OperList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			try {
				if (btn == btnPlus) {// 加
					op = 1;
					opFlag = false;
					No_result1 = Double.parseDouble(txtAnswer.getText());
					
				} else if (btn == btnMinus) {// 減
					op = 2;
					opFlag = false;
					No_result1 = Double.parseDouble(txtAnswer.getText());
				} else if (btn == btnMultiplied) {// 乘
					op = 3;
					opFlag = false;
					No_result1 = Double.parseDouble(txtAnswer.getText());
				} else if (btn == btnDivided) {// 除
					op = 4;
					opFlag = false;
					No_result1 = Double.parseDouble(txtAnswer.getText());
				}
			} catch (Exception ex) {
				System.out.println("運算子+-*/傾聽器:" + ex);
			}
		}
	}

	// 等於
	class EqualList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			try {
				if (btn == btnEqual) {
					opFlag = false;
					No_result2 = Double.parseDouble(txtAnswer.getText());
					switch (op) {
					case 1:
						temp = No_result1 + No_result2;
						if ((temp < maxNum) && (temp > minNum)) { // 小於最大值,大於最小值
							No_result2 = temp; // 正常輸出
						} else if (temp > maxNum) { // 大於最大值
							No_result2 = maxNum; // 只能是最大值
						} else if (temp < minNum) { // 小於最小值
							No_result2 = minNum; // 只能是最小值
						}
						break;
					case 2:
						temp = No_result1 - No_result2;
						if ((temp < maxNum) && (temp > minNum)) {
							No_result2 = temp;
						} else if (temp > maxNum) {
							No_result2 = maxNum;
						} else if (temp < minNum) {
							No_result2 = minNum;
						}
						break;
					case 3:
						temp = No_result1 * No_result2;
						if ((temp < maxNum) && (temp > minNum)) {
							No_result2 = temp;
						} else if (temp > maxNum) {
							No_result2 = maxNum;
						} else if (temp < minNum) {
							No_result2 = minNum;
						}
						break;
					case 4:
						if (No_result2 != 0) {
							temp = No_result1 / No_result2;
							if ((temp < maxNum) && (temp > minNum)) {
								No_result2 = temp;
							} else if (temp > maxNum) {
								No_result2 = maxNum;
							} else if (temp < minNum) {
								No_result2 = minNum;
							}
						} else {
							JOptionPane.showMessageDialog(computer.this, "Divided by zero is wrong!");
						}
						break;
					default:
						break;
					}
					DecimalFormat df = new DecimalFormat("#.############"); // 設定數字格式
					String strTemp = String.valueOf(df.format(No_result2)); // 結果套到數字格式,暫存在strTemp
					if (strTemp.indexOf(".", 0) == -1) { // 無小數點
						if (strTemp.indexOf("-", 0) == -1) { // 是正數
							if (strTemp.length() > 11) { // 長度大於12,從0開始算
								txtAnswer.setText(strTemp.substring(0, 12)); // 取12位數從0開始算,輸出
							} else {
								txtAnswer.setText(strTemp);
							}
						} else { // 是負數
							if (strTemp.length() > 12) { // 長度大於13,從0開始算
								txtAnswer.setText(strTemp.substring(0, 13)); // 取13位數從0開始算,輸出
							} else {
								txtAnswer.setText(strTemp);
							}
						}
					} else { // 有小數點
						if (strTemp.indexOf("-", 0) == -1) { // 是正數
							if (strTemp.length() > 12) { // 長度大於13,從0開始算
								txtAnswer.setText(strTemp.substring(0, 13));
							} else {
								txtAnswer.setText(strTemp);
							}
						} else { // 是負數
							if (strTemp.length() > 13) { // 長度大於14,從0開始算
								txtAnswer.setText(strTemp.substring(0, 14)); // 取13位數從0開始算,輸出
							} else {
								txtAnswer.setText(strTemp);
							}
						}
					}
				}
			} catch (Exception ex) {
				System.out.println("等於傾聽器:" + ex);
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					computer frame = new computer();
					frame.setSize(600, 500); // 設定視窗大小
					frame.setVisible(true); // 顯示視窗
				} catch (Exception e) {
					System.out.println("main:" + e);
				}
			}
		});
	}
}
