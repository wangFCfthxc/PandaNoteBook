package cc.openhome;

import java.awt.BorderLayout;
import java.awt.Font;
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

	private JTextArea textArea;
	private JLabel stateBar;

	private JPopupMenu popUpMenu;

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
		initTextArea();
		initStateBar();
		popUpMenu = editMenu.getPopupMenu();
	}

	private void initStateBar() {
		// 狀態列
		stateBar = new JLabel("未修改");
		stateBar.setHorizontalAlignment(SwingConstants.LEFT); // 文字靠左顯示
		// 設置Border,用BorderFactory的createEtchedBorder效果
		stateBar.setBorder(BorderFactory.createEtchedBorder());
		getContentPane().add(stateBar, BorderLayout.SOUTH); // 放南邊=下面

	}

	private void initTextArea() {
		// 文字編輯區
		textArea = new JTextArea();
		textArea.setFont(new Font("細明體", Font.PLAIN, 16)); // 設定文字字型
		textArea.setLineWrap(true); // true時，超出文字區域寬度時自動換行
		// 卷軸
		JScrollPane panel = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, // 垂直卷軸必要時自動顯示
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // 水平卷軸因為有自動換行所以不用

		getContentPane().add(panel, BorderLayout.CENTER); // 放中間

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

		// 按下視窗關閉鈕事件處理
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				closeWindow(event);
			}
		});

		initMenuListener();

		// 編輯區鍵盤事件
		textArea.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				jtextAreaActionPerformed(event);
			}
		});

		// 編輯區滑鼠事件
		textArea.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON3) { // BUTTON3滑鼠右鍵
					popUpMenu.show(editMenu, event.getX(), event.getY());
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) { // BUTTON1滑鼠左鍵
					popUpMenu.setVisible(false);
				}
			}
		});
	}
	
    private void initMenuListener() {
        menuOpen.addActionListener(this::openFile); // 選單 - 開啟舊檔
        menuSave.addActionListener(this::saveFile); // 選單 - 儲存檔案
        menuSaveAs.addActionListener(this::saveFileAs); // 選單 - 另存新檔
        menuClose.addActionListener(this::closeFile); // 選單 - 關閉檔案
        menuCut.addActionListener(this::cut); // 選單 - 剪下
        menuCopy.addActionListener(this::copy); // 選單 - 複製
        menuPaste.addActionListener(this::paste); // 選單 - 貼上
        menuAbout.addActionListener(event -> { // 選單 - 關於
            JOptionPane.showOptionDialog(null,  // 顯示對話方塊
                    "JNotePad 0.1\n來自 http://openhome.cc",
                    "關於JNotePad",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, null, null);
        });
    }

	private void initAccelerator() {
		// 快速鍵設置
		menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK)); // 開啟舊檔Ctrl+O
		menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK)); // 儲存檔案Ctrl+S
		menuClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK)); // 關閉Ctrl+Q
		menuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK)); // 剪下Ctrl+X
		menuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK)); // 複製Ctrl+C
		menuPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK)); // 貼上Ctrl+V

	}
	
    private void closeWindow(WindowEvent event) {}
    private void openFile(ActionEvent event) {}
    private void saveFile(ActionEvent event) {}    
    private void saveFileAs(ActionEvent event) {}    
    private void closeFile(ActionEvent event) {}
    private void cut(ActionEvent event) {}
    private void copy(ActionEvent event) {}    
    private void paste(ActionEvent event) {} 
    private void jtextAreaActionPerformed(KeyEvent event) {}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> { // 將建立JNotePad實例與
			new JNotePad().setVisible(true); // setVistble()的動作排入事件佇列
		});
	}
}
