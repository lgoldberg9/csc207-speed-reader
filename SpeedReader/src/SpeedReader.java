import java.awt.*;
import java.io.IOException;

public class SpeedReader {

	public static int sleepDelay = 0;
	
	public static int getFocusIndex(int length) {
		switch (length) {
			case 0: case 1:
				return 0;
			case 2: case 3: case 4: case 5:
				return 1;
			case 6: case 7: case 8: case 9:
				return 2;
			case 10: case 11: case 12: case 13:
				return 3;
			default:
				return 4; // return 5 if the length is 14 or greater 
		}
	}

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
			
			int focusIndex = getFocusIndex(word.length());
			String leftHalf = word.substring(0, focusIndex);
			String focusLetter = word.substring(focusIndex, focusIndex + 1);
			String rightHalf = word.substring(focusIndex + 1);
			
			// Calculate where the word should be to center it
			int leftWidth = metrics.stringWidth(leftHalf);
			int rightWidth = metrics.stringWidth(rightHalf);
			int centerWidth = metrics.stringWidth(focusLetter);
			int xPos = (width / 2) - (centerWidth / 2);
			int yPos = (height / 2) - (fontHeight / 2);
			
			panelGraphics.setColor(Color.WHITE);
			panelGraphics.fillRect(0, 0, width + 1, height + 1); // clear the previous word
			
			// draw left half and right half black
			panelGraphics.setColor(Color.BLACK);
			panelGraphics.drawString(leftHalf, xPos - leftWidth, yPos); 
			panelGraphics.drawString(rightHalf, xPos + centerWidth, yPos); 
			
			// draw center as red
			panelGraphics.setColor(Color.RED);
			panelGraphics.drawString(focusLetter, xPos, yPos); 
			
			Thread.sleep(sleepDelay);
		}
		
		
		return;
	}
	
}
