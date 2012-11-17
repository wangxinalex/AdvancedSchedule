package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * 本类用于实现mini界面显示，记在小型界面中显示本周日程,由于时间限制，本功能并未完成，将在寒假中继续
 * @author Administrator
 *
 */
public class DesktopJFrame extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel functionPanel = null;
	private JPanel bodyPanel = null;
	private JButton changeButton = null;
	private JButton previousButton = null;
	private JButton nextButton = null;
	static DesktopJFrame newFrame =null;
	Icon leftIcon = new ImageIcon("./icon/left.png");
	Icon rightIcon = new ImageIcon("./icon/right.png");
	Icon changeIcon = new ImageIcon("./icon/change.png");
	int beginningMonthIndex = 0;
	DayPanel littlePanel;
	int beginningDay = 0;

	/**
	 * This is the default constructor
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public DesktopJFrame() throws IOException, ClassNotFoundException, InterruptedException {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() throws IOException,
	ClassNotFoundException, InterruptedException {
		int today = (Calendar.DAY_OF_WEEK_IN_MONTH)/7+1;
		beginningDay+=today*7;
		//this.setContentPane(getJContentPane());
		//System.out.println(beginningDay);
		littlePanel= new DayPanel(beginningMonthIndex);
		this.setSize(223, 713);
		//System.out.println(scrSize.width);
		//this.setLocation(scrSize.width-275, 0);
		this.setContentPane(getJContentPane());
		this.setTitle("Desktop");
		Toolkit toolkit = this.getToolkit();
		Image topicon = toolkit.getImage("./icon/title.png");
		this.setIconImage(topicon);
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
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

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	private JPanel getJContentPane() throws IOException, ClassNotFoundException, InterruptedException {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setEnabled(true);
			jContentPane.add(getFunctionPanel(), BorderLayout.NORTH);
			jContentPane.add(getBodyPanel(), BorderLayout.CENTER);
			
		}
		return jContentPane;
	}

	/**
	 * This method initializes functionPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFunctionPanel() {
		if (functionPanel == null) {
			String dateString = null;
			//System.out.println(dateString);
			functionPanel = new JPanel();
			functionPanel.setLayout(null);
			functionPanel.setBounds(new Rectangle(0, 6, 215, 61));
			functionPanel.add(getPreviousButton(), null);
			functionPanel.add(getNextButton(), null);
			functionPanel.add(getChangeButton(), null);
		}
		return functionPanel;
	}

	/**
	 * This method initializes previousButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPreviousButton() {
		if (previousButton == null) {
			previousButton = new JButton();
			
			previousButton.setIcon(leftIcon);
			previousButton.setBounds(new Rectangle(5, 10, 60, 40));
			previousButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					beginningDay-=7;
					jContentPane.removeAll();
					
					jContentPane.setLayout(null);
					//jContentPane.setEnabled(false);
					jContentPane.add(getFunctionPanel(), null);
					try {
						jContentPane.add(getBodyPanel(), null);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jContentPane.validate();
				}
				
				
			});
			
		}
		return previousButton;
	}

	/**
	 * This method initializes nextButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton();
			
			nextButton.setIcon(rightIcon);
			nextButton.setBounds(new Rectangle(150, 10, 60, 40));
			nextButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					beginningDay+=7;
					jContentPane.removeAll();
					
					jContentPane.setLayout(null);
					//jContentPane.setEnabled(false);
					jContentPane.add(getFunctionPanel(), null);
					try {
						jContentPane.add(getBodyPanel(), null);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jContentPane.validate();
				}
			});
		}
		return nextButton;
	}
	/**
	 * This method initializes bodyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	private JPanel getBodyPanel() throws IOException, ClassNotFoundException, InterruptedException {
		if (bodyPanel == null) {
			bodyPanel = new JPanel();
			bodyPanel.setLayout(new GridLayout(7,1,0,0));
			bodyPanel.setBounds(new Rectangle(3, 70, 212, 611));
			
		}
		
		bodyPanel.removeAll();
		if(beginningDay<0){
			littlePanel.monthInTitle-=1;
			beginningMonthIndex-=1;
		
			beginningDay+=(42-littlePanel.titleBlank-littlePanel.endingBlank);
			littlePanel=new DayPanel(beginningMonthIndex);
		}else if(beginningDay>=42){
			littlePanel.monthInTitle+=1;
			beginningMonthIndex+=1;
			beginningDay-=42;
			littlePanel=new DayPanel(beginningMonthIndex);
		}
		String[] s =littlePanel.handleDateString();
		for(String string:s){
			System.out.println(string+"\t"+beginningDay+"\t"+littlePanel.monthInTitle);
		}
		for (int i = beginningDay; i < beginningDay+7; i++) {
			
			//littlePanel.initialize(0);
			
			EachDayPanel thisDayPanel = new EachDayPanel(i, s,littlePanel.yearInTitle,littlePanel.monthInTitle,littlePanel.titleBlank,littlePanel.endingBlank,littlePanel.today);
			bodyPanel.add(thisDayPanel);

		}

		return bodyPanel;
	}

	/**
	 * This method initializes changeButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getChangeButton() {
		if (changeButton == null) {
			changeButton = new JButton();
			changeButton.setBounds(new Rectangle(87, 11, 38, 36));
			changeButton.setIcon(changeIcon);
			changeButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					newFrame.dispose();
					try {
						MainMenu infoMenu = new MainMenu();
						infoMenu.showFrame();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchMethodException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
			});
		}
		return changeButton;
	}

	public static  void showMe(){
		try {
			newFrame =new DesktopJFrame();
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
		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();  //  @jve:decl-index=0:
		newFrame.setLocation(scrSize.width-223, 0);
		newFrame.setVisible(true);
	}
	public static void main(String args[]){
		showMe();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
