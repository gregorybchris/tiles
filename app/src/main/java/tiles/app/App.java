package tiles.app;

import javax.swing.JFrame;

import tiles.graphics.GraphicsFrame;

public class App {
    public static void main(String[] args) {
        GraphicsFrame f = new GraphicsFrame("Tiles - Copyright 2013 © Chris Gregory");
		f.setBounds(0, 0, 1200, 670);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
    }
}
