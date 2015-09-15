import java.awt.*;
import java.io.IOException;

public class SpeedReader {

	public static int sleepDelay = 0;

	public static void main(String[] args) throws IOException, 
	InterruptedException {
		
		if (args.length != 5) {
			System.out.println("Invalid usage. Usage: SpeedReader "
					+ "<filename> <width> <height> <font size> <wpm>");
			return;
		}
		
		String filename = args[0];
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		int fontSize = Integer.parseInt(args[3]);
		int wpm = Integer.parseInt(args[4]);
		
		sleepDelay = 60000 / wpm; // (60s * 1000 s/ms) / wpm = delay between words
		
		// This calls the WordGenerator class with all its methods. 
		// The scanner is built into it so we don't lose track of the file pointer.
		WordGenerator speedReader = new WordGenerator(filename);
		
		// Below handles the panel drawing and such.
		DrawingPanel panel = new DrawingPanel(width, height);
		Graphics panelGraphics = panel.getGraphics();
		Font panelFont = new Font("Courier", Font.BOLD, fontSize);
		FontMetrics metrics = panelGraphics.getFontMetrics(panelFont);
		int fontHeight = metrics.getHeight();
		
		panelGraphics.setFont(panelFont);
		
		Thread.sleep(sleepDelay);
		
		while (speedReader.hasNext()) {
			
			String word = speedReader.next(); 
			
			// Calculate where the word should be to center it
			int fontWidth = metrics.stringWidth(word);
			int xPos = (width / 2) - (fontWidth / 2);
			int yPos = (height / 2) - (fontHeight / 2);
			
			panelGraphics.setColor(Color.WHITE);
			panelGraphics.fillRect(0, 0, width + 1, height + 1); // clear the previous word
			panelGraphics.setColor(Color.BLACK);
			panelGraphics.drawString(word, xPos, yPos); // draw a word
			Thread.sleep(sleepDelay);
		}
		
		
		return;
	}
	
}
