/**
 * Nikitenko Pavel
 * 336195524
 */
public class AnimalFactory implements AbstractSeaFactory {
	private int size, col, x_front, y_front, horSpeed, verSpeed, regular;
	private AquaPanel panel;
	
	public AnimalFactory(int size, int x_front, int y_front, int hor_speed, int ver_speed, int col, AquaPanel panel, int regular) {
		this.size = size;
		this.x_front = x_front;
		this.y_front = y_front;
		this.horSpeed = hor_speed;
		this.verSpeed = ver_speed;
		this.col = col;
		this.panel = panel;
		this.regular = regular;
	}
	@Override
	public SeaCreature produceSeaCreature(String type) {
		if (type.equals("Fish"))
			return new Fish(size,x_front,y_front,horSpeed,verSpeed,col,panel, regular);
		else if(type.equals("Jellyfish"))
			return new Jellyfish(size,x_front,y_front,horSpeed,verSpeed,col,panel, regular);
		return null;
	}
	
}
