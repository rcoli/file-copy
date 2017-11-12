package br.com.rcoli.file.copy.visitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.com.rcoli.file.copy.helper.FileHelper;
import br.com.rcoli.file.copy.helper.StringHelper;

public class FileCopyVisitor extends SimpleFileVisitor<Path> {

	private static final String NEW_PATH_FORMAT = "%s/%s/%s_%s";

	private static final List<String> AUDIO_EXTENSIONS = (List<String>) Arrays.asList(new String[] { "mp3" });

	private String copyToDir;
	
	private List<String> artists;

	public FileCopyVisitor(String copyTo, String artistsFile) throws IOException {
		this.copyToDir = copyTo;
		this.artists = FileHelper.getArtistsFromFile(artistsFile);
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

		if (!attrs.isDirectory()) {
			proccess(file);
		}
		return FileVisitResult.CONTINUE;
	}

	private void proccess(Path file) throws IOException {

		if (isAudioFile(file) && isInFilter(file)) {

			String processedArtistName = FileHelper.getProcessedArtistName(file);
			String processedAlbumName = FileHelper.getProcessedAlbumName(file);
			String fileProcessedName = FileHelper.getFileProcessedName(file);

			String newFullFileName = String.format(NEW_PATH_FORMAT, copyToDir, processedArtistName, processedAlbumName,
					fileProcessedName);

			System.out.println(newFullFileName);

			FileUtils.copyFile(file.toFile(), new File(newFullFileName));
		}
	}

	private boolean isAudioFile(Path file) {
		return AUDIO_EXTENSIONS.contains(FileHelper.getFileExtension(file));
	}

	private boolean isInFilter(Path file) throws IOException {
		String artist = StringHelper.getSecondLastValueFrom(file.getParent().toString(), FileHelper.PATH_SEPARATOR);
		return artists.contains(artist);
	}

}
