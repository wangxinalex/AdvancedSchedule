package frame;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * ÎªJPanelÌí¼Ó±³¾°Í¼Æ¬
 * @author Administrator
 *
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

	/**
	 * This is the default constructor
	 */
	public ImagePanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		backgroundImage = new ImageIcon("./icon/background2.jpg").getImage();

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

}
