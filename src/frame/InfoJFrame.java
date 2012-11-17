package frame;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Pj2�ĳ������
 * @author 10302010023 ����
 *
 */
public class InfoJFrame {
	private JFrame mainFrame;
	private Font f3 = new Font("΢���ź�", Font.PLAIN, 12);

	public InfoJFrame() throws IOException, ClassNotFoundException,
			InterruptedException {
		CreateMainWindow();
		@SuppressWarnings("unused")
		MainMenu myMenu = new MainMenu(mainFrame);

	}

	private void CreateMainWindow() {
		mainFrame = new JFrame("�ճ̱����");
		mainFrame.setFont(f3);
		mainFrame.setSize(1000, 700);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			InfoJFrame infoJFrame = new InfoJFrame();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}