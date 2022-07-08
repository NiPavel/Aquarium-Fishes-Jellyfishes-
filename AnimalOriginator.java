import java.awt.Color;

/**
 * Nikitenko Pavel
 * 336195524
 */
public class AnimalOriginator {
	private Color color;
	private int size, x_front, y_front, verSpeed, horSpeed;
	private AquaPanel panel;
	
	public AnimalOriginator(AquaPanel panel) {
		this.panel = panel;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setXFront(int x_front) {
		this.x_front = x_front;
	}
	
	public int getXFront() {
		return this.x_front;
	}
	
	public void setYFront(int y_front) {
		this.y_front = y_front;
	}
	
	public int getYFront() {
		return this.y_front;
	}
	
	public void setVerSpeed(int verSpeed) {
		this.verSpeed = verSpeed;
	}
	
	public int getVerSpeed() {
		return this.verSpeed;
	}
	
	public void setHorSpeed(int horSpeed) {
		this.horSpeed = horSpeed;
	}
	
	public int getHorSpeed() {
		return this.horSpeed;
	}
	
	public Fish saveStateToFish() {
		return new Fish(size, x_front, y_front, horSpeed, verSpeed, color, panel, 1);
	}
	
	public Jellyfish saveStateToJellyfish() {
		return new Jellyfish(size, x_front, y_front, horSpeed, verSpeed, color, panel, 1);
	}
	
}
