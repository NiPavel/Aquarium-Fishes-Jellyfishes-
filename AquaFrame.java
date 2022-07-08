import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Nikitenko Pavel
 * 336195524
 */

/**
 * 
 * @author Павел AquaFrame class that creating a JFrame for an aquarium
 */
public class AquaFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JMenuBar jmb;
	JMenu jm1, jm2, jm3, jm4;
	JMenuItem jmt11, jmt21, jmt22, jmt23, jmt31, jmt41, jmt42;
	private AquaPanel panel;
	private AddAnimalDialog dialog;
	private AnimalOriginator animal;
	private FlowerOriginator flower;
	private Swimmable helpAnimal;
	private Immobile helpFlower;
	private int countAnimal = 1, countFlower = 1;
	List<Swimmable> animalList = new ArrayList<Swimmable>();
	List<Immobile> flowerList = new ArrayList<Immobile>();

	/**
	 * Constructor of the JFrame
	 */
	public AquaFrame() {
		super("my Aquarium");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jmb = new JMenuBar();
		jm1 = new JMenu("File");
		jm2 = new JMenu("Background");
		jm3 = new JMenu("Help");
		jm4 = new JMenu("Memento");

		jmt11 = new JMenuItem("Exit");
		jmt11.addActionListener(this);
		jmt21 = new JMenuItem("Image");
		jmt21.addActionListener(this);
		jmt22 = new JMenuItem("Blue");
		jmt22.addActionListener(this);
		jmt23 = new JMenuItem("None");
		jmt23.addActionListener(this);
		jmt31 = new JMenuItem("Help");
		jmt31.addActionListener(this);
		jmt41 = new JMenuItem("Save Object State");
		jmt41.addActionListener(this);
		jmt42 = new JMenuItem("Restore Object State");
		jmt42.addActionListener(this);

		jm1.add(jmt11);
		jm2.add(jmt21);
		jm2.add(jmt22);
		jm2.add(jmt23);
		jm3.add(jmt31);
		jm4.add(jmt41);
		jm4.add(jmt42);

		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);

		add(jmb, BorderLayout.NORTH);
		panel = new AquaPanel(this);
		add(panel);

		setSize(1566, 840);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * Function that making an action from the user's doing in the GUI
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(jmt11))
			System.exit(0);
		if (e.getSource().equals(jmt21)) {
			panel.setImg(true);
		}
		if (e.getSource().equals(jmt22)) {
			panel.setImg(false);
			panel.setBackground(Color.BLUE);
		}
		if (e.getSource().equals(jmt23)) {
			panel.setImg(false);
			panel.setBackground(getBackground());
		}
		
		//Save State
		if (e.getSource().equals(jmt41)) {
			if (this.panel.fishSet.size() > 0)
				dialog = new AddAnimalDialog("Save State", this.panel);
			else
				JOptionPane.showMessageDialog(this, "Add at least 1 animal!");
		}

		if (dialog != null) {
			if (dialog.saveStateAnimal.size() > 0) {
				for (JButton i : dialog.saveStateAnimal) {
					i.addActionListener(this);
					if (e.getSource().equals(i)) {
						for (Swimmable j : this.panel.fishSet) {
							if (countAnimal == Integer.parseInt(i.getName())) {
								animal = new AnimalOriginator(this.panel);
								animal.setColor(j.getColorr());
								animal.setSize(j.getSize());
								animal.setHorSpeed(j.getHorSpeed());
								animal.setVerSpeed(j.getVerSpeed());
								animal.setXFront(j.getXFront());
								animal.setYFront(j.getYFront());
								if (j instanceof Fish)
									animalList.add(animal.saveStateToFish());
								else
									animalList.add(animal.saveStateToJellyfish());
								dialog.dispose();
								JOptionPane.showMessageDialog(this, "You have saved the " + j.getAnimalName()
										+ " with color " + j.getRGBColor() + " and size " + j.getSize());

							}
							countAnimal++;
						}
					}
				}
			}
			if (dialog.saveStateFlower.size() > 0) {
				for (JButton i : dialog.saveStateFlower) {
					i.addActionListener(this);
					if (e.getSource().equals(i)) {
						for (Immobile j : this.panel.flowerSet) {
							if (countFlower == Integer.parseInt(i.getName())) {
								flower = new FlowerOriginator(this.panel);
								flower.setSize(j.getSize());
								flower.setX(j.getX());
								flower.setY(j.getY());
								if (j instanceof Laminaria)
									flowerList.add(flower.saveToLaminaria());
								else
									flowerList.add(flower.saveToZostera());
								dialog.dispose();
								JOptionPane.showMessageDialog(this, "You have saved the flower with size "  + j.getSize());
							}
							countFlower++;
						}
					}
				}
			}
		}
		
		//Restore State
		if (e.getSource().equals(jmt42)) {
			if (animalList.size() > 0 || flowerList.size() > 0)
				dialog = new AddAnimalDialog(this);
			else
				JOptionPane.showMessageDialog(this, "Save at least 1 animal or 1 flower!");
		}
		
		if (dialog != null) {
			if (dialog.resAnimal.size() > 0) {
				for(JButton i : dialog.resAnimal) {
					i.addActionListener(this);
					if (e.getSource().equals(i)) {
						helpAnimal = animalList.get(Integer.parseInt(i.getName()) - 1);
						if (helpAnimal instanceof Fish) {
							helpAnimal = new Fish(helpAnimal.getSize(), helpAnimal.getXFront(), helpAnimal.getYFront(),
									helpAnimal.getHorSpeed(), helpAnimal.getVerSpeed(), helpAnimal.getColorr(), this.panel, 1);
						}
						else {
							helpAnimal = new Jellyfish(helpAnimal.getSize(), helpAnimal.getXFront(), helpAnimal.getYFront(),
									helpAnimal.getHorSpeed(), helpAnimal.getVerSpeed(), helpAnimal.getColorr(), this.panel, 1);
						}
						this.panel.fishSet.add(helpAnimal);
						dialog.dispose();
						JOptionPane.showMessageDialog(this, "You have restored the " + helpAnimal.getAnimalName()
						+ " and size " + helpAnimal.getSize());
					}
				}
			}
			
			if (dialog.resFlower.size() > 0) {
				for(JButton i : dialog.resFlower) {
					i.addActionListener(this);
					if (e.getSource().equals(i)) {
						helpFlower = flowerList.get(Integer.parseInt(i.getName()) - 1);
						if (helpFlower instanceof Laminaria) {
							helpFlower = new Laminaria(helpFlower.getSize(), helpFlower.getX(), helpFlower.getY());
						}
						else {
							helpFlower = new Zostera(helpFlower.getSize(), helpFlower.getX(), helpFlower.getY());
						}
						this.panel.flowerSet.add(helpFlower);
						dialog.dispose();
						JOptionPane.showMessageDialog(this, "You have restored the flower with size "  + helpFlower.getSize());
					}
				}
			}
		}

		if (e.getSource().equals(jmt31))
			JOptionPane.showMessageDialog(this, "Home Work 3\n" + "GUI @Threads");
	}

	/**
	 * Main of the programm
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new AquaFrame();
	}

}
