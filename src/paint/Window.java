package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;

	public Window() {
		
		super("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocation(50, 50);
		setLayout(new FlowLayout());
		
		//	Canvas
		canvas = new Canvas(780, 500);
		
		//	Color palette
		JPanel palette = new JPanel();
		Color colors[] = {Color.WHITE, Color.BLACK, Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
		for (Color c : colors) {
			JButton b = new JButton();
			b.setPreferredSize(new Dimension(30, 30));
			b.setBackground(c);
			b.setContentAreaFilled(false);
			b.setOpaque(true);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					canvas.setColor(c);
				}
			});
			palette.add(b);
		}
		palette.setPreferredSize(new Dimension(380, 50));
		palette.setBackground(Color.GRAY);
        
        //	Menu
        JPanel menu = new JPanel();
		menu.setPreferredSize(new Dimension(380, 50));
		menu.setBackground(Color.GRAY);
        
		add(canvas);
		add(palette);
		add(menu);
		
		setVisible(true);
	}
}
