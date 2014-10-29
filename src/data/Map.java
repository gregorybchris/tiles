package data;

public class Map {
	public static final int MAP_WIDTH = 25;
	public static final int MAP_HEIGHT = 15;

	public static final int METAL_TILE = 0;
	public static final int BLUE_TILE = 1;
	public static final int RED_TILE = 2;
	public static final int PLAYER_TILE = 3;

	private int[][] map;
	private Location playerLocation;

	public Map() {
		map = new int[MAP_WIDTH][MAP_HEIGHT];
		for(int y = 0; y < MAP_HEIGHT; y++)
			for(int x = 0; x < MAP_WIDTH; x++)
				map[x][y] = METAL_TILE;
		playerLocation = new Location();
	}

	public Map(String mapString) {
		map = new int[MAP_WIDTH][MAP_HEIGHT];
		setFromMapString(mapString);
	}

	public void setFromMapString(String mapString) {
		int letterCounter = 0;
		for(char c : mapString.toCharArray()) {
			int cInt = Integer.parseInt(c + "");
			int xPos = letterCounter % Map.MAP_WIDTH;
			int yPos = letterCounter / Map.MAP_WIDTH;
			if(cInt == PLAYER_TILE)
				playerLocation = new Location(xPos, yPos);
			map[xPos][yPos] = cInt;
			letterCounter++;
		}
	}
	
	public void resetFromMap(Map newMap) {
		for(int y = 0; y < MAP_HEIGHT; y++)
			for(int x = 0; x < MAP_WIDTH; x++)
				map[x][y] = newMap.get(x, y);
		setPlayerLocation(newMap.playerLocation.x, newMap.playerLocation.y);
	}
	
	public Location getPlayerLocation() {
		return playerLocation;
	}
	
	public void setPlayerLocation(int x, int y) {
		this.playerLocation.x = x;
		this.playerLocation.y = y;
	}

	public void set(int x, int y, int t) {
		map[x][y] = t;
	}

	public int get(int x, int y) {
		return map[x][y];
	}

	public boolean checkComplete() {
		for(int y = 0; y < MAP_HEIGHT; y++)
			for(int x = 0; x < MAP_WIDTH; x++)
				if(map[x][y] == BLUE_TILE)
					return false;
		return true;
	}

	@Override
	public String toString() {
		String toReturn = "\n";
		for(int y = 0; y < MAP_HEIGHT; y++) {
			for(int x = 0; x < MAP_WIDTH; x++)
				toReturn += map[x][y];
			toReturn += "\n";
		}
		return toReturn;
	}
	
	@Override
	public Map clone() {
		Map toReturn = new Map();
		for(int y = 0; y < MAP_HEIGHT; y++)
			for(int x = 0; x < MAP_WIDTH; x++)
				toReturn.set(x, y, map[x][y]);
		toReturn.setPlayerLocation(this.playerLocation.x, this.playerLocation.y);
		return toReturn;
	}
}
