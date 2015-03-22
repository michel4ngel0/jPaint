package paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Pencil extends Tool {
	
	private Canvas canvas;
	private int clickX;
	private int clickY;
	
	public Pencil(Canvas c) {
		canvas = c;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		clickX = e.getX();
		clickY = e.getY();
		
		Graphics2D g = canvas.getGraphics2D();
		g.setPaint(canvas.getCurrentColor());
		if (g != null)
			g.drawLine(clickX, clickY, clickX, clickY);
		canvas.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int currentX = e.getX();
		int currentY = e.getY();
		
		Graphics2D g = canvas.getGraphics2D();
		g.setPaint(canvas.getCurrentColor());
		if(g != null)
			g.drawLine(clickX, clickY, currentX, currentY);
		canvas.repaint();
		
		clickX = currentX;
		clickY = currentY;
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
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
