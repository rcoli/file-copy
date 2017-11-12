package br.com.rcoli.file.copy.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.com.rcoli.file.copy.helper.DateHelper;
import br.com.rcoli.file.copy.visitor.FileCopyVisitor;

public class Main {

	private static String readFrom = new String("/home/rcoli/Music");
	private static String copyToDir = new String("/media/rcoli/kingston64G");

	// ls -ltr >> ~/Desktop/artists.txt and delete what you dont want
	private static String artistsFile = new String("/home/rcoli/Desktop/artists.txt");
	
	private static int expectedArgs = 3;

	public static void main(String[] args) {

		try {

			String from = (args.length == expectedArgs) ? args[0] : readFrom;
			String to = (args.length == expectedArgs) ? args[1] : copyToDir;
			String artists = (args.length == expectedArgs) ? args[2] : artistsFile;

			long initialDate = System.currentTimeMillis();

			System.out.println("Start: " + DateHelper.getFormattedDate(initialDate));

			Files.walkFileTree(Paths.get(from), new FileCopyVisitor(to, artists));

			long finalDate = System.currentTimeMillis();

			System.out.println("Stop! Elapsed time: " + DateHelper.formatInterval(finalDate - initialDate));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
