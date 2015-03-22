package paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Line extends Tool {
	
	private Canvas canvas;
	private int clickX;
	private int clickY;
	private int releaseX;
	private int releaseY;
	private boolean drawing;
	
	public Line(Canvas c) {
		canvas = c;
		drawing = false;
	}
	
	@Override
	public void drawPreview(Graphics g) {
		if (!drawing)
			return;
		
		g.setColor(canvas.getCurrentColor());
		if (g != null)
			g.drawLine(clickX, clickY, releaseX, releaseY);
		canvas.repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		clickX = releaseX = e.getX();
		clickY = releaseY = e.getY();
		drawing = true;
		
		canvas.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		releaseX = e.getX();
		releaseY = e.getY();
		
		canvas.repaint();
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
		if (!drawing)
			return;
		
		drawing = false;
		
		Graphics2D g = canvas.getGraphics2D();
		g.setColor(canvas.getCurrentColor());
		if (g != null)
			g.drawLine(clickX, clickY, releaseX, releaseY);
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
