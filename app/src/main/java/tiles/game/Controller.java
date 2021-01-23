package tiles.game;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import javax.swing.JFrame;

import tiles.data.GameState;
import tiles.data.Map;
import tiles.data.MapsLoader;
import tiles.graphics.CompletePanel;
import tiles.graphics.GamePanel;
import tiles.graphics.HomePanel;
import tiles.graphics.TransitionPanel;

public class Controller {
	private HomePanel homePanel;
	private GamePanel gamePanel;
	private TransitionPanel transitionPanel;
	private CompletePanel completePanel;

	private JFrame mainFrame;

	private MovementEngine movementEngine;
	private ArrayList<Map> mapsList = (new MapsLoader()).getMapsList();
	private Preferences preferences = Preferences.userRoot().node(this.getClass().getName());

	private static final int HOME_ACTIVE_CODE = 1;
	private static final int GAME_ACTIVE_CODE = 2;
	private static final int TRANSITION_ACTIVE_CODE = 3;
	private static final int COMPLETE_ACTIVE_CODE = 4;

	private GameState gameState;
	private int currentPanel;
	private Map currentMap;

	private boolean skipLevelCheatOn = false;

	public Controller(JFrame mainFrame) {
		this.mainFrame = mainFrame;

		gameState = new GameState(preferences.getInt("level", 1));
		if(gameState.level > mapsList.size()) {
			gameState.level = 1;
			preferences.putInt("level", 1);
		}
		currentMap = mapsList.get(gameState.level - 1).clone();
		movementEngine = new MovementEngine(currentMap);

		homePanel = new HomePanel();
		gamePanel = new GamePanel(currentMap);
		transitionPanel = new TransitionPanel(currentMap, gameState);
		completePanel = new CompletePanel(currentMap);

		currentPanel = HOME_ACTIVE_CODE;
		mainFrame.getContentPane().add(homePanel);
	}

	public void keyPressed(char key) {
		if(currentPanel == HOME_ACTIVE_CODE) {
			if(key == 's' || key == 'a') {
				mainFrame.getContentPane().remove(homePanel);
				mainFrame.getContentPane().add(gamePanel);
				gamePanel.revalidate();
				gamePanel.repaint();
				currentPanel = GAME_ACTIVE_CODE;
			}
			else if(key == 'o') {
				preferences.putInt("level", 1);
				gameState.level = 1;
				currentMap.resetFromMap(mapsList.get(gameState.level - 1));
			}
		}
		else if(currentPanel == GAME_ACTIVE_CODE) {
			if(key == 's') {
				currentMap.resetFromMap(mapsList.get(gameState.level - 1));
			}
			else if(key == 'u' || key == 'd' || key == 'l' || key == 'r') {
				movementEngine.makeChange(key);
			}
			else if(key == 'm') {
				if(skipLevelCheatOn) {
					if(gameState.level != mapsList.size()) {
						mainFrame.getContentPane().remove(gamePanel);
						mainFrame.getContentPane().add(transitionPanel);
						transitionPanel.revalidate();
						transitionPanel.repaint();
						currentPanel = TRANSITION_ACTIVE_CODE;
						transitionPanel.startTransition();
					}
					else {
						mainFrame.getContentPane().remove(gamePanel);
						mainFrame.getContentPane().add(completePanel);
						completePanel.revalidate();
						completePanel.repaint();
						currentPanel = COMPLETE_ACTIVE_CODE;
						completePanel.startTransition();
						preferences.putInt("level", 1);
						gameState.level = 1;
					}
				}
			}
			gamePanel.repaint();

			if(currentMap.checkComplete()) {
				if(gameState.level != mapsList.size()) {
					mainFrame.getContentPane().remove(gamePanel);
					mainFrame.getContentPane().add(transitionPanel);
					transitionPanel.revalidate();
					transitionPanel.repaint();
					currentPanel = TRANSITION_ACTIVE_CODE;
					transitionPanel.startTransition();
				}
				else {
					mainFrame.getContentPane().remove(gamePanel);
					mainFrame.getContentPane().add(completePanel);
					completePanel.revalidate();
					completePanel.repaint();
					currentPanel = COMPLETE_ACTIVE_CODE;
					completePanel.startTransition();
					preferences.putInt("level", 1);
					gameState.level = 1;
				}
			}
		}
		else if(currentPanel == TRANSITION_ACTIVE_CODE) {
			if(key == 's') {
				gameState.level++;
				currentMap.resetFromMap(mapsList.get(gameState.level - 1));
				transitionPanel.resetTransition();
				preferences.putInt("level", gameState.level);

				mainFrame.getContentPane().remove(transitionPanel);
				mainFrame.getContentPane().add(gamePanel);
				gamePanel.revalidate();
				gamePanel.repaint();
				currentPanel = GAME_ACTIVE_CODE;
			}
		}
		else if(currentPanel == COMPLETE_ACTIVE_CODE) {
			if(key == 's') {
				mainFrame.getContentPane().remove(completePanel);
				mainFrame.getContentPane().add(homePanel);
				homePanel.revalidate();
				homePanel.repaint();
				currentPanel = HOME_ACTIVE_CODE;
				completePanel.resetTransition();
				preferences.putInt("level", 1);
				gameState.level = 1;
				currentMap.resetFromMap(mapsList.get(gameState.level - 1));
			}
		}
		mainFrame.repaint();
	}
}
