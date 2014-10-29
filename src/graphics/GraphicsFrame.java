package graphics;

import game.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class GraphicsFrame extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;

	Controller controller = new Controller(this);
	
	public GraphicsFrame(String title) {
		super(title);
		this.addKeyListener(this);
		this.setFocusable(true);
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		int code = ke.getKeyCode();
		if(code == KeyEvent.VK_UP)
			controller.keyPressed('u');
		else if(code == KeyEvent.VK_DOWN)
			controller.keyPressed('d');
		else if(code == KeyEvent.VK_LEFT)
			controller.keyPressed('l');
		else if(code == KeyEvent.VK_RIGHT)
			controller.keyPressed('r');
		else if(code == KeyEvent.VK_SPACE)
			controller.keyPressed('s');
		else if(code == KeyEvent.VK_1)
			controller.keyPressed('o');
		else if(code == KeyEvent.VK_2)
			controller.keyPressed('m');
		else
			controller.keyPressed('a');
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
}