/**
 * Nikitenko Pavel
 * 336195524
 */
public class Hungry implements HungerState {
	private Fish fish;
	private Jellyfish jellyfish;
	private AquaPanel panel;

	public Hungry(Fish fish, AquaPanel panel) {
		this.fish = fish;
		this.panel = panel;
	}

	public Hungry(Jellyfish jellyfish, AquaPanel panel) {
		this.jellyfish = jellyfish;
		this.panel = panel;
	}

	public void doAction() {
		int x = panel.getWidth() / 2, y = panel.getHeight() / 2;
		if (fish != null) {
			if (this.fish.getX_front() < x - 5) {
				this.fish.setX_dir(1);
				this.fish.setX_front(this.fish.getX_front() + this.fish.getHorSpeed());
			} else if (this.fish.getX_front() > x + 5) {
				this.fish.setX_dir(-1);
				this.fish.setX_front(this.fish.getX_front() - this.fish.getHorSpeed());
			}
			if (this.fish.getY_front() < y - 5)
				this.fish.setY_front(this.fish.getY_front() + this.fish.getVerSpeed());
			else if (this.fish.getY_front() > y + 5)
				this.fish.setY_front(this.fish.getY_front() - this.fish.getVerSpeed());

			if ((this.fish.getX_front() <= x + 5 && this.fish.getX_front() >= x - 5)
					&& (this.fish.getY_front() <= y + 5 && this.fish.getY_front() >= y - 5)) {
				panel.call(this.fish);
				this.panel.setWantEat(false);
				this.fish.setWantEat(0);
			}
		}
		if (jellyfish != null) {
			if (this.jellyfish.getX_front() < x - 5) {
				this.jellyfish.setX_dir(1);
				this.jellyfish.setX_front(this.jellyfish.getX_front() + this.jellyfish.getHorSpeed());
			} else if (this.jellyfish.getX_front() > x + 5) {
				this.jellyfish.setX_dir(-1);
				this.jellyfish.setX_front(this.jellyfish.getX_front() - this.jellyfish.getHorSpeed());
			}
			if (this.jellyfish.getY_front() < y - 5)
				this.jellyfish.setY_front(this.jellyfish.getY_front() + this.jellyfish.getVerSpeed());
			else if (this.jellyfish.getY_front() > y + 5)
				this.jellyfish.setY_front(this.jellyfish.getY_front() - this.jellyfish.getVerSpeed());

			if ((this.jellyfish.getX_front() <= x + 5 && this.jellyfish.getX_front() >= x - 5)
					&& (this.jellyfish.getY_front() <= y + 5 && this.jellyfish.getY_front() >= y - 5)) {
				panel.call(this.jellyfish);
				this.panel.setWantEat(false);
				this.jellyfish.setWantEat(0);
			}
		}
	}
}
