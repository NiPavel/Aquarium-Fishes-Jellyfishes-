import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * Nikitenko Pavel
 * 336195524
 */
public class Zostera extends Immobile{
	
	public Zostera(){
		super();
	}
	
	public Zostera(String name, int size, int x, int y) {
		super(name, size, x, y);
	}
	
	public Zostera(int size, int x, int y) {
		this.size = size;
		this.x = x;
		this.y = y;
	}

	@Override
	public void drawCreature(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(colorr);
		g.drawLine(x,  y, x,  y-size);
		g.drawLine(x-2,  y, x-10,  y-size*9/10);
		g.drawLine(x+2,  y, x+10,  y-size*9/10);
		g.drawLine(x-4,  y, x-20,  y-size*4/5);
		g.drawLine(x+4,  y, x+20,  y-size*4/5);
		g.drawLine(x-6,  y, x-30,  y-size*7/10);
		g.drawLine(x+6,  y, x+30,  y-size*7/10);
		g.drawLine(x-8,  y, x-40,  y-size*4/7);
		g.drawLine(x+8,  y, x+40,  y-size*4/7);
		g2.setStroke(new BasicStroke(1));	
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
