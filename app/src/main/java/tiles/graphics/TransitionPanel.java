package tiles.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import tiles.data.GameState;
import tiles.data.Map;

public class TransitionPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private RadialGradientPaint backgroundGradient = new RadialGradientPaint(new Point2D.Double(600, 350), 1000, 
			new float[]{0.5f, 0.7f, 1.0f}, new Color[]{new Color(40, 40, 40), new Color(100, 100, 100), new Color(100, 100, 100)});
	
	private Color colMessageBackground = new Color(0f, 0f, 0f, 0f);
	private Color colMessageText = new Color(.8f, .8f, .8f, 0f);
	private float fadeValue = 0f;
	private Timer t;
	
	private Font mainFont = null;
	private BufferedImage metalTileImg = null;
	private BufferedImage blueTileImg = null;
	private BufferedImage redTileImg = null;
	private BufferedImage playerTileImg = null;

	private Map map;
	private GameState gameState;
	
	public TransitionPanel(Map map, GameState gameState) {
		this.map = map;
		this.gameState = gameState;
		getResources();
	}
	
	public void startTransition() {
		t = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(fadeValue <= .8) {
					fadeValue += .02f;
					colMessageBackground = new Color(0f, 0f, 0f, fadeValue);
					colMessageText = new Color(.8f, .8f, .8f, fadeValue);
				}
				else
					t.stop();
				repaint();
			}
		});
		t.start();
	}
	
	public void resetTransition() {
		colMessageBackground = new Color(0f, 0f, 0f, 0f);
		colMessageText = new Color(.8f, .8f, .8f, 0f);
		fadeValue = 0f;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setPaint(backgroundGradient);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		for(int y = 0; y < 15; y++) {
			for(int x = 0; x < 25; x++) {
				if(map.get(x, y) == Map.PLAYER_TILE)
					g2.drawImage(playerTileImg, 100 + 40 * x, 20 + 40 * y, 40, 40, null);
				else if(map.get(x, y) == Map.METAL_TILE)
					g2.drawImage(metalTileImg, 100 + 40 * x, 20 + 40 * y, 40, 40, null);
				else if(map.get(x, y) == Map.BLUE_TILE)
					g2.drawImage(blueTileImg, 100 + 40 * x, 20 + 40 * y, 40, 40, null);
				else if(map.get(x, y) == Map.RED_TILE)
					g2.drawImage(redTileImg, 100 + 40 * x, 20 + 40 * y, 40, 40, null);
			}
		}
		
		g2.setStroke(new BasicStroke(3));
		g2.setColor(new Color(30, 30, 30));
		g2.drawRect(100, 20, 1000, 600);
		g2.setColor(new Color(100, 100, 100));
		g2.drawRect(1, 1, 1197, 644);
		
		g2.setColor(colMessageBackground);
		g2.fillRect(200, 70, 800, 500);
		
		g2.setColor(colMessageText);
		g2.setFont(mainFont.deriveFont(70f));
		g2.drawString("Nice work", 450, 240);
		g2.setFont(mainFont.deriveFont(50f));
		int nextLevelDigits = ((gameState.level + 1) + "").length();
		if(nextLevelDigits == 1)
			g2.drawString("On to level " + (gameState.level + 1), 460, 390);
		else if(nextLevelDigits == 2)
			g2.drawString("On to level " + (gameState.level + 1), 450, 390);
		else if(nextLevelDigits == 3)
			g2.drawString("On to level " + (gameState.level + 1), 435, 390);
	}

	private void getResources() {
		try {
			mainFont = Font.createFont(Font.TRUETYPE_FONT, 
					TransitionPanel.class.getResourceAsStream("/fonts/Titillium-Light.ttf"));
		} catch (FontFormatException e) {
			System.err.println("Error: Problem With Font Format");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error: Problem Finding Font");
			e.printStackTrace();
		}
		mainFont = mainFont.deriveFont(70f);

		try {
			metalTileImg = ImageIO.read(TransitionPanel.class.getResourceAsStream("/images/tile0.jpg"));
			playerTileImg = ImageIO.read(TransitionPanel.class.getResourceAsStream("/images/tile1.jpg"));
			blueTileImg = ImageIO.read(TransitionPanel.class.getResourceAsStream("/images/tile2.jpg"));
			redTileImg = ImageIO.read(TransitionPanel.class.getResourceAsStream("/images/tile3.jpg"));
		} catch (IOException e) {
			System.err.println("Error: Problem Finding Image");
			e.printStackTrace();
		}
	}
}
