import java.awt.*;
import java.io.IOException;

public class SpeedReader {

	public static int main(String[] args) throws IOException, 
	InterruptedException {
		
		if (args.length != 5) {
			System.out.println("Invalid usage. Usage: SpeedReader "
					+ "<filename> <width> <height> <font size> <wpm>");
			return 1;
		}
		
		String filename = args[0];
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		int fontSize = Integer.parseInt(args[3]);
		int wpm = Integer.parseInt(args[4]);
		
		// This calls the WordGenerator class with all its methods. 
		// The scanner is built into it so we don't lose track of the file pointer.
		WordGenerator speedReader = new WordGenerator(filename);
		
		// Below handles the panel drawing and such.
		DrawingPanel panel = new DrawingPanel(width, height);
		Graphics panelGraphics = panel.getGraphics();
		Font panelFont = new Font("Courier", Font.BOLD, fontSize);
		panelGraphics.setFont(panelFont);
		while (speedReader.hasNext()) {
			panelGraphics.drawString(speedReader.next(), 100, 100);
			Thread.sleep(1000 / wpm); // I'm not sure what the wpm rate should be.
		}
		
		
		return 0;
	}
	
}
