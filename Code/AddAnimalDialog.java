import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Hashtable;

import javax.swing.*;

/**
 * Nikitenko Pavel
 * 336195524
 */

/**
 * 
 * @author Nikitenko Pavel AddAnimalDialog class that creating JDialog for
 *         adding an animal to the aquarium
 */
public class AddAnimalDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private String[] colors = { "Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink" };
	private String fishType, flowerType, name, whatToDo;
	private int fishSize, fishColor, fishVerSpeed, fishHorSpeed, flowerSize, xPlace, yPlace, ind, fishRegular;
	private boolean flower;
	private static int count = 1, countColor = 1, saveAnimal = 1, saveFlower = 1, restoreAnimal = 1, restoreFlower = 1;
	private AquaPanel panel;
	private AquaFrame frame;
	private static Hashtable<Integer, Swimmable> duplicateAnimal = new Hashtable<Integer, Swimmable>();

	JComboBox<String> type, color;
	JSpinner size, verSpeed, horSpeed, x, y, regular;
	SpinnerModel sizeVal, verSpeedVal, horSpeedVal, xVal, yVal, regularVal;
	JButton ok, duplicate, fish, changeCol, saveState;
	HashSet<JButton> fishButton = new HashSet<JButton>();
	HashSet<JButton> colorButton = new HashSet<JButton>();
	HashSet<JButton> saveStateAnimal = new HashSet<JButton>();
	HashSet<JButton> saveStateFlower = new HashSet<JButton>();
	HashSet<JButton> resAnimal = new HashSet<JButton>();
	HashSet<JButton> resFlower = new HashSet<JButton>();
	JTextField flowerName;

	/**
	 * Constructor of JDialog for animals
	 */
	public AddAnimalDialog() {
		setTitle("Add Animal");
		GridLayout grid = new GridLayout(7, 2);
		this.setLayout(grid);

		type = new JComboBox<String>();
		type.addItem("Fish");
		type.addItem("Jellyfish");
		add(new JLabel("Type:"));
		add(type);

		sizeVal = new SpinnerNumberModel(20, 20, 320, 1);
		size = new JSpinner(sizeVal);
		add(new JLabel("Size:"));
		add(size);

		verSpeedVal = new SpinnerNumberModel(1, 1, 10, 1);
		verSpeed = new JSpinner(verSpeedVal);
		add(new JLabel("Vertical Speed:"));
		add(verSpeed);

		horSpeedVal = new SpinnerNumberModel(1, 1, 10, 1);
		horSpeed = new JSpinner(horSpeedVal);
		add(new JLabel("Horizontal Speed:"));
		add(horSpeed);

		color = new JComboBox<String>();
		for (int i = 0; i < colors.length; i++)
			color.addItem(colors[i]);
		add(new JLabel("Color:"));
		add(color);

		regularVal = new SpinnerNumberModel(1, 1, 10, 1);
		regular = new JSpinner(regularVal);
		add(new JLabel("Regularity:"));
		add(regular);

		ok = new JButton("Add");
		add(ok);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Constructor for flowers and 1/2 duplicate
	public AddAnimalDialog(boolean flower, AquaPanel panel) {
		this.flower = flower;
		if (this.flower) {
			setTitle("Add Flower");
			GridLayout grid = new GridLayout(6, 2);
			this.setLayout(grid);

			type = new JComboBox<String>();
			type.addItem("Laminaria");
			type.addItem("Zostera");
			add(new JLabel("Type:"));
			add(type);

			flowerName = new JTextField();
			add(new JLabel("Name:"));
			add(flowerName);

			sizeVal = new SpinnerNumberModel(100, 100, 320, 1);
			size = new JSpinner(sizeVal);
			add(new JLabel("Size:"));
			add(size);

			xVal = new SpinnerNumberModel(10, 10, 1550, 1);
			x = new JSpinner(xVal);
			add(new JLabel("X Place:"));
			add(x);

			yVal = new SpinnerNumberModel(10, 10, 830, 1);
			y = new JSpinner(yVal);
			add(new JLabel("Y Place:"));
			add(y);

			ok = new JButton("Add");
			add(ok);
		} else {
			this.panel = panel;
			setTitle("Duplicate Animal");
			GridLayout grid = new GridLayout(0, 2);
			this.setLayout(grid);

			for (Swimmable i : panel.fishSet) {
				fish = new JButton("Duplicate " + i.getAnimalName() + " with size " + i.getSize());
				fish.setName(Integer.toString(count));
				duplicateAnimal.put(count, i);
				fishButton.add(fish);
				add(fish);
				count++;
			}
		}

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Constructor for the second 1/2 duplicate
	public AddAnimalDialog(int ind) {
		this.ind = ind;

		setTitle("Duplicate Animal");
		GridLayout grid = new GridLayout(0, 2);
		this.setLayout(grid);

		sizeVal = new SpinnerNumberModel(20, 20, 320, 1);
		size = new JSpinner(sizeVal);
		add(new JLabel("Size:"));
		add(size);

		verSpeedVal = new SpinnerNumberModel(1, 1, 10, 1);
		verSpeed = new JSpinner(verSpeedVal);
		add(new JLabel("Vertical Speed:"));
		add(verSpeed);

		horSpeedVal = new SpinnerNumberModel(1, 1, 10, 1);
		horSpeed = new JSpinner(horSpeedVal);
		add(new JLabel("Horizontal Speed:"));
		add(horSpeed);

		color = new JComboBox<String>();
		for (int i = 0; i < colors.length; i++)
			color.addItem(colors[i]);
		add(new JLabel("Color:"));
		add(color);

		duplicate = new JButton("Duplicate");
		add(duplicate);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Constructor for change color, save state
	public AddAnimalDialog(String whatToDo, AquaPanel panel) {
		this.whatToDo = new String(whatToDo);
		this.panel = panel;
		if (whatToDo.equals("Change Color")) {
			setTitle("Change color to animals");
			GridLayout grid = new GridLayout(0, 1);
			this.setLayout(grid);

			for (Swimmable i : panel.fishSet) {
				changeCol = new JButton("Change color to " + i.getAnimalName() + " with color " + i.getRGBColor());
				changeCol.addActionListener(panel);
				add(changeCol);
				changeCol.setName(Integer.toString(countColor));
				colorButton.add(changeCol);
				countColor++;
			}
		}

		if (whatToDo.equals("Save State")) {
			setTitle("Save state of objects");
			JPanel temp = new JPanel(), temp2 = new JPanel(), temp3 = new JPanel();
			GridLayout grid1, grid2;
			BorderLayout border = new BorderLayout(), border2 = new BorderLayout();
			this.setLayout(border);

			temp.setLayout(border2);
			add(temp, BorderLayout.NORTH);
			if (this.panel.fishSet.size() > 0) {
				temp.add(new JLabel("Animals:"), BorderLayout.WEST);
				grid1 = new GridLayout(this.panel.fishSet.size(), 0);
				temp2.setLayout(grid1);
				for (Swimmable i : panel.fishSet) {
					saveState = new JButton("Save state of " + i.getAnimalName() + " with color " + i.getRGBColor()
							+ " with size " + i.getSize());
					temp2.add(saveState);
					saveState.setName(Integer.toString(saveAnimal));
					saveStateAnimal.add(saveState);
					saveAnimal++;
				}
				add(temp2, BorderLayout.WEST);
			}
			if (this.panel.flowerSet.size() > 0) {
				temp.add(new JLabel("Plants:"), BorderLayout.EAST);
				grid2 = new GridLayout(this.panel.flowerSet.size(), 0);
				temp3.setLayout(grid2);
				for (Immobile i : panel.flowerSet) {
					saveState = new JButton("Save state of " + i.getName() + " with size " + i.getSize());
					temp3.add(saveState);
					saveState.setName(Integer.toString(saveFlower));
					saveStateFlower.add(saveState);
					saveFlower++;
				}

				add(temp3, BorderLayout.EAST);
			}
		}
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	//Constructor for Restore Objects
	public AddAnimalDialog(AquaFrame frame) {
		this.frame = frame;
		
		setTitle("Restore objects from save state");
		JPanel temp = new JPanel(), temp2 = new JPanel(), temp3 = new JPanel();
		GridLayout grid1, grid2;
		BorderLayout border = new BorderLayout(), border2 = new BorderLayout();
		this.setLayout(border);
		
		temp.setLayout(border2);
		add(temp, BorderLayout.NORTH);
		if (this.frame.animalList.size() > 0) {
			temp.add(new JLabel("Animals:"), BorderLayout.WEST);
			grid1 = new GridLayout(this.frame.animalList.size(), 0);
			temp2.setLayout(grid1);
			for (Swimmable i : frame.animalList) {
				saveState = new JButton("Restore state of " + i.getAnimalName() + " with color " + i.getRGBColor()
						+ " with size " + i.getSize());
				temp2.add(saveState);
				saveState.setName(Integer.toString(restoreAnimal));
				resAnimal.add(saveState);
				restoreAnimal++;
			}
			restoreAnimal = 1;
			add(temp2, BorderLayout.WEST);
		}
		if (this.frame.flowerList.size() > 0) {
			temp.add(new JLabel("Plants:"), BorderLayout.EAST);
			grid2 = new GridLayout(this.frame.flowerList.size(), 0);
			temp3.setLayout(grid2);
			for (Immobile i : frame.flowerList) {
				saveState = new JButton("Restore state of " + i.getName() + " with size " + i.getSize());
				temp3.add(saveState);
				saveState.setName(Integer.toString(restoreFlower));
				resFlower.add(saveState);
				restoreFlower++;
			}
			restoreFlower = 1;
			add(temp3, BorderLayout.EAST);
		}
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Function that takes input from the JDialog and inserts to a variables
	 */
	public void addFish() {
		if (this.type != null)
			this.setFishType(type.getItemAt(type.getSelectedIndex()));
		this.setFishColor(colorIndex());
		this.setFishSize((Integer) size.getValue());
		this.setFishVerSpeed((Integer) verSpeed.getValue());
		this.setFishHorSpeed((Integer) horSpeed.getValue());
		if (this.regular != null)
			this.setFishRegular((Integer) regular.getValue());
	}

	public void addFlower() {
		this.setFlowerType(type.getItemAt(type.getSelectedIndex()));
		this.setFlowerSize((Integer) size.getValue());
		this.setXPlace((Integer) x.getValue());
		this.setYPlace((Integer) y.getValue());
		if (flowerName.getText() != null)
			this.setFlowerName(flowerName.getText());
		else
			this.setFlowerName("");
	}

	/**
	 * Function that takes input from the color and making integer from 1 to 9
	 * 
	 * @return
	 */
	public int colorIndex() {
		String col = color.getItemAt(color.getSelectedIndex());
		for (int i = 0; i < colors.length; i++) {
			if (colors[i].equals(col))
				return i + 1;
		}
		return 0;
	}

	public Swimmable getAnimal() {
		Swimmable animal = duplicateAnimal.get(this.ind);
		return (Swimmable) animal.clone();
	}

	/**
	 * Getter of the fihsType
	 * 
	 * @return fishType
	 */
	public String getFishType() {
		return fishType;
	}

	/**
	 * Setter of the fish Type
	 * 
	 * @param fishType
	 */
	public void setFishType(String fishType) {
		this.fishType = fishType;
	}

	/**
	 * getter of the fishSize
	 * 
	 * @return fishSize
	 */
	public int getFishSize() {
		return fishSize;
	}

	/**
	 * Setter of the fishSize
	 * 
	 * @param fishSize
	 */
	public void setFishSize(int fishSize) {
		this.fishSize = fishSize;
	}

	/**
	 * Getter of the fishColor
	 * 
	 * @return fishColor
	 */
	public int getFishColor() {
		return fishColor;
	}

	/**
	 * Setter of the fishColor
	 */
	public void setFishColor(int fishColor) {
		this.fishColor = fishColor;
	}

	/**
	 * Getter of the fishVerSpeed
	 * 
	 * @return fishVerSpeed
	 */
	public int getFishVerSpeed() {
		return fishVerSpeed;
	}

	/**
	 * Setter of the fishVerSpeed
	 * 
	 * @param fishVerSpeed
	 */
	public void setFishVerSpeed(int fishVerSpeed) {
		this.fishVerSpeed = fishVerSpeed;
	}

	/**
	 * Getter of the fishHorSpeed
	 * 
	 * @return fishHorSpeed
	 */
	public int getFishHorSpeed() {
		return fishHorSpeed;
	}

	/**
	 * Setter of the fishHorSpeed
	 * 
	 * @param fishHorSpeed
	 */
	public void setFishHorSpeed(int fishHorSpeed) {
		this.fishHorSpeed = fishHorSpeed;
	}

	/**
	 * Getter of the boolean indication of flower
	 * 
	 * @return true/false
	 */
	public boolean getFlower() {
		return this.flower;
	}

	/**
	 * Getter of the Flower Size
	 * 
	 * @return flower size
	 */
	public int getFlowerSize() {
		return this.flowerSize;
	}

	/**
	 * Setter of the flower size
	 * 
	 * @param flowerSize
	 */
	public void setFlowerSize(int flowerSize) {
		this.flowerSize = flowerSize;
	}

	/**
	 * Getter of the Xplace
	 * 
	 * @return Xplace
	 */
	public int getXPlace() {
		return this.xPlace;
	}

	/**
	 * Setter of the xPlace
	 * 
	 * @param xPlace
	 */
	public void setXPlace(int xPlace) {
		this.xPlace = xPlace;
	}

	/**
	 * Getter of the yPlace
	 * 
	 * @return yPlcae
	 */
	public int getYPlace() {
		return this.yPlace;
	}

	/**
	 * Setter of the Yplace
	 * 
	 * @param yPlace
	 */
	public void setYPlace(int yPlace) {
		this.yPlace = yPlace;
	}

	/**
	 * Getter of the flower type
	 * 
	 * @return flower type
	 */
	public String getFlowerType() {
		return this.flowerType;
	}

	/**
	 * Setter of the flower type
	 * 
	 * @param flowerType
	 */
	public void setFlowerType(String flowerType) {
		this.flowerType = flowerType;
	}

	/**
	 * Getter of the name of flower
	 * 
	 * @return name
	 */
	public String getFlowerName() {
		return this.name;
	}

	/**
	 * Setter of the name of flower
	 * 
	 * @param name
	 */
	public void setFlowerName(String name) {
		this.name = name;
	}

	public void setFishRegular(int regular) {
		this.fishRegular = regular;
	}

	public int getFishRegular() {
		return this.fishRegular;
	}

}
