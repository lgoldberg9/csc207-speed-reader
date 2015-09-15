import static org.junit.Assert.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class WordGeneratorTest {

	// These tests depend on the "test.txt" and "whitespace.txt" files
	// If either file changes, these tests may fail

	@Test
	public void hasNextTest() throws IOException {

		WordGenerator test1 = new WordGenerator("test.txt");
		WordGenerator test2 = new WordGenerator("whitespace.txt");

		assertEquals("returns true when not at end of file", true, test1.hasNext());
		assertEquals("returns true when not at end of file", true, test2.hasNext());

		while (test1.hasNext()) {
			test1.next();
		}
		
		while (test2.hasNext()) {
			test2.next();
		}
		
		assertEquals("returns false at end of file", false, test1.hasNext());
		assertEquals("returns false at end of file", false, test2.hasNext());
		
	}

	@Test
	public void nextTest() throws IOException {

		WordGenerator test = new WordGenerator("test.txt");

		while (test.hasNext()) {
			test.next();
		}

		assertEquals("Counts words correctly", 52, test.getWordCount());
		assertEquals("Counts sentences correctly", 7, test.getSentenceCount());

	}
	
}
