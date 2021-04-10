import Vectors.Vector2D;

import java.awt.event.*;

import javax.swing.JFrame;

public class MainClass {
	public static void main(String[] args) {
		Algorithms algorithms = new Algorithms();
		Mouse mouse = initiateMouse(algorithms);
		Keyboard keyboard = initiateKeyboard(algorithms);
		Canvas meuCanvas = new Canvas(mouse, keyboard, algorithms);
		JFrame f = new JFrame();
		f.addKeyListener(keyboard.getKeyListener());
		f.setSize(Configs.WIDTH, Configs.HEIGHT);
		f.setVisible(true);
		f.getContentPane().add(meuCanvas);
		f.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        System.exit(0);
		    }
		});

		meuCanvas.start();
	}

	private static Mouse initiateMouse(Algorithms alg){
		Mouse mouse = new Mouse();

		mouse.setCursorListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				mouse.setCursor(new Vector2D(e.getX(), e.getY()));
			}
		});
		mouse.setClickListner(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				mouse.setClick(new Vector2D(e.getX(), e.getY()));
				switch (e.getButton()) {
					case MouseEvent.BUTTON1:
						alg.setRotateSide(1);
						break;
					case MouseEvent.BUTTON3:
						alg.setRotateSide(-1);
						break;
					default: break;
				}
				mouse.click();
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		return mouse;
	}

	private static Keyboard initiateKeyboard(Algorithms alg){
		Keyboard returnKeyListener = new Keyboard();
		returnKeyListener.setKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				returnKeyListener.movementRule(e, alg);
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		return returnKeyListener;
	}

}
