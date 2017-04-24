package cc.openhome;

import java.awt.event.*;
import javax.swing.*;

public class JNotePad extends JFrame { // 繼承JFrame
	private JMenuBar menuBar; // 選單列
	private JMenu fileMenu; // "檔案"選單
	private JMenuItem menuOpen;
	private JMenuItem menuSave;
	private JMenuItem menuSaveAs;
	private JMenuItem menuClose;

	private JMenu editMenu; // "編輯"選單
	private JMenuItem menuCut;
	private JMenuItem menuCopy;
	private JMenuItem menuPaste;

	private JMenu aboutMenu; // "關於"選單
	private JMenuItem menuAbout;

	public JNotePad() {
		initComponents(); // 初始元件外觀
		initEventListeners(); // 初始元件事件傾聽器
	}

	private void initComponents() {
		setTitle("新增純文字檔案"); // 設定視窗標題
		setSize(800, 600); // 設定視窗大小pixel
		initFileMenu();
		initEditMenu();
		initAboutMenu();
		initMenuBar();
	}

	private void initMenuBar() {
		// 選單列
		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(aboutMenu);
		// 設置選單列
		setJMenuBar(menuBar);
	}

	private void initAboutMenu() {
		// 設置「關於」選單
		aboutMenu = new JMenu("關於");
		menuAbout = new JMenuItem("關於JNotePad");
		aboutMenu.add(menuAbout);
	}

	private void initEditMenu() {
		// 設置「編輯」選單
		editMenu = new JMenu("編輯");
		menuCut = new JMenuItem("剪下");
		menuCopy = new JMenuItem("複製");
		menuPaste = new JMenuItem("貼上");

		editMenu.add(menuCut);
		editMenu.add(menuCopy);
		editMenu.add(menuPaste);
	}

	private void initFileMenu() {
		// 設置「檔案」選單
		fileMenu = new JMenu("檔案");
		menuOpen = new JMenuItem("開啟舊檔");
		menuSave = new JMenuItem("儲存檔案");
		menuSaveAs = new JMenuItem("另存新檔");
		menuClose = new JMenuItem("關閉");

		fileMenu.add(menuOpen);
		fileMenu.addSeparator(); // 分隔線
		fileMenu.add(menuSave);
		fileMenu.add(menuSaveAs);
		fileMenu.addSeparator(); // 分隔線
		fileMenu.add(menuClose);
	}

	private void initEventListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 按下右上X按鈕的預設行為
		initAccelerator();
	}

	private void initAccelerator() {
		// 快速鍵設置
		menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));	// 開啟舊檔Ctrl + O
		menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));	// 儲存檔案Ctrl + S
		menuClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));	// 關閉Ctrl + Q
		menuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));	// 剪下Ctrl + X
		menuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));	// 複製Ctrl + C
		menuPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));	// 貼上Ctrl + V
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> { // 將建立JNotePad實例與
			new JNotePad().setVisible(true); // setVistble()的動作排入事件佇列
		});
	}
}
