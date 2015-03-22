package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public enum ToolType {
		PENCIL, LINE, FILL
	}
	
	private Color currentColor;
	private Tool currentTool;
	private ToolType currentToolType;
	private Image image;
	private Graphics2D graphics2D;
	
	public Canvas(int width, int heigth) {
		super();
		setPreferredSize(new Dimension(width, heigth));
		
		currentColor = Color.BLACK;
		
		setTool(ToolType.PENCIL);
	}
	
	@Override
	public void paintComponent(Graphics g){
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			clear(Color.WHITE);
		}
		g.drawImage(image, 0, 0, null);
		currentTool.drawPreview(g);
	}
	
	public void setColor(Color c) {
		currentColor = c;
		//TODO Remove debug code
		setBackground(currentColor);
	}
	
	public void setTool(ToolType t) {
		if (currentToolType == t)
			return;
		
		currentToolType = t;
		
		removeMouseListener(currentTool);
		removeMouseMotionListener(currentTool);
		
		switch(t) {
		case PENCIL: {
			currentTool = new Pencil(this);
		} break;
		case LINE: {
			currentTool = new Line(this);
		} break;
		case FILL: {
			currentTool = new Fill(this);
		} break;
		}
		
		addMouseListener(currentTool);
		addMouseMotionListener(currentTool);
	}
	
	public void clear(Color c){
		graphics2D.setPaint(c);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(currentColor);
		repaint();
	}
	
	public Graphics2D getGraphics2D() {
		return graphics2D;
	}
	
	public Color getCurrentColor() {
		return currentColor;
	}
	
	public Image getImage() {
		return image;
	}
}
