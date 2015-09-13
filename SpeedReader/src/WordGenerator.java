import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class WordGenerator {
	
	private Scanner textToRead;
	private int wordCount;
	private int sentenceCount;
	
	public WordGenerator(String filename) throws IOException {
		this.textToRead = new Scanner(new File(filename));
		this.wordCount = 0;
		this.sentenceCount = 0;
	}
	
	public boolean hasNext() {
		return textToRead.hasNext();
	}
	
	public String next() {
		String nextString = textToRead.next();
		this.wordCount += 1;
		if (nextString.endsWith("?") || nextString.endsWith(".") || 
				nextString.endsWith("!")) {
			this.sentenceCount += 1;	
		}
		return nextString;
	}
	
	public int getWordCount() {
		return this.wordCount;
	}
	
	public int getSentenceCount() {
		return this.sentenceCount;
	}

}
