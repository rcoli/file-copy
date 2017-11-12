package br.com.rcoli.file.copy.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileHelper {

	public static final String PATH_SEPARATOR = "\\/";

	private FileHelper() {
	}

	public static String getFileExtension(Path file) {
		return StringHelper.getLastValueFrom(file.getFileName().toString(), "\\.");
	}

	public static String getFileProcessedName(Path file) {
		return StringHelper.removeEspecialCharacters(file.getFileName().toString());
	}

	public static String getProcessedAlbumName(Path file) {
		String albumName = StringHelper.getLastValueFrom(file.getParent().toString(), PATH_SEPARATOR);;
		return StringHelper.removeEspecialCharacters(albumName);
	}

	public static String getProcessedArtistName(Path file) {
		String albumName = StringHelper.getSecondLastValueFrom(file.getParent().toString(), PATH_SEPARATOR);;
		return StringHelper.removeEspecialCharacters(albumName);
	}
	/**
	 * drwx------  3 rcoli rcoli     4096 Jan 22  2015 Sururu Na Roda
	 * drwx------  3 rcoli rcoli     4096 Jul  1 20:22 Afro-Cuban All Stars
	 * 
	 * @param artistsToCopy
	 * @return
	 * @throws IOException
	 */
	public static List<String> getArtistsFromFile(String artistsToCopy) throws IOException {
	
		String artistsFile = FileUtils.readFileToString(new File(artistsToCopy));
	
		List<String> toReturn = new ArrayList<>();
	
		for (String line : artistsFile.split("\\\n")) {
	
			String yearAndName = StringHelper.getLastValueFrom(line, "\\  ");
	
			toReturn.add(StringHelper.getLastValueFrom(yearAndName, "\\d{4}\\ |\\d{2}:\\d{2}\\ ").trim());
		}
		return toReturn;
	}
	

}
