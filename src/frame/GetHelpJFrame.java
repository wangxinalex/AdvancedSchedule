package frame;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
/**
 * 本类用于显示使用说明，更详细的说明请参阅“使用文档”
 * @author Administrator
 *
 */
public class GetHelpJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton emailButton = null;
	private JButton okButton = null;
	private JTextArea contentTextArea = null;
	private JScrollPane contentScrollPane = null;
	private Font f3 = new Font("微软雅黑", Font.PLAIN, 12);
	private Icon mailMeIcon = new ImageIcon("./icon/mailMe.png");
	private Icon okIcon = new ImageIcon("./icon/okay.png");
	private JLabel textLabel = null;
	
	/**
	 * This is the default constructor
	 * @throws IOException 
	 */
	public GetHelpJFrame(){
		super();
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		this.setSize(697, 257);
		this.setContentPane(getJContentPane());
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("帮助");
		Toolkit toolkit = this.getToolkit();
		Image topicon =toolkit.getImage("./icon/gethelp.png");
		this.setIconImage(topicon);
		String in ,content = "";
		BufferedReader readStream = null;
		try {
			readStream = new BufferedReader(new FileReader(
					"Help.data"));
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "帮助文件不存在，请参阅使用说明","提示",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		
			while ((in = readStream.readLine()) != null) {
				content+="\n"+in;
			}
		contentTextArea.setText(content);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			textLabel = new JLabel();
			textLabel.setBounds(new Rectangle(44, 109, 40, 26));
			textLabel.setText("联系我");
			textLabel.setFont(f3);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getEmailButton(), null);
			jContentPane.add(getContentScrollPane(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(textLabel, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes emailButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEmailButton() {
		if (emailButton == null) {
			emailButton = new JButton();
			emailButton.setBounds(new Rectangle(16, 16, 90, 90));
			emailButton.setIcon(mailMeIcon);
			emailButton.setToolTipText("联系我");
			emailButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			emailButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					MailJFrame mailJFrame = new MailJFrame();
					mailJFrame.setRecipientText("10302010023@fudan.edu.cn");
					mailJFrame.setLocationRelativeTo(null);
					mailJFrame.setVisible(true);
				}
			});
		}
		return emailButton;
	}

	/**
	 * This method initializes contentTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getContentTextArea() {
		if (contentTextArea == null) {
			contentTextArea = new JTextArea();
			contentTextArea.setFont(f3);
			contentTextArea.setEditable(false);
		}
		return contentTextArea;
	}

	/**
	 * This method initializes contentScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getContentScrollPane() {
		if (contentScrollPane == null) {
			contentScrollPane = new JScrollPane();
			contentScrollPane.setBounds(new Rectangle(131, 15, 526, 191));
			contentScrollPane.setViewportView(getContentTextArea());
		}
		return contentScrollPane;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setIcon(okIcon);
			okButton.setBounds(new Rectangle(21, 166, 83, 35));
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return okButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
