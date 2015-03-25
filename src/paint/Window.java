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
	private final int minPaletteWidth = 170;
	private final int minToolboxWidth = 165;
	private final int minMenuWidth = 115;
	private final int bottomBarHeight = 54;
	private Canvas canvas = null;

	public Window() {
		
		super("jPaint");
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
		
		JPanel palette = createPalette();
        JPanel toolbox = createToolbox();
		JPanel menu = createMenu();
		
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
		int minWindowWidth = minPaletteWidth + minToolboxWidth + minMenuWidth;
		setSize(Math.max(canvasWidth, minWindowWidth) + xOffset, canvasHeight + bottomBarHeight + yOffset);
		
		return true;
	}
	
	private JPanel createPalette() {
		JPanel palette = new JPanel();
		Color colors[] = {
				Color.BLACK,
				Color.DARK_GRAY,
				Color.GRAY,
				Color.LIGHT_GRAY,
				Color.WHITE,
				Color.RED,
				Color.ORANGE,
				Color.YELLOW,
				Color.GREEN,
				Color.CYAN,
				Color.BLUE,
				Color.MAGENTA
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
		palette.setPreferredSize(new Dimension(minPaletteWidth, bottomBarHeight));
		palette.setBackground(Color.GRAY);
		
		return palette;
	}

	private JPanel createToolbox() {
		JPanel toolbox = new JPanel();
        ToolType tools[] = {
        		ToolType.PENCIL,
        		ToolType.LINE,
        		ToolType.FILL,
        		ToolType.BEZIER
        	};
        for (ToolType t : tools) {
        	String name = "";
        	switch (t) {
        		case PENCIL: name = "Pencil"; break;
        		case LINE:   name = "Line";   break;
        		case FILL:   name = "Fill";   break;
        		case BEZIER: name = "Curve";  break;
        	}
        	JButton b = new JButton(name);
			b.setPreferredSize(new Dimension(70, 20));
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
		toolbox.setPreferredSize(new Dimension(minToolboxWidth, bottomBarHeight));
		toolbox.setBackground(Color.GRAY);
		
		return toolbox;
	}

	private JPanel createMenu() {
		JPanel menu = new JPanel();
		{
			JButton clear = new JButton("Clear");
			clear.setPreferredSize(new Dimension(70, 20));
			clear.setBackground(Color.LIGHT_GRAY);
			clear.setContentAreaFilled(false);
			clear.setOpaque(true);
			clear.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					canvas.clear(Color.WHITE);
				}
			});
			menu.add(clear);
			
			JButton save = new JButton("Save");
			save.setPreferredSize(new Dimension(70, 18));
			save.setBackground(Color.LIGHT_GRAY);
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
		menu.setPreferredSize(new Dimension(minMenuWidth, bottomBarHeight));
		menu.setBackground(Color.GRAY);
		
		return menu;
	}
}
