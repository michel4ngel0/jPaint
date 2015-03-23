package paint;

import java.awt.Image;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Application {
	public static void main(String arguments[]) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Window();
			}
		});
	}
	
	public static void saveImage(Image image) {
	    String name = JOptionPane.showInputDialog(
	        null, 
	        "Enter file name:", 
	        "Save picture", 
	        JOptionPane.QUESTION_MESSAGE
	    );
	    
	    if (name == null)
	    	return;
	    
	    try {
	        BufferedImage bi = (BufferedImage)image;
	        File outputfile = new File(name + ".png");
	        ImageIO.write(bi, "png", outputfile);
	        JOptionPane.showMessageDialog(
	    			null,
	    			"File " + name + ".png was saved successfully",
	    			"",
	    			JOptionPane.INFORMATION_MESSAGE
	    	);
	    } catch (IOException e) {
	    	JOptionPane.showMessageDialog(
	    			null,
	    			"Failed to save " + name + ".png",
	    			"",
	    			JOptionPane.ERROR_MESSAGE
	    	);
	    }
	}
}
