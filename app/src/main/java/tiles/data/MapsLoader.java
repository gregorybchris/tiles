package tiles.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MapsLoader {
	public MapsLoader() {}

	public ArrayList<Map> getMapsList() {
		ArrayList<Map> mapsList = new ArrayList<Map>();
		BufferedReader in = null;
		InputStream inputStream = MapsLoader.class.getResourceAsStream("/levels/levels.txt");
		in = new BufferedReader(new InputStreamReader(inputStream));

		int lineCount = 0;
		String lineAccumulator = "";
		String nextLine = null;
		try {
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
		} catch (IOException e) {
			e.printStackTrace();
		}


		return mapsList;
	}
}
