package tiles.game;

import tiles.data.Map;

public class MovementEngine {
	private Map map;

	public MovementEngine(Map map) {
		this.map = map;
	}

	public void makeChange(char move) {
		int x = map.getPlayerLocation().x;
		int y = map.getPlayerLocation().y;
		
		if(move == 'u') {
			if(map.get(x, y - 1) == Map.BLUE_TILE) {
				map.set(x, y, Map.RED_TILE);
				map.set(x, y - 1, Map.PLAYER_TILE);
				map.setPlayerLocation(x, y - 1);
			}
		}
		else if(move == 'd') {
			if(map.get(x, y + 1) == Map.BLUE_TILE) {
				map.set(x, y, Map.RED_TILE);
				map.set(x, y + 1, Map.PLAYER_TILE);
				map.setPlayerLocation(x, y + 1);
			}
		}
		else if(move == 'l') {
			if(map.get(x - 1, y) == Map.BLUE_TILE) {
				map.set(x, y, Map.RED_TILE);
				map.set(x - 1, y, Map.PLAYER_TILE);
				map.setPlayerLocation(x - 1, y);
			}
		}
		else if(move == 'r') {
			if(map.get(x + 1, y) == Map.BLUE_TILE) {
				map.set(x, y, Map.RED_TILE);
				map.set(x + 1, y, Map.PLAYER_TILE);
				map.setPlayerLocation(x + 1, y);
			}
		}
	}
}
