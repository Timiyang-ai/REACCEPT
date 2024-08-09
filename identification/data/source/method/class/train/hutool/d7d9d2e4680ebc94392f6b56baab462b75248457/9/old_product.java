public static DateTime parse(String dateStr, String format, Locale locale) {
		return new DateTime(dateStr, new SimpleDateFormat(format, locale));
	}