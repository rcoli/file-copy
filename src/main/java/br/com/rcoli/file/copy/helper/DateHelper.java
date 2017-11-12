package br.com.rcoli.file.copy.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateHelper {

	private DateHelper() {
		// TODO Auto-generated constructor stub
	}

	public static String formatInterval(final long l) {
		final long hr = TimeUnit.MILLISECONDS.toHours(l);
		final long min = TimeUnit.MILLISECONDS.toMinutes(l - TimeUnit.HOURS.toMillis(hr));
		final long sec = TimeUnit.MILLISECONDS
				.toSeconds(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
		final long ms = TimeUnit.MILLISECONDS.toMillis(
				l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
		return String.format("%02dhr: %02dm: %02ds .%03dms", hr, min, sec, ms);
	}

	public static String getFormattedDate(long timemillis) {

		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");

		Date resultdate = new Date(timemillis);
		return sdf.format(resultdate);

	}

}
