import java.awt.Color;
import java.awt.Graphics;

/**
 * Nikitenko Pavel
 * 336195524
 */
public class Laminaria extends Immobile {
	public Laminaria() {
		super();
	}
	
	public Laminaria(String name, int size, int x, int y) {
		super(name, size, x, y);
	}
	
	public Laminaria(int size, int x, int y) {
		this.size = size;
		this.x = x;
		this.y = y;
	}
	
	public void drawCreature(Graphics g) {
		g.setColor(colorr);
		
		g.fillArc(x - size/20, y - size, size / 10, size*4/5, 0, 360);
		g.fillArc(x - size*3/20, y - size * 13/ 15, size/10, size*2/3, 0, 360);
		g.fillArc(x + size/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.drawLine(x, y, x, y-size/5);
		g.drawLine(x, y, x-size/10, y-size/5);
		g.drawLine(x, y, x+size/10, y-size/5);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
