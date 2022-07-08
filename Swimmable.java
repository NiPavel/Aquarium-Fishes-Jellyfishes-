import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;

/**
 * Nikitenko Pavel
 * 336195524
 */

/**
 * Swimmable abstract class that contains an abstract methods of aquarim's animals 
 * and implements a comparable interface
 * @author Nikitenko Pavel
 *
 */
public abstract class Swimmable extends Observer implements SeaCreature, Runnable, MarineAnimal{
	
	protected int horSpeed;
	protected int verSpeed;

	
	/*
	 * Default constructor
	 */
	public Swimmable() {
		this.setHorSpeed(0);
		this.setVerSpeed(0);
	}
	
	/**
	 * Constructor that takes two arguments of int
	 * @param horSpeed 1st argument
	 * @param verSpeed 2nd argument
	 */
	public Swimmable(int horSpeed, int verSpeed) {
		this.setHorSpeed(horSpeed);
		this.setVerSpeed(verSpeed);
	}
	
	
	/**
	 * Function that gets a horSpeed
	 * @return horSpeed
	 */
	public int getHorSpeed() {
		return horSpeed;
	}
	
	/**
	 * Function that sets a horSpeed
	 * @param horSpeed 
	 * @return true if success, false if not
	 */
	public boolean setHorSpeed(int horSpeed) {
		this.horSpeed = horSpeed;
		return this.horSpeed == horSpeed;
	}
	
	/**
	 * Function that gets a verSpeed
	 * @return verSpeed
	 */
	public int getVerSpeed() {
		return verSpeed;
	}
	
	/**
	 * Function that sets a verSpeed
	 * @param verSpeed
	 * @return true if success, false if not
	 */
	public boolean setVerSpeed(int verSpeed) {
		this.verSpeed = verSpeed;
		return this.verSpeed == verSpeed;
	}
	/*
	 * Abstract Functions
	 */
	abstract public String getAnimalName();
	abstract public void setSuspend();
	abstract public void setResume();
	abstract public void setBarrier(CyclicBarrier b);
	abstract public int getSize();
	abstract public void eatInc();
	abstract public int getEatCount();
	abstract public Color getColorr();
	abstract public String getColor();
	abstract public String getRGBColor();
	abstract public void drawCreature(Graphics g);
	abstract public Object clone();
	abstract public int getXFront();
	abstract public int getYFront();
}
