package cc.openhome;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

	private JPopupMenu popUpMenu; // 出現視窗

	private JFileChooser fileChooser;

	private TextDAO textDAO;

	public JNotePad(TextDAO textDAO) {
		this();
		this.textDAO = textDAO;
	}

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
		fileChooser = new JFileChooser();

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
			JOptionPane.showOptionDialog(null, // 顯示對話方塊
					"JNotePad 0.1\n來自 http://openhome.cc", "關於JNotePad", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
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

	private void openFile(ActionEvent event) {
		if (stateBar.getText().equals("未修改")) { // 文件是否為儲存狀態
			showFileDialog(); // 開啟舊檔
		} else {
			int option = JOptionPane.showInternalConfirmDialog( // 顯示對話方塊
					null, "檔案已修改，是否儲存?", "儲存檔案?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
			switch (option) {
			case JOptionPane.YES_OPTION: // 確認儲存檔案
				saveFile(event); // 儲存檔案
				break;
			case JOptionPane.NO_OPTION: // 放棄檔案儲存
				showFileDialog();
				break;
			}
		}
	}

	private void showFileDialog() {
		int option = fileChooser.showDialog(null, null); // 檔案選取對話方塊

		// 使用者按下確認鍵
		if (option == JFileChooser.APPROVE_OPTION) {
			try {
				setTitle(fileChooser.getSelectedFile().toString()); // 設定文件標題
				textArea.setText(""); // 清除前一次文件
				stateBar.setText("未修改"); // 設定狀態列
				String text = textDAO.read(fileChooser.getSelectedFile().toString()); // 讀取檔案
				textArea.setText(text); // 附加至文字編輯區
			} catch (Throwable e) {
				JOptionPane.showMessageDialog(null, e.toString(), "開啟檔案失敗", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void saveFile(ActionEvent event) { // 儲存檔案
		// 從標題列取得檔案名稱
		Path path = Paths.get(getTitle());
		if (Files.notExists(path)) { // 若指定的檔案不存在
			saveFileAs(event); // 執行令存新檔
		} else {
			try {
				// 儲存檔案
				textDAO.save(path.toString(), textArea.getText());
				// 設定狀態列為未修改
				stateBar.setText("未修改");
			} catch (Throwable e) {
				JOptionPane.showMessageDialog(null, e.toString(), "寫入檔案失敗", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void saveFileAs(ActionEvent event) { // 另存新檔
		// 顯示檔案對話方塊
		int option = fileChooser.showDialog(null, null);
		// 如果選取檔案
		if (option == JFileChooser.APPROVE_OPTION) {
			// 在標題列上設定檔案名稱
			setTitle(fileChooser.getSelectedFile().toString());
			textDAO.create(fileChooser.getSelectedFile().toString()); // 建立檔案
			saveFile(event); // 進行儲存檔案
		}
	}

	private void closeWindow(WindowEvent event) {
		closeFile(new ActionEvent(event.getSource(), event.getID(), "windowClosing"));
	}

	private void closeFile(ActionEvent event) {
		if (stateBar.getText().equals("未修改")) { // 是否已儲存文件
			dispose(); // 釋放視窗資源，關閉程式
		} else {
			int option = JOptionPane.showConfirmDialog(null, "檔案已修改，是否儲存?", "儲存檔案?", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE, null);
			switch (option) {
			case JOptionPane.YES_OPTION:
				saveFile(event);
				break;
			case JOptionPane.NO_OPTION:
				dispose();
			}
		}
	}

	private void cut(ActionEvent event) { // 剪下
        textArea.cut();
        stateBar.setText("已修改");
        popUpMenu.setVisible(false);
	}

	private void copy(ActionEvent event) { // 複製
        textArea.copy();
        popUpMenu.setVisible(false); 
	}

	private void paste(ActionEvent event) { // 貼上
        textArea.paste();
        stateBar.setText("已修改");
        popUpMenu.setVisible(false);
	}

	private void jtextAreaActionPerformed(KeyEvent event) {
		stateBar.setText("已修改");
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> { // 將建立JNotePad實例與
			new JNotePad(new FileTextDAO()).setVisible(true); // setVistble()的動作排入事件佇列
		});
	}
}
