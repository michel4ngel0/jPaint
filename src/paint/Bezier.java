package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.ListIterator;

public class Bezier extends Tool {
	
	private Canvas canvas;
	LinkedList<IntPair> vertices = new LinkedList<IntPair>();
	boolean drawing;
	
	public Bezier(Canvas c) {
		canvas = c;
		drawing = false;
	}
	
	private LinkedList<FloatPair> generateCurve(LinkedList<IntPair> vertices) {
		int numberOfVertices = vertices.size();
		if (numberOfVertices == 0)
			return new LinkedList<FloatPair>();
		double step = 0.1 / (float)(numberOfVertices);
		LinkedList<FloatPair> temp = new LinkedList<FloatPair>();
		LinkedList<FloatPair> finalList = new LinkedList<FloatPair>();
		finalList.add(new FloatPair(
				(float)vertices.getFirst().first,
				(float)vertices.getFirst().second)
			);
		for (double x = 1; x >= 0; x -= step) {
			LinkedList<FloatPair> copy = new LinkedList<FloatPair>();
			ListIterator<IntPair> cpit = vertices.listIterator();
			while (cpit.hasNext()) {
				IntPair p = cpit.next();
				copy.add(new FloatPair((float)p.first, (float)p.second));
			}
			for (int i = numberOfVertices; i > 1; --i) {
				ListIterator<FloatPair> it = copy.listIterator();
				FloatPair prev = it.next();
				while (it.hasNext()) {
					FloatPair current = it.next();
					float newX = (float)(x*prev.first + (1-x)*current.first);
					float newY = (float)(x*prev.second + (1-x)*current.second);
					temp.add(new FloatPair(newX, newY));
					prev = current;
				}
				copy = (LinkedList<FloatPair>)temp.clone();
				temp.clear();
			}
			finalList.add(copy.peek());
		}
		
		return finalList;
	}
	
	@Override
	public void drawPreview(Graphics g) {
		if (!drawing)
			return;
		
		{
			g.setColor(canvas.getCurrentColor());
			LinkedList<FloatPair> curveList = generateCurve(vertices);
			ListIterator<FloatPair> it = curveList.listIterator();
			FloatPair prev = it.next();
			while (it.hasNext()) {
				FloatPair current = it.next();
				g.drawLine((int)prev.first, (int)prev.second, (int)current.first, (int)current.second);
				prev = current;
			}
		}
		
		{
			g.setColor(Color.LIGHT_GRAY);
			if (!vertices.isEmpty()) {
				ListIterator<IntPair> it = vertices.listIterator();
				IntPair prev = it.next();
				while (it.hasNext()) {
					IntPair current = it.next();
					g.drawLine(prev.first, prev.second, current.first, current.second);
					prev = current;
				}
			}
		}
		
		{
			g.setColor(Color.DARK_GRAY);
			for (IntPair p : vertices) {
				g.drawOval(p.first-1, p.second-1, 3, 3);
			}
		}
		
		canvas.repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() != MouseEvent.BUTTON1) {
			if (!drawing)
				return;
			
			drawing = false;
			
			LinkedList<FloatPair> finalList = generateCurve(vertices);
			
			Graphics2D g = (Graphics2D)canvas.getImage().getGraphics();
			g.setColor(canvas.getCurrentColor());
			ListIterator<FloatPair> it = finalList.listIterator();
			FloatPair prev = it.next();
			while (it.hasNext()) {
				FloatPair current = it.next();
				g.drawLine((int)prev.first, (int)prev.second, (int)current.first, (int)current.second);
				prev = current;
			}
		
			vertices.clear();
		}
		
		canvas.repaint();
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
		if (e.getButton() == MouseEvent.BUTTON1) {
			vertices.add(new IntPair(e.getX(), e.getY()));
			drawing = true;
		}
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
