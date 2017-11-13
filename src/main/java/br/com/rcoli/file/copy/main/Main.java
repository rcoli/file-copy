package br.com.rcoli.file.copy.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import br.com.rcoli.file.copy.helper.DateHelper;
import br.com.rcoli.file.copy.visitor.FileCopyVisitor;

public class Main {

	private static String readFrom = new String("/home/rcoli/Music");
	private static String copyToDir = new String("/media/rcoli/kingston64G");

	// ls -ltr ~/Music >> ~/alelo/workspace/file-copy/src/main/resources/all-artists.txt and delete what you dont want
	private static String artistsFile = new String("/home/rcoli/ciandt/alelo/workspace/file-copy/src/main/resources/artists.txt");
	private static Integer maxFiles = 999;
	
	private static int expectedArgs = 4;

	public static void main(String[] args) {

		try {

			String from = (args.length == expectedArgs) ? args[0] : readFrom;
			String to = (args.length == expectedArgs) ? args[1] : copyToDir;
			String artists = (args.length == expectedArgs) ? args[2] : artistsFile;
			Integer maxNumFiles = (args.length == expectedArgs) ? Integer.valueOf(args[3]) : maxFiles;

			long initialDate = System.currentTimeMillis();

			System.out.println("Start: " + DateHelper.getFormattedDate(initialDate));
			
			System.out.println("Deleting... " + to);
			
			Files.walk(Paths.get(to), FileVisitOption.FOLLOW_LINKS)
		    .sorted(Comparator.reverseOrder())
		    .map(Path::toFile)
		    .peek(System.out::println)
		    .forEach(File::delete);
			
			long deleteDate = System.currentTimeMillis();
			
			System.out.println("Files deleted! Elapsed time: " + DateHelper.formatInterval(deleteDate - initialDate));

			Files.walkFileTree(Paths.get(from), new FileCopyVisitor(to, artists, maxNumFiles));

			long finalDate = System.currentTimeMillis();

			System.out.println("Stop! Elapsed time: " + DateHelper.formatInterval(finalDate - initialDate));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
