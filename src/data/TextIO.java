package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextIO {
	private static File myFile = new File("Category.txt");

	// Method to read an information from a file.

	public static ArrayList<String> readRecords() throws IOException {
		Scanner scanner = new Scanner(myFile);
		ArrayList<String> records = new ArrayList<String>();
		while (scanner.hasNext()) {
			String record = scanner.nextLine();
			records.add(record);
		}
		scanner.close();
		return records;
	}

}
