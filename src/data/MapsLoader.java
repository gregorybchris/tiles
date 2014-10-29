package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MapsLoader {
	public MapsLoader() {}

	public ArrayList<Map> getMapsList() {
		ArrayList<Map> mapsList = new ArrayList<Map>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(MapsLoader.class.getResourceAsStream("/levels.txt")));

			int lineCount = 0;
			String lineAccumulator = "";
			String nextLine = null;
			while((nextLine = in.readLine()) != null) {
				if((nextLine.contains("0") || nextLine.contains("1") || nextLine.contains("3"))) {
					lineAccumulator += nextLine;
					lineCount++;
				}

				if(lineCount % 15 == 0 && lineCount != 0) {
					if(!lineAccumulator.isEmpty())
						mapsList.add(new Map(lineAccumulator));
					lineAccumulator = "";
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return mapsList;
	}
}
