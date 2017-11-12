package br.com.rcoli.file.copy.helper;

public class StringHelper {

	private StringHelper() {
	}

	public static String removeEspecialCharacters(String value) {
		return value.replaceAll("[^A-Za-z0-9\\/\\\\ \\.]", "").replaceAll("\\ ", "_");
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
