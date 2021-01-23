package tiles.data;

import java.io.Serializable;

public class GameState implements Serializable {
	private static final long serialVersionUID = 12202013L;
	public int level;
	
	public GameState(int level) {
		this.level = level;
	}
	
	public void incrementLevel() {
		level++;
	}
	
	public int getLevel() {
		return level;
	}
}
