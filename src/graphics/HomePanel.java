package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.JPanel;

public class HomePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private RadialGradientPaint backgroundGradient = new RadialGradientPaint(new Point2D.Double(600, 350), 1000, 
			new float[]{0.5f, 0.7f, 1.0f}, new Color[]{new Color(40, 40, 40), new Color(100, 100, 100), new Color(100, 100, 100)});

	private Font mainFont = null;

	public HomePanel() {
		getResources();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setPaint(backgroundGradient);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		g2.setStroke(new BasicStroke(3));
		g2.setColor(new Color(100, 100, 100));
		g2.drawRect(1, 1, 1197, 644);

		g2.setFont(mainFont.deriveFont(220f));
		g2.setColor(Color.BLACK);
		g2.drawString("Tiles", 384, 244);
		g2.setColor(Color.LIGHT_GRAY);
		g2.drawString("Tiles", 380, 240);

		g2.setFont(mainFont.deriveFont(40f));
		g2.drawString("Reach every tile", 460, 360);
		g2.setFont(mainFont.deriveFont(67f));
		g2.drawString("only once", 458, 425);
		g2.setFont(mainFont.deriveFont(31f));
		g2.drawString("Arrow keys to move", 462, 472);
		g2.setFont(mainFont.deriveFont(34f));
		g2.drawString("Space bar to reset", 462, 523);
	}

	private void getResources() {
		try {
			mainFont = Font.createFont(Font.TRUETYPE_FONT, 
					GamePanel.class.getResourceAsStream("/Fonts/Titillium-Light.ttf"));
		} catch (FontFormatException e) {
			System.err.println("Error: Problem With Font Format");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error: Problem Finding Font");
			e.printStackTrace();
		}
		mainFont = mainFont.deriveFont(70f);
	}
}
