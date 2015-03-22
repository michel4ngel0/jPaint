package paint;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.Queue;

import javax.swing.text.Position;

public class Fill extends Tool {
	
	private Canvas canvas;
	
	public Fill(Canvas c) {
		canvas = c;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		Graphics2D g = canvas.getGraphics2D();
		g.setColor(canvas.getCurrentColor());
		if (g != null) {
			Image i = canvas.getImage();
			//	BFS
			//Queue q = new Queue<Position>();
		}
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
