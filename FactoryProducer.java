/**
 * Nikitenko Pavel
 * 336195524
 */
public class FactoryProducer {
	public static AbstractSeaFactory getFactory(int size, int x_front, int y_front, int horSpeed, int verSpeed, 
			int col,AquaPanel panel, int regular) {
		return new AnimalFactory(size, x_front, y_front, horSpeed, verSpeed, col, panel, regular);
	}
	public static AbstractSeaFactory getFactory(String name, int size, int x, int y, AquaPanel panel) {
		return new PlantFactory(name, size, x, y, panel);
	}
}
