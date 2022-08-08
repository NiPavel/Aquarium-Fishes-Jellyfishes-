import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * Nikitenko Pavel
 * 336195524
 */
public class Worm {
	public static Worm instance = null;
	AquaPanel panel;
	
	/**
	 * Constructor of the singleton
	 */
	private Worm() {}
	public static Worm getInstance() {
		if (instance == null) {
			synchronized (Worm.class) {
				if (instance == null) 
					instance = new Worm();
			}
		}
		return instance;
	}
	
	/**
	 * Function that setting a panel to a worm
	 * @param panel
	 */
	public void setWorm(AquaPanel panel) {
		if (instance != null) {
			this.panel = panel;
		}
	}
	
	/**
	 * Function that painting a food
	 * 
	 * @param g
	 */
	public void drawWorm(Graphics g) {
		if (panel != null) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.RED);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g2.drawString("S", panel.getWidth()/2, panel.getHeight()/2);
		}
	}
	
}
