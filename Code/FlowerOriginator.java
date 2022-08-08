import java.awt.Color;

/**
 * Nikitenko Pavel
 * 336195524
 */
public class FlowerOriginator {
	private final Color color = Color.GREEN;
	private int size, x , y;
	private AquaPanel panel;
	
	public FlowerOriginator(AquaPanel panel) {
		this.panel = panel;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Laminaria saveToLaminaria() {
		return new Laminaria(size, x, y);
	}
	
	public Zostera saveToZostera() {
		return new Zostera(size, x, y);
	}
}
