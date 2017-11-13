package br.com.rcoli.file.copy.helper;

import java.text.Normalizer;

public class StringHelper {

	private StringHelper() {
	}

	public static String normalizeCharacters(String value) {

		String normalized = Normalizer.normalize(value.toLowerCase(), Normalizer.Form.NFD);
		return normalized
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
				.replaceAll("(?!.mp3)[^a-zA-Z0-9\\s]", "")
				.replaceAll("\\p{javaSpaceChar}{1,}","_");
	}

	public static String getLastValueFrom(String toSplit, String regex) {
		return split(toSplit, regex, -1);

	}

	public static String getSecondLastValueFrom(String toSplit, String regex) {
		return split(toSplit, regex, -2);
	}

	private static String split(String toSplit, String regex, int index) {
		String[] splitedValue = toSplit.split(regex);
		return splitedValue[splitedValue.length + index];
	}
}
