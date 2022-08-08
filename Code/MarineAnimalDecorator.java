import java.awt.Color;

/**
 * Nikitenko Pavel
 * 336195524
 */
public class MarineAnimalDecorator implements MarineAnimal{
	private MarineAnimal animal;
	private Color color;
	
	public MarineAnimalDecorator(MarineAnimal animal, Color color){
		this.animal = animal;
		this.color = color;
	}
	
	public void PaintFish(Color color) {
		//Actually we don't need this
	}
}
