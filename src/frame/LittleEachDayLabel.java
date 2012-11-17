package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import data.Schedule;
import demo.PrintCalendar;

/**
 * 本类用于显示每个具体日期的小方格，即根据日期参数确定标签上显示的日期和主面板里显示的日程标签
 * 
 */
public class LittleEachDayLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	private JLabel titleLabel = null;
	private Color color1 = new Color(87, 248, 182);
	private Color color2 = new Color(167, 192, 194);
	private Color color3 = new Color(155, 181, 225); // @jve:decl-index=0:
	private Color color4 = new Color(255, 170, 32);  //  @jve:decl-index=0:
	private Color color5 = new Color(212, 225, 241);
	@SuppressWarnings("unused")
	private Color color8 = new Color(30, 235, 54);
	private Color color9 = new Color(254, 33, 8);
	private Font f3 = new Font("微软雅黑", Font.PLAIN, 12); // @jve:decl-index=0:
	private Font f4 = new Font("微软雅黑", Font.BOLD, 14);  //  @jve:decl-index=0:
	private String dayStr = null;
	private String dateStr = null;
	private boolean isSelected = false;
	private boolean isThisMonth = false;

	public boolean isThisMonth() {
		return isThisMonth;
	}

	public void setThisMonth(boolean isThisMonth) {
		this.isThisMonth = isThisMonth;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	private String[] dateString = new String[42];
	GregorianCalendar d = new GregorianCalendar();
	public static SimpleDateFormat dateFormatter2 = new SimpleDateFormat(
			"yyyy/MM");
	public static SimpleDateFormat dateFormatter3 = new SimpleDateFormat(
			"yyyy/MM/dd");
	private ArrayList<Schedule> scheduleToday = new ArrayList<Schedule>(); // @jve:decl-index=0:
	private ArrayList<Schedule> scheduleListCertainDay = new ArrayList<Schedule>();
	PrintCalendar cal = new PrintCalendar();
	private AddSchedule newAddSchedule = null;
	private ItemLabel selectedLabel = null;
	int yearInTitle;
	int monthInTitle;
	int TitleBlank = 0;
	int endingBlank = 0;
	int today = 0;

	public LittleEachDayLabel(int year, int month, int day, String[] s,
			int yearInTitle, int monthInTitle, int titleBlank, int endingBlank,
			int today) throws IOException, ClassNotFoundException,
			InterruptedException {

		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.GRAY,10));
		this.dateString = s;
		this.yearInTitle = yearInTitle;
		this.monthInTitle = monthInTitle;
		this.TitleBlank = titleBlank;
		this.endingBlank = endingBlank;
		this.today = today;
		this.titleLabel = new JLabel();
		this.titleLabel.setBackground(color5);
		this.addMouseListener(getMouseListener());

		// this.add(bodyScrollPane, BorderLayout.CENTER);
		// bodyScrollPane.setViewportView(bodyPanel);

		// CalendarManager.loadRecordsFromFile();

		// 以下部分用于根据方格上显示的日期获得统一格式(yyyy/MM/dd)的字符串值，用于索引每一天的日程

		if (dateString[day].contains("年")) {
			// System.out.println(dateString[day].length());
			String yearStr = dateString[day].substring(0, dateString[day]
					.indexOf('年'));
			String monthStr = dateString[day].substring(dateString[day]
					.indexOf('年') + 1, dateString[day].indexOf('月'));
			dayStr = dateString[day].substring(
					dateString[day].indexOf('月') + 1,
					dateString[day].length() - 1);

			dateStr = yearStr
					+ "/"
					+ ((monthStr.length() == 1) ? ("0" + (monthStr))
							: (monthStr)) + "/"
					+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			if (Integer.parseInt(yearStr) != yearInTitle) {
				this.setForeground(Color.GRAY);
			} else {
				this.setThisMonth(true);
			}
		} else if (day < TitleBlank) {
			this.setForeground(Color.GRAY);

			if (dateString[day].contains("月")) {
				dayStr = dateString[day].substring(
						dateString[day].indexOf('月') + 1, dateString[day]
								.indexOf('日'));
			} else {
				dayStr = dateString[day].replaceAll("日", "");
			}
			if (monthInTitle == 1) {
				dateStr = yearInTitle - 1 + "/" + "12" + "/"
						+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			} else {
				dateStr = yearInTitle
						+ "/"
						+ ((monthInTitle < 10) ? ("0" + (monthInTitle - 1))
								: (monthInTitle - 1)) + "/"
						+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			}
			// System.out.println(dateStr);
		} else if (day >= TitleBlank && day < 42 - endingBlank) {
			if (dateString[day].contains("月")) {
				dayStr = dateString[day].substring(
						dateString[day].indexOf('月') + 1, dateString[day]
								.indexOf('日'));
			} else {
				dayStr = dateString[day].replaceAll("日", "");
			}
			dateStr = yearInTitle
					+ "/"
					+ ((monthInTitle < 10) ? ("0" + (monthInTitle))
							: (monthInTitle)) + "/"
					+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			this.setThisMonth(true);
			// System.out.println(dateStr);
		} else {

			if (dateString[day].contains("月")) {
				dayStr = dateString[day].substring(
						dateString[day].indexOf('月') + 1, dateString[day]
								.indexOf('日'));
			} else {
				dayStr = dateString[day].replaceAll("日", "");
			}
			if (monthInTitle == 12) {
				dateStr = yearInTitle + 1 + "/" + "01" + "/"
						+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			} else {
				dateStr = yearInTitle
						+ "/"
						+ ((monthInTitle < 10) ? ("0" + (monthInTitle + 1))
								: (monthInTitle + 1)) + "/"
						+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			}
			this.setForeground(Color.GRAY);
			// System.out.println(dateStr);
		}

		if ((day == TitleBlank + today - 1)) {
			this.setForeground(color4);
			this.setFont(f4);
			this.setOpaque(true);
			//this.repaint();
			// this.setForeground(Color.RED);
		}
		String[] newString = new String[dateString.length];
		for (int i = 0; i < dateString.length; i++) {
			if (dateString[i].contains("月")) {
				newString[i] = (dateString[i].substring(dateString[i]
						.indexOf("月") + 1, dateString[i].indexOf("日")));
			} else {
				newString[i] = (dateString[i].substring(0, dateString[i]
						.indexOf("日")));
			}
		}
		this.setText(newString[day]);
		//this.setFont(f3);
		this.setAlignmentX(CENTER_ALIGNMENT);

	}

	private MouseAdapter getMouseListener() {
		// TODO Auto-generated method stub
		MouseAdapter labelMouse = new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				LittleEachDayLabel selectLabel = (LittleEachDayLabel) evt
						.getSource();

				selectLabel.setForeground(color4);
				selectLabel.setSelected(true);
			}

			public void mouseExited(MouseEvent evt) {
				LittleEachDayLabel selectLabel = (LittleEachDayLabel) evt
						.getSource();
				if (selectLabel.isSelected()) {
					selectLabel.setSelected(false);
					if (selectLabel.isThisMonth()) {
						selectLabel.setForeground(Color.BLACK);
					} else {
						selectLabel.setForeground(Color.GRAY);
					}
				}
			}

			public void mouseReleased(MouseEvent evt) {
				System.out.println(dateStr);
			}
		};
		return labelMouse;
	}

}
