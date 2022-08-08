import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.*;

/**
 * Nikitenko Pavel
 * 336195524
 */

/**
 * Fish class that extends from Swimmable and building an object of the
 * Swimmable Fish
 * 
 * @author Nikitenko Pavel
 *
 */
public class Fish extends Swimmable {
	private final int EAT_DISTANCE = 4;
	private int size, col, eatCount, x_front, y_front, x_dir, y_dir, regular, wantEat;
	private boolean go_pressed = true, food = false, food_pressed = false, reset = false, memento = false;
	private CyclicBarrier barrier;
	private AquaPanel panel;
	private Thread thread;
	private Color color;
	private HungerState state;

	/**
	 * Default constructor
	 */
	public Fish() {
		super();
		this.setCol(0);
		this.setEatCount(0);
		this.setSize(0);
		this.setX_dir(0);
		this.setX_front(0);
		this.setY_dir(0);
		this.setY_front(0);
		this.setRegular(0);
		this.panel = null;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Constructor that takes 6 arguments of ints
	 * 
	 * @param size     size of the fish
	 * @param x_front
	 * @param y_front
	 * @param horSpeed
	 * @param verSpeed
	 * @param col      color of the fish
	 */
	public Fish(int size, int x_front, int y_front, int horSpeed, int verSpeed, int col, AquaPanel panel, int regular) {
		super(horSpeed, verSpeed);
		this.setCol(col);
		this.setEatCount(0);
		this.setSize(size);
		this.setX_dir(1);
		this.setY_dir(1);
		this.setX_front(x_front);
		this.setY_front(y_front);
		this.setRegular(regular);
		this.panel = panel;
		thread = new Thread(this);
		thread.start();
	}

	public Fish(int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col, AquaPanel panel,
			int regular) {
		super(horSpeed, verSpeed);
		this.setColor(col);
		this.setEatCount(0);
		this.setSize(size);
		this.setX_dir(1);
		this.setY_dir(1);
		this.setX_front(x_front);
		this.setY_front(y_front);
		this.setRegular(regular);
		this.panel = panel;
		this.memento = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Copy Constructor
	 * 
	 * @param other object of the Fish
	 */
	public Fish(Fish other) {
		this();
		if (other != null) {
			this.setCol(other.getCol());
			this.setEatCount(other.getEatCount());
			this.setSize(other.getSize());
			this.setHorSpeed(other.getHorSpeed());
			this.setVerSpeed(other.getVerSpeed());
			this.setX_dir(other.getX_dir());
			this.setX_front(other.getX_front());
			this.setY_dir(other.getY_dir());
			this.setY_front(other.getY_front());
			this.setRegular(other.getRegular());
			this.setAquaPanel(other.getPanel());
		}
	}

	/**
	 * Function that takes a non change param
	 * 
	 * @return maximum of food that fish can eat
	 */
	public int getEAT_DISTANCE() {
		return EAT_DISTANCE;
	}

	/**
	 * Function that gets a size of the Fish
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Function that set a size of the Fish
	 * 
	 * @param size
	 * @return true if success, false if not
	 */
	public boolean setSize(int size) {
		this.size = size;
		return this.size == size;
	}

	/**
	 * Function that gets a number of color
	 * 
	 * @return number of color
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Function that sets a number of color
	 * 
	 * @param col
	 * @return true if success, false if not
	 */
	public boolean setCol(int col) {
		if (col >= 1 && col <= 9)
			this.col = col;
		return this.col == col;
	}

	/**
	 * Function that gets a count of how much fish ate
	 */
	public int getEatCount() {
		return eatCount;
	}

	/**
	 * Function that sets a number of food
	 * 
	 * @param eatCount
	 * @return true if success, false if not
	 */
	public boolean setEatCount(int eatCount) {
		this.eatCount = eatCount;
		return this.eatCount == eatCount;
	}

	/**
	 * Function that gets a X_front
	 * 
	 * @return X_front
	 */
	public int getX_front() {
		return x_front;
	}

	/**
	 * Function that sets a X_front
	 * 
	 * @param x_front
	 * @return true is success, false if not
	 */
	public boolean setX_front(int x_front) {
		this.x_front = x_front;
		return this.x_front == x_front;
	}

	/**
	 * Function that gets an Y_front
	 * 
	 * @return Y_front
	 */
	public int getY_front() {
		return y_front;
	}

	/**
	 * Function that sets an Y_front
	 * 
	 * @param y_front
	 * @return true if success, false if not
	 */
	public boolean setY_front(int y_front) {
		this.y_front = y_front;
		return this.y_front == y_front;
	}

	/**
	 * Function that gets a X_dir
	 * 
	 * @return x_dir
	 */
	public int getX_dir() {
		return x_dir;
	}

	/**
	 * Function that sets a x_dir
	 * 
	 * @param x_dir
	 * @return true if success, false if not
	 */
	public boolean setX_dir(int x_dir) {
		this.x_dir = x_dir;
		return this.x_dir == x_dir;
	}

	/**
	 * Function that gets an y_dir
	 * 
	 * @return y_dir
	 */
	public int getY_dir() {
		return y_dir;
	}

	/**
	 * Function that sets an y_dir
	 * 
	 * @param y_dir
	 * @return true if success, false if not
	 */
	public boolean setY_dir(int y_dir) {
		this.y_dir = y_dir;
		return this.y_dir == y_dir;
	}

	/**
	 * The setter of the regularity
	 * 
	 * @param regular
	 */
	public void setRegular(int regular) {
		this.regular = regular;
	}

	/**
	 * The getter of the regularity
	 * 
	 * @return regular
	 */
	public int getRegular() {
		return this.regular;
	}

	/**
	 * Setter of the aqua panel
	 * 
	 * @param panel
	 */
	public void setAquaPanel(AquaPanel panel) {
		this.panel = panel;
	}

	/**
	 * Getter of the aquapanel
	 * 
	 * @return
	 */
	public AquaPanel getPanel() {
		return this.panel;
	}

	/**
	 * Function that returns a name of the class
	 */
	public String getAnimalName() {
		return "Fish";
	}

	public Color getColorr() {
		return color;
	}

	public int getXFront() {
		return this.x_front;
	}

	public int getYFront() {
		return this.y_front;
	}

	/**
	 * Function that making a Color object for a paint
	 * 
	 * @return Color object
	 */
	public void setColor(Color color) {
		String[] colors = { "Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink" };
		if (color == null) {
			switch (colors[this.getCol() - 1]) {
			case "Black":
				this.color = Color.BLACK;
				break;
			case "Red":
				this.color = Color.RED;
				break;
			case "Blue":
				this.color = Color.BLUE;
				break;
			case "Green":
				this.color = Color.GREEN;
				break;
			case "Cyan":
				this.color = Color.CYAN;
				break;
			case "Orange":
				this.color = Color.ORANGE;
				break;
			case "Yellow":
				this.color = Color.YELLOW;
				break;
			case "Magenta":
				this.color = Color.MAGENTA;
				break;
			case "Pink":
				this.color = Color.PINK;
				break;
			}
		} else
			this.color = color;
	}
	
	public void setWantEat(int num) {
		this.wantEat = num;
	}
	public int getWantEat() {
		return this.wantEat;
	}

	/**
	 * Function that return a color in string
	 */
	public String getColor() {
		String[] colors = { "Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink" };
		return colors[this.getCol() - 1];
	}

	public String getRGBColor() {
		if (color != null)
			return "(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
		return this.getColor();
	}

	/**
	 * Function that increase an eatCount buy 1
	 */
	public void eatInc() {
		this.setEatCount(this.getEatCount() + 1);
	}

	/**
	 * Function that checking if fish is changed buy Eat_Distance and doing the
	 * changes
	 */
	public void changeFish() {
		if (this.getEatCount() == this.getEAT_DISTANCE()) {
			this.setSize(this.getSize() + 1);
			this.setEatCount(0);
		}
	}

	/**
	 * Function that changing color to a Fish
	 */
	public void changeColor() {
		if (this.getCol() != 9) {
			this.setCol(this.getCol() + 1);
		} else
			this.setCol(1);
	}

	/**
	 * Function that checking if two objects of Fish are equal
	 */
	public boolean equals(Object other) {
		boolean ans = false;
		if (other instanceof Fish) {
			ans = this.getSize() == ((Fish) other).getSize() && this.getCol() == ((Fish) other).getCol()
					&& this.getEatCount() == ((Fish) other).getEatCount()
					&& this.getX_dir() == ((Fish) other).getX_dir() && this.getX_front() == ((Fish) other).getX_front()
					&& this.getY_dir() == ((Fish) other).getY_dir() && this.getY_front() == ((Fish) other).getY_front();
		}
		return ans;
	}

	/**
	 * Override toString Function that helps to print a class
	 */
	public String toString() {
		return String.format("%-15s %-15s %-15s %-15s", this.getAnimalName(), this.getColor(), this.getSize(),
				this.getEatCount());
	}

	/**
	 * Function that running a thread of the fish
	 */
	public void run() {
		if (!memento) {
			this.setX_front(new Random().nextInt(1480 - 10 + 1) + 10);
			this.setY_front(new Random().nextInt(750 - 10 + 1) + 10);
		}
		while (!reset) {
			try {
				Thread.sleep(10);
				synchronized (this) {
					while (!go_pressed || panel.getIsSleeping())
						wait();
				}
				if (go_pressed && !panel.getIsSleeping()) {
					if (food && panel.getWorm() && this.wantEat >= 500) {
						this.state = new Hungry(this, this.panel);
						state.doAction();
					} 
					else
					{
						this.state = new Satiated(this, this.panel);
						state.doAction();
					}
				}

				if (food_pressed)
					this.barrier.await();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Function that make a thread to wait...
	 */
	public void setSuspend() {
		this.go_pressed = false;
	}

	/**
	 * Function that awake a thread
	 */
	public void setResume() {
		if (!go_pressed || !panel.getIsSleeping()) {
			synchronized (this) {
				notify();
				this.go_pressed = true;
			}
		}
	}

	/**
	 * Function that set a barrier for a thread
	 */
	public void setBarrier(CyclicBarrier b) {
		this.barrier = b;
		this.food_pressed = true;
	}

	/**
	 * Function that giving an indication for feeding
	 * 
	 * @param food
	 */
	public void setFood(boolean food) {
		this.food = food;
	}

	public void setReset() {
		this.reset = true;
		thread.interrupt();
	}

	/**
	 * Function that checking barrier
	 */
	void checkBarrier() {
		if (this.food_pressed) {
			this.food_pressed = false;
			if (!food)
				food = true;
			this.barrier.reset();
		}
	}

	@Override
	public void drawCreature(Graphics g) {
		this.setColor(color);
		g.setColor(color);
		if (x_dir == 1) // fish swims to right side
		{
			// Body of fish
			g.fillOval(x_front - size, y_front - size / 4, size, size / 2);

			// Tail of fish
			int[] x_t = { x_front - size - size / 4, x_front - size - size / 4, x_front - size };
			int[] y_t = { y_front - size / 4, y_front + size / 4, y_front };
			Polygon t = new Polygon(x_t, y_t, 3);
			g.fillPolygon(t);

			// Eye of fish
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue()));
			g2.fillOval(x_front - size / 5, y_front - size / 10, size / 10, size / 10);

			// Mouth of fish
			if (size > 70)
				g2.setStroke(new BasicStroke(3));
			else if (size > 30)
				g2.setStroke(new BasicStroke(2));
			else
				g2.setStroke(new BasicStroke(1));
			g2.drawLine(x_front, y_front, x_front - size / 10, y_front + size / 10);
			g2.setStroke(new BasicStroke(1));
		} else // fish swims to left side
		{
			// Body of fish
			g.fillOval(x_front, y_front - size / 4, size, size / 2);

			// Tail of fish
			int[] x_t = { x_front + size + size / 4, x_front + size + size / 4, x_front + size };
			int[] y_t = { y_front - size / 4, y_front + size / 4, y_front };
			Polygon t = new Polygon(x_t, y_t, 3);
			g.fillPolygon(t);

			// Eye of fish
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue()));
			g2.fillOval(x_front + size / 10, y_front - size / 10, size / 10, size / 10);

			// Mouth of fish
			if (size > 70)
				g2.setStroke(new BasicStroke(3));
			else if (size > 30)
				g2.setStroke(new BasicStroke(2));
			else
				g2.setStroke(new BasicStroke(1));
			g2.drawLine(x_front, y_front, x_front + size / 10, y_front + size / 10);
			g2.setStroke(new BasicStroke(1));
		}

	}

	public Object clone() {
		Object clone = null;
		clone = new Fish(this);
		return clone;
	}

	public void update() {
		if (this.panel.getAte()) {
			this.panel.setAte(false);
		}
	}

	public void PaintFish(Color color) {
		if (this.panel.getColor() != null)
			this.setColor(this.panel.getColor());
		else
			this.setColor(null);
	}

}
