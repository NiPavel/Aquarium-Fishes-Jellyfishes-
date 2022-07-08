import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CyclicBarrier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Nikitenko Pavel
 * 336195524
 */

/**
 * 
 * @author Nikitenko Pavel AquaPanel class that creating a JPanel of the
 *         Aquarium
 */
public class AquaPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private int countClick = 0, sum = 0, ind, count = 1;
	private AddAnimalDialog dialog;
	private String[] columns = { "Animal", "Color", "Size", "Hor.speed", "Ver.speed", "Eat counter" };
	private boolean worm = false, img = false, ate = false, isSleeping = false, wantEat = false;
	private Swimmable animal;
	private Immobile flower;
	private Worm w;
	private AbstractSeaFactory seaFactory;
	private SeaCreature creature;
	private AquaFrame frame;
	private Color newColor;

	Image image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Павел\\Desktop\\pic.jpg");
	JPanel temp, JPanelDecorator;
	JLabel hungry;
	BorderLayout border = new BorderLayout();
	JButton addAnimal, addFlower, duplicate, sleep, wake_up, reset, food, info, decorator, exit, color;
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);
	JScrollPane sp = new JScrollPane(table);
	HashSet<Swimmable> fishSet = new HashSet<Swimmable>();
	HashSet<Immobile> flowerSet = new HashSet<Immobile>();

	/**
	 * Constructor of the JPanel
	 */
	public AquaPanel(AquaFrame frame) {
		this.frame = frame;

		temp = new JPanel();
		GridLayout grid = new GridLayout(0, 9);
		temp.setLayout(grid);

		addAnimal = new JButton("Add Animal");
		addAnimal.addActionListener(this);
		addAnimal.setBackground(Color.GRAY);
		temp.add(addAnimal);

		addFlower = new JButton("Add Flower");
		addFlower.addActionListener(this);
		addFlower.setBackground(Color.GRAY);
		temp.add(addFlower);

		duplicate = new JButton("Duplicate Animal");
		duplicate.addActionListener(this);
		duplicate.setBackground(Color.GRAY);
		temp.add(duplicate);

		sleep = new JButton("Sleep");
		sleep.addActionListener(this);
		sleep.setBackground(Color.GRAY);
		temp.add(sleep);

		wake_up = new JButton("Wake up");
		wake_up.addActionListener(this);
		wake_up.setBackground(Color.GRAY);
		temp.add(wake_up);

		reset = new JButton("Reset");
		reset.addActionListener(this);
		reset.setBackground(Color.GRAY);
		temp.add(reset);

		food = new JButton("Food");
		food.addActionListener(this);
		food.setBackground(Color.GRAY);
		temp.add(food);

		decorator = new JButton("Decorator");
		decorator.addActionListener(this);
		decorator.setBackground(Color.GRAY);
		temp.add(decorator);

		exit = new JButton("Exit");
		exit.addActionListener(this);
		exit.setBackground(Color.GRAY);
		temp.add(exit);

		frame.add(temp, BorderLayout.SOUTH);

		JPanelDecorator = new JPanel();
		JPanelDecorator.setLayout(border);

		color = new JButton("Change Color");
		color.addActionListener(this);
		JPanelDecorator.add(color, BorderLayout.PAGE_END);

		JPanelDecorator.setVisible(false);
		frame.add(JPanelDecorator, BorderLayout.BEFORE_LINE_BEGINS);

		setVisible(true);
	}

	@Override
	/**
	 * Function that making an action from the user's doing in the GUI
	 */
	public void actionPerformed(ActionEvent e) {
		// Add Animal
		if (e.getSource().equals(addAnimal)) {
			dialog = new AddAnimalDialog();
			dialog.ok.addActionListener(this);
		}

		// Add Flower
		if (e.getSource().equals(addFlower)) {
			dialog = new AddAnimalDialog(true, this);
			dialog.ok.addActionListener(this);
		}

		// Duplicate Fish
		if (e.getSource().equals(duplicate)) {
			if (fishSet.size() > 0) {
				dialog = new AddAnimalDialog(false, this);
				for (JButton i : dialog.fishButton)
					i.addActionListener(this);
			} else
				JOptionPane.showMessageDialog(this, "You have to add 1 fish or jellyfish at least!");
		}

		// Decorator
		if (e.getSource().equals(decorator)) {
			countClick += 1;
			model = (DefaultTableModel) table.getModel();
			if (model.getColumnCount() == 0) {
				for (int i = 0; i < columns.length; i++)
					model.addColumn(columns[i]);
			}
			if (fishSet.size() != 0) {
				for (Swimmable i : fishSet) {
					if (i.getColorr() == null) {
						model.addRow(new Object[] { i.getAnimalName(), i.getColor(), i.getSize(), i.getHorSpeed(),
								i.getVerSpeed(), i.getEatCount() });
					} else {
						model.addRow(new Object[] { i.getAnimalName(), i.getRGBColor(), i.getSize(), i.getHorSpeed(),
								i.getVerSpeed(), i.getEatCount() });
					}
				}
				model.addRow(new Object[] { "Total", "", "", "", "", sum });
			}

			JPanelDecorator.add(sp, BorderLayout.CENTER);
			sp.setSize(1024, 576);
			sp.setVisible(true);

			if (countClick % 2 == 1) {
				JPanelDecorator.setSize(1024, 576);
				JPanelDecorator.setVisible(true);
				this.setVisible(false);
			}

			else {
				JPanelDecorator.setVisible(false);
				this.setVisible(true);
				model.setRowCount(0);
			}

		}

		// ChooseColor
		if (e.getSource().equals(color) && fishSet.size() > 0)
			dialog = new AddAnimalDialog("Change Color", this);

		if (dialog != null && dialog.colorButton.size() > 0) {
			for (JButton i : dialog.colorButton) {
				if (e.getSource().equals(i)) {
					for (Swimmable j : fishSet) {
						if (count == Integer.parseInt(i.getName())) {
							newColor = JColorChooser.showDialog(this, "Select a color", Color.green);
							j.PaintFish(newColor);
							dialog.dispose();
						} else {
							newColor = null;
						}
						count++;
					}
					model.setRowCount(0);

					for (Swimmable k : fishSet) {
						model.addRow(new Object[] { k.getAnimalName(), k.getRGBColor(), k.getSize(), k.getHorSpeed(),
								k.getVerSpeed(), k.getEatCount() });
					}
					model.addRow(new Object[] { "Total", "", "", "", "", sum });
				}
			}
		}

		// Exit
		if (e.getSource().equals(exit))
			System.exit(0);

		// Food
		if (e.getSource().equals(food)) {
			this.worm = true;
			for (Swimmable i : fishSet) {
				i.setBarrier(new CyclicBarrier(fishSet.size()));
			}
			for (Swimmable i : fishSet) {
				if (i instanceof Fish)
					((Fish) i).checkBarrier();
				else
					((Jellyfish) i).checkBarrier();
			}

		}

		// Reset
		if (e.getSource().equals(reset))

		{
			for (Swimmable i : fishSet) {
				if (i instanceof Fish)
					((Fish) i).setReset();
				else
					((Jellyfish) i).setReset();
			}
			fishSet.clear();
			flowerSet.clear();
			sum = 0;
			this.worm = false;
			hungry.setVisible(false);
			repaint();
		}

		// Sleep
		if (e.getSource().equals(sleep)) {
			for (Swimmable i : fishSet)
				i.setSuspend();
			this.isSleeping = true;
		}

		// Wake up
		if (e.getSource().equals(wake_up)) {
			this.isSleeping = false;
			for (Swimmable i : fishSet)
				i.setResume();
		}

		// Fish button of the JDialog
		if (this.dialog != null && dialog.fishButton.size() > 0) {
			for (JButton i : dialog.fishButton) {
				if (e.getSource().equals(i)) {
					ind = Integer.parseInt(dialog.fish.getName());
					dialog.dispose();
					dialog = new AddAnimalDialog(ind);
					dialog.duplicate.addActionListener(this);
				}
			}
		}

		// Duplicate button
		if (this.dialog != null) {
			if (e.getSource().equals(dialog.duplicate)) {
				if (fishSet.size() < 5) {
					animal = dialog.getAnimal();
					dialog.addFish();
					animal.setHorSpeed(dialog.getFishHorSpeed());
					animal.setVerSpeed(dialog.getFishVerSpeed());
					if (animal instanceof Fish) {
						((Fish) animal).setSize(dialog.getFishSize());
						((Fish) animal).setCol(dialog.getFishColor());
					} else {
						((Jellyfish) animal).setSize(dialog.getFishSize());
						((Jellyfish) animal).setCol(dialog.getFishColor());
					}

					fishSet.add(animal);

				} else
					JOptionPane.showMessageDialog(this, "You have already 5 animals in aquarium!");

				this.dialog.dispose();
			}
		}

		// OK of the JDialog
		if (this.dialog != null) {
			if (!this.dialog.getFlower()) {
				if (e.getSource().equals(dialog.ok)) {
					if (fishSet.size() < 5) {
						dialog.addFish();
						seaFactory = FactoryProducer.getFactory(dialog.getFishSize(), 0, 0, dialog.getFishHorSpeed(),
								dialog.getFishVerSpeed(), dialog.getFishColor(), this, dialog.getFishRegular());
						if (dialog.getFishType().equals("Fish")) {
							creature = seaFactory.produceSeaCreature(dialog.getFishType());
							animal = ((Fish) creature);
						} else {
							creature = seaFactory.produceSeaCreature(dialog.getFishType());
							animal = ((Jellyfish) creature);
						}

						fishSet.add(animal);
					} else
						JOptionPane.showMessageDialog(this, "You have already 5 animals in aquarium!");
					dialog.dispose();
				}
			} else {
				if (e.getSource().equals(dialog.ok)) {
					if (flowerSet.size() < 5) {
						dialog.addFlower();
						seaFactory = FactoryProducer.getFactory(dialog.getFlowerName(), dialog.getFlowerSize(),
								dialog.getXPlace(), dialog.getYPlace(), this);
						if (dialog.getFlowerType().equals("Laminaria")) {
							creature = seaFactory.produceSeaCreature(dialog.getFlowerType());
							flower = ((Laminaria) creature);
						} else {
							creature = seaFactory.produceSeaCreature(dialog.getFlowerType());
							flower = ((Zostera) creature);
						}

						flowerSet.add(flower);
					} else
						JOptionPane.showMessageDialog(this, "You have already 5 flowers in aquarium!");
					dialog.dispose();
				}
			}
		}
	}

	@Override
	/**
	 * Function that painting an animals and image background
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.img) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, 0, 0, this);
		}
		for (Swimmable i : fishSet)
			i.drawCreature(g);

		for (Immobile i : flowerSet)
			i.drawCreature(g);

		if (worm) {
			w = Worm.getInstance();
			w.setWorm(this);
			w.drawWorm(g);
		}
		repaint();
	}

	/**
	 * Callback of the aquapanel from the Fish, Jellyfish
	 * 
	 * @param f Swimmable(Fish, Jellyfish)
	 */
	public void call(Swimmable f) {
		this.ate = true;
		this.worm = false;
		sum += 1;
		if (f instanceof Fish) {
			((Fish) f).setFood(worm);
			f.eatInc();
			((Fish) f).changeFish();
		}
		if (f instanceof Jellyfish) {
			((Jellyfish) f).setFood();
			f.eatInc();
			((Jellyfish) f).changeJellyfish();
		}
		for (Swimmable i : fishSet) {
			if (ate) {
				if (i instanceof Fish)
					((Fish) i).setFood(worm);
				else
					((Jellyfish) i).setFood();
			}
		}
	}

	/**
	 * Function that giving indication to am image background
	 * 
	 * @param bool
	 */
	public void setImg(boolean bool) {
		this.img = bool;
	}

	/**
	 * Function that giving indication if animal ate the food
	 * 
	 * @param ate
	 */
	public void setAte(boolean ate) {
		this.ate = ate;
	}

	public boolean getAte() {
		return this.ate;
	}

	public boolean getWorm() {
		return this.worm;
	}

	public boolean getIsSleeping() {
		return this.isSleeping;
	}

	public void setWantEat(boolean wantEat) {
		this.wantEat = wantEat;
		this.noitifyAllObservers();
	}

	public boolean getWantEat() {
		return this.wantEat;
	}

	public void noitifyAllObservers() {
		for (Swimmable i : fishSet) {
			i.update();
			if (this.hungry == null) {
				this.hungry = new JLabel("Animal is hungry!");
				this.hungry.setForeground(Color.RED);
				this.hungry.setHorizontalTextPosition(JLabel.RIGHT);
				this.hungry.setVerticalTextPosition(JLabel.TOP);
				this.add(hungry);
				this.hungry.setSize(getPreferredSize());
			}
			if (this.wantEat)
				hungry.setVisible(true);
			else
				hungry.setVisible(false);
		}
	}

	public Color getColor() {
		return this.newColor;
	}
}
