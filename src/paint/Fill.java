package paint;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

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
		int startX = e.getX();
		int startY = e.getY();
		
		BufferedImage i = (BufferedImage)canvas.getImage();
		int maxX = i.getWidth();
		int maxY = i.getHeight();
		
		if (startX >= maxX || startX < 0 || startY >= maxY || startY < 0)
			return;
		
		int fillColor = canvas.getCurrentColor().getRGB();
		int previousColor = i.getRGB(startX, startY);
		if (fillColor == previousColor)
			return;
		
		//	BFS
		Queue<IntPair> k = new LinkedList<IntPair>();
		k.add(new IntPair(startX, startY));
		i.setRGB(startX, startY, fillColor);
		while (!k.isEmpty()) {
			IntPair pos = k.remove();
			if (pos.first+1 < maxX) {
				if (i.getRGB(pos.first+1, pos.second) == previousColor) {
					i.setRGB(pos.first+1, pos.second, fillColor);
					k.add(new IntPair(pos.first+1, pos.second));
				}
			}
			if (pos.first-1 >= 0) {
				if (i.getRGB(pos.first-1, pos.second) == previousColor) {
					i.setRGB(pos.first-1, pos.second, fillColor);
					k.add(new IntPair(pos.first-1, pos.second));
				}
			}
			if (pos.second+1 < maxY) {
				if (i.getRGB(pos.first, pos.second+1) == previousColor) {
					i.setRGB(pos.first, pos.second+1, fillColor);
					k.add(new IntPair(pos.first, pos.second+1));
				}
			}
			if (pos.second-1 >= 0) {
				if (i.getRGB(pos.first, pos.second-1) == previousColor) {
					i.setRGB(pos.first, pos.second-1, fillColor);
					k.add(new IntPair(pos.first, pos.second-1));
				}
			}
		}
		
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
