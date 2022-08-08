/**
 * Nikitenko Pavel
 * 336195524
 */
public class Satiated implements HungerState{
	private Fish fish;
	private Jellyfish jellyfish;
	private AquaPanel panel;
	
	public Satiated(Fish fish, AquaPanel panel) {
		this.fish = fish;
		this.panel = panel;
	}
	
	public Satiated(Jellyfish jellyfish, AquaPanel panel) {
		this.jellyfish = jellyfish;
		this.panel = panel;
	}
	
	public void doAction() {
		if (fish != null) {
			this.fish.setX_front(this.fish.getX_front() + this.fish.getHorSpeed() * this.fish.getX_dir());
			this.fish.setY_front(this.fish.getY_front() + this.fish.getVerSpeed() * this.fish.getY_dir());
			if (this.fish.getX_front() > panel.getWidth())
				this.fish.setX_dir(-1);
			if (this.fish.getY_front() > panel.getHeight())
				this.fish.setY_dir(-1);
			if (this.fish.getX_front() < 0)
				this.fish.setX_dir(1);
			if (this.fish.getY_front() < 0)
				this.fish.setY_dir(1);
			if (this.fish.getWantEat() < 500) {
				this.fish.setWantEat(this.fish.getWantEat() + this.fish.getRegular());
			} else
				this.panel.setWantEat(true);
		}
		if (jellyfish != null) {
			this.jellyfish.setX_front(this.jellyfish.getX_front() + this.jellyfish.getHorSpeed() * this.jellyfish.getX_dir());
			this.jellyfish.setY_front(this.jellyfish.getY_front() + this.jellyfish.getVerSpeed() * this.jellyfish.getY_dir());
			if (this.jellyfish.getX_front() > panel.getWidth())
				this.jellyfish.setX_dir(-1);
			if (this.jellyfish.getY_front() > panel.getHeight())
				this.jellyfish.setY_dir(-1);
			if (this.jellyfish.getX_front() < 0)
				this.jellyfish.setX_dir(1);
			if (this.jellyfish.getY_front() < 0)
				this.jellyfish.setY_dir(1);
			if (this.jellyfish.getWantEat() < 500) {
				this.jellyfish.setWantEat(this.jellyfish.getWantEat() + this.jellyfish.getRegular());
			} else
				this.panel.setWantEat(true);
		}
	}
}
