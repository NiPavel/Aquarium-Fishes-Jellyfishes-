/**
 * Nikitenko Pavel
 * 336195524
 */
public class PlantFactory implements AbstractSeaFactory{
	private int size, x ,y;
	private AquaPanel panel;
	private String name;
	
	public PlantFactory(String name, int size, int x, int y, AquaPanel panel) {
		this.size = size;
		this.x = x;
		this.y = y;
		this.panel = panel;
		this.name = name;
	}
	@Override
	public SeaCreature produceSeaCreature(String type) {
		if (type.equals("Laminaria"))
			return new Laminaria(name, size, x, y);
		else if (type.equals("Zostera"))
			return new Zostera(name, size, x, y);
		return null;
	}
	
}
