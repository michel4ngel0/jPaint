package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import paint.Canvas.ToolType;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private int canvasWidth;
	private int canvasHeight;
	private final int bottomBarHeight = 50;
	private Canvas canvas;

	public Window() {
		
		super("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		while(!resize()) {}
		setLayout(new FlowLayout(
					FlowLayout.CENTER,
					0,
					0
				));
		
		//	Canvas
		canvas = new Canvas(canvasWidth, canvasHeight);
		
		//	Color palette
		JPanel palette = new JPanel();
		Color colors[] = {
				Color.WHITE,
				Color.BLACK,
				Color.RED,
				Color.YELLOW,
				Color.GREEN,
				Color.BLUE,
				Color.CYAN,
				Color.DARK_GRAY,
				Color.GRAY,
				Color.LIGHT_GRAY,
				Color.MAGENTA,
				Color.ORANGE,
				Color.PINK
			};
		for (Color c : colors) {
			JButton b = new JButton();
			b.setPreferredSize(new Dimension(20, 20));
			b.setBackground(c);
			b.setContentAreaFilled(false);
			b.setOpaque(true);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					canvas.setColor(c);
				}
			});
			palette.add(b);
		}
		palette.setPreferredSize(new Dimension(200, bottomBarHeight));
		palette.setBackground(Color.GRAY);
        
        //	Toolbox
        JPanel toolbox = new JPanel();
        ToolType tools[] = {ToolType.PENCIL, ToolType.LINE, ToolType.FILL};
        for (ToolType t : tools) {
        	JButton b = new JButton();
			b.setPreferredSize(new Dimension(30, 30));
			b.setBackground(Color.PINK);
			b.setContentAreaFilled(false);
			b.setOpaque(true);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					canvas.setTool(t);
				}
			});
			toolbox.add(b);
        }
		toolbox.setPreferredSize(new Dimension(150, bottomBarHeight));
		toolbox.setBackground(Color.GRAY);
        
		//	Menu
		JPanel menu = new JPanel();
		{
			JButton save = new JButton();
			save.setPreferredSize(new Dimension(30, 30));
			save.setBackground(Color.CYAN);
			save.setContentAreaFilled(false);
			save.setOpaque(true);
			save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Application.saveImage(canvas.getImage());
				}
			});
			menu.add(save);
		}
		menu.setPreferredSize(new Dimension(100, bottomBarHeight));
		menu.setBackground(Color.GRAY);
		
		JPanel bottomBar = new JPanel();
		bottomBar.add(palette);
		bottomBar.add(toolbox);
		bottomBar.add(menu);
		bottomBar.setLayout(new FlowLayout(
					FlowLayout.CENTER,
					0,
					0
				));
		
		add(canvas);
		add(bottomBar);
	}
	
	public boolean resize() {
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		
		JPanel resizeDialog = new JPanel();
		resizeDialog.add(new JLabel("Width: "));
		resizeDialog.add(xField);
		resizeDialog.add(Box.createHorizontalStrut(15));
		resizeDialog.add(new JLabel("Height: "));
		resizeDialog.add(yField);
		
		JOptionPane.showConfirmDialog(
				null,
				resizeDialog, 
				"Enter image size",
				JOptionPane.PLAIN_MESSAGE
		);
		
		try {
			canvasWidth = Integer.parseInt(xField.getText());
			canvasHeight = Integer.parseInt(yField.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(
					null,
					"Invalid image size",
					"",
					JOptionPane.ERROR_MESSAGE
			);
			return false;
		}
		
		int xOffset = getInsets().left + getInsets().right;
		int yOffset = getInsets().bottom + getInsets().top;
		System.out.println(xOffset + " " + yOffset);
		setSize(Math.max(canvasWidth, 400) + xOffset, canvasHeight + bottomBarHeight + yOffset);
		
		return true;
	}
}
