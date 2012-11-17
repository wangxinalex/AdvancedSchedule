package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class LittleCalendar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel yearLabel = null;
	private JPanel buttonPanel1 = null;
	private JButton preYearButton = null;
	private JButton nextYearButton = null;
	private JLabel monthLabel = null;
	private JPanel buttonPanel2 = null;
	private JButton preMonthButton = null;
	private JButton nextMonthButton = null;
	private JPanel datePanel = null;
	private int occurrence=0;
	private String yearStr="";
	private String monthStr="";
	private LittleDayPanel dayPanel = null;  //  @jve:decl-index=0:visual-constraint="503,48"
	private LittleDayPanel littleDayPanel = null;
	private JLabel weekLabel = null;
	Calendar d=Calendar.getInstance();
	private JLabel jLabelExit = null;
	
	/**
	 * This is the default constructor
	 */
	public LittleCalendar() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(383, 301);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setUndecorated(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jLabelExit = new JLabel();
			jLabelExit.setBounds(new Rectangle(351, 17, 14, 17));
			jLabelExit.setText("X");
			jLabelExit.setHorizontalAlignment(0);
			jLabelExit.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					dispose();
				}
			});
			yearStr=String.valueOf(d.get(Calendar.YEAR));
			monthStr=String.valueOf(d.get(Calendar.MONTH)+1);
			monthLabel = new JLabel();
			monthLabel.setBounds(new Rectangle(184, 10, 50, 30));
			monthLabel.setText(monthStr);
			monthLabel.setOpaque(true);
			monthLabel.setBackground(Color.WHITE);
			monthLabel.setHorizontalAlignment(0);
			
			
			yearLabel = new JLabel();
			yearLabel.setBounds(new Rectangle(13, 10, 50, 30));
			yearLabel.setText(yearStr);
			yearLabel.setOpaque(true);
			yearLabel.setHorizontalAlignment(0);

			yearLabel.setBackground(Color.WHITE);

			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			
			jContentPane.add(yearLabel, null);
			jContentPane.add(getButtonPanel1(), null);
			jContentPane.add(monthLabel, null);
			jContentPane.add(getButtonPanel2(), null);
			jContentPane.add(getDatePanel(), null);
			jContentPane.add(jLabelExit, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes buttonPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonPanel1() {
		if (buttonPanel1 == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			buttonPanel1 = new JPanel();
			buttonPanel1.setLayout(gridLayout);
			buttonPanel1.setBounds(new Rectangle(70, 10, 100, 30));
			buttonPanel1.add(getPreYearButton(), null);
			buttonPanel1.add(getNextYearButton(), null);
		}
		return buttonPanel1;
	}

	/**
	 * This method initializes preYearButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPreYearButton() {
		if (preYearButton == null) {
			preYearButton = new JButton();
			preYearButton.setText("<<");
			preYearButton.addActionListener(getActionListener());

		}
		return preYearButton;
	}

	private ActionListener getActionListener() {
		ActionListener mouseListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource()==preYearButton){
					occurrence-=12;
					datePanel.remove(littleDayPanel);
					try {
						datePanel.add(littleDayPanel=new LittleDayPanel(occurrence));
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
					datePanel.updateUI();
					datePanel.updateUI();
					d.add(Calendar.YEAR, -1);
					yearStr=String.valueOf(d.get(Calendar.YEAR));
					monthStr=String.valueOf(d.get(Calendar.MONTH)+1);
					yearLabel.setText(yearStr);
					monthLabel.setText(monthStr);
					
				}else if(evt.getSource()==nextYearButton){
					occurrence+=12;
					datePanel.remove(littleDayPanel);
					try {
						datePanel.add(littleDayPanel=new LittleDayPanel(occurrence));
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
					datePanel.updateUI();
					d.add(Calendar.YEAR, 1);
					yearStr=String.valueOf(d.get(Calendar.YEAR));
					monthStr=String.valueOf(d.get(Calendar.MONTH)+1);
					yearLabel.setText(yearStr);
					monthLabel.setText(monthStr);
				}else if(evt.getSource()==preMonthButton){
					occurrence-=1;
					datePanel.remove(littleDayPanel);
					try {
						datePanel.add(littleDayPanel=new LittleDayPanel(occurrence));
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
					datePanel.updateUI();
					d.add(Calendar.MONTH, -1);
					yearStr=String.valueOf(d.get(Calendar.YEAR));
					monthStr=String.valueOf(d.get(Calendar.MONTH)+1);
					yearLabel.setText(yearStr);
					monthLabel.setText(monthStr);
					//monthLabel.setText(getMonthString(monthLabel.getText(),false));
				}else if(evt.getSource()==nextMonthButton){
					occurrence+=1;
					datePanel.remove(littleDayPanel);
					try {
						datePanel.add(littleDayPanel=new LittleDayPanel(occurrence));
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
					datePanel.updateUI();
					d.add(Calendar.MONTH, 1);
					yearStr=String.valueOf(d.get(Calendar.YEAR));
					monthStr=String.valueOf(d.get(Calendar.MONTH)+1);
					yearLabel.setText(yearStr);
					monthLabel.setText(monthStr);
					//monthLabel.setText(getMonthString(monthLabel.getText(),true));
				}
				
			}

//			private String getMonthString(String s,boolean plus) {
//				String newString=null;
//				int monthInt=Integer.parseInt(s);
//				if(plus){
//					if(monthInt==12){
//						newString=String.valueOf(1);
//					}else {
//						newString=String.valueOf(Integer.parseInt(s)+1);
//					}
//				}else{
//					if(monthInt==1){
//						newString=String.valueOf(12);
//					}else{
//					newString=String.valueOf(Integer.parseInt(s)-1);}
//				}
//				return newString;
//			}
			
		};
		return mouseListener;
	}

	/**
	 * This method initializes nextYearButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNextYearButton() {
		if (nextYearButton == null) {
			nextYearButton = new JButton();
			nextYearButton.setText(">>");
			nextYearButton.addActionListener(getActionListener());
		}
		return nextYearButton;
	}

	/**
	 * This method initializes buttonPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonPanel2() {
		if (buttonPanel2 == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(1);
			buttonPanel2 = new JPanel();
			buttonPanel2.setLayout(gridLayout1);
			buttonPanel2.setBounds(new Rectangle(240, 10, 100, 30));
			buttonPanel2.add(getPreMonthButton(), null);
			buttonPanel2.add(getNextMonthButton(), null);
		}
		return buttonPanel2;
	}

	/**
	 * This method initializes preMonthButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPreMonthButton() {
		if (preMonthButton == null) {
			preMonthButton = new JButton();
			preMonthButton.setText("<");
			preMonthButton.addActionListener(getActionListener());
		}
		return preMonthButton;
	}

	/**
	 * This method initializes nextMonthButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNextMonthButton() {
		if (nextMonthButton == null) {
			nextMonthButton = new JButton();
			nextMonthButton.setText(">");
			nextMonthButton.addActionListener(getActionListener());
		}
		return nextMonthButton;
	}

	/**
	 * This method initializes datePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDatePanel() {
		if (datePanel == null) {
			 
			weekLabel = new JLabel();
			weekLabel.setBounds(new Rectangle(5, 2, 347, 24));
			weekLabel.setText("SUN        MON        TUE        WED       THU         FRI          SAT");
			weekLabel.setForeground(Color.BLUE);
			datePanel = new JPanel();
			datePanel.setLayout(new BorderLayout());
			datePanel.setBounds(new Rectangle(15, 44, 352, 222));
			//datePanel.add(getDayPanel(), getDayPanel().getName());
			datePanel.add(getLittleDayPanel(),  BorderLayout.CENTER);
			datePanel.add(weekLabel, BorderLayout.NORTH);
		}
		return datePanel;
	}

	/**
	 * This method initializes littleDayPanel	
	 * 	
	 * @return frame.LittleDayPanel	
	 */
	private LittleDayPanel getLittleDayPanel() {
		if (littleDayPanel == null) {
			try {
				littleDayPanel = new LittleDayPanel(0);
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
			littleDayPanel.setBounds(new Rectangle(6, 25, 343, 192));
		}
		return littleDayPanel;
	}

	/**
	 * This method initializes dayPanel	
	 * 	
	 * @return frame.DayPanel	
	 */
//	private LittleDayPanel getDayPanel() {
//		if (dayPanel == null) {
//			try {
//				dayPanel = new LittleDayPanel(0);
//				dayPanel.setLayout(null);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			dayPanel.setSize(new Dimension(442, 213));
//		}
//		return dayPanel;
//	}

}  //  @jve:decl-index=0:visual-constraint="9,10"
