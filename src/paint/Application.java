package paint;

import java.awt.Image;
import java.awt.EventQueue;

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
		
	}
}
