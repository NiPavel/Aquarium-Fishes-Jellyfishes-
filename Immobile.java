import java.awt.Color;
import java.awt.Graphics;

/**
 * Nikitenko Pavel
 * 336195524
 */
public abstract class Immobile implements SeaCreature{
	protected String name;
	protected int size, x , y;
	protected final Color colorr = Color.GREEN;
	
	public Immobile() {
		this.name = "";
		this.size = 0;
		this.x = 0;
		this.y = 0;
	}
	
	public Immobile(String name,int size,int x,int y) {
		this.name = name;
		this.size = size;
		this.x = x;
		this.y = y;
	}
	
	abstract public void drawCreature(Graphics g);
	abstract public String getName();
	abstract public int getSize();
	abstract public int getX();
	abstract public int getY();
}
