package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import data.Media;
/**
 * 本窗口无特别意义，为该软件“彩蛋”，内附一段音乐和动画
 * @author Administrator
 *
 */
public class HelpJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel infoPanel = null;
	private JPanel colorPanel = null;
	private JButton okayButton = null;
	private JButton exitButton = null;
	private JButton helpButton = null;
	private JLabel infoLabel1 = null;
	private JLabel infoLabel2 = null;
	private Icon helpIcon = new ImageIcon("./icon/helpIcon.png");
	private Icon okayIcon = new ImageIcon("./icon/okay.png");
	private Icon cancelIcon  = new ImageIcon("./icon/cancel.png");
	private Font f3 = new Font("微软雅黑", Font.PLAIN, 12);
	
	public int red = 100;
	public int green = 100;
	public int blue = 100;
	private Timer t;  //  @jve:decl-index=0:
	
	/**
	 * This is the default constructor
	 */
	public HelpJFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(560, 210);
		this.setContentPane(getJContentPane());
		this.setTitle("About Anvanced Schedule");
		this.setFont(f3);
		Toolkit toolkit = this.getToolkit();
		Image topicon =toolkit.getImage("./icon/helpTitle.png");
		this.setIconImage(topicon);
		this.setLocationRelativeTo(null);
	}
//	java.applet.AudioClip clip = java.applet.Applet.newAudioClip(this
//			.getClass().getResource("./music/music1.mid"));
//	public void play() {	
//		clip.loop();
//	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			infoLabel1 = new JLabel();
			infoLabel1.setText("Advanced Schedule From Wangxin ");
			infoLabel1.setFont(f3);
			infoLabel1.setBounds(new Rectangle(8, 7, 342, 20));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(542, 203));
			jContentPane.add(getInfoPanel(), null);
			jContentPane.add(getColorPanel(), null);
			jContentPane.add(getHelpButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes infoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getInfoPanel() {
		if (infoPanel == null) {
			infoLabel2 = new JLabel();
			infoLabel2.setText("(C)CopyRight Alex Corporation All Rights Reserved");
			infoLabel2.setFont(f3);
			infoLabel2.setBounds(new Rectangle(6, 35, 345, 21));
			infoPanel = new JPanel();
			infoPanel.setLayout(null);
			infoPanel.setBounds(new Rectangle(100, 20, 430, 70));
			infoPanel.setBorder(new LineBorder(Color.CYAN,2));
			infoPanel.setLayout(new GridLayout(2,1));
			infoPanel.add(infoLabel1, null);
			infoPanel.add(infoLabel2, null);
		}
		return infoPanel;
	}

	/**
	 * This method initializes colorPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getColorPanel() {
		if (colorPanel == null) {
			colorPanel = new JPanel();
			colorPanel.setLayout(null);
			colorPanel.setBounds(new Rectangle(20, 100, 510, 71));
			colorPanel.add(getOkayButton(), null);
			colorPanel.add(getExitButton(), null);
			 t = new Timer(20, new ActionListener() {

				

				public void actionPerformed(ActionEvent arg0) {

					red += 4;
					green += 3;
					blue += 2;
					red %= 250;
					green %= 250;
					blue %= 250;
					// System.out.println("123");
					colorPanel.setBorder(new LineBorder(new Color(red, green, blue),3));
				}

			});
			//t.start();
		}
		return colorPanel;
	}

	/**
	 * This method initializes okayButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkayButton() {
		if (okayButton == null) {
			okayButton = new JButton();
			okayButton.setBounds(new Rectangle(130, 15, 100, 40));
			okayButton.setText("确定");
			okayButton.setFont(f3);
			okayButton.setIcon(okayIcon);
			okayButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					setVisible(false);
				}
				
			});
		}
		return okayButton;
	}

	
	
	


	/**
	 * This method initializes exitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getExitButton() {
		if (exitButton == null) {
			exitButton = new JButton();
			exitButton.setBounds(new Rectangle(250, 15, 100, 40));
			exitButton.setText("退出");
			exitButton.setFont(f3);
			exitButton.setIcon(cancelIcon);
			exitButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					setVisible(false);
				}
				
			});
		}
		return exitButton;
	}

	/**
	 * This method initializes helpButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton();
			helpButton.setBounds(new Rectangle(20, 20, 70, 70));
			helpButton.setIcon(helpIcon);
			helpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			helpButton.setToolTipText("单击开始播放音乐和动画，双击停止");
			helpButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount()==1){
						t.start();
						Media.autoRun();
					} else if(e.getClickCount()==2){
						t.stop();
						Media.autostop();
					}
				}
			});
		}
		return helpButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
